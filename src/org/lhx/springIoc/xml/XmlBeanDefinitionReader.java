package org.lhx.springIoc.xml;

import org.lhx.springIoc.AbstractBeanDefinitionReader;
import org.lhx.springIoc.resource.XmlResourceLoader;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @author lhx
 * @date 2019/5/1 - 14:52
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    /**
     * 通过构造创建一个map容器
     *
     * @param xmlResourceLoader 资源加载器
     */
    public XmlBeanDefinitionReader(XmlResourceLoader xmlResourceLoader) {
        super(xmlResourceLoader);
    }

    public void readXml(String location) throws Exception{
        //创建资源加载器
        XmlResourceLoader xmlResourceLoader = new XmlResourceLoader();
        //获取输入流
        InputStream inputStream = xmlResourceLoader.getXmlResource(location).getXmlResource();
        //创建DocumentBuilderFactory实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //创建DocumentBuilder
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        //创建文档对象
        Document document = documentBuilder.parse(inputStream);
        //根据xml将bean注册到容器中
        registerBeanDefinitions(document);
        inputStream.close();
    }

    private void registerBeanDefinitions(Document document) {
    }
}
