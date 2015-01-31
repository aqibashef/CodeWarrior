package com.technext.cwc.database.model;

import java.util.List;

import com.google.gson.annotations.Expose;

public class Products {
	@Expose
	public List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
