package com.model;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.db.DBconnect;

public class BookList extends ArrayList<Book> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static BookList booklist = null;
	
	private BookList() {
		
	}
	/**
	 * select by sql 
	 * @return
	 */
	public static BookList selectAllBooks() {
		DBconnect con = new DBconnect();
		SQLiteDatabase db = con.getConnection();
		Cursor cr = db.query("book", null, null, null, null, null, "id");
		BookList booklist = new BookList();
		while(cr.moveToNext()) {
			int id = cr.getColumnIndex("id");
			int name = cr.getColumnIndex("name");
			int price = cr.getColumnIndex("price");
			String sid = cr.getString(id);
			String sname = cr.getString(name);
			String sprice = cr.getString(price);
			booklist.add(new Book(sid,sname,sprice));
		}
		db.close();
		return booklist;
	}
	public static BookList getBookList() {
		if(booklist == null) {
			booklist = new BookList();
			DBconnect con = new DBconnect();//create database
			SQLiteDatabase db = con.getConnection();
			Cursor cur = db.query("book", null, null, null, null, null, null);
			while(cur.moveToNext()) {
				int idNum = cur.getColumnIndex("id");
				int nameNum = cur.getColumnIndex("name");
				int priceNum = cur.getColumnIndex("price");
				
				String id = cur.getString(idNum);
				String name = cur.getString(nameNum);
				String price = cur.getString(priceNum);
				
				Book book = new Book(id,name,price);
				booklist.add(book);
				cur.moveToNext();
			}
			con.close();
		}
		return booklist;
	}
	//checkid judge the book whether exists
	/**
	 * exists true; not exists false
	 * @param bookid
	 * @return
	 */
	private boolean checkId(String bookid) {
		for(int i = 0; i < booklist.size(); i++) {
			Book book = booklist.get(i);
			String id = book.getId();
			if(id.equals(bookid)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * check Name if find ,and delete from booklist
	 * @param bookid
	 * @return
	 */
	private boolean checkName(String name) {
		for(int i = 0; i < booklist.size(); i++) {
			Book book = booklist.get(i);
			String bname = book.getName();
			if(bname.equals(name)) {
				booklist.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * insert a book
	 * @param book
	 * @return
	 */
	public boolean insert(Book book) {
		String bid = book.getId();
		if(checkId(bid)) {
			return false;
		}else {
			//insert booklist , the same as the SQLite db
			booklist.add(book);
			String id = book.getId();
			String name = book.getName();
			String price = book.getPrice();
			DBconnect con = new DBconnect();
			SQLiteDatabase db = con.getConnection();
			String sql = "insert into book(id,name,price)" + " values('" + id
							+ "','" + name +"','" + price + "')";
			db.execSQL(sql);
			db.close();
			return true;
		}
	}
	/**
	 * delete a book by name
	 * @param name
	 * @return
	 */
	public boolean delete(String name) {
		if(checkName(name)) {
			DBconnect con = new DBconnect();
			SQLiteDatabase db = con.getConnection();
			String sql = "delete from book where name='" + name + "'";
			db.execSQL(sql);
			db.close();
			return true;
		}else {
			return false;
		}
	}
	/**
	 * get the book location 
	 * @param bookid
	 * @return
	 */
	private int getIndex(String bookid) {
		int i = 0;
		for(; i < booklist.size();i++) {
			Book book = booklist.get(i);
			String id = book.getId();
			if(id.equals(bookid)) {
				break;
			}
		}
		return i;
	}

	
	/**
	 * update a book
	 * @param book
	 * @return
	 */
	public boolean update(Book book) {
		if(checkId(book.getId())) {
			String id = book.getId();
			String name = book.getName();
			String price = book.getPrice();
			int index = getIndex(id);//get the book location from the booklist
			booklist.set(index, book);//first update the booklist
			DBconnect con = new DBconnect();
			SQLiteDatabase db = con.getConnection();
			String sql = "update book set name='" + name +"', price = '" + price +"' where id='" + id +"'";
			db.execSQL(sql);
			db.close();
			return true;
		}else{
			return false;
		}
	}
}
