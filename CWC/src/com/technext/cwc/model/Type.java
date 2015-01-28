package com.technext.cwc.model;

public class Type {
	
	private Long id;
	private String name;
	private Type sub_type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getSub_type() {
		return sub_type;
	}
	public void setSub_type(Type sub_type) {
		this.sub_type = sub_type;
	}
	
	
}
