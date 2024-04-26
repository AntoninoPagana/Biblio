package co.develhope.Biblio.services;

import co.develhope.Biblio.entities.Book;
import co.develhope.Biblio.enums.DisponibilitaEnum;
import co.develhope.Biblio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * @param book
     * @return book added
     */
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * @return book list
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * @param id
     * @return book searched by id
     */
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * @param id
     * @param book
     * @return updated book
     */
    public Optional<Book> updateBook(Long id, Book book) {
        Optional<Book> bookToReturn = bookRepository.findById(id);
        if (bookToReturn.isPresent()) {
            bookToReturn.get().setTitolo(book.getTitolo());
            bookToReturn.get().setAutore(book.getAutore());
            bookToReturn.get().setAnnoDiPubblicazione(book.getAnnoDiPubblicazione());
            bookToReturn.get().setDisponibilitaEnum(book.getDisponibilitaEnum());
            bookRepository.save(bookToReturn.get());
            return bookToReturn;
        } else {
            return Optional.of(bookToReturn.get());
        }
    }

    /**
     * @param id
     * @return deleted book by id
     */
    public Optional<Book> deleteBook(Long id) {
        Optional<Book> bookToDelete = bookRepository.findById(id);
        bookRepository.delete(bookToDelete.get());
        return bookToDelete;
    }

    /**
     * @param id
     * @return lended book
     */
    public Optional<Book> lendBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent() && book.get().getDisponibilitaEnum() == DisponibilitaEnum.DISPONIBILE) {
            if (book.get().getTimesLent() < 50) {
                book.get().incrementTimesLent();
                book.get().setDisponibilitaEnum(DisponibilitaEnum.PRESTATO);
                bookRepository.save(book.get());
            }
        }
        return book;
    }

    /**
     * @param id
     * @return lendend book returned DISPONIBILE
     * @throws Exception
     */
    public Optional<Book> returnBook(Long id) throws Exception {
        Optional<Book> libroToReturn = bookRepository.findById(id);
        if (libroToReturn.isPresent()) {
            if (libroToReturn.get().getDisponibilitaEnum() == DisponibilitaEnum.PRESTATO) {
                libroToReturn.get().setDisponibilitaEnum(DisponibilitaEnum.DISPONIBILE);
                bookRepository.save(libroToReturn.get());
            } else {
                throw new Exception("Libro non in prestito!");
            }
        }
        return libroToReturn;
    }
}