package com.technext.cwc.model;

public class ProductImage {
	private String local_url;
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

	public String getLocal_url() {
		return local_url;
	}

	public void setLocal_url(String local_url) {
		this.local_url = local_url;
	}


	
}
