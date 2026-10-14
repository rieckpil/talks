package de.rieckpil.talks.book;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public Book findById(Long id) {
    return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
  }

  public Book create(BookRequest request) {
    return bookRepository.save(new Book(request.title(), request.author(), request.isbn()));
  }

  public Book update(Long id, BookRequest request) {
    Book book = findById(id);
    book.setTitle(request.title());
    book.setAuthor(request.author());
    book.setIsbn(request.isbn());
    return bookRepository.save(book);
  }

  public void delete(Long id) {
    bookRepository.delete(findById(id));
  }
}
