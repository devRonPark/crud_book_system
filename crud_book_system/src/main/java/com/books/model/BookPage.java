package com.books.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookPage {
    private List<Book> books;
    private int totalPages;
}

