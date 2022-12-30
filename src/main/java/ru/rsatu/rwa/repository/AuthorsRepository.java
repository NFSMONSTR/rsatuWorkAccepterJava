package ru.rsatu.rwa.repository;

import ru.rsatu.rwa.mapper.AuthorMapper;
import ru.rsatu.rwa.pojo.dto.AuthorDto;
import ru.rsatu.rwa.pojo.entity.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с авторами
 */
@ApplicationScoped
public class AuthorsRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    AuthorMapper authorMapper;

    /**
     * Получить всех авторов
     */
    public List<Author> getAuthors() {
        return entityManager.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    /**
     * Сохранение автора
     */
    @Transactional
    public Author saveAuthor(AuthorDto authorDto) {
        Author author = authorMapper.toAuthor(authorDto);
        if (author.getId() != null) {
            entityManager.merge(author);
        } else {
            entityManager.persist(author);
        }
        entityManager.flush();
        return author;
    }

    /**
     * Удаление автора
     */
    @Transactional
    public void deleteAuthor(Long authorId) {
        Author author = entityManager.find(Author.class, authorId);
        entityManager.remove(author);
    }

}
