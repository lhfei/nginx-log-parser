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
package com.ifeng.logparser;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Apr 10, 2015
 */
public class NginxLogMapper extends Mapper<LongWritable, Text, Text, Text> {

	private static final Logger log = LoggerFactory.getLogger(NginxLogMapper.class);

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		//log.info("Key = {} -- value = {}", key, value);
		String line = value.toString();
		String[] logs = line.split(" - - ");
		
		if(logs.length == 2){
			String seria = "\"NULL\"";
			String ip = logs[0];
			String factors = logs[1];
			
			//String[] msgs = factors.split("\"-\"");
			//String[] msgs = factors.split("\\\"[^\"]*\""); //\"[^"]*"
			
			Pattern pattern = Pattern.compile("\"[^\"]*\"");
			
			Matcher matcher = pattern.matcher(factors);
			
			matcher.find();
			matcher.find();
			
			if(matcher.find()){
				seria = matcher.group().replaceAll("\"", "");
				log.info("Seria: {}", seria);
			}
			
			
			//log.info("length: {} == {}", msgs.length, msgs);
			
			/*if(msgs != null && msgs.length > 1){
				seria = msgs[2].trim();
			}*/
			
			seria = (seria.length() > 0 && (!seria.equals("-"))) ? seria : "NULL";
			for(int i=0; i<(15-ip.length()); i++){
				ip += " ";
			}
			
			context.write(new Text(ip), new Text("\t" +seria));
			
			log.info("IP: {}, Mathine: {}", ip, seria);
		}
		
	}
	
	
	
}
