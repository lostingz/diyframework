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
package com.test;

import com.z.diyframework.aop.AbstractMethodInterceptor;
import com.z.diyframework.aop.AopProxy;
import com.z.diyframework.aop.annotation.AfterAdvice;
import com.z.diyframework.aop.annotation.BeforeAdvice;

/**
 * @author lostingz<a href="mailto:18710833123@163.com">lostingz</a>
 * @version $Id$
 */
public class AopTest {
    public static void main(String[] args) {
        AopProxy.addInterceptor(new Interceptor1());
        AopProxy.addInterceptor(new Interceptor2());
        SomeCls some = AopProxy.create(SomeCls.class);
        some.save();
        some.get();
    }
}

class Interceptor1 extends AbstractMethodInterceptor {

    @Override
    @BeforeAdvice
    public void beforeAdvice() {
        System.out.println("before");
    }

    @Override
    @AfterAdvice
    public void afterAdvice() {
        System.out.println("after");
    }
}

class Interceptor2 extends AbstractMethodInterceptor {

    @Override
    @BeforeAdvice(expression = "@javax.annotation.Resource")
    public void beforeAdvice() {
        System.out.println("before Resource");
    }

    @Override
    @AfterAdvice(expression = "@javax.annotation.Resource")
    public void afterAdvice() {
        System.out.println("after Resource");
    }

}

