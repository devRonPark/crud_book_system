package com.books.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
	private int id;
	private String title, writerName, genre, publisher, summary;
	private int price, totalPages;
	private String publishedAt;
	
	public Book(String title, String writerName, String genre, String publisher, String summary, int price,
			int totalPages) {
		super();
		this.title = title;
		this.writerName = writerName;
		this.genre = genre;
		this.publisher = publisher;
		this.summary = summary;
		this.price = price;
		this.totalPages = totalPages;
	}
	
	
}
