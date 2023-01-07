package ru.rsatu.rwa.repository;

import ru.rsatu.rwa.mapper.WorkMapper;
import ru.rsatu.rwa.pojo.dto.WorkDto;
import ru.rsatu.rwa.pojo.entity.Work;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с лаб. работами
 */
@ApplicationScoped
public class WorksRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    WorkMapper workMapper;

    /**
     * Получить все работы
     */
    public List<Work> getWorks(Long page, Long size) {
        return entityManager.createQuery("select w from Work w", Work.class)
                .getResultList();
    }

    /**
     * Получить работу по id
     */
    public Work getWork(Long workId) {
        return entityManager.find(Work.class, workId);
    }

    /**
     * Сохранение работы
     */
    @Transactional
    public Work saveWork(WorkDto workDto) {
        Work work = workMapper.toWork(workDto);
        if (work.getId() != null) {
            entityManager.merge(work);
        } else {
            entityManager.persist(work);
        }
        entityManager.flush();
        return work;
    }

    /**
     * Удаление работы
     */
    @Transactional
    public void deleteWork(Long workId) {
        Work work = entityManager.find(Work.class, workId);
        entityManager.remove(work);
    }

    public String getWorkAuthorUsername(Long workId) {
        Work work = entityManager.find(Work.class, workId);
        return work.getAuthor().getUsername();
    }
}
