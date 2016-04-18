package com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBconnect extends SQLiteOpenHelper {
	
	private final static int DATABASE_VERSION = 1;// database version
	private final static String DATABASE_NAME = "book.db";// database name
	private static Context context;//the context of app
	
	//set context
	public static void setContext(Context con) {
		DBconnect.context = con;
	}
	//construct the construct function
	public DBconnect() {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
			String sql = "create table book(id varchar(30) not null, " +
						 "name varchar(50) not null," +
					     "price varchar(30) not null);";
			db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}
	
	public SQLiteDatabase getConnection() {
		SQLiteDatabase db = getWritableDatabase();
		return db;
	}
	
	public void  close(SQLiteDatabase db) {
		db.close();
	}

}
