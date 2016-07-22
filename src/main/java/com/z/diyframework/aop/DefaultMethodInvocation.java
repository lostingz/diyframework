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

import java.lang.reflect.Method;
import java.util.List;

import com.z.diyframework.aop.annotation.AfterAdvice;
import com.z.diyframework.aop.annotation.BeforeAdvice;
import com.z.diyframework.aop.intercept.MethodInvocation;

/**
 * @author lostingz<a href="mailto:18710833123@163.com">lostingz</a>
 * @version $Id$
 */
public class DefaultMethodInvocation implements MethodInvocation {

    private DefaultProxy proxy;
    private List<AbstractMethodInterceptor> interceptorList;
    private Method method;
    private Object target;
    private Object[] args;
    private int index;
    private boolean executed = false;

    public DefaultMethodInvocation(DefaultProxy proxy, List<AbstractMethodInterceptor> interceptorList, Method method,
            Object target, Object[] args) {
        this.proxy = proxy;
        this.interceptorList = interceptorList;
        this.method = method;
        this.target = target;
        this.args = args;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Object proceed() {
        AbstractMethodInterceptor interceptor = null;
        Object result = null;
        if (interceptorList.size() > 0 && index < interceptorList.size()) {
            interceptor = interceptorList.get(index++);
            if (new AdviceMatcher(interceptor, this).match(BeforeAdvice.class, "beforeAdvice")) {
                interceptor.beforeAdvice(); // 执行前置建议
            }
            proceed(); // 执行下一个拦截器
        }
        // 执行真正的方法调用
        if (!executed) {
            executed = true;
            try {
                result = method.invoke(target, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (index > 0) {
            interceptor = interceptorList.get(--index);
            if (new AdviceMatcher(interceptor, this).match(AfterAdvice.class, "afterAdvice")) {
                interceptor.afterAdvice(); // 执行后置建议
            }
        }
        return result;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    public DefaultProxy getProxy() {
        return proxy;
    }

    public Object getTarget() {
        return target;
    }

    public Object[] getArgs() {
        return args;
    }
}
