package com.technext.cwc.database;

public class TableCreateQueryBuilder{
	private String query = "create table";
	
	public TableCreateQueryBuilder(String tableName){
		query += " "+tableName + "(";
	}
	
	public TableCreateQueryBuilder addId(){
		query += " _id integer primary key autoincrement";
		return this;
	}
	
	public TableCreateQueryBuilder addStringColumn(String columnName){
		query += ", " + columnName+ " text";
		return this;
	}
	
	public TableCreateQueryBuilder addIntegerColumn(String columnName){
		query += ", " + columnName+ " integer";
		return this;
	}
	public TableCreateQueryBuilder addRealColumn(String columnName){
		query += ", " + columnName+ " real";
		return this;
	} 
	
	public String getQuery(){
		return query += ");";
	}
	
	public TableCreateQueryBuilder resetWithTable(String tableName){
		query = "create table";
		query += " "+tableName + "(";
		return this;
	}
}
