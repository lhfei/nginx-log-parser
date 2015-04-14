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
package com.ifeng.sorter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ifeng.sorter.bean.LogBean;


/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Apr 14, 2015
 */
public class NginxApp {
	
	private static final Logger log = LoggerFactory.getLogger(NginxApp.class);

	public static void main(String[] args) {
		
		String input = "src/test/resources/data/nginx_report.txt";
		
		PrintWriter pw = null;
		
		Map<String, List<LogBean>> resultMap = new HashMap<String, List<LogBean>>();
		List<String> ips = new ArrayList<String>();
		
		try {
			List<String> lines = FileUtils.readLines(new File(input));
			List<LogBean> items = new ArrayList<LogBean>();
			
			for(String line : lines){
				String[] values = line.split("\t");
				
				if(values != null && values.length == 3){// ip total seria
					try {
						String ip = values[0].trim();
						String total = values[1].trim();
						String seria = values[2].trim();
						
						LogBean bean = new LogBean(ip, Integer.parseInt(total), seria);
						
						items.add(bean);
						
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
			
			Collections.sort(items);
			
			for(LogBean bean : items){
				String key = bean.getIp();
				
				if(resultMap.containsKey(key)){
					resultMap.get(key).add(bean);
				}else{
					List<LogBean> keyList = new ArrayList<LogBean>();
					keyList.add(bean);
					resultMap.put(key, keyList);
					
					ips.add(key);
				}
			}
			
			pw = new PrintWriter("src/test/resources/output/result.txt", "UTF-8");
			
			for(String ip : ips){
				List<LogBean> list = resultMap.get(ip);
				
				for(LogBean bean : list){
					pw.append(bean.toString());
					pw.println();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			pw.flush();
			pw.close();
		}
	}

}
