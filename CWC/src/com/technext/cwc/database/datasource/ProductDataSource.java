package com.technext.cwc.database.datasource;

import java.util.ArrayList;
import java.util.List;

import com.technext.cwc.database.MySQLiteHelper;
import com.technext.cwc.database.entity.ProductEntity;
import com.technext.cwc.model.Product;

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
			String category, String type, String location) {
		ContentValues values = new ContentValues();
		values.put(ProductEntity.COLUMN_TITLE, title);
		values.put(ProductEntity.COLUMN_DESCRIPTION, description);
		values.put(ProductEntity.COLUMN_CATEORY, category);
		values.put(ProductEntity.COLUMN_TYPE, type);
		values.put(ProductEntity.COLUMN_LOCATION, location);
		long insertId = database.insert(ProductEntity.TABLE_NAME, null, values);
		Cursor cursor = database.query(ProductEntity.TABLE_NAME, null,
				ProductEntity.COLUMN_ID + " = " + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		Product newProduct = cursorToProduct(cursor);
		cursor.close();
		return newProduct;
	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();

		Cursor cursor = database.query(ProductEntity.TABLE_NAME, null, null,
				null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Product product = cursorToProduct(cursor);
			products.add(product);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return products;
	}
	
	  public void deleteProduct(Product product) {
		    long id = product.getId();
		    System.out.println("Comment deleted with id: " + id);
		    database.delete(ProductEntity.TABLE_NAME, ProductEntity.COLUMN_ID
		        + " = " + id, null);
		  }

	private Product cursorToProduct(Cursor cursor) {
		Product product = new Product();
		product.setId(cursor.getLong(0));
		product.setTitle(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_TITLE)));
		product.setDescription(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_DESCRIPTION)));
		product.setCategory(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_CATEORY)));
		product.setLocation(cursor.getString(cursor
				.getColumnIndex(ProductEntity.COLUMN_LOCATION)));
		return product;
	}

}
