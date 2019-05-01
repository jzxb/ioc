package org.lhx.springIoc.resource;

import java.net.URL;

/**
 * @author lhx
 * @date 2019/5/1 - 13:35
 */
public class XmlResourceLoader {

    /**
     *通过路径加载资源获取输入流
     * @param location 文件路径
     * @return
     */
    public XmlResourceUrl getXmlResource(String location) {
        URL url = this.getClass().getClassLoader().getResource(location);
        return new XmlResourceUrl(url);
    }
}
