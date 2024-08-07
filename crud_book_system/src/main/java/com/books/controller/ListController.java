package com.books.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.books.model.Book;
import com.books.service.BookService;
import com.books.service.BookServiceImpl;

public class ListController implements BookController {
	private BookService bs = new BookServiceImpl();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("도서 목록 조회");
		List<Book> bookList = bs.getBookList();
		
		for (Book book: bookList) {
			System.out.println(book);
		}
	}

}
