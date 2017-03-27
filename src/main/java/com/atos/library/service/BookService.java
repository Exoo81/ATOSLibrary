package com.atos.library.service;

import java.util.List;

import com.atos.library.entity.Book;
import com.atos.library.entity.User;

public interface BookService {
	
	public void addNewBookToLibrary(Book book);
	
	public void removeBookFromLibrary(Integer id);
	
	public List<Book> findBookBy(String searchBy, String searchWord);
	
	public List<Book> findBookByTitleAndAuthor(String title, String author);
	
	public List<Book> findBookByTitleAndYear(String title, String year);
	
	public List<Book> findBookByAuthorAndYear(String author, String year);
	
	public List<String> findDistinctTitles();
	
	public void lentBook (Integer id, User user);
	
	public Book showBookDetails(Integer id);
	
	public List<Book> showAllListOfBook();
	
	

}
