/**
 * lostingz
 * Created on 2016年7月22日
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.z.diyframework.route;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.z.diyframework.kit.PathKit;

/**
 * @author lostingz<a href="mailto:18710833123@163.com">lostingz</a>
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
