package com.books.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditController implements BookController {

	@Override
	public void process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("도서 정보 수정");
	}

}