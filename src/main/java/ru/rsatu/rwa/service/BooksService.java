package ru.rsatu.rwa.service;

import ru.rsatu.rwa.mapper.BookMapper;
import ru.rsatu.rwa.pojo.dto.BookDto;
import ru.rsatu.rwa.repository.BooksRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Сервис для работы с книгами
 */
@ApplicationScoped
public class BooksService {

    @Inject
    BookMapper bookMapper;

    @Inject
    BooksRepository booksRepository;

    /**
     * Загрузить все книги
     */
    public List<BookDto> loadBookList() {
        return booksRepository.loadBooks()
                .stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    /**
     * Загрузить книги конкретного автора по id
     */

    public List<BookDto> getBooksByAuthor(Long authorId) {
        return booksRepository.getBooksByAuthor(authorId)
                .stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    /**
     * Сохранение книги
     */
    public BookDto saveBook(BookDto bookDto) {
        return bookMapper.toBookDto(booksRepository.saveBook(bookDto));
    }

    /**
     * Удаление книги
     */
    public void deleteBook(Long bookId) {
        booksRepository.deleteBook(bookId);
    }

}
