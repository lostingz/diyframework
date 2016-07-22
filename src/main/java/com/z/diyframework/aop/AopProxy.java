/**
 * lostingz
 * Created on 2016年7月22日
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.z.diyframework.aop;

import net.sf.cglib.core.ReflectUtils;



/**
 * @author lostingz<a href="mailto:18710833123@163.com">lostingz</a>
 * @version $Id$
 */
public class AopProxy {
    private static DefaultProxy defaultProxy = new DefaultProxy();

    private AopProxy() {
    }

    /**
     * 根据Class创建一个代理
     * 
     * @param clazz
     *            Class对象
     * @return 返回代理对象
     */
    public static <T> T create(Class<?> clazz) {
        Object target = ReflectUtils.newInstance(clazz);
        Object proxy = defaultProxy.getProxy(target);
        return (T) proxy;
    }

    /**
     * 根据Class创建一个代理
     * 
     * @param clazz
     *            Class对象
     * @return 返回代理对象
     */
    public static Object createProxy(Class<?> clazz) {
        Object target = ReflectUtils.newInstance(clazz);
        Object proxy = defaultProxy.getProxy(target);
        return proxy;
    }

    /**
     * 创建一个代理对象
     * 
     * @param target
     *            原始java对象
     * @return 返回代理对象
     */
    public static <T> T create(Object target) {
        Object proxy = defaultProxy.getProxy(target);
        return (T) proxy;
    }

    /**
     * 创建一个代理对象
     * 
     * @param target
     *            原始java对象
     * @return 返回代理对象
     */
    public static Object createProxy(Object target) {
        Object proxy = defaultProxy.getProxy(target);
        return proxy;
    }

    public static void addInterceptor(AbstractMethodInterceptor abstractMethodInterceptor) {
        defaultProxy.addInterceptor(abstractMethodInterceptor);
    }
}
