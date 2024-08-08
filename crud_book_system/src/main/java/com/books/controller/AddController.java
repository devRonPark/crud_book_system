package com.books.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.books.service.BookService;
import com.books.service.BookServiceImpl;

public class AddController implements BookController {
	private BookService bs = new BookServiceImpl();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String reqMethod = req.getMethod();
		
		if (reqMethod.equals("POST")) {
			bs.addBook(req);
		}
		else if (reqMethod.equals("GET")) {
			req.getRequestDispatcher("/WEB-INF/views/BookRegister.jsp").forward(req, res);			
		}
	}
}
