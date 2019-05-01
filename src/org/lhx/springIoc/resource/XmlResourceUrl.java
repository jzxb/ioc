package org.lhx.springIoc.resource;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author lhx
 * @date 2019/5/1 - 13:42
 */
public class XmlResourceUrl implements XmlResource{

    private URL url;

    public XmlResourceUrl(URL url) {
        this.url = url;
    }

    /**
     * 从url中获取输入流
     * @return xml输入流
     * @throws Exception
     */
    @Override
    public InputStream getXmlResource() throws Exception {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
