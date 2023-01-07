package ru.rsatu.rwa.repository;

import ru.rsatu.rwa.mapper.GroupMapper;
import ru.rsatu.rwa.pojo.dto.GroupDto;
import ru.rsatu.rwa.pojo.entity.Group;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с группами
 */
@ApplicationScoped
public class GroupsRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    GroupMapper groupMapper;

    /**
     * Получить все группы
     */
    public List<Group> getGroups() {
        return entityManager.createQuery("select g from Group g", Group.class)
                .getResultList();
    }

    /**
     * Получить группу по id
     */

    public Group getGroup(Long groupId) {
        return entityManager.find(Group.class, groupId);
    }

    /**
     * Сохранение группы
     */
    @Transactional
    public Group saveGroup(GroupDto groupDto) {
        Group group = groupMapper.toGroup(groupDto);
        if (group.getId() != null) {
            entityManager.merge(group);
        } else {
            entityManager.persist(group);
        }
        entityManager.flush();
        return group;
    }

    /**
     * Удаление группы
     */
    @Transactional
    public void deleteGroup(Long groupId) {
        Group group = entityManager.find(Group.class, groupId);
        entityManager.remove(group);
    }
}
