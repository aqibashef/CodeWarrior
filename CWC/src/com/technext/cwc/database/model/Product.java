package com.technext.cwc.database.model;

import java.util.HashMap;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;


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
	public HashMap<String, String> settings;
	
	public List<ProductImage> getImages(){
		return new Select().from(ProductImage.class).where("product_id=?", getId()).execute();
	}
	
	public List<Settings> getSettings(){
	
	    return new Select()
	        .from(Settings.class)
	        .where("product_id = ?", getId())
	      /*  .orderBy("Name ASC")*/
	        .execute();
	    }

	public Long getServer_id() {
		return server_id;
	}

	public void setServer_id(Long server_id) {
		this.server_id = server_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice_type() {
		return price_type;
	}

	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getSub_category() {
		return sub_category;
	}

	public void setSub_category(Category sub_category) {
		this.sub_category = sub_category;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getSub_location() {
		return sub_location;
	}

	public void setSub_location(Location sub_location) {
		this.sub_location = sub_location;
	}

	public Long getView_count() {
		return view_count;
	}

	public void setView_count(Long view_count) {
		this.view_count = view_count;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public void setSettings(HashMap<String, String> settings) {
		this.settings = settings;
	}

	

}
