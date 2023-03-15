package com.api.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.Dao.bookRepo;
import com.api.model.book;

@Component

public class bookService {

	@Autowired
	private bookRepo bookRepo;

//	private static List<book> list = new ArrayList<>();
//	static {
//
//		list.add(new book(12, "python", "xyz"));
//		list.add(new book(21, "php", "pqr"));
//		list.add(new book(32, "java", "mhg"));
//
//	}

	// GET ALL BOOKS
	public List<book> getAllBook() {

		List<book> list = (List<book>) this.bookRepo.findAll();
		return list;
	}

	// GET SINGLE BOOK BY ID
	public book getBookById(int id) {
		book book = null;
		try {
			// book = list.stream().filter(e -> e.getId() == id).findFirst().get();
	book= this.bookRepo.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	// ADDING THE BOOK
	public book addBook(book b1) {

		// list.add(b1);
		book save = bookRepo.save(b1);
		return save;
	}

//delete the book
	public void deleteBook(int bid) {
		// list = list.stream().filter(book -> book.getId() !=
		// bid).collect(Collectors.toList());
		bookRepo.deleteById(bid);
	}

//update the book
	public void updateBook(book book, int bookId) {

//		list = list.stream().map(b -> {
//			if (b.getId() == bookId) {
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());

		book.setId(bookId);
		book save1 = bookRepo.save(book);

	}
}
