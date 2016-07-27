/**
 * lostingz
 * Created on 2016年7月25日
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
package com.test.ioc;

import com.z.diyframework.ioc.container.IContainer;
import com.z.diyframework.ioc.container.SimpleIocContainer;

/**
 * @author lostingz<a href="mailto:18710833123@163.com">lostingz</a>
 * @version $Id$
 */
public class IOCTest {
    private static IContainer ioc = new SimpleIocContainer();

    public static void main(String[] args) {
        try {
            ioc.registerBean(Person.class);
            ioc.initWrited();

            Person worker = ioc.getBean(Person.class);
            worker.work();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
