package com.books.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.books.model.Book;
import com.books.service.BookService;
import com.books.service.BookServiceImpl;

public class ViewController implements BookController {
	private BookService bs = new BookServiceImpl();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("도서 정보 자세히 보기");
		
		Book book = bs.getBookDetail(req);
		req.setAttribute("book", book);
		req.getRequestDispatcher("/WEB-INF/views/BookDetail.jsp").forward(req, res);
	}

}
