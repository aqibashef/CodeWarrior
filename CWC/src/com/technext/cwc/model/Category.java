package com.technext.cwc.model;

public class Category {

	private Long id;
	private String name;
	private String image_name;
	/*private Category sub_category;*/
	private Settings settings;
	
	public Settings getSettings() {
		return settings;
	}
	public void setSettings(Settings settings) {
		this.settings = settings;
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
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	/*public Category getSub_category() {
		return sub_category;
	}
	public void setSub_category(Category sub_category) {
		this.sub_category = sub_category;
	}*/
	
	
}
