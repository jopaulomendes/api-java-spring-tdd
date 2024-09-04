package br.com.jopaulo.api.java.spring.tdd;

import br.com.jopaulo.api.java.spring.tdd.domain.Book;
import br.com.jopaulo.api.java.spring.tdd.domain.Category;
import br.com.jopaulo.api.java.spring.tdd.domain.Status;

public class BookFactory {

    public static Book createBook(){
        Book book = new Book();
        book.setId(1L);
        book.setName("Use a Cabeça Java");
        book.setCategory(Category.PROGRAMMING);
        book.setStatus(Status.NOT_STARTED);
        return book;
    }
}
