package com.technext.cwc.model;

import java.util.List;

public class Location {

	
	private Long id;
	private String name;
	private List<Location> sub_locations;
	
	public List<Location> getSub_locations() {
		return sub_locations;
	}
	public void setSub_locations(List<Location> sub_locations) {
		this.sub_locations = sub_locations;
	}
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
}
