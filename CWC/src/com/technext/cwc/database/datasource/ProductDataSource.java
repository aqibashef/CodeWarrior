/*package com.technext.cwc.database.datasource;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.technext.cwc.database.MySQLiteHelper;
import com.technext.cwc.database.entity.ProductEntity;
import com.technext.cwc.database.entity.SettingsEntity;
import com.technext.cwc.http.Client;
import com.technext.cwc.model.Category;
import com.technext.cwc.model.Location;
import com.technext.cwc.model.Product;
import com.technext.cwc.model.ProductImage;
import com.technext.cwc.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ProductDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;

	public ProductDataSource(Context context) {
		dbHelper = MySQLiteHelper.getInstance(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Product createProduct(String title, String description,
			String username, String userEmail, String userPhone, String location, String subLocation,String category,String subCategory,String priceType, Double price,HashMap<String, String> settings) {
		ContentValues values = new ContentValues();
		values.put(ProductEntity.COLUMN_TITLE, title);
		values.put(ProductEntity.COLUMN_DESCRIPTION, description);
		values.put(ProductEntity.COLUMN_USER_NAME, username);
		values.put(ProductEntity.COLUMN_USER_EMAIL, userEmail);
		values.put(ProductEntity.COLUMN_USER_PHONE, userPhone);
		values.put(ProductEntity.COLUMN_CATEORY, category);
		values.put(ProductEntity.COLUMN_SUB_CATEORY, subCategory);
		values.put(ProductEntity.COLUMN_LOCATION, location);
		values.put(ProductEntity.COLUMN_SUB_LOCATION, subLocation);
		values.put(ProductEntity.COLUMN_PRICE_TYPE, priceType);
		values.put(ProductEntity.COLUMN_PRICE, price);
		values.put(ProductEntity.COLUMN_CREATED_AT, new Date(System.currentTimeMillis()).toString());
		values.put(ProductEntity.COLUMN_UPDATED_AT, new Date(System.currentTimeMillis()).toString());
		
		long insertId = database.insert(ProductEntity.TABLE_NAME, null, values);
		Cursor cursor = database.query(ProductEntity.TABLE_NAME, null,
				ProductEntity.COLUMN_ID + " = " + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		Product newProduct = cursorToProduct(cursor);
		cursor.close();
		
		if(settings != null){
			Set<Entry<String, String>> entries	= settings.entrySet();
			
			Iterator<Entry<String, String>> iter = entries.iterator();
			while(iter.hasNext()){
				Entry<String, String> entry = iter.next();
				ContentValues settingValues = new ContentValues();
				settingValues.put(SettingsEntity.COLUMN_PRODUCT_ID, insertId);
				settingValues.put(SettingsEntity.COLUMN__KEY, entry.getKey());
				settingValues.put(SettingsEntity.COLUMN__VALUE, entry.getValue());
				
				database.insert(SettingsEntity.TABLE_NAME, null, values);
			}
		}
		
		
		newProduct.setSettings(getSettings(newProduct.getId()));
		
		return newProduct;
	}

	private HashMap<String, String> getSettings(long productId) {
		HashMap<String, String> settings = new HashMap<String, String>();
		Cursor cursor = database.query(SettingsEntity.TABLE_NAME, null,
				SettingsEntity.COLUMN_PRODUCT_ID + " = " + productId, null, null, null,
				null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			settings.put(SettingsEntity.COLUMN_PRODUCT_ID, cursor.getString(cursor
					.getColumnIndex(SettingsEntity.COLUMN_PRODUCT_ID)));
			settings.put(SettingsEntity.COLUMN__KEY, cursor.getString(cursor
					.getColumnIndex(SettingsEntity.COLUMN__KEY)));
			settings.put(SettingsEntity.COLUMN__VALUE, cursor.getString(cursor
					.getColumnIndex(SettingsEntity.COLUMN__VALUE)));
		}
		cursor.close();
		return settings;
	}
	
	

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();

		Cursor cursor = database.query(ProductEntity.TABLE_NAME, null, null,
				null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Product product = cursorToProduct(cursor);
			product.setSettings(getSettings(product.getId()));
			products.add(product);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return products;
	}
	
	  public void deleteProduct(Product product) {
		    long id = product.getId();
		    database.delete(ProductEntity.TABLE_NAME, ProductEntity.COLUMN_ID
		        + " = " + id, null);
	  }

	private Product cursorToProduct(Cursor cursor) {
		Product product = new Product();
		product.setId(cursor.getLong(0));
		product.setServer_id(cursor.getLong(cursor
				.getColumnIndex(ProductEntity.COLUMN_SERVER_ID)));
		product.setTitle(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_TITLE)));
		product.setDescription(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_DESCRIPTION)));
		product.setPrice_type(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_PRICE_TYPE)));
		product.setPrice(cursor.getDouble(cursor
				.getColumnIndex(ProductEntity.COLUMN_PRICE)));
		product.setStatus(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_STAUS)));
		
		product.setUser_name(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_USER_NAME)));
		product.setUser_email(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_USER_EMAIL)));
		product.setUser_phone(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_USER_PHONE)));
		
		if(Client.getUser() != null){
			product.setUser(Client.getUser());
		}
		

		Category category = new Category();
		category.setName(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_CATEORY)));
		product.setCategory(category);

		Category subCategory = new Category();
		subCategory.setName(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_SUB_CATEORY)));
		product.setSub_category(subCategory);
		
		Location location = new Location();
		location.setName(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_LOCATION)));
		product.setLocation(location);
		
		Location subLocation = new Location();
		location.setName(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_SUB_LOCATION)));
		product.setSub_location(subLocation);
		return product;
	}

}
*/