package com.books.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.books.model.Book;

public interface BookService {
	List<Book> getBookList() throws SQLException;
	void addBook(HttpServletRequest req) throws SQLException;
	Book getBookDetail(HttpServletRequest req) throws Exception;
	void editBook(HttpServletRequest req) throws Exception;
	void deleteBook(HttpServletRequest req) throws Exception;
	List<Book> searchBookListByKeyword(HttpServletRequest req) throws Exception;
}
