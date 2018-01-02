package com.depth.cms.commons.util;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>@Title 模板xml属性转换类 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/4/6 10:10 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Component
public class TemplateXmlAttrConvertUtil {

    private static Logger logger = Logger.getLogger(TemplateXmlAttrConvertUtil.class);

    @Resource
    private XmlUtil xmlUtil;

    public Map<String, String> getElementByRootTemplateIdAndDepth(String templateXmlPath, String rootTemplateId, String depth) {
        String xmlPath = "/template" + File.separator + templateXmlPath;
        String elePath = "/templateMaps/templateMap[@rootTemplateId='" + rootTemplateId + "'][@depth='" + depth + "']";
        Document document = null;
        try {
            document = xmlUtil.getDocument(xmlPath);
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
        Element element = xmlUtil.getElement(document, elePath);
        Map<String, String> map = new HashMap<String, String>();
        List<Attribute> attrs = element.attributes();
        for (Attribute att : attrs) {
            map.put(att.getName(), att.getStringValue());
        }
        return map;
    }

    private Element getElement(String templateXmlPath, String templateId) {
        String xmlPath = "/template" + File.separator + templateXmlPath;
        String elePath = "/templateMaps/templateMap[@id='" + templateId + "']";
        logger.info("xml路径 ：" + xmlPath);
        logger.info("elem路径 ：" + elePath);
        Document document = null;
        try {
            document = xmlUtil.getDocument(xmlPath);
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
        logger.info("xmlUtil");
        logger.info(JSON.toJSONString(xmlUtil));
        Element element = xmlUtil.getElement(document, elePath);
        logger.info("获取元素json : " + JSON.toJSONString(element));
        return element;
    }

    /**
     * 获取元素的属性
     *
     * @param templateXmlPath xml配置文件
     * @param templateId      配置文件中有效配置单元的id
     * @return Map<String,String>
     */
    public Map<String, String> getElementAttrs(String templateXmlPath, String templateId) {
        Map<String, String> map = new HashMap<String, String>();
        Element element = getElement(templateXmlPath, templateId);
        List<Attribute> attrs = element.attributes();
        for (Attribute att : attrs) {
            map.put(att.getName(), att.getStringValue());
        }
        return map;
    }

    /**
     * 获取元素路径下子元素的全部信息
     *
     * @param templateXmlPath xml文件路径
     * @param templateId      元素id
     * @return List<Map<String, String>>
     */
    public List<Map<String, String>> getElementConfig(String templateXmlPath, String templateId) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Element element = getElement(templateXmlPath, templateId);
        if (element != null) {
            List<Element> subElements = element.elements();
            for (Element ele : subElements) {
                List<Attribute> attrs = ele.attributes();
                Map<String, String> map = new HashMap<String, String>();
                for (Attribute att : attrs) {
                    map.put(att.getName(), att.getStringValue());
                }
                if (!map.isEmpty()) {
                    list.add(map);
                }
            }
        }
        return list;
    }

    /**
     * mybatis输出参数转换
     *
     * @param list            列表数据源
     * @param templateXmlPath 模板文件路径,只在resource/template/下查找,不需要带"/"
     * @param templateId      模板映射的TemplateMap的id
     */
    public <T extends Map> void columnNameConvertParameter(List<T> list, String templateXmlPath, String templateId) {
        // 获取被转换的参数列表映射
        Element element = getElement(templateXmlPath, templateId);
        if(null == element){
            logger.error("--------------\n没有找到模板配置信息,\ntemplateXmlPath:"+ templateXmlPath + "\ntemplateId:" + templateId +"\n\n\n");
            return ;
        }
        List<Element> eles = element.elements();
        for (Map map : list) {
            columnNameConvertParameter(map, eles);
        }
    }

    /**
     * mybatis输出参数转换
     *
     * @param map  数据源
     * @param eles 参数转换映射,源自配置文件
     */
    public void columnNameConvertParameter(Map map, List<Element> eles) {
        if (map == null) {
            return;
        }
        for (Element attr : eles) {
            // 数据库映射的列名
            Attribute columnAttr = attr.attribute("column");
            String columnName = columnAttr.getStringValue();

            // 实际的映射列名
            Attribute propertyAttr = attr.attribute("property");
            String propertyName = propertyAttr.getStringValue();

            // 获取键值
            Object value = map.get(columnName);

            // 删除原有的key
            map.remove(columnName);

            // 新增转换一个的列名
            map.put(propertyName, value);
        }
    }

    /**
     * mybatis输出参数转换
     *
     * @param map 数据源
     */
    public void columnNameConvertParameter(Map map, String templateXmlPath, String templateId) {
        // 获取被转换的参数列表映射
        Element element = getElement(templateXmlPath, templateId);
        List<Element> eles = element.elements();
        columnNameConvertParameter(map, eles);
    }

    /**
     * 形参转换
     *
     * @param list
     */
    public void attrNameConvertParameter(List<? extends Map> list, String templateXmlPath, String templateId) {
        // 获取被转换的参数列表映射
        Element element = getElement(templateXmlPath, templateId);
        List<Element> eles = element.elements();
        for (Map map : list) {
            columnNameConvertParameter(map, eles);
        }
    }

    /**
     * 形参转换
     *
     * @param map  数据源
     * @param eles 参数转换映射,源自配置文件
     */
    public void attrConvertParameter(Map map, List<Element> eles) {

        for (Element attr : eles) {
            // 数据库映射的列名
            Attribute columnAttr = attr.attribute("column");
            String columnName = columnAttr.getStringValue();

            // 实际的映射列名
            Attribute propertyAttr = attr.attribute("property");
            String propertyName = propertyAttr.getStringValue();

            // 获取是否必填
            Attribute isNotNullAttr = attr.attribute("isNotNull");
            String isNotNullName = isNotNullAttr.getStringValue();
            if ("true".equals(isNotNullName) && !map.containsKey(propertyName)) {
                throw new NullPointerException(propertyName);
            }

            // 获取键值
            Object value = map.get(propertyName);

            // 删除原有的key
            map.remove(propertyName);

            // 新增转换一个的列名
            map.put(columnName, value);
        }
    }

    /**
     * 形参转换
     * <p>@version
     *
     * @param map 数据源
     */
    public void attrConvertParameter(Map map, String templateXmlPath, String templateId) {
        // 获取被转换的参数列表映射
        Element element = getElement(templateXmlPath, templateId);
        List<Element> eles = element.elements();
        attrConvertParameter(map, eles);
    }

}
