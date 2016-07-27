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
package com.z.diyframework.ioc.container;

import java.util.Set;

/**
 * @author lostingz<a href="mailto:18710833123@163.com">lostingz</a>
 * @version $Id$
 */
public interface IContainer {
    public <T> T getBean(Class<T> cls);

    public <T> T getBeanByName(String name);

    public Object registerBean(Class<?> cls) throws Exception;

    public Object registerBean(Object bean);

    public Object registerBean(String name, Object bean);

    public void remove(Class<?> cls);

    public void removeByName(String name);

    public void initWrited();

    Set<String> getBeanNames();
}