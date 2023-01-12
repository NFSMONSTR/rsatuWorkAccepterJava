package ru.rsatu.rwa.repository;

import ru.rsatu.rwa.mapper.CommentWorkMapper;
import ru.rsatu.rwa.pojo.dto.CommentWorkDto;
import ru.rsatu.rwa.pojo.entity.CommentWork;
import ru.rsatu.rwa.pojo.entity.DoneWork;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с ответом на лаб работу
 */
@ApplicationScoped
public class CommentWorksRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    CommentWorkMapper commentWorkMapper;

    /**
     * Получить все
     */
    public List<CommentWork> getCommentWorks() {
        return entityManager.createQuery("select c from CommentWork c", CommentWork.class)
                .getResultList();
    }

    /**
     * Получить по id
     */
    public CommentWork getCommentWork(Long commentWorkId) {
        return entityManager.find(CommentWork.class, commentWorkId);
    }

    /**
     * Сохранение
     */
    @Transactional
    public CommentWork saveCommentWork(CommentWorkDto commentWorkDto) {
        CommentWork commentWork = commentWorkMapper.toCommentWork(commentWorkDto);
        if (commentWork.getId() != null) {
            entityManager.merge(commentWork);
        } else {
            entityManager.persist(commentWork);
        }
        if (commentWork.getDwork() != null) {
            DoneWork d = commentWork.getDwork();
            d.setComment(commentWork);
            entityManager.merge(d);
        }
        entityManager.flush();
        return commentWork;
    }

    /**
     * Удаление
     */
    @Transactional
    public void deleteCommentWork(Long commentWorkId) {
        CommentWork commentWork = entityManager.find(CommentWork.class, commentWorkId);
        entityManager.remove(commentWork);
    }

}
