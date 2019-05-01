package org.lhx.springIoc;

/**
 * @author lhx
 * @date 2019/5/1 - 13:33
 */
public class BeanDefinition {

    /**
     * bean
     */
    private Object bean;

    /**
     * bean的class
     */
    private Class beanClass;

    /**
     * bean的class的全类名
     */
    private String className;

    /**
     * 类的属性集合
     */
    private PropertyValues propertyValues = new PropertyValues();

    /**
     * 获取bean
     * @return bean
     */
    public Object getBean() {
        return bean;
    }

    /**
     * 设置bean对象
     * @param bean 传入的bean
     */
    public void setBean(Object bean) {
        this.bean = bean;
    }

    /**
     * 获取bean的class
     * @return bean的class
     */
    public Class getBeanClass() {
        return beanClass;
    }

    /**
     * 设置类名称反射生成class对象
     * @param className 全类名
     */
    public void setClassName(String className) {
        this.className = className;
        try {
            this.beanClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取bean属性集合
     * @return bean属性集合
     */
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    /**
     * 设置bean属性集合
     * @param propertyValues 属性集合
     */
    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
