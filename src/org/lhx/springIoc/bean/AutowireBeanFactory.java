package org.lhx.springIoc.bean;

import org.lhx.springIoc.BeanDefinition;
import org.lhx.springIoc.BeanReference;
import org.lhx.springIoc.PropertyValue;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author lhx
 * @date 2019/5/2 - 15:23
 */
public class AutowireBeanFactory implements BeanFactory {
    /**
     * 容器
     */
    private HashMap<String, BeanDefinition> map = new HashMap<>();

    /**
     * 根据bean的名称获取bean， 如果没有，则抛出异常 如果有， 则从bean定义对象获取bean实例
     */
    @Override
    public Object getBean(String id) throws Exception {
        BeanDefinition beandefinition = map.get(id);
        if (beandefinition == null) {
            throw new IllegalArgumentException("No bean named " + id + " is defined");
        }
        Object bean = beandefinition.getBean();
        if (bean == null) {
            bean = doCreate(beandefinition);
        }
        return bean;
    }

    /**
     * 注册 bean定义 的抽象方法实现，这是一个模板方法， 调用子类方法doCreate，
     */
    @Override
    public void registerBeanDefinition(String name, BeanDefinition beandefinition) throws Exception {
        Object bean = doCreate(beandefinition);
        beandefinition.setBean(bean);
        map.put(name, beandefinition);
    }

    /**
     * 减少一个bean
     */
    /**
     * 根据bean 定义创建实例， 并将实例作为key， bean定义作为value存放，并调用 addPropertyValue 方法 为给定的bean的属性进行注入
     */
    protected Object doCreate(BeanDefinition beandefinition) throws Exception {
        Object bean = beandefinition.getBeanClass().newInstance();
        addPropertyValue(bean, beandefinition);
        return bean;
    }

    /**
     * 给定一个bean定义和一个bean实例，为给定的bean中的属性注入实例。
     */
    protected void addPropertyValue(Object bean, BeanDefinition beandefinition) throws Exception {
        // 循环给定 bean 的属性集合
        for (PropertyValue pv : beandefinition.getPropertyValues().getPropertyValueList()) {
            // 根据给定属性名称获取 给定的bean中的属性对象
            Field declaredField = bean.getClass().getDeclaredField(pv.getName());
            // 设置属性的访问权限
            declaredField.setAccessible(true);
            // 获取定义的属性中的对象
            Object value = pv.getValue();
            // 判断这个对象是否是 BeanReference 对象
            if (value instanceof BeanReference) {
                // 将属性对象转为 BeanReference 对象
                BeanReference beanReference = (BeanReference) value;
                // 调用父类的 AbstractBeanFactory 的 getBean 方法，根据bean引用的名称获取实例，此处即是递归
                value = getBean(beanReference.getName());
            }
            // 反射注入bean的属性
            declaredField.set(bean, value);
        }
    }
}
