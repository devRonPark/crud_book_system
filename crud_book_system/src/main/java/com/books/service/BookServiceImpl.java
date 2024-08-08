package com.books.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.BadRequestException;

import com.books.dao.BookDAO;
import com.books.dao.BookDAOImpl;
import com.books.model.Book;
import com.books.util.TypeConverter;

public class BookServiceImpl implements BookService {
	private BookDAO bookDAO = new BookDAOImpl();
	
	@Override
	public List<Book> getBookList() throws SQLException {
		return bookDAO.findAll();
	}

	@Override
	public void addBook(HttpServletRequest req) throws SQLException {
		String title = req.getParameter("title");
		String writerName = req.getParameter("writerName");
		String genre = req.getParameter("genre") != null ? req.getParameter("genre") : null;
		String publisher = req.getParameter("publisher") != null ? req.getParameter("publisher") : null;
		String summary = req.getParameter("summary") != null ? req.getParameter("summary") : null;
		int price = Integer.parseInt(req.getParameter("price"));
		int totalPages = Integer.parseInt(req.getParameter("totalPages"));
		LocalDate publishedAt = TypeConverter.stringToLocalDate(req.getParameter("publishedAt"));
		
		Book newBook = new Book(title, writerName, genre, publisher, summary, price, totalPages, publishedAt);
		
		int resultRow = bookDAO.insert(newBook);
		if (resultRow <= 0) throw new SQLException("새로운 책 추가 실패");
	}
	
	@Override
	public Book getBookDetail(HttpServletRequest req) throws Exception {
		int bookId = Integer.parseInt(req.getParameter("id"));
		// id parameter 에 대한 유효성 검사
		if (bookId < 0) throw new Exception("id is invalid value");
		Book book = bookDAO.findByID(bookId);
		book.setPublishedAt(book.getPublishedAt());
		
		return book;
	}

	@Override
	public void editBook(HttpServletRequest req) throws Exception {
		int id = Integer.parseInt(req.getParameter("id"));
		Book book = bookDAO.findByID(id);
		if (book == null) {
			throw new BadRequestException("id 에 해당하는 책 정보가 존재하지 않습니다.");
		}
		
		String title = req.getParameter("title");
		String writerName = req.getParameter("writerName");
		String genre = req.getParameter("genre") != null ? req.getParameter("genre") : null;
		String publisher = req.getParameter("publisher") != null ? req.getParameter("publisher") : null;
		String summary = req.getParameter("summary") != null ? req.getParameter("summary") : null;
		int price = Integer.parseInt(req.getParameter("price"));
		int totalPages = Integer.parseInt(req.getParameter("totalPages"));
		
		LocalDate publishedAt = TypeConverter.stringToLocalDate(req.getParameter("publishedAt"));
		
		book.setTitle(title);
		book.setWriterName(writerName);
		book.setGenre(genre);
		book.setPublisher(publisher);
		book.setSummary(summary);
		book.setPrice(price);
		book.setTotalPages(totalPages);
		book.setPublishedAt(publishedAt);
		
		int resultRow = bookDAO.update(book);
		if (resultRow <= 0) throw new SQLException("책 정보 업데이트 실패");
	}

}
