/**
 * Chsi
 * Created on 2016年7月21日
 */
package com.test;

import com.z.diyframework.Master;
import com.z.diyframework.boot.Bootstrap;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class MainTest implements Bootstrap {

    @Override
    public void init(Master master) {
        master.addRoute("/dd", "index", TestController.class);
    }
}
