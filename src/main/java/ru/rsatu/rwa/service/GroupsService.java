package ru.rsatu.rwa.service;

import ru.rsatu.rwa.mapper.GroupMapper;
import ru.rsatu.rwa.pojo.dto.GroupDto;
import ru.rsatu.rwa.repository.GroupsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Сервис для работы с группами
 */
@ApplicationScoped
public class GroupsService {

    @Inject
    GroupMapper groupMapper;

    @Inject
    GroupsRepository groupsRepository;

    /**
     * Получить все группы
     */
    public List<GroupDto> getGroups() {
        return groupsRepository.getGroups()
                .stream()
                .map(groupMapper::toGroupDto)
                .toList();
    }

    /**
     * Получить группу по id
     */

    public GroupDto getGroup(Long groupId) {
        return groupMapper.toGroupDto(groupsRepository.getGroup(groupId));
    }

    /**
     * Сохранить группу
     */
    public GroupDto saveGroup(GroupDto groupDto) {
        return groupMapper.toGroupDto(groupsRepository.saveGroup(groupDto));
    }

    /**
     * Удалить группу
     */
    public void deleteGroup(Long groupId) {
        groupsRepository.deleteGroup(groupId);
    }
}
