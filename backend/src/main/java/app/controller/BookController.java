package app.controller;

import app.domain.Book;
import app.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book deleteBook(@Valid @RequestHeader int id) {
        return null;
    }

}
