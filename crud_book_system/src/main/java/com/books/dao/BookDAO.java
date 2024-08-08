package com.books.dao;

import java.sql.SQLException;
import java.util.List;

import com.books.model.Book;

public interface BookDAO {
	List<Book> findAll() throws SQLException;
	int insert(Book book) throws SQLException;
	Book findByID(int bookId) throws SQLException;
}
