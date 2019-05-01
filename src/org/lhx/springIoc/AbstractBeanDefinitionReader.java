package org.lhx.springIoc;

import org.lhx.springIoc.resource.XmlResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhx
 * @date 2019/5/1 - 13:32
 */
public class AbstractBeanDefinitionReader {

    /**
     * bean容器
     */
    private Map<String,BeanDefinition> registry;

    /**
     *资源加载器
     */
    private XmlResourceLoader xmlResourceLoader;

    /**
     * 通过构造创建一个map容器
     * @param xmlResourceLoader 资源加载器
     */
    public AbstractBeanDefinitionReader(XmlResourceLoader xmlResourceLoader) {
        this.registry = new HashMap<>();
        this.xmlResourceLoader = xmlResourceLoader;
    }

    /**
     * 获取容器
     * @return map容器
     */
    public Map<String,BeanDefinition> getRegistry(){
        return registry;
    }

    /**
     * 获取资源加载器
     * @return 资源加载器
     */
    public XmlResourceLoader getXmlResourceLoader(){
        return xmlResourceLoader;
    }
}
