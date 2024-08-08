package com.books.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.books.model.Book;
import com.books.service.BookService;
import com.books.service.BookServiceImpl;

public class EditController implements BookController {
	private BookService bs = new BookServiceImpl();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String reqMethod = req.getMethod();
		int bookId = Integer.parseInt(req.getParameter("id"));
		// id parameter 에 대한 유효성 검사
		if (bookId < 0) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		};
		
		if (reqMethod.equals("GET")) {
			Book book = bs.getBookDetail(req);
			req.setAttribute("book", book);
			req.getRequestDispatcher("/WEB-INF/views/BookEdit.jsp").forward(req, res);
		} else if (reqMethod.equals("POST")) {
			bs.editBook(req);
			return;
		}
		
	}
}
