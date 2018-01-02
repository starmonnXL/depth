package com.depth.cms.commons.util;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>@Title Xml工具类 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/4/6 8:57 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Component
public class XmlUtil {

    private static Logger logger = Logger.getLogger(XmlUtil.class);

    /**
     * <p>根据配置文件获取document
     *
     * <p>@param path 路径
     * <p>@throws DocumentException
     */
    public Document getDocument(String  path) throws DocumentException {
        logger.info("根据配置文件获取document,start...");
        logger.info(path);
        Document document = new SAXReader().read(this.getClass().getResourceAsStream(path));
        logger.info(document);
        logger.info("根据配置文件获取document, end ...");
        return document;
    }



    /**
     * <p>根据xpath获取配置文件下的元素节点
     * <p>例: 获取到myxml下面的template节点为id为test的节点
     * <p>Element element = (Element) document.selectSingleNode("/myXml/template[@id='test']");
     *
     * <p>@return Element
     */
    public Element getElement(Document document,String xpathExpression) {

        logger.info("根据xpath获取配置文件下的元素节点, start ... ");
        logger.info(xpathExpression);
        logger.info(document);
        Element element = (Element) document.selectSingleNode(xpathExpression);
        logger.info(element);
        logger.info("根据xpath获取配置文件下的元素节点,  end  ... ");
        return element;
    }

    /**
     * <p>根据xpath获取文件下的元素节点
     *
     * <p>@param xpathExpression xpath
     * <p>@return List<Element>
     */
    public List<Element> getElements(Document document,String xpathExpression) {
        List<Element> elements = document.selectNodes(xpathExpression);
        return elements;
    }

    /**
     * <p>获取元素中的属性值
     *
     * <p>@param element
     * <p>@param attrName
     * <p>@return
     */
    public String getAttr(Element element, String attrName) {
        //解析xml，并获取到节点里的值。
        return element.attributeValue(attrName);
    }

}
