package org.lhx.springIoc;

import org.lhx.springIoc.bean.AutowireBeanFactory;
import org.lhx.springIoc.bean.BeanFactory;
import org.lhx.springIoc.resource.XmlResourceLoader;
import org.lhx.springIoc.service.IStudentService;
import org.lhx.springIoc.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author lhx
 * @date 2019/5/2 - 15:25
 */
public class ClassPathXmlApplicationContext {

    XmlBeanDefinitionReader xmlBeanDefinitionReader;

    BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String location) throws Exception {
        this.xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new XmlResourceLoader());
        // 解析该文件
        xmlBeanDefinitionReader.readXml(location);
        this.beanFactory = new AutowireBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            // 将XML容器中的bean注册到bean工厂
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }

    public Object getBean(String studentService) throws Exception {
        return beanFactory.getBean(studentService);
    }
}
