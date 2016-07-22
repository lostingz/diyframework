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
package com.z.diyframework;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.z.diyframework.route.Route;
import com.z.diyframework.route.Routes;

/**
 * @author lostingz<a href="mailto:18710833123@163.com">lostingz</a>
 * @version $Id$
 */
public final class Master {
    private Routes routes;
    private boolean isInit = false;
    
    private static class MasterHolder {
        private static Master me = new Master();
    }

    private Master() {
        routes = new Routes();
    }

    public static Master me() {
        return MasterHolder.me;
    }

    public void addRoutes(Routes routes){
        this.routes.addRoutes(routes.getRouteList());
    }

    public void addRoute(Route route) {
        this.routes.addRoute(route);
    }

    public Master addRoute(String path, String methodName, Class<?> controller) {
        try {
            Method method = controller.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            this.routes.addRoute(path, method, controller);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Routes getRoutes() {
        return routes;
    }

    public void setRoutes(Routes routes) {
        this.routes = routes;
    }

    public boolean isInit() {
        return isInit;
    }

    public void setInit(boolean isInit) {
        this.isInit = isInit;
    }
}
