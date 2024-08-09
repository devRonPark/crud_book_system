package com.books.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.coyote.BadRequestException;

import com.books.dao.BookDAO;
import com.books.dao.BookDAOImpl;
import com.books.model.Book;
import com.books.model.BookPage;
import com.books.util.TypeConverter;

public class BookServiceImpl implements BookService {
	private BookDAO bookDAO = new BookDAOImpl();
	
	@Override
	public List<Book> getBookList() throws SQLException {
		return bookDAO.findAll();
	}

	@Override
	public void addBook(HttpServletRequest req) throws Exception {
		String title = req.getParameter("title");
		String writerName = req.getParameter("writerName");
		String genre = req.getParameter("genre") != null ? req.getParameter("genre") : null;
		String publisher = req.getParameter("publisher") != null ? req.getParameter("publisher") : null;
		String summary = req.getParameter("summary") != null ? req.getParameter("summary") : null;
		int price = req.getParameter("price") != null ? Integer.parseInt(req.getParameter("price")) : 0;
		int totalPages = req.getParameter("totalPages") != null ? Integer.parseInt(req.getParameter("totalPages")) : 0;
		LocalDate publishedAt = req.getParameter("publishedAt") != null ? TypeConverter.stringToLocalDate(req.getParameter("publishedAt")) : null;
		System.out.println(title + ", " + writerName + ", " + genre + ", " + publisher + ", " + summary + ", " + price + ", " + totalPages);
		String thumbnail = saveImg(req);
		
		
		Book newBook = new Book(title, writerName, genre, publisher, summary, price, totalPages, publishedAt, thumbnail);
		System.out.println(newBook);
		
//		int resultRow = bookDAO.insert(newBook);
//		if (resultRow <= 0) throw new SQLException("새로운 책 추가 실패");
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

	@Override
	public void deleteBook(HttpServletRequest req) throws Exception {
		int id = Integer.parseInt(req.getParameter("id"));
		Book book = bookDAO.findByID(id);
		if (book == null) {
			throw new BadRequestException("id 에 해당하는 책 정보가 존재하지 않습니다.");
		}
		
		int resultRow = bookDAO.softDelete(id);
		if (resultRow <= 0) throw new SQLException("책 정보 삭제 실패");
	}

	@Override
	public List<Book> searchBookListByKeyword(HttpServletRequest req) throws Exception {
		String keyword = req.getParameter("keyword");
		System.out.println(keyword);
		
		return bookDAO.findAllByWord(keyword);
	}

	@Override
	public BookPage getBookListByPage(int page) throws Exception {
		return bookDAO.findAllByPage(page);
	}

	private String saveImg(HttpServletRequest req) throws Exception {
		Part part = req.getPart("thumbnail");
		System.out.println(part);
		String header = part.getHeader("content-disposition");
		int start = header.indexOf("filename=");
		String originImg = header.substring(start + 10, header.length() - 1);
		System.out.println(header);
		if (originImg != null && !originImg.isEmpty()) {
			StringBuilder img = new StringBuilder();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmSS");
			String time = dateFormat.format(cal.getTime());
			img.append(time).append(originImg.substring(originImg.lastIndexOf(".")));
			originImg = img.toString();
			part.write(originImg);
		} else {
			originImg = "default.jpg";
		}
		return originImg;
	}

}
