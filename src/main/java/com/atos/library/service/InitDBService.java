package com.atos.library.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.library.entity.Book;
import com.atos.library.entity.User;


@Service
public class InitDBService {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void initDB(){
		createData();				//uncomment to initiate some data in DB
	}
	private void createData(){

		for(int i =1; i<5; i++){
			Book book = new Book();
			book.setTitle("Pan Tadeusz");
			book.setYear("2010");
			book.setAuthor("Adam Mickiewicz");
			book.setLent(false);
			bookService.addNewBookToLibrary(book);
			
			Book book1 = new Book();
			book1.setTitle("Pan Tadeusz");
			book1.setYear("1981");
			book1.setAuthor("Adam Mickiewicz");
			book1.setLent(false);
			bookService.addNewBookToLibrary(book1);
			
			Book book2 = new Book();
			book2.setTitle("Ogniem i mieczem");
			book2.setYear("1993");
			book2.setAuthor("Henryk Sienkiewicz");
			book2.setLent(false);
			bookService.addNewBookToLibrary(book2);
		}
		
		Book book3 = new Book();
		book3.setTitle("Ogniem i mieczem");
		book3.setYear("2010");
		book3.setAuthor("Henryk Sienkiewicz");
		book3.setLent(true);
		
		
		User user = new User();
		user.setName("Marcin Piskor");
			List<Book> lendBooksList = new ArrayList<Book>();
		lendBooksList.add(book3);
		user.setBooks(lendBooksList);
		userService.addUserToLibrary(user);
		
		book3.setLentByUser(user);
		bookService.addNewBookToLibrary(book3);
		
		
		bookService.removeBookFromLibrary(35);		//book not exist
		bookService.removeBookFromLibrary(14);		//book currently not available
		bookService.removeBookFromLibrary(3);		//remove book
		
		bookService.showAllListOfBook();
		
		//bookService.showAllBooksDISC();
		bookService.findBookBy("title" , "Pan Tadeusz"); 	//find by title
		bookService.findBookBy("author" , "Henryk Sienkiewicz");	//find by author
		bookService.findBookBy("year" , "2010");					// find by year
		bookService.findBookBy("title" , "2010");					// no match
		
		bookService.findBookByTitleAndAuthor("Pan Tadeusz" , "Adam Mickiewicz");	// search by title and author
		bookService.findBookByTitleAndAuthor("Pan Tadeusz" , "Adam Mickiewicsdsd");	// no match
		
		bookService.findBookByTitleAndYear("Pan Tadeusz" , "1981");	// search by title and year
		bookService.findBookByTitleAndYear("Pan Tadeusz" , "1980");	// no match
		
		bookService.findBookByAuthorAndYear("Adam Mickiewicz" , "2010");	// search by author and year
		bookService.findBookByAuthorAndYear("Adam Mickiewicz" , "1980");	// no match
		
		bookService.findDistinctTitles();
		
		bookService.lentBook(35, user);  		// book not exist
		bookService.lentBook(14, user);			// book currently not available
		bookService.lentBook(1, user);			// lent a book
		
		bookService.showBookDetails(35);		// book not exist
		bookService.showBookDetails(2);		// book avaible
		bookService.showBookDetails(14);		// book lent
	}


}
