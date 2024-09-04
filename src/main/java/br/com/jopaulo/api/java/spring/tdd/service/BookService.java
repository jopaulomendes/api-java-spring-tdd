package br.com.jopaulo.api.java.spring.tdd.service;

import br.com.jopaulo.api.java.spring.tdd.domain.Book;
import br.com.jopaulo.api.java.spring.tdd.exception.BookNotFoundException;
import br.com.jopaulo.api.java.spring.tdd.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) throws BookNotFoundException {
        if (bookRepository.findById(id).isEmpty()){
            throw new BookNotFoundException(id);
        }
        return bookRepository.findById(id);
    }

    public ResponseEntity<Book> updateBookById(Long id, Book book) {
        return bookRepository.findById(id).map(x -> {
            x.setName(book.getName());
            x.setCategory(book.getCategory());
            x.setStatus(book.getStatus());
            Book updated = bookRepository.save(x);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteBookById(Long id) {
        return bookRepository.findById(id).map(x -> {
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    public List<Book> listBooksThatStartsWithPartialName(String name) {
        return bookRepository.findByNameStartingWith(name);
    }
}
