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

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.z.diyframework.ioc.annotation.Autowired;

/**
 * @author lostingz<a href="mailto:18710833123@163.com">lostingz</a>
 * @version $Id$
 */
public class SimpleIocContainer implements IContainer {

    private Map<String, Object> beans;

    private Map<String, String> beankeys;

    public SimpleIocContainer() {
        this.beans = new ConcurrentHashMap<String, Object>();
        this.beankeys = new ConcurrentHashMap<String, String>();
    }

    public void initWrited() {
        Iterator<Entry<String, Object>> it = beans.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            Object o = entry.getValue();
            injectObject(o);
        }
    }

    public void injectObject(Object object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String name = autowired.name();
                    Class cls = autowired.value();
                    Object autoWritedField = null;
                    if (!name.equals("")) {
                        String className = beankeys.get(name);
                        if (null != className && !className.equals("")) {
                            autoWritedField = beans.get(className);
                        }
                        if (null == autoWritedField) {
                            throw new RuntimeException("Unable to load " + name);
                        }
                    } else {
                        if (autowired.value() == Class.class) {
                            autoWritedField = registerBean(field.getType());
                        } else {
                            autoWritedField = this.getBean(autowired.value());
                            if (null == autoWritedField) {
                                autoWritedField = registerBean(autowired.value());
                            }
                        }
                    }
                    if (null == autoWritedField) {
                        throw new RuntimeException("Unable to load " + field.getType().getCanonicalName());
                    }
                    boolean accessible = field.isAccessible();
                    field.setAccessible(true);
                    field.set(object, autoWritedField);
                    field.setAccessible(accessible);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T getBean(Class<T> cls) {
        String name = cls.getName();
        Object bean = beans.get(name);
        if (null != bean) {
            return (T) bean;
        }
        return null;
    }

    @Override
    public <T> T getBeanByName(String name) {
        String className = beankeys.get(name);
        Object obj = beans.get(className);
        if (null != obj) {
            return (T) obj;
        }
        return null;
    }

    @Override
    public Object registerBean(Class<?> cls) throws Exception {
        String name = cls.getName();
        beankeys.put(name, name);
        Object bean = cls.newInstance();
        beans.put(name, bean);
        return bean;
    }

    @Override
    public Object registerBean(Object bean) {
        String name = bean.getClass().getName();
        beankeys.put(name, name);
        beans.put(name, bean);
        return bean;
    }

    @Override
    public Object registerBean(String name, Object bean) {
        String className = bean.getClass().getName();
        beankeys.put(name, className);
        beans.put(className, bean);
        return bean;
    }

    @Override
    public void remove(Class<?> clazz) {
        String className = clazz.getName();
        if (null != className && !className.equals("")) {
            beankeys.remove(className);
            beans.remove(className);
        }
    }

    @Override
    public void removeByName(String name) {
        String className = beankeys.get(name);
        if (null != className && !className.equals("")) {
            beankeys.remove(name);
            beans.remove(className);
        }
    }

    @Override
    public Set<String> getBeanNames() {
        return beankeys.keySet();
    }
}
