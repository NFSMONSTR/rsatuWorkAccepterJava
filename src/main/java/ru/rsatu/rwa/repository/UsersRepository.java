package ru.rsatu.rwa.repository;

import org.apache.commons.codec.digest.DigestUtils;
import ru.rsatu.rwa.mapper.UserMapper;
import ru.rsatu.rwa.pojo.dto.UserDto;
import ru.rsatu.rwa.pojo.entity.Group;
import ru.rsatu.rwa.pojo.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с пользователями
 */
@ApplicationScoped
public class UsersRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    UserMapper userMapper;

    /**
     * Найти пользователя по username
     */

    public User getUserByName(String username) {
        return entityManager.createQuery("select u from User u where username = :username", User.class).setParameter("username",username).getSingleResult();
    }

    /**
     * Получить всех пользователей
     */
    public List<User> getUsers(Long page, Long size) {
        long firstResult = (page-1)*size;
        return entityManager.createQuery("select u from User u", User.class).setFirstResult((int) firstResult).setMaxResults(Math.toIntExact(size))
                .getResultList();
    }

    /**
     * Сохранение пользователя
     */
    @Transactional
    public User saveUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        if (user.getPassword() != null) {
            user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        }
        if (user.getId() != null) {
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
        entityManager.flush();
        return user;
    }

    /**
     * Удаление пользователя
     */
    @Transactional
    public void deleteUser(Long userId) {
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
    }

    public User getUser(Long userId) {
        return entityManager.find(User.class, userId);
    }

    public Long getCount(Long size) {
        Long count = entityManager.createQuery("select count(*) from User u", Long.class)
                .getSingleResult();
        return Math.round(Math.ceil((double) count/size));
    }

    @Transactional
    public void addToGroup(Long userId, Long groupId) {
        User u = entityManager.find(User.class,userId);
        Group g = entityManager.find(Group.class,groupId);
        u.setGroup(g);
        entityManager.flush();
    }
}
