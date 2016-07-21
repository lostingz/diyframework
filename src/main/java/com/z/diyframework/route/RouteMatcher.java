/**
 * Chsi
 * Created on 2016年7月18日
 */
package com.z.diyframework.route;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.z.diyframework.kit.PathKit;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class RouteMatcher {

    private List<Route> routes;

    public RouteMatcher() {
        super();
    }

    public RouteMatcher(List<Route> routes) {
        this.routes = routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Route findRoute(String url) {
        String path = parsePath(url);
        for (Route route : routes) {
            if (matchesPath(route.getPath(), path)) {
                return route;
            }
        }
        return null;
    }

    private boolean matchesPath(String routePath, String pathToMatch) {
        return routePath.equals(pathToMatch);
    }

    private String parsePath(String path) {
        path = PathKit.fixPath(path);
        try {
            URI uri = new URI(path);
            return uri.getPath();
        } catch (URISyntaxException e) {
            return null;
        }
    }

}
