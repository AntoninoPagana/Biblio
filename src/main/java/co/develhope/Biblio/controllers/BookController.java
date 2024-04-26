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

    /**
     * @param book
     * @return book added
     */
    @PostMapping("/create")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book bookToCreate = bookService.addBook(book);
        return ResponseEntity.ok().body(book);
    }

    /**
     * @return book list
     */
    @GetMapping("/readAll")
    public ResponseEntity<List<Book>> readAll() {
        List<Book> listToRead = bookService.getAllBooks();
        return ResponseEntity.ok().body(listToRead);
    }


    /**
     * @param id
     * @return book searched by id
     */
    @GetMapping("/readSingle/{id}")
    public ResponseEntity<Optional<Book>> readSingle(@PathVariable Long id) {
        Optional<Book> bookToRead = bookService.getBookById(id);
        return ResponseEntity.ok().body(bookToRead);
    }

    /**
     * @param id
     * @param book
     * @return updated book
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Book>> update(@RequestBody Book book, @PathVariable Long id) {
        Optional<Book> bookToUpdate = bookService.updateBook(id, book);
        return ResponseEntity.ok().body(bookToUpdate);
    }

    /**
     * @param id
     * @return deleted book by id
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Book>> delete(@PathVariable Long id) {
        Optional<Book> bookToDelete = bookService.deleteBook(id);
        return ResponseEntity.ok().body(bookToDelete);
    }

    /**
     * @param id
     * @return lended book
     */
    @PutMapping("/lend/{id}")
    public ResponseEntity<Optional<Book>> lend(@PathVariable Long id) {
        Optional<Book> bookTolend = bookService.lendBook(id);
        return ResponseEntity.ok().body(bookTolend);
    }

    /**
     * @param id
     * @return lendend book returned DISPONIBILE
     * @throws Exception
     */
    @PutMapping("/return/{id}")
    public ResponseEntity<Optional<Book>> returnBook(@PathVariable Long id) throws Exception {
        Optional<Book> bookToReturn = bookService.returnBook(id);
        return ResponseEntity.ok().body(bookToReturn);
    }
}
