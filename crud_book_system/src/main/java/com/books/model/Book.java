package com.books.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
	private int id;
	private String title, writerName, genre, publisher;
	private int totalPages;
	private String publishedAt;
}
