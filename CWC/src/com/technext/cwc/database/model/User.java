package com.technext.cwc.database.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.technext.cwc.model.Location;

@Table(name = "products")
public class User extends Model{

	@Expose
	@Column(name = "server_id")
	public Long server_id;
	
	@Expose
	@Column(name = "email")
	public String email;
	
	@Expose
	@Column(name = "firstname")
	public String firstname;
	
	@Expose
	@Column(name = "lastname")
	public String lastname;
	
	@Expose
	@Column(name = "mobile_no")
	public String mobile_no;
	
	@Expose
	@Column(name = "profile_pic_url")
	public String profile_pic_url;
	
	@Expose
	@Column(name = "session_token")
	public String session_token;
	
	@Expose
	@Column(name = "profile_pic_extension")
	public String profile_pic_extension;
	
	@Expose
	@Column(name = "location")
	public Location location;
	
}
