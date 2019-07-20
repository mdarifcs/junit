package com.example.test.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

@RestController
@RequestMapping("/hello")
public class MainController {
	
	@GetMapping
	public String helloWorld() {
		return "hello World";
	}
	
	@GetMapping(value="/json",produces=MediaType.APPLICATION_JSON_VALUE)
	public Hello json() {
		return new Hello("Greetings", "Hello World");
	}
	
	private class Hello{
		private String title;
		private String value;

		public Hello(String title, String value) {
			this.title = title;
			this.value = value;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		@Override
		public String toString() {
			return "Hello [title=" + title + ", value=" + value + "]";
		}
	}
	
}
