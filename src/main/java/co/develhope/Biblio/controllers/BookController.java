package co.develhope.Biblio.controllers;

import co.develhope.Biblio.entities.Book;
import co.develhope.Biblio.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book bookToCreate = bookService.addBook(book);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<Book>> readAll() {
        List<Book> listToRead = bookService.getAllBooks();
        return ResponseEntity.ok().body(listToRead);
    }

    @GetMapping("/readSingle/{id}")
    public ResponseEntity<Optional<Book>> readSingle(@PathVariable Long id) {
        Optional<Book> bookToRead = bookService.getBookById(id);
        return ResponseEntity.ok().body(bookToRead);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Book>> update(@RequestBody Book book, @PathVariable Long id) {
        Optional<Book> bookToUpdate = bookService.updateBook(id, book);
        return ResponseEntity.ok().body(bookToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Book>> delete(@PathVariable Long id) {
        Optional<Book> bookToDelete = bookService.deleteBook(id);
        return ResponseEntity.ok().body(bookToDelete);
    }

    @PutMapping("/lend/{id}")
    public ResponseEntity<Optional<Book>> lend(@PathVariable Long id) {
        Optional<Book> bookTolend = bookService.lendBook(id);
        return ResponseEntity.ok().body(bookTolend);
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<Optional<Book>> returnBook(@PathVariable Long id) throws Exception {
        Optional<Book> bookToReturn = bookService.returnBook(id);
        return ResponseEntity.ok().body(bookToReturn);
    }
}