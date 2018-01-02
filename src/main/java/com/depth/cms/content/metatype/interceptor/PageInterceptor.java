package com.depth.cms.content.metatype.interceptor;

import com.depth.cms.commons.page.Page;
import com.depth.cms.content.metatype.util.ReflectHelper;
import com.depth.cms.content.metatype.util.Tools;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;

import javax.xml.bind.PropertyException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 13:48 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class}
)})
public class PageInterceptor implements Interceptor {
    private static Logger logger = Logger.getLogger(PageInterceptor.class);
    private static String dialect = "";
    private static String pageSqlId = "";

    public PageInterceptor() {
    }

    public Object intercept(Invocation ivk) throws Throwable {
        if(ivk.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler)ivk.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement)ReflectHelper.getValueByFieldName(delegate, "mappedStatement");
            if(mappedStatement.getId().matches(pageSqlId)) {
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();
                if(parameterObject == null) {
                    throw new NullPointerException("parameterObject尚未实例化！");
                }

                Connection connection = (Connection)ivk.getArgs()[0];
                String sql = boundSql.getSql();
                String countSql = (new MybatisHepler()).getCountSql(sql);
                PreparedStatement countStmt = connection.prepareStatement(countSql);
                BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
                this.setParameters(countStmt, mappedStatement, countBS, parameterObject);
                ResultSet rs = countStmt.executeQuery();
                int count = 0;
                if(rs.next()) {
                    count = rs.getInt(1);
                }

                rs.close();
                countStmt.close();
                Page page = null;
                if(parameterObject instanceof Page) {
                    page = (Page)parameterObject;
                    page.setEntityOrField(true);
                    page.setTotalResult(count);
                } else {
                    Field pageSql = ReflectHelper.getFieldByFieldName(parameterObject, "page");
                    if(pageSql == null) {
                        throw new NoSuchFieldException(parameterObject.getClass().getName() + "不存在 page 属性！");
                    }

                    page = (Page)ReflectHelper.getValueByFieldName(parameterObject, "page");
                    if(page == null) {
                        page = new Page();
                    }

                    page.setEntityOrField(false);
                    page.setTotalResult(count);
                    ReflectHelper.setValueByFieldName(parameterObject, "page", page);
                }

                String pageSql1 = this.generatePageSql(sql, page);
                ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql1);
            }
        }

        return ivk.proceed();
    }

    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List parameterMappings = boundSql.getParameterMappings();
        if(parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null?null:configuration.newMetaObject(parameterObject);

            for(int i = 0; i < parameterMappings.size(); ++i) {
                ParameterMapping parameterMapping = (ParameterMapping)parameterMappings.get(i);
                if(parameterMapping.getMode() != ParameterMode.OUT) {
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    Object value;
                    if(parameterObject == null) {
                        value = null;
                    } else if(typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if(boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if(propertyName.startsWith("__frch_") && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if(value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null?null:metaObject.getValue(propertyName);
                    }

                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if(typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
                    }

                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }

    }

    private String generatePageSql(String sql, Page page) {
        if(page != null && Tools.notEmpty(dialect)) {
            StringBuffer pageSql = new StringBuffer();
            if("mysql".equals(dialect)) {
                pageSql.append(sql);
                pageSql.append(" limit " + page.getCurrentResult() + "," + page.getShowCount());
            }

            return pageSql.toString();
        } else {
            return sql;
        }
    }

    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    public void setProperties(Properties p) {
        dialect = p.getProperty("dialect");
        if(Tools.isEmpty(dialect)) {
            try {
                throw new PropertyException("dialect property is not found!");
            } catch (PropertyException var4) {
                var4.printStackTrace();
            }
        }

        pageSqlId = p.getProperty("pageSqlId");
        if(Tools.isEmpty(pageSqlId)) {
            try {
                throw new PropertyException("pageSqlId property is not found!");
            } catch (PropertyException var3) {
                var3.printStackTrace();
            }
        }

    }
}
