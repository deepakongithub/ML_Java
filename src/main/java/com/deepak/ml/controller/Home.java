package com.deepak.ml.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

	

	@GetMapping("/")
	public  List<String> index() {
		List<String> list=  new ArrayList<String>() ;
		list.add("Greetings from Spring Boot!");
		
		return list;
	}
}
