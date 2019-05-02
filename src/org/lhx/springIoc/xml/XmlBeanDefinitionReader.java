package org.lhx.springIoc.xml;

import org.lhx.springIoc.AbstractBeanDefinitionReader;
import org.lhx.springIoc.BeanDefinition;
import org.lhx.springIoc.BeanReference;
import org.lhx.springIoc.PropertyValue;
import org.lhx.springIoc.resource.XmlResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

    /**
     * 根据xml文件将bean注册到容器中
     * @param document 文档对象
     */
    private void registerBeanDefinitions(Document document) {
        //读取文档的根元素
        Element element =document.getDocumentElement();
        //解析元素的根节点及根节点下的所有子节点并添加进注册容器
        parseBeanDefinitions(element);
    }

    /**
     * 解析元素的根节点及根节点下的所有子节点并添加进注册容器
     * @param element xml文档根节点
     */
    private void parseBeanDefinitions(Element element) {
        //读取根元素的所有子元素
        NodeList nodeList = element.getChildNodes();
        //遍历子元素
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node =nodeList.item(i);
            if (node instanceof Element){
                Element element1 = (Element) node;
                // 解析给给定的节点，包括name，class，property， name， value，ref
                processBeanDefinition(element1);
            }
        }
    }

    /**
     * 解析给给定的节点，包括name，class，property， name， value，ref
     * @param element xml解析元素
     */
    private void processBeanDefinition(Element element) {
        String id = element.getAttribute("id");
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        // 设置bean 定义对象的 全限定类名
        beanDefinition.setClassName(className);
        // 向 bean 注入配置文件中的成员变量
        addPropertyValues(element, beanDefinition);
        // 向注册容器 添加bean名称和bean定义
        getRegistry().put(id, beanDefinition);
    }

    /**
     * 向bean实例添加配置文件中的属性元素
     * @param element 元素
     * @param beanDefinition bean对象
     */
    private void addPropertyValues(Element element, BeanDefinition beanDefinition) {
        // 获取给定元素的 property 属性集合
        NodeList propertyNode = element.getElementsByTagName("property");
        // 循环集合
        for (int i = 0; i < propertyNode.getLength(); i++) {
            // 获取集合中某个给定位置的节点
            Node node = propertyNode.item(i);
            // 类型判断
            if (node instanceof Element) {
                // 将节点向下强转为子元素
                Element propertyEle = (Element) node;
                // 元素对象获取 name 属性
                String name = propertyEle.getAttribute("name");
                // 元素对象获取 value 属性值
                String value = propertyEle.getAttribute("value");
                // 判断value不为空
                if (value != null && value.length() > 0) {
                    // 向给定的 “bean定义” 实例中添加该成员变量
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    // 如果为空，则获取属性ref
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        // 如果属性ref为空，则抛出异常
                        throw new IllegalArgumentException(
                                "Configuration problem: <property> element for property '"
                                        + name + "' must specify a ref or value");
                    }
                    // 如果不为空，测创建一个 “bean的引用” 实例，构造参数为名称，实例暂时为空
                    BeanReference beanRef = new BeanReference(name);
                    // 向给定的 “bean定义” 中添加成员变量
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanRef));
                }
            }
        }
    }
}
