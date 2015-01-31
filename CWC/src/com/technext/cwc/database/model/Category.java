package com.technext.cwc.database.model;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;



@Table(name = "categories")
public class Category extends Model{

	@Expose
	@Column(name = "name")
	public String name;
	
	@Expose
	@Column(name = "image_url")
	public String image_url;
	
	@Expose
	public List<SubCategory> sub_categories;
	
	public List<SubCategory> getSubCategories(){
		  return new Select()
	        .from(SubCategory.class)
	        .where("category_id = ?", getId())
	      /*  .orderBy("Name ASC")*/
	        .execute();
	}	    
}
