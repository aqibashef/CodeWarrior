package com.technext.cwc.database.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;

@Table(name = "sub_locations")
public class SubLocation extends Model{
	
	@Expose
	@Column(name = "name")
	public String name;
	
	@Expose
	@Column(name = "location_id")
	public Long location_id;
	
	
	 
   
	
}
