package com.superman.entiy;

import java.io.Serializable;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

/**
 * 
 * ClassName: Person 
 * @Description: (对象实现序列化，利于对象在网络间进行io流传输),因jar包问题，hibernate注解方式的写法只提供实列
 * @author Ningsc
 * @date 2017年11月8日
 */
@Entity
@Table(appliesTo = "T_PERSON" )
public class Person implements Serializable{
	
	public Person(){
		
	}
	/*@Id
	@Generated(value=Generated)*/
	private Long pid;
	
	/*@Columns(columns = { null })*/
	private String name;
	private String description;
	
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
