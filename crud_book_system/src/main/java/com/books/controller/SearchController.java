package com.books.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.books.model.Book;
import com.books.service.BookService;
import com.books.service.BookServiceImpl;

public class SearchController implements BookController {
	private BookService bs = new BookServiceImpl();

	@Override
	public void process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String reqMethod = req.getMethod();
		String keyword = req.getParameter("keyword");
		System.out.println("도서 목록 중 키워드로 검색");
		
		System.out.println(reqMethod);
		
		if (reqMethod.equals("GET") && keyword != null) {
			List<Book> bookList = bs.searchBookListByKeyword(req);
			
			req.setAttribute("searchResult", bookList);
			req.getRequestDispatcher("/WEB-INF/views/BookSearchResult.jsp").forward(req, res);
		}
	}

}
