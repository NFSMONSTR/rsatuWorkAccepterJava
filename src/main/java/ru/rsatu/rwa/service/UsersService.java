package ru.rsatu.rwa.service;

import ru.rsatu.rwa.mapper.UserMapper;
import ru.rsatu.rwa.pojo.dto.UserDto;
import ru.rsatu.rwa.pojo.entity.User;
import ru.rsatu.rwa.repository.UsersRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Сервис для работы с авторами
 */
@ApplicationScoped
public class UsersService {

    @Inject
    UserMapper userMapper;

    @Inject
    UsersRepository usersRepository;

    /**
     * Найти пользователя по username
     */

    public User getUserByName(String username) {
        return usersRepository.getUserByName(username);
    }

    /**
     * Получить всех авторов
     */
    public List<UserDto> getUsers(Long page, Long size) {
        return usersRepository.getUsers(page, size)
                .stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Сохранение автора
     */
    public UserDto saveUser(UserDto userDto) {
        return userMapper.toUserDto(usersRepository.saveUser(userDto));
    }

    /**
     * Удаление автора
     */
    public void deleteUser(Long userId) {
        usersRepository.deleteUser(userId);
    }

    public UserDto getUser(Long userId) {
        return userMapper.toUserDto(usersRepository.getUser(userId));
    }

    public Long getCount(Long size) {
        return usersRepository.getCount(size);
    }
}
