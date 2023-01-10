package ru.rsatu.rwa.repository;

import ru.rsatu.rwa.mapper.WorkMapper;
import ru.rsatu.rwa.pojo.dto.WorkDto;
import ru.rsatu.rwa.pojo.entity.Group;
import ru.rsatu.rwa.pojo.entity.User;
import ru.rsatu.rwa.pojo.entity.Work;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        long firstResult = (page-1)*size;
        return entityManager.createQuery("select w from Work w", Work.class).setFirstResult((int) firstResult).setMaxResults(Math.toIntExact(size))
                .getResultList();
    }

    public Map.Entry<Long, List<Work>> getWorksTeacher(Long userId, Long page, Long size) {
        long firstResult = (page-1)*size;
        List<Work> works = entityManager.createQuery("select w from Work w where w.author.id = :userId", Work.class).setParameter("userId", userId).setFirstResult((int) firstResult).setMaxResults(Math.toIntExact(size))
                .getResultList();
        Long count = entityManager.createQuery("select count(*) from Work w where w.author.id = :userId", Long.class).setParameter("userId", userId)
                .getSingleResult();
        return Map.entry(Math.round(Math.ceil((double) count/size)), works);
    }

    public Map.Entry<Long, List<Work>> getWorksUser(Long userId, Long page, Long size) {
        User u = entityManager.find(User.class, userId);
        Group g = u.getGroup();
        if (g == null) {
            return Map.entry(0L, new ArrayList<>());
        }
        long firstResult = (page-1)*size;
        List<Work> works = entityManager.createQuery("select w from Work w join w.groups g where g.id = :groupId", Work.class).setParameter("groupId", g.getId()).setFirstResult((int) firstResult).setMaxResults(Math.toIntExact(size))
                .getResultList();
        Long count = entityManager.createQuery("select count(*) from Work w join w.groups g where g.id = :groupId", Long.class).setParameter("groupId", g.getId())
                .getSingleResult();
        return Map.entry(Math.round(Math.ceil((double) count/size)), works);
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

    public Long getCount(Long size) {
        Long count = entityManager.createQuery("select count(*) from Work w", Long.class)
                .getSingleResult();
        return Math.round(Math.ceil((double) count/size));
    }

    @Transactional
    public boolean connectWork(Long userId, Long workId, Long groupId) {
        User u = entityManager.find(User.class, userId);
        Work work = entityManager.find(Work.class, workId);
        if (!Objects.equals(work.getAuthor().getId(), userId) && !u.getRole().equals("ADMIN")) {
            return false;
        }
        Group g = entityManager.find(Group.class, groupId);
        g.getWorks().add(work);
        work.getGroups().add(g);
        return true;
    }
}
