package com.books.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.books.dao.BookDAO;
import com.books.dao.BookDAOImpl;
import com.books.model.Book;

public class BookServiceImpl implements BookService {
	private BookDAO bookDAO = new BookDAOImpl();
	
	@Override
	public List<Book> getBookList() throws SQLException {
		return bookDAO.findAll();
	}

	@Override
	public Book getBookDetail(HttpServletRequest req) throws Exception {
		int bookId = Integer.parseInt(req.getParameter("id"));
		// id parameter 에 대한 유효성 검사
		if (bookId < 0) throw new Exception("id is invalid value");
		Book book = bookDAO.findByID(bookId);
		book.setPublishedAt(book.getPublishedAt().substring(0, 10).replace("-", ". "));
		
		return book;
	}

}
