package com.technext.cwc.database.model;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;

@Table(name = "favourites")
public class Favourite extends Model{

	@Expose
	@Column(name = "product_id")
	public Long product_id;
	
	public List<Product> getFavouriteProducts(){
		return  new Select().from(Product.class).where("id=?", this.product_id).execute();
	}
	
}
