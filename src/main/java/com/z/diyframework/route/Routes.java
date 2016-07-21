/**
 * Chsi
 * Created on 2016年7月18日
 */
package com.z.diyframework.route;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class Routes {
    private List<Route> routeList = new ArrayList<Route>();

    public void addRoute(Route route) {
        routeList.add(route);
    }

    public void removeRoute(Route route) {
        routeList.remove(route);
    }
    
    public void addRoute(String path, Method method, Object controller) {
        Route r = new Route();
        r.setController(controller);
        r.setMethod(method);
        r.setPath(path);
        routeList.add(r);
    }

    public void addRoutes(List<Route> list) {
        routeList.addAll(list);
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }
}
