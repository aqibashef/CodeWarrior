package com.technext.cwc.database.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

@Table(name = "settings")
public class Settings extends Model{
	@Expose
 	@Column(name = "product_id")
    public Long product_id;
 	
	@Expose
 	@Column(name = "key")
 	public String key;
 	
	@Expose
 	@Column(name = "value")
 	public String value;
 	
 	
 	
}
