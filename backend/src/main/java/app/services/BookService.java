package app.services;

import app.domain.Book;
import app.domain.Genre;
import app.repository.BookRepository;

public class BookService {
    BookRepository bookRepository;

    public Book createBook(Book book) {
            return bookRepository.saveAndFlush(book);
    }

    public void deleteBook(int id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
    }
}
