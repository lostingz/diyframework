/**
 * Chsi
 * Created on 2016年7月21日
 */
package com.z.diyframework.wrapper;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class Response {
    private HttpServletResponse response;

    public Response(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

}
