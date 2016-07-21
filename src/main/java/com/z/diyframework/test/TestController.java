/**
 * Chsi
 * Created on 2016年7月21日
 */
package com.z.diyframework.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class TestController {
    public void index(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("index");
        System.out.println(request.getParameter("name"));
    }

    public void hello(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("name"));
    }
}