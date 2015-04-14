/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ifeng.sorter.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Apr 14, 2015
 */
public class LogBeanTest {

	private static final Logger log = LoggerFactory.getLogger(LogBeanTest.class);
	
	@Test
	public void equalsTest() {
		List<LogBean> data = buildData();
		Set<LogBean> set = new HashSet<LogBean>();
		for(LogBean bean : data){
			set.add(bean);
		}
		Iterator<LogBean> iterator = set.iterator();
		
		while(iterator.hasNext()){
			LogBean o = iterator.next();
			log.info(o.toString());
		}
		
	}
	
	@Test
	public void sort() {
		List<LogBean> data = buildData();
		for(LogBean o : data){
			log.info(o.toString());
		}
		
		Collections.sort(data);;
		
		log.info("Sorted. ... ......");
		
		for(LogBean o : data){
			log.info(o.toString());
		}
		
		Collections.sort(data);;
		
		log.info("Sorted. ... ......");
		
		for(LogBean o : data){
			log.info(o.toString());
		}
	}
	
	@Test
	public void testMap() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("00", 10);
		map.put("s22", 22);
		map.put("33", 33);
		map.put("11s", 11);
		map.put("55", 55);
		map.put("44", 44);
		
		Iterator<String> keys = map.keySet().iterator();
		while(keys.hasNext()){
			String key = keys.next();
			
			log.info("Kye: {}, Value: {}", key, map.get(key));
		}
		
	}
	
	private List<LogBean>  buildData() {
		List<LogBean> list = new ArrayList<LogBean> ();
		
		LogBean l1 = new LogBean("12,12,12,12", 120, "FF1");
		LogBean l2 = new LogBean("13,13,13,13", 120, "FF3");
		LogBean l3 = new LogBean("14,14,14,14", 120, "FF4");
		LogBean l4 = new LogBean("12,12,12,12", 120, "FF1");
		LogBean l5 = new LogBean("13,13,13,13", 180, "FF5");
		
		LogBean l6 = new LogBean("15,15,15,15", 480, "FF6");
		LogBean l7 = new LogBean("14,14,14,14", 80, "FF7");
		
		list.add(l1);
		list.add(l2);
		list.add(l3);
		list.add(l4);
		list.add(l5);
		list.add(l6);
		list.add(l7);
		
		return list;
	}
}
