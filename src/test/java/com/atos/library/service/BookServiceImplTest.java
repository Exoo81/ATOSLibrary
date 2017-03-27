package com.atos.library.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atos.library.entity.Book;
import com.atos.library.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BookServiceImplTest {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;

	@Test
	public void testAddNewBookToLibrary() {
		
		assertEquals(2, bookService.showAllListOfBook().size());
		
		Book book = new Book();
		book.setTitle("Head First Java");
		book.setAuthor("Kathy Sierra");
		book.setYear("2005");
		book.setLent(false);
		bookService.addNewBookToLibrary(book);
		Integer bookID = book.getId();
		assertNotNull(bookID);
		
		
		Book book1 = new Book();
		book1.setTitle("MySQL Cookbook");
		book1.setAuthor("Paul DuBois");
		book1.setYear("2010");
		book1.setLent(false);
		bookService.addNewBookToLibrary(book1);
		Integer book1ID = book1.getId();
		assertNotNull(book1ID);
		
		Book book2 = new Book();
		book2.setTitle("MySQL Cookbook");
		book2.setAuthor("Paul DuBois");
		book2.setYear("2010");
		book2.setLent(false);
		bookService.addNewBookToLibrary(book2);
		Integer book2ID = book2.getId();
		assertNotNull(book2ID);
		
		Book book3 = new Book();
		book3.setTitle("MySQL Cookbook");
		book3.setAuthor("Paul DuBois");
		book3.setYear("2014");
		book3.setLent(false);
		bookService.addNewBookToLibrary(book3);
		Integer book3ID = book3.getId();
		assertNotNull(book3ID);
		
		
		assertEquals(6, bookService.showAllListOfBook().size());
	}

	@Test
	public void testRemoveBookFromLibrary() {
		
		assertEquals(6, bookService.showAllListOfBook().size());
		

		List<Book> bookList = bookService.findBookByTitleAndYear("MySQL Cookbook", "2014");
		Book findBook = bookList.get(0);
		
		assertEquals(new Integer(6), findBook.getId());
		assertEquals("Paul DuBois", findBook.getAuthor());
		assertEquals("MySQL Cookbook", findBook.getTitle());
		assertEquals("2014", findBook.getYear());
		assertEquals(false, findBook.isLent());
		
		bookService.removeBookFromLibrary(findBook.getId());
		
		assertEquals(5, bookService.showAllListOfBook().size());

	}

	@Test
	public void testFindBookBy() {
		
		//all books in library
		assertEquals(6, bookService.showAllListOfBook().size());
		
		//find by title
		assertEquals(3, bookService.findBookBy("title", "MySQL Cookbook").size());
		assertEquals(1, bookService.findBookBy("title", "PHP 7 in easy steps").size());
		
		//find by author
		assertEquals(3, bookService.findBookBy("author", "Paul DuBois").size());
		assertEquals(2, bookService.findBookBy("author", "Mike McGrath").size());
		
		//find by year
		assertEquals(1, bookService.findBookBy("year", "2016").size());
		assertEquals(1, bookService.findBookBy("year", "2005").size());
		assertEquals(2, bookService.findBookBy("year", "2010").size());
		
	}

	@Test
	public void testFindBookByTitleAndAuthor() {
		List<Book> booksList = bookService.findBookByTitleAndAuthor("MySQL Cookbook", "Paul DuBois");
		assertEquals(3, booksList.size());

		List<Book> booksList2 = bookService.findBookByTitleAndAuthor("Head First Java", "Kathy Sierra");
		assertEquals(1, booksList2.size());
	}

	@Test
	public void testFindBookByTitleAndYear() {
		
		List<Book> booksList = bookService.findBookByTitleAndYear("MySQL Cookbook", "2010");
		assertEquals(2, booksList.size());
		
		List<Book> booksList2 = bookService.findBookByTitleAndYear("MySQL Cookbook", "2014");
		assertEquals(1, booksList2.size());
		
	}

	@Test
	public void testFindBookByAuthorAndYear() {
		
		assertEquals(0, bookService.showAllListOfBook().size());
		
		Book book = new Book();
		book.setTitle("C# Programming in Easy Steps");
		book.setAuthor("Mike McGrath");
		book.setYear("2016");
		book.setLent(false);
		bookService.addNewBookToLibrary(book);
		Integer bookID = book.getId();
		assertNotNull(bookID);
		
		Book book1 = new Book();
		book1.setTitle("PHP 7 in easy steps");
		book1.setAuthor("Mike McGrath");
		book1.setYear("2015");
		book1.setLent(false);
		bookService.addNewBookToLibrary(book1);
		Integer book1ID = book1.getId();
		assertNotNull(book1ID);
		
		assertEquals(2, bookService.showAllListOfBook().size());
		

		List<Book> bookList  = bookService.findBookByAuthorAndYear("Mike McGrath", "2016");
		
		assertEquals(1, bookList.size());
		assertEquals("Mike McGrath", bookList.get(0).getAuthor());
		assertEquals("C# Programming in Easy Steps", bookList.get(0).getTitle());
		assertEquals("2016", bookList.get(0).getYear());
		assertEquals(false, bookList.get(0).isLent());

	}

	@Test
	public void testFindDistinctTitles() {
		
		assertEquals(5, bookService.showAllListOfBook().size());
		List<String> distTitlesList = bookService.findDistinctTitles();
		assertEquals(4, distTitlesList.size());
	}

	@Test
	public void testLentBook() {
		
		User user = new User();
		user.setName("Marcin Piskor");
		userService.addUserToLibrary(user);
		Integer userID = user.getId();
		assertNotNull(userID);
		
		List<Book> booksList = bookService.findBookBy("title", "Head First Java"); 
		assertEquals(1, booksList.size());
		assertEquals("Kathy Sierra", booksList.get(0).getAuthor());
		assertEquals("Head First Java", booksList.get(0).getTitle());
		assertEquals("2005", booksList.get(0).getYear());
		assertEquals(false, booksList.get(0).isLent());
		
		Book book = booksList.get(0);
		Integer bookID = book.getId();
		
		bookService.lentBook(bookID, user);
		
		List<Book> lentBooks = bookService.findBookBy("title", "Head First Java");
		assertEquals(1, booksList.size());
		assertEquals("Kathy Sierra", lentBooks.get(0).getAuthor());
		assertEquals("Head First Java", lentBooks.get(0).getTitle());
		assertEquals("2005", lentBooks.get(0).getYear());
		assertEquals(true, lentBooks.get(0).isLent());

	}

	@Test
	public void testShowBookDetails() {
		
		Book book = bookService.showBookDetails(2);
		
		assertEquals(new Integer(2), book.getId());
		assertEquals("PHP 7 in easy steps", book.getTitle());
		assertEquals("Mike McGrath", book.getAuthor());
		assertEquals("2015", book.getYear());
		assertEquals(false, book.isLent());
		assertEquals(null, book.getLentByUser());
		
		Book book1 = bookService.showBookDetails(3);
		
		assertEquals(new Integer(3), book1.getId());
		assertEquals("Head First Java", book1.getTitle());
		assertEquals("Kathy Sierra", book1.getAuthor());
		assertEquals("2005", book1.getYear());
		assertEquals(true, book1.isLent());
		assertEquals("Marcin Piskor", book1.getLentByUser().getName());

	}

}
