package ru.rsatu.rwa.repository;

import ru.rsatu.rwa.mapper.BookMapper;
import ru.rsatu.rwa.pojo.dto.BookDto;
import ru.rsatu.rwa.pojo.entity.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с книгами
 */
@ApplicationScoped
public class BooksRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    BookMapper bookMapper;

    /**
     * Загрузить все книги
     */
    public List<Book> loadBooks() {
        return entityManager.createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    /**
     * Получить книги по id автора
     */
    public List<Book> getBooksByAuthor(Long authorId) {
        return entityManager.createQuery("select b from Book b where author.id = :authorId", Book.class).setParameter("authorId", authorId).getResultList();
    }

    /**
     * Сохранение книги
     */
    @Transactional
    public Book saveBook(BookDto bookDto) {
        Book book = bookMapper.toBook(bookDto);
        if (book.getId() != null) {
            entityManager.merge(book);
        } else {
            entityManager.persist(book);
        }
        entityManager.flush();
        return book;
    }

    /**
     * Удаление книги
     */
    @Transactional
    public void deleteBook(Long bookId) {
        Book book = entityManager.find(Book.class, bookId);
        entityManager.remove(book);
    }

}
