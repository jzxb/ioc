package org.lhx.springIoc;

/**
 * @author lhx
 * @date 2019/5/1 - 14:37
 */
public class PropertyValue {

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性内容
     */
    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 获取属性名称
     * @return 属性名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取属性内容
     * @return 属性值
     */
    public Object getValue() {
        return value;
    }
}
