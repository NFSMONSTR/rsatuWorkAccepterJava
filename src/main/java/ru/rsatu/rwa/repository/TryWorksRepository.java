package ru.rsatu.rwa.repository;

import ru.rsatu.rwa.mapper.TryWorkMapper;
import ru.rsatu.rwa.pojo.dto.TryWorkDto;
import ru.rsatu.rwa.pojo.entity.TryWork;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с попыткой сдать лаб работу
 */
@ApplicationScoped
public class TryWorksRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    TryWorkMapper tryWorkMapper;

    /**
     * Получить все
     */
    public List<TryWork> getTryWorks(Long page, Long size) {
        return entityManager.createQuery("select t from TryWork t", TryWork.class)
                .getResultList();
    }

    /**
     * Получить по id
     */
    public TryWork getTryWork(Long tryWorkId) {
        return entityManager.find(TryWork.class, tryWorkId);
    }

    /**
     * Сохранение
     */
    @Transactional
    public TryWork saveTryWork(TryWorkDto tryWorkDto) {
        TryWork tryWork = tryWorkMapper.toTryWork(tryWorkDto);
        if (tryWork.getId() != null) {
            entityManager.merge(tryWork);
        } else {
            entityManager.persist(tryWork);
        }
        entityManager.flush();
        return tryWork;
    }

    /**
     * Удаление
     */
    @Transactional
    public void deleteTryWork(Long tryWorkId) {
        TryWork tryWork = entityManager.find(TryWork.class, tryWorkId);
        entityManager.remove(tryWork);
    }

}
