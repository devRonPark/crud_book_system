package com.books.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books/*")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookServlet() {
        
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}