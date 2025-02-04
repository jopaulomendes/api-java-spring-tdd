package br.com.jopaulo.api.java.spring.tdd.controller;

import br.com.jopaulo.api.java.spring.tdd.domain.Book;
import br.com.jopaulo.api.java.spring.tdd.exception.BookNotFoundException;
import br.com.jopaulo.api.java.spring.tdd.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> listAllBooks(){
        return bookService.listAllBooks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Book> getBookById(@PathVariable(value = "id") Long id) throws BookNotFoundException {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> updateBookById(@PathVariable(value = "id") Long id, @RequestBody Book book){
        return bookService.updateBookById(id, book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteBookById(@PathVariable(value = "id")Long id){
        return bookService.deleteBookById(id);
    }

    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> listBooksThatStartsWithPartialName(@RequestParam String name){
        return bookService.listBooksThatStartsWithPartialName(name);
    }
}
