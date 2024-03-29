package com.technext.cwc.database.model;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;

@Table(name = "locations")
public class Location extends Model {

	@Expose
 	@Column(name = "name")
	public String name;
	
	@Expose
	public List<SubLocation> sub_locations;
 	
 	public List<SubLocation> getSublocations(){
 		
 		    return new Select()
 		        .from(SubLocation.class)
 		        .where("location_id = ?", getId())
 		      /*  .orderBy("Name ASC")*/
 		        .execute();

 	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubLocation> getSub_locations() {
		return sub_locations;
	}

	public void setSub_locations(List<SubLocation> sub_locations) {
		this.sub_locations = sub_locations;
	}
 	
 	
 	
}
