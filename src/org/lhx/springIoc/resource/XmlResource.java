package org.lhx.springIoc.resource;

import java.io.InputStream;

/**
 * @author lhx
 * @date 2019/5/1 - 13:41
 */
public interface XmlResource {

    /**
     * 获取输入流
     * @return xml文件的输入流
     * @throws Exception
     */
    InputStream getXmlResource() throws Exception;

}
