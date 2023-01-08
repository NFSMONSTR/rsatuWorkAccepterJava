package ru.rsatu.rwa.repository;

import ru.rsatu.rwa.mapper.DoneWorkMapper;
import ru.rsatu.rwa.pojo.dto.DoneWorkDto;
import ru.rsatu.rwa.pojo.entity.DoneWork;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * 
 */
@ApplicationScoped
public class DoneWorksRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    DoneWorkMapper doneWorkMapper;

    /**
     * Получить все работы
     */
    public List<DoneWork> getDoneWorks(Long page, Long size) {
        long firstResult = (page-1)*size;
        return entityManager.createQuery("select d from DoneWork d", DoneWork.class).setFirstResult((int) firstResult).setMaxResults(Math.toIntExact(size))
                .getResultList();
    }

    /**
     * Получить работу по id
     */
    public DoneWork getDoneWork(Long doneWorkId) {
        return entityManager.find(DoneWork.class, doneWorkId);
    }

    /**
     * Сохранение работы
     */
    @Transactional
    public DoneWork saveDoneWork(DoneWorkDto doneWorkDto) {
        DoneWork doneWork = doneWorkMapper.toDoneWork(doneWorkDto);
        if (doneWork.getId() != null) {
            entityManager.merge(doneWork);
        } else {
            entityManager.persist(doneWork);
        }
        entityManager.flush();
        return doneWork;
    }

    /**
     * Удаление работы
     */
    @Transactional
    public void deleteDoneWork(Long doneWorkId) {
        DoneWork doneWork = entityManager.find(DoneWork.class, doneWorkId);
        entityManager.remove(doneWork);
    }

    public String getDoneWorkAuthorUsername(Long doneWorkId) {
        DoneWork doneWork = entityManager.find(DoneWork.class, doneWorkId);
        return doneWork.getAuthor().getUsername();
    }

    public Long getCount(Long size) {
        Long count = entityManager.createQuery("select count(*) from DoneWork w", Long.class)
                .getSingleResult();
        return Math.round(Math.ceil((double) count/size));
    }
}
