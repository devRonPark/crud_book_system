package com.books.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BookController {
	void process(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
