package ru.rsatu.rwa.repository;

import org.apache.commons.codec.digest.DigestUtils;
import ru.rsatu.rwa.mapper.UserMapper;
import ru.rsatu.rwa.pojo.dto.UserDto;
import ru.rsatu.rwa.pojo.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class)
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
}
