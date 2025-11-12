package br.com.libraryone.service;

import br.com.libraryone.entity.Book;
import br.com.libraryone.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        if (book.getIsbn() != null && !book.getIsbn().isEmpty()) {
            if (bookRepository.existsByIsbn(book.getIsbn())) {
                throw new RuntimeException("ISBN já cadastrado: " + book.getIsbn());
            }
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setIsbn(bookDetails.getIsbn());
            book.setPublicationYear(bookDetails.getPublicationYear());

            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Livro não encontrado com ID: " + id);
        }
    }

    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new RuntimeException("Livro não encontrado com ID: " + id);
        }
    }

    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

}
