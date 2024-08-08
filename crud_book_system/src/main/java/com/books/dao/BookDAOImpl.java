package com.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.books.model.Book;
import com.books.model.BookPage;
import com.books.util.ConnectionPool;
import com.books.util.TypeConverter;

public class BookDAOImpl implements BookDAO {

	@Override
	public List<Book> findAll() throws SQLException {
		String sql = "SELECT * FROM books WHERE deletedAt is null";
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
					TypeConverter.timeStampToLocalDate(rs.getTimestamp("publishedAt"))
				);
				bookList.add(book);
			}
			return bookList;
		}
	}

	@Override
	public int insert(Book book) throws SQLException {
		String sql = "INSERT INTO books (title, writerName, genre, publisher, summary, price, totalPages) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (
			Connection conn = ConnectionPool.DBPool.getDBPool();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getWriterName());
			pstmt.setString(3, book.getGenre());
			pstmt.setString(4, book.getPublisher());
			pstmt.setString(5, book.getSummary());
			pstmt.setInt(6, book.getPrice());
			pstmt.setInt(7, book.getTotalPages());
			
			return pstmt.executeUpdate();
		}
	}
	
	@Override
	public Book findByID(int bookId) throws SQLException {
		String sql = "SELECT * FROM books WHERE id = ?";
		try (
			Connection conn = ConnectionPool.DBPool.getDBPool();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, bookId);
			
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					Book book = new Book(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("writerName"),
						rs.getString("genre"),
						rs.getString("publisher"),
						rs.getString("summary"),
						rs.getInt("price"),
						rs.getInt("totalPages"),
						TypeConverter.timeStampToLocalDate(rs.getTimestamp("publishedAt"))
					);
					return book;
				}
				return null;
			}			
		}
	}

	@Override
	public int update(Book book) throws SQLException {
		String sql = "UPDATE books SET title = ?, writerName = ?, genre = ?, publisher = ?, summary = ?, price = ?, totalPages = ?, publishedAt = ? WHERE id = ? AND deletedAt is null";
		try (
			Connection conn = ConnectionPool.DBPool.getDBPool();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getWriterName());
			pstmt.setString(3, book.getGenre());
			pstmt.setString(4, book.getPublisher());
			pstmt.setString(5, book.getSummary());
			pstmt.setInt(6, book.getPrice());
			pstmt.setInt(7, book.getTotalPages());
			pstmt.setTimestamp(8, TypeConverter.localDateToTimestamp(book.getPublishedAt()));
			pstmt.setInt(9, book.getId());
			
			return pstmt.executeUpdate();
		}
	}

	@Override
	public int softDelete(int bookId) throws SQLException {
		String sql = "UPDATE books SET deletedAt = NOW() WHERE id = ?";
		try (
			Connection conn = ConnectionPool.DBPool.getDBPool();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, bookId);
			
			return pstmt.executeUpdate();
		}
	}

	@Override
	public List<Book> findAllByWord(String keyword) throws SQLException {
		String sql = "SELECT * FROM books WHERE deletedAt is null AND (title LIKE ? OR writerName LIKE ? OR genre LIKE ? OR publisher LIKE ? OR summary OR ?)";
		String searchCondStr = "%" + keyword + "%";
		try (
			Connection conn = ConnectionPool.DBPool.getDBPool();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			for (int i = 1; i < 6; i++) {
				pstmt.setString(i, searchCondStr);
			}
			
			try (ResultSet rs = pstmt.executeQuery();) {
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
						TypeConverter.timeStampToLocalDate(rs.getTimestamp("publishedAt"))
					);
					bookList.add(book);
				}
				return bookList;				
			}
	    }
	}

	@Override
	public BookPage findAllByPage(int page) throws SQLException {
		int limit = 10;
		int offset = (page - 1) * 10;
		String sql = "SELECT b.*, floor(Count(*) OVER () / 10) + 1 AS totalBookListPages FROM books b WHERE b.deletedAt is null ORDER BY b.publishedAt DESC LIMIT 10 OFFSET ?";
		try (
			Connection conn = ConnectionPool.DBPool.getDBPool();
			PreparedStatement pstmt = conn.prepareStatement(sql);	
		) {
			pstmt.setInt(1, offset);
			
			try (ResultSet rs = pstmt.executeQuery();) {
				List<Book> bookList = new ArrayList<>();
				int totalBookListPages = 0;
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
						TypeConverter.timeStampToLocalDate(rs.getTimestamp("publishedAt"))
					);
					totalBookListPages = rs.getInt("totalBookListPages");
					bookList.add(book);
				}	
				return new BookPage(bookList, totalBookListPages);
			}	
		}
	}
}
