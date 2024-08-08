package com.books.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.books.model.Book;
import com.books.service.BookService;
import com.books.service.BookServiceImpl;

public class DeleteController implements BookController {
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
		
		if (reqMethod.equals("DELETE")) {
			bs.deleteBook(req);
		}
		
		System.out.println(reqMethod);
		System.out.println("삭제 요청 들어옴");
	}
}
