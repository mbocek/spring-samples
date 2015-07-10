/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.sample.spring.batch.sample1;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
@XmlRootElement(name = "record")
public class Report {
 
	private int id;
	private BigDecimal sales;
	private int qty;
	private String staffName;
	private Date date;
 
	@XmlAttribute(name = "id")
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	@XmlElement(name = "sales")
	public BigDecimal getSales() {
		return sales;
	}
 
	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}
 
	@XmlElement(name = "qty")
	public int getQty() {
		return qty;
	}
 
	public void setQty(int qty) {
		this.qty = qty;
	}
 
	@XmlElement(name = "staffName")
	public String getStaffName() {
		return staffName;
	}
 
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
 
	public Date getDate() {
		return date;
	}
 
	public void setDate(Date date) {
		this.date = date;
	}
 
	@Override
	public String toString() {
		return "Report [id=" + id + ", sales=" + sales 
                    + ", qty=" + qty + ", staffName=" + staffName + "]";
	}
 
}