package com.atos.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atos.library.entity.Book;


@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String addBook(Book book){
		
		return "/WEB-INF/jsp/index.jsp";
	}
}
