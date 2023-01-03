package ru.rsatu.rwa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rsatu.rwa.pojo.dto.GroupDto;
import ru.rsatu.rwa.pojo.entity.Group;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Mapper(componentModel = "cdi")
public abstract class GroupMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    public abstract GroupDto toGroupDto(Group group);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    public abstract Group toGroup(GroupDto groupDto);

}
