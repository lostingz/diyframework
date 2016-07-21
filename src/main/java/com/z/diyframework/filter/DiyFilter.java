/**
 * Chsi
 * Created on 2016年7月18日
 */
package com.z.diyframework.filter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.z.diyframework.Master;
import com.z.diyframework.boot.Bootstrap;
import com.z.diyframework.config.Const;
import com.z.diyframework.kit.PathKit;
import com.z.diyframework.route.Route;
import com.z.diyframework.route.RouteMatcher;
import com.z.diyframework.route.Routes;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class DiyFilter implements Filter {

    private ServletContext servletContext;
    private static final Logger log = Logger.getLogger(DiyFilter.class.getName());
    private RouteMatcher routeMatcher = new RouteMatcher(new ArrayList<Route>());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Master master = Master.me();
        if (!master.isInit()) {
            String bootClassName = filterConfig.getInitParameter("bootClassName");
            Bootstrap bootstrap = getBootstrap(bootClassName);
            bootstrap.init(master);
            Routes routes = master.getRoutes();
            if (null != routes) {
                routeMatcher.setRoutes(routes.getRouteList());
            }
            servletContext = filterConfig.getServletContext();
        }
        master.setInit(true);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding(Const.DEFAULT_CHARSER);
        response.setCharacterEncoding(Const.DEFAULT_CHARSER);
        String url = PathKit.getRelativePath(request);
        log.info("Request URI：" + url);
        Route route = routeMatcher.findRoute(url);
        if (route != null) {
            handle(request, response, route);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, Route route) {
        Object controller = route.getController();
        Method method = route.getMethod();
        log.info("Controller:" + controller.getClass().getCanonicalName() + "  Method:" + method.getName());
        try {
            method.invoke(controller, request, response);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        log.info("destroy app");
    }

    private Bootstrap getBootstrap(String className) {
        if (null != className) {
            try {
                Class<?> cls = Class.forName(className);
                Bootstrap bootstrap = (Bootstrap) cls.newInstance();
                return bootstrap;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("init bootstrap class error");
    }
}
