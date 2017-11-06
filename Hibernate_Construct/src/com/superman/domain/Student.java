package com.superman.domain;

import java.io.Serializable;
/**
 * 实体类实现序列化，便于网络之间对象的传输（对象在网络之间传输时转换为二进制字节流）
 * @author Ningsc(supermanNingsc)
 *
 */
public class Student implements Serializable {

	private Long sid;
	private String name;
	private String description;
	private Classes classes;//关联对象
	
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	
}
