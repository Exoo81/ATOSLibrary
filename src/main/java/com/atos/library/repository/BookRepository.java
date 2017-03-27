package com.atos.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atos.library.entity.Book;


public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByTitle(String title);

	List<Book> findByAuthor(String searchWord);

	List<Book> findByYear(String searchWord);

	List<Book> findByTitleAndAuthor(String title, String author);

	List<Book> findByTitleAndYear(String title, String year);

	List<Book> findByAuthorAndYear(String author, String year);

	@Query("select distinct b.title from Book b")
	List<String> findDistinctByTitle();

	@Query("select count(b.title) from Book b where b.title = ?1")
	int countAllTitles(String t);

	@Query("select count(b.title) from Book b where b.title = ?1 and b.isLent = false")
	int countAvailableTitles(String title);

	

}
