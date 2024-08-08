package com.books.dao;

import java.sql.SQLException;
import java.util.List;

import com.books.model.Book;
import com.books.model.BookPage;

public interface BookDAO {
	List<Book> findAll() throws SQLException;
	int insert(Book book) throws SQLException;
	Book findByID(int bookId) throws SQLException;
	int update(Book book) throws SQLException;
	int softDelete(int bookId) throws SQLException;
	List<Book> findAllByWord(String keyword) throws SQLException;
	BookPage findAllByPage(int pageNum) throws SQLException;
}
