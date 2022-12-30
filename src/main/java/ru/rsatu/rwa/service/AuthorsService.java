package ru.rsatu.rwa.service;

import ru.rsatu.rwa.mapper.AuthorMapper;
import ru.rsatu.rwa.pojo.dto.AuthorDto;
import ru.rsatu.rwa.repository.AuthorsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Сервис для работы с авторами
 */
@ApplicationScoped
public class AuthorsService {

    @Inject
    AuthorMapper authorMapper;

    @Inject
    AuthorsRepository authorsRepository;

    /**
     * Получить всех авторов
     */
    public List<AuthorDto> getAuthors() {
        return authorsRepository.getAuthors()
                .stream()
                .map(authorMapper::toAuthorDto)
                .toList();
    }

    /**
     * Сохранение автора
     */
    public AuthorDto saveAuthor(AuthorDto authorDto) {
        return authorMapper.toAuthorDto(authorsRepository.saveAuthor(authorDto));
    }

    /**
     * Удаление автора
     */
    public void deleteAuthor(Long authorId) {
        authorsRepository.deleteAuthor(authorId);
    }

}
