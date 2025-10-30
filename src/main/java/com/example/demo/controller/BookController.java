package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Management System")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @Operation(summary = "View a list of available books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by Id")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }


    @PostMapping
    @Operation(summary = "Add a new book")
    public ResponseEntity<Book> createCarrera(@RequestBody Book book) {
        Book createdBook = bookService.saveBook(book);
        return ResponseEntity.status(201).body(createdBook);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing book")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            return bookService.saveBook(existingBook);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book")
    public void deleteBook(@PathVariable Long id) {
        
    }
}
