package com.superman.domain;

import java.io.Serializable;

import org.hibernate.impl.SessionFactoryImpl;

/**
 * 
 * ClassName: Person 
 * @Description: (对象实现序列化，利于对象在网络间进行io流传输)
 * @author Ningsc
 * @date 2017年11月8日
 */
public class Person implements Serializable{
	private Long pid;
	private String name;
	private String description;
	
	public Person(String name){
		this.name = name;
	}
	
	public Person(){}
	
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
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
}
