package co.develhope.Biblio;

import co.develhope.Biblio.entities.Book;
import co.develhope.Biblio.enums.DisponibilitaEnum;
import co.develhope.Biblio.repositories.BookRepository;
import co.develhope.Biblio.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BiblioApplicationTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddBook() {
        Book bookToAdd = new Book(1L, "Test Book", "Test Author", 2022, DisponibilitaEnum.DISPONIBILE, 0);
        when(bookRepository.save(bookToAdd)).thenReturn(bookToAdd);

        Book addedBook = bookService.addBook(bookToAdd);

        assertEquals(bookToAdd, addedBook);
        verify(bookRepository, times(1)).save(bookToAdd);
    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book(1L, "Book 1", "Author 1", 2000, DisponibilitaEnum.DISPONIBILE, 0);
        Book book2 = new Book(2L, "Book 2", "Author 2", 2005, DisponibilitaEnum.PRESTATO, 10);
        List<Book> expectedBooks = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<Book> allBooks = bookService.getAllBooks();

        assertEquals(expectedBooks, allBooks);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookById() {
        Long id = 1L;
        Book bookById = new Book(id, "Test Book", "Test Author", 2022, DisponibilitaEnum.DISPONIBILE, 0);
        when(bookRepository.findById(id)).thenReturn(Optional.of(bookById));

        Optional<Book> foundBook = bookService.getBookById(id);

        assertTrue(foundBook.isPresent());
        assertEquals(bookById, foundBook.get());
        verify(bookRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteBook() {
        Long id = 1L;
        Book bookToDelete = new Book(id, "Test Book", "Test Author", 2022, DisponibilitaEnum.DISPONIBILE, 0);
        when(bookRepository.findById(id)).thenReturn(Optional.of(bookToDelete));

        Optional<Book> result = bookService.deleteBook(id);

        assertTrue(result.isPresent());
        assertEquals(bookToDelete, result.get());
        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).delete(bookToDelete);
    }

    @Test
    void testLendBook() {
        Long id = 1L;
        Book book = new Book(id, "Test Book", "Test Author", 2022, DisponibilitaEnum.DISPONIBILE, 0);
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.lendBook(id);

        assertTrue(result.isPresent());
        assertEquals(DisponibilitaEnum.PRESTATO, result.get().getDisponibilitaEnum());
        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testReturnBook() throws Exception {
        Long id = 1L;
        Book lentBook = new Book(id, "Test Book", "Test Author", 2022, DisponibilitaEnum.PRESTATO, 0);
        when(bookRepository.findById(id)).thenReturn(Optional.of(lentBook));

        Optional<Book> result = bookService.returnBook(id);

        assertTrue(result.isPresent());
        assertEquals(DisponibilitaEnum.DISPONIBILE, result.get().getDisponibilitaEnum());
        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).save(lentBook);
    }
}
