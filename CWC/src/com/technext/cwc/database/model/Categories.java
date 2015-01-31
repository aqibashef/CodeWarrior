package com.technext.cwc.database.model;

import java.util.List;

import com.google.gson.annotations.Expose;

public class Categories {

	@Expose
	private List<Category> categories;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
