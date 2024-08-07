package com.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.books.model.Book;
import com.books.util.ConnectionPool;

public class BookDAOImpl implements BookDAO {

	@Override
	public List<Book> findAll() throws SQLException {
		String sql = "SELECT * FROM books";
		try (
			Connection conn = ConnectionPool.DBPool.getDBPool();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			List<Book> bookList = new ArrayList<>();
			while (rs.next()) {
				Book book = new Book(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getString("writerName"),
					rs.getString("genre"),
					rs.getString("publisher"),
					rs.getString("summary"),
					rs.getInt("price"),
					rs.getInt("totalPages"),
					rs.getString("publishedAt")
				);
				bookList.add(book);
			}
			return bookList;
		}
	}

}
