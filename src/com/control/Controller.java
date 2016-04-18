package com.control;

import com.model.Book;
import com.model.BookList;

public class Controller {
	public Controller() {
		
	}
	/**
	 * insert a book
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	public boolean addBook(String id,String name,String price) {
		BookList booklist = BookList.getBookList();
		Book book = new Book(id,name,price);
		if(booklist.insert(book)) 
			return true;
		else 
			return false;
	}
	/**
	 * update a book
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	public boolean updateBook(String id,String name,String price) {
		BookList booklist = BookList.getBookList();
		Book book = new Book(id,name,price);
		if(booklist.update(book)) 
			return true;
		else 
			return false;
	}
	/**
	 * delete a book by name
	 * @param name
	 * @return
	 */
	public boolean deleteBook(String name) {
		BookList booklist = BookList.getBookList();
		if(booklist.delete(name)) 
			return true;
		else
			return false;
	}
	/**
	 * select all books
	 * @return
	 */
	public BookList selectBook() {
		BookList booklist = BookList.getBookList();
		return booklist;
	}
	public BookList selectALlBook() {
		BookList booklist = BookList.selectAllBooks();
		return booklist;
	}
}
