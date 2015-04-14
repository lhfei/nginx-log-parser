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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Apr 14, 2015
 */
public class TotalMap extends Text {
	
	public TotalMap(String ip, Integer total) {
		this.ip = ip;
		this.total = total;
	}
	
	/*@Override
	public void write(DataOutput out) throws IOException {
		
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		
	}

	@Override
	public int getLength() {
		return 0;
	}

	@Override
	public byte[] getBytes() {
		return null;
	}*/

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getSeria() {
		return seria;
	}

	public void setSeria(String seria) {
		this.seria = seria;
	}

	public List<LogBean> getData() {
		return data;
	}

	public void setData(List<LogBean> data) {
		this.data = data;
	}

	private String ip;
	private Integer total;
	private String seria;
	private List<LogBean> data;
	private Map<String, Integer> set = new HashMap<String, Integer>();




}
