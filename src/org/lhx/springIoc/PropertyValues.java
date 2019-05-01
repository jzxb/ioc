package org.lhx.springIoc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhx
 * @date 2019/5/1 - 14:15
 */
public class PropertyValues {

    /**
     * 属性的集合
     */
    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public PropertyValues() {
    }

    /**
     * 向集合添加属性
     * @param propertyValue 要添加的属性
     */
    public void addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }

    /**
     * 获取属性集合
     * @return 属性集合
     */
    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
