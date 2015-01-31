package com.technext.cwc.database.model;

import java.util.List;

import com.google.gson.annotations.Expose;

public class Locations {
	
	@Expose
	private List<Location> locations;

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	
}
