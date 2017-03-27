package com.atos.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atos.library.entity.Book;
import com.atos.library.entity.User;
import com.atos.library.repository.BookRepository;
import com.atos.library.repository.UserRepository;

@Service
public class BookServiceImpl implements BookService {

	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	public void addNewBookToLibrary(Book book) {
		
		System.out.println("***************************");
		System.out.println("Add new book to library");
		bookRepository.save(book);
		System.out.println("--> Book: " + book.toString() + " has been added to library.");
	}
	
	@Transactional
	public void removeBookFromLibrary(Integer id) {
		
		System.out.println("***************************");
		System.out.println("Remove book from library");
		
		if(bookRepository.exists(id)){
			Book book = bookRepository.findOne(id);
			if(!book.isLent()){
				bookRepository.delete(book);
				System.out.println("--> Book with id " + book.getId() + " has been removed.");
			}else{
				System.out.println("--> You can't remove this book because is currently lent by " + book.getLentByUser().getName());
			}
		}else{
			System.out.println("--> The book with id: "+ id + " doesn't exist in library data base.");
		}
		
	}


	public List<Book> findBookBy(String searchBy, String searchWord) {
		
		List<Book> bookList = new ArrayList<Book>();
		
		if(searchBy.equals("title")){
			System.out.println("***************************");
			System.out.println("Find by title");
			bookList = bookRepository.findByTitle(searchWord);
			
		}
		if(searchBy == "author"){
			System.out.println("***************************");
			System.out.println("Find by author");
			bookList = bookRepository.findByAuthor(searchWord);
			
		}
		if(searchBy == "year"){
			System.out.println("***************************");
			System.out.println("Find by year");
			bookList = bookRepository.findByYear(searchWord);		
		}
		
		
		if(!bookList.isEmpty()){
			System.out.println("Search result: ");
			for(Book book : bookList){
				System.out.println("--> Book: " + book.getId() + " " + book.toString());
			}
		}else{
			System.out.println("--> NO match - 0 results.");
		}
		
		
		return bookList;
	}
	

	public List<Book> findBookByTitleAndAuthor(String title, String author) {
		List<Book> bookList = bookRepository.findByTitleAndAuthor(title, author);
		
		System.out.println("***************************");
		System.out.println("Find by title and author");
		
		if(!bookList.isEmpty()){
			System.out.println("Search result: ");
			for(Book book : bookList){
				System.out.println("--> Book: " + book.getId() + " " + book.toString());
			}
		}else{
			System.out.println("--> NO match - 0 results.");
		}
		
		return bookList;
	}


	public List<Book> findBookByTitleAndYear(String title, String year) {
		List<Book> bookList = bookRepository.findByTitleAndYear(title, year);
		
		System.out.println("***************************");
		System.out.println("Find by title and year");
		
		if(!bookList.isEmpty()){
			System.out.println("Search result: ");
			for(Book book : bookList){
				System.out.println("--> Book: " + book.getId() + " " + book.toString());
			}
		}else{
			System.out.println("--> NO match - 0 results.");
		}
		
		return bookList;
	}

	
	public List<Book> findBookByAuthorAndYear(String author, String year) {
		List<Book> bookList = bookRepository.findByAuthorAndYear(author, year);
		
		System.out.println("***************************");
		System.out.println("Find by author and year");
		
		if(!bookList.isEmpty()){
			System.out.println("Search result: ");
			for(Book book : bookList){
				System.out.println("--> Book: " + book.getId() + " " + book.toString());
			}
		}else{
			System.out.println("--> NO match - 0 results.");
		}
		
		return bookList;
	}
	

	public List<String> findDistinctTitles() {
		List<String> distTitlesList = bookRepository.findDistinctByTitle();
		
		System.out.println("***************************");
		System.out.println("Find Distinct Book");
		
		if(!distTitlesList.isEmpty()){
			for(String title : distTitlesList){
				int countAll = bookRepository.countAllTitles(title);
				int availableCount = bookRepository.countAvailableTitles(title);
				System.out.println("--> Book: " + title + " || All titles in library: " +countAll + " || Available: " +  availableCount);
			}
		}else{
			System.out.println("--> NO match - 0 results.");
		}
		
		return distTitlesList;
	}
	

	@Transactional
	public void lentBook(Integer id, User user) {
			
		System.out.println("***************************");
		System.out.println("Lent a book");
		
		if(bookRepository.exists(id)){
			Book book = bookRepository.findOne(id);
			if(!book.isLent()){
				book.setLent(true);
				book.setLentByUser(user);
				
					User userDB = userRepository.findOne(user.getId());
					List<Book> newListOfBook = new ArrayList<Book>();
					for(Book bookFromList : userDB.getBooks()){
						newListOfBook.add(bookFromList);
					}
					newListOfBook.add(book);
					userDB.setBooks(newListOfBook);
					userRepository.save(userDB);
				
				bookRepository.save(book);
				System.out.println("--> Book " + book.getTitle() + " with ID: " + book.getId() + " has been lent by " + user.getName() +".");
			}else{
				System.out.println("--> The book with id: "+ id + " is already lent by " + book.getLentByUser().getName());
			}
			
		}else{
			System.out.println("--> The book with id: "+ id + " doesn't exist in library data base.");
		}
		
	}

	@Transactional
	public Book showBookDetails(Integer id) {
		
		System.out.println("***************************");
		System.out.println("Book deatails");
		
		if(bookRepository.exists(id)){
			Book book = bookRepository.findOne(id);
			if(book.isLent()){
				System.out.println("--> Book: " + book.toString() + " Lent by: " + book.getLentByUser().getName()  );
			}else{
				System.out.println("--> Book: " + book.toString() );
			}
			return book;
		}else{
			System.out.println("--> The book with id: "+ id + " doesn't exist in library data base.");
		}
		return null;			
	}

	
	public List<Book> showAllListOfBook() {
		
		List<Book> bookList = bookRepository.findAll();
		
		return bookList;
		
	}

}
