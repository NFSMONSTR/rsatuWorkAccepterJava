package ru.rsatu.rwa.repository;

import ru.rsatu.rwa.mapper.DoneWorkMapper;
import ru.rsatu.rwa.pojo.dto.DoneWorkDto;
import ru.rsatu.rwa.pojo.entity.DoneWork;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

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
    public Map.Entry<Long,List<DoneWork>> getDoneWorks(Long page, Long size, Boolean rated) {
        String sql = "from DoneWork d";
        if (rated!=null) {
            if (rated) {
                sql = sql.concat(" where d.comment is NOT NULL");
            } else {
                sql = sql.concat(" where d.comment is NULL");
            }
        }
        long firstResult = (page-1)*size;
        List<DoneWork> doneWorks = entityManager.createQuery("select d ".concat(sql), DoneWork.class).setFirstResult((int) firstResult).setMaxResults(Math.toIntExact(size))
                .getResultList();
        Long count = entityManager.createQuery("select count(*) ".concat(sql),Long.class).getSingleResult();
        return Map.entry(Math.round(Math.ceil((double) count/size)), doneWorks);
    }

    public Map.Entry<Long,List<DoneWork>> getDoneWorksByUser(Long userId, Long page, Long size, Boolean rated) {
        long firstResult = (page-1)*size;
        String sql = "from DoneWork d where d.author.id = :userId";
        if (rated!=null) {
            if (rated) {
                sql = sql.concat(" and d.comment is not null");
            } else {
                sql = sql.concat(" and d.comment is null");
            }
        }
        List<DoneWork> doneWorks = entityManager.createQuery("select d ".concat(sql), DoneWork.class).setParameter("userId", userId).setFirstResult((int) firstResult).setMaxResults(Math.toIntExact(size))
                .getResultList();
        Long count = entityManager.createQuery("select count(*) ".concat(sql), Long.class).setParameter("userId", userId)
                .getSingleResult();
        return Map.entry(Math.round(Math.ceil((double) count/size)), doneWorks);
    }

    public Map.Entry<Long,List<DoneWork>> getDoneWorksByTeacher(Long userId, Long page, Long size, Boolean rated) {
        long firstResult = (page-1)*size;
        String sql = "from DoneWork d where d.work.author.id = :userId";
        if (rated!=null) {
            if (rated) {
                sql = sql.concat(" and d.comment is not null");
            } else {
                sql = sql.concat(" and d.comment is null");
            }
        }
        List<DoneWork> doneWorks = entityManager.createQuery("select d ".concat(sql), DoneWork.class).setParameter("userId", userId).setFirstResult((int) firstResult).setMaxResults(Math.toIntExact(size))
                .getResultList();
        Long count = entityManager.createQuery("select count(*) ".concat(sql), Long.class).setParameter("userId", userId)
                .getSingleResult();
        return Map.entry(Math.round(Math.ceil((double) count/size)), doneWorks);
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
}
