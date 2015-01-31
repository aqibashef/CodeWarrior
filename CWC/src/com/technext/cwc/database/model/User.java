package com.technext.cwc.database.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.technext.cwc.http.Client;


@Table(name = "users")
public class User extends Model{
	
	public User(){
		
	}
	
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
	@Column(name = "location_id")
	public Long location_id;
	
	@Expose
	@Column(name = "created_at")
	public String created_at;
	
	@Expose
	@Column(name = "updated_at")
	public String updated_at;

	public Long getServer_id() {
		return server_id;
	}

	public void setServer_id(Long server_id) {
		this.server_id = server_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getProfile_pic_url() {
		return profile_pic_url;
	}

	public void setProfile_pic_url(String profile_pic_url) {
		this.profile_pic_url = profile_pic_url;
	}

	public String getSession_token() {
		return session_token;
	}

	public void setSession_token(String session_token) {
		this.session_token = session_token;
	}

	public String getProfile_pic_extension() {
		return profile_pic_extension;
	}

	public void setProfile_pic_extension(String profile_pic_extension) {
		this.profile_pic_extension = profile_pic_extension;
	}

	public Long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	
	
	
}
