package com.books.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.books.model.Book;
import com.books.model.BookPage;
import com.books.service.BookService;
import com.books.service.BookServiceImpl;

public class ListController implements BookController {
	private BookService bs = new BookServiceImpl();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageStr = req.getParameter("page");
		BookPage bookPage = null;
		List<Book> bookList = null;
		// 페지네이션 처리
		if (pageStr != null) {
			bookPage = bs.getBookListByPage(Integer.parseInt(pageStr));
			bookList = bookPage.getBooks();
		}
		else {
			// 기본값은 page: 1일 때,
			bookPage = bs.getBookListByPage(1);
			bookList = bookPage.getBooks();
		}
		int totalPages = bookPage.getTotalPages();
		
		req.setAttribute("currentPage", pageStr != null ? Integer.parseInt(pageStr) : 1);		
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("bookList", bookList);
		req.getRequestDispatcher("/WEB-INF/views/BookList.jsp").forward(req, res);			
	}
}
