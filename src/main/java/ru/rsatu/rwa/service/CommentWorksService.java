package ru.rsatu.rwa.service;

import ru.rsatu.rwa.mapper.CommentWorkMapper;
import ru.rsatu.rwa.pojo.dto.CommentWorkDto;
import ru.rsatu.rwa.repository.CommentWorksRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * 
 */
@ApplicationScoped
public class CommentWorksService {

    @Inject
    CommentWorkMapper commentWorkMapper;

    @Inject
    CommentWorksRepository commentWorksRepository;

    /**
     * Получить все
     */
    public List<CommentWorkDto> getCommentWorks() {
        return commentWorksRepository.getCommentWorks()
                .stream()
                .map(commentWorkMapper::toCommentWorkDto)
                .toList();
    }

    /**
     * Получить по id
     */

    public CommentWorkDto getCommentWork(Long commentWorkId) {
        return commentWorkMapper.toCommentWorkDto(commentWorksRepository.getCommentWork(commentWorkId));
    }

    /**
     * Сохранить
     */
    public CommentWorkDto saveCommentWork(CommentWorkDto commentWorkDto) {
        return commentWorkMapper.toCommentWorkDto(commentWorksRepository.saveCommentWork(commentWorkDto));
    }

    /**
     * Удалить
     */
    public void deleteCommentWork(Long commentWorkId) {
        commentWorksRepository.deleteCommentWork(commentWorkId);
    }

}
