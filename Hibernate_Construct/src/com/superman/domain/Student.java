package com.superman.domain;

import java.io.Serializable;
/**
 * ʵ����ʵ�����л�����������֮�����Ĵ��䣨����������֮�䴫��ʱת��Ϊ�������ֽ�����
 * @author Ningsc(supermanNingsc)
 *
 */
public class Student implements Serializable {

	private Long sid;
	private String name;
	private String description;
	private Classes classes;//��������
	
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
