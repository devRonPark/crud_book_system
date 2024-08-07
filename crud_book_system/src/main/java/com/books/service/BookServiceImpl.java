package com.books.service;

import java.sql.SQLException;
import java.util.List;

import com.books.dao.BookDAO;
import com.books.dao.BookDAOImpl;
import com.books.model.Book;

public class BookServiceImpl implements BookService {
	private BookDAO bookDAO = new BookDAOImpl();
	
	@Override
	public List<Book> getBookList() throws SQLException {
		return bookDAO.findAll();
	}

}
