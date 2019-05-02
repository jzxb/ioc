package org.lhx.springIoc.bean;

import org.lhx.springIoc.BeanDefinition;

/**
 * @author lhx
 * @date 2019/5/2 - 15:21
 */
public interface BeanFactory {
    /**
     * 根据bean的名称从容器中获取bean对象
     *
     * @param name bean 名称
     * @return bean实例
     * @throws Exception 异常
     */
    Object getBean(String name) throws Exception;

    /**
     * 将bean注册到容器中
     *
     * @param name bean 名称
     * @param bean bean实例
     * @throws Exception 异常
     */
    void registerBeanDefinition(String name, BeanDefinition bean) throws Exception;
}
