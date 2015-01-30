package com.technext.cwc.model;

public class ProductImage {
	private String image_url;
	private Long id;
	private String server_url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServer_url() {
		return server_url;
	}

	public void setServer_url(String server_url) {
		this.server_url = server_url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	
}
