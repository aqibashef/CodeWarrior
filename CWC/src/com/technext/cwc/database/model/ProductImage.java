package com.technext.cwc.database.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

@Table(name = "product_images")
public class ProductImage extends Model{

	@Expose
 	@Column(name = "local_url")
	public String local_url;
	
	@Expose
 	@Column(name = "server_url")
	public String server_url;
	
 	@Column(name = "product_id")
	public Long product_id;

	public String getLocal_url() {
		return local_url;
	}

	public void setLocal_url(String local_url) {
		this.local_url = local_url;
	}

	public String getServer_url() {
		return server_url;
	}

	public void setServer_url(String server_url) {
		this.server_url = server_url;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
 	
 	

}
