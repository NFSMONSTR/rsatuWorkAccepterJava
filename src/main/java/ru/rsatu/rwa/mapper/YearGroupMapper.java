package ru.rsatu.rwa.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.rwa.pojo.dto.YearGroupDto;
import ru.rsatu.rwa.pojo.entity.Group;
import ru.rsatu.rwa.pojo.entity.YearGroup;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Mapper(componentModel = "cdi")
public abstract class YearGroupMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "semestr", source = "semestr")
    @Mapping(target = "group", source = "group.id")
    public abstract YearGroupDto toYearGroupDto(YearGroup yearGroup);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "semestr", source = "semestr")
    public abstract YearGroup toYearGroup(YearGroupDto yearGroupDto);

    @AfterMapping
    public void afterYearGroupMapping(@MappingTarget YearGroup result, YearGroupDto yearGroupDto) {
        Group group = entityManager.getReference(Group.class, yearGroupDto.getGroup());
        result.setGroup(group);
    }

}
