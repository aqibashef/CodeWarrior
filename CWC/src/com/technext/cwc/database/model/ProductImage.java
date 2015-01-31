package com.technext.cwc.database.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

@Table(name = "product_images")
public class ProductImage extends Model{

	@Expose
 	@Column(name = "local_url")
	private String local_url;
	
	@Expose
 	@Column(name = "server_url")
	private String server_url;
	
 	@Column(name = "product_id")
	private Long product_id;

}
