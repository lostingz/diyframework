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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lostingz<a href="mailto:18710833123@163.com">lostingz</a>
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
    
    public void addRoute(String path, Method method, Class<?> controller) {
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
