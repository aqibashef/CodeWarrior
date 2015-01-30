package com.technext.cwc.database.model;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

@Table(name = "sub_categories")
public class SubCategory extends Model{

	@Expose
	@Column(name = "name")
	public String name;
	
	@Expose
	@Column(name = "Category")
	public Category category;
}
