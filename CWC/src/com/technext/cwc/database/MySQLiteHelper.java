package com.technext.cwc.database;

import com.technext.cwc.database.entity.ProductEntity;
import com.technext.cwc.database.entity.ProductImagesEntity;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	private static MySQLiteHelper instance;

	
	private static final String DATABASE_NAME = "technext_cwc.db";
	private static final int DATABASE_VERSION = 1;

	public static final MySQLiteHelper getInstance(Context context){
		if(instance == null){
			instance = new MySQLiteHelper(context);
		}
		
		return instance;
	}
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		createTables(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + ProductEntity.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + ProductImagesEntity.TABLE_NAME);
		onCreate(db);
	}
	
	
	
	private void createTables(SQLiteDatabase database){
		TableCreateQueryBuilder  tableCreateQueryBuilder = new TableCreateQueryBuilder(ProductEntity.TABLE_NAME);
		String createProductTableQuery = tableCreateQueryBuilder
			.addId()
			.addStringColumn(ProductEntity.COLUMN_TITLE)
			.addStringColumn(ProductEntity.COLUMN_DESCRIPTION)
			.addStringColumn(ProductEntity.COLUMN_CATEORY)
			.addStringColumn(ProductEntity.COLUMN_TYPE)
			.addStringColumn(ProductEntity.COLUMN_LOCATION).getQuery();
		
		String productImagesTableQuery = tableCreateQueryBuilder
				.resetWithTable(ProductImagesEntity.TABLE_NAME)
				.addId()
				.addStringColumn(ProductImagesEntity.COLUMN_URL)
				.addIntegerColumn(ProductImagesEntity.COLUMN_IS_LOCAL).getQuery();
		database.execSQL(createProductTableQuery);
		database.execSQL(productImagesTableQuery);
	}
}
