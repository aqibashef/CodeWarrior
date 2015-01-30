package com.technext.cwc.database.model;

import java.util.HashMap;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.technext.cwc.model.Category;
import com.technext.cwc.model.Location;
import com.technext.cwc.model.ProductImage;
import com.technext.cwc.model.User;

@Table(name = "products")
public class Product extends Model {

	@Expose
	@Column(name = "server_id")
	public Long server_id;

	@Expose
	@Column(name = "title")
	public String title;

	@Expose
	@Column(name = "description")
	public String description;

	@Expose
	@Column(name = "price_type")
	public String price_type;
	
	@Expose
	@Column(name = "price")
	public Double price;
	
	@Expose
	@Column(name = "status")
	public String status;
	
	@Expose
	@Column(name = "user_name")
	public String user_name;
	
	@Expose
	@Column(name = "user_email")
	public String user_email;
	
	@Expose
	@Column(name = "user_phone")
	public String user_phone;
	
	@Expose
	@Column(name = "user")
	public User user;
	
	@Expose
	@Column(name = "category")
	public Category category;
	
	@Expose
	@Column(name = "sub_category")
	public Category sub_category;
	
	@Expose
	@Column(name = "location")
	public Location location;
	
	@Expose
	@Column(name = "sub_location")
	public Location sub_location;
	
	@Expose
	@Column(name = "images")
	public List<ProductImage> images;

	@Expose
	@Column(name = "view_count")
	public Long view_count;
	
	@Expose
	@Column(name = "created_at")
	public String created_at;
	
	@Expose
	@Column(name = "updated_at")
	public String updated_at;
	
	@Expose
	public List<Settings> settings;
	
	public List<Settings> getSettings(){
	
	    return new Select()
	        .from(Settings.class)
	        .where("product_id = ?", getId())
	      /*  .orderBy("Name ASC")*/
	        .execute();
	    }


}
