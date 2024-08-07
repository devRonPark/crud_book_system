package com.books.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
	private int id;
	private String title, writerName, genre, publisher, summary;
	private int price, totalPages;
	private String publishedAt;
}
