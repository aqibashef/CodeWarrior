package com.technext.cwc.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Product {
	private Long id;
	private Long server_id;
	private String title;
	private String description;
	private String price_type;
	private Double price;
	private String status;
	private String user_name;
	private String user_email;
	private String user_phone;
	private User user;
	private Category category;
	private Category sub_category;
	private Location location;
	private Location sub_location;
	private List<ProductImage> images;
	
	private Long view_count;
	private String created_at;
	private String updated_at;
	private HashMap<String, String> settings;
	
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy:hh-mm-ss");
	
	public Date getCreated_at() {
		
		Date inputDate = null;
		try {
			inputDate = dateFormat.parse(this.created_at);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputDate;
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
	public Location getSub_location() {
		return sub_location;
	}
	public void setSub_location(Location sub_location) {
		this.sub_location = sub_location;
	}
	public Long getServer_id() {
		return server_id;
	}
	public void setServer_id(Long server_id) {
		this.server_id = server_id;
	}
	public Long getView_count() {
		return view_count;
	}
	public void setView_count(Long view_count) {
		this.view_count = view_count;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public List<ProductImage> getImages() {
		return images;
	}
	public void setImages(List<ProductImage> images) {
		this.images = images;
	}
	public Category getSub_category() {
		return sub_category;
	}
	public void setSub_category(Category sub_category) {
		this.sub_category = sub_category;
	}
	public HashMap<String, String> getSettings() {
		return settings;
	}
	public void setSettings(HashMap<String, String> settings) {
		this.settings = settings;
	}
}
