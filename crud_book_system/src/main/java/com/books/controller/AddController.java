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
		String action = req.getParameter("action");
		
		if (reqMethod.equals("POST")) {
			System.out.println(req.getPart("title"));
			System.out.println(req.getPart("writerName"));
//			bs.addBook(req);
//			res.sendRedirect("/books/list");
		}
		else if (reqMethod.equals("GET") && action == null) {
			req.getRequestDispatcher("/WEB-INF/views/BookRegister.jsp").forward(req, res);			
		}
	}
}
