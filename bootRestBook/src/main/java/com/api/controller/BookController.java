package com.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.book;
import com.api.services.bookService;

@RestController
public class BookController {
	@Autowired
	private bookService bookService;

//get all book handler
	@GetMapping("/books")
	public ResponseEntity<List<book>> getBooks() {
		List<book> list = bookService.getAllBook();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

// get the book by id handler
	@GetMapping("/books/{id}")
	public ResponseEntity<book> getBook(@PathVariable("id") int id) {
		book book = bookService.getBookById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.of(Optional.of(book));
	}

// add book handler
	@PostMapping("/books")
	public ResponseEntity<book> addBook(@RequestBody book b) {
		book addBook = null;
		try {
			b = this.bookService.addBook(b);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

// delete book handler
	@DeleteMapping("/books/{id}")

	public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
		try {
			this.bookService.deleteBook(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

// update book handler
	@PutMapping("/books/{bookId}")
	public ResponseEntity<book> updateBook(@RequestBody book book, @PathVariable("bookId") int bookId) {
		try {
			this.bookService.updateBook(book, bookId);
			return ResponseEntity.ok().body(book);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}

}
