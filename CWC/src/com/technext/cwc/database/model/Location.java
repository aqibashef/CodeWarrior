package com.technext.cwc.database.model;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

@Table(name = "locations")
public class Location extends Model {

	@Expose
 	@Column(name = "name")
	public String name;
	
	@Expose
	public List<SubLocation> sub_locations;
 	
 	public List<SubLocation> getSublocations(){
 		return getMany(SubLocation.class, "Location");
 	}
 	
}
