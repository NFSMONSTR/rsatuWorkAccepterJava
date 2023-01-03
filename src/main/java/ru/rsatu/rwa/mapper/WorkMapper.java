package ru.rsatu.rwa.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.rwa.pojo.dto.UserDto;
import ru.rsatu.rwa.pojo.dto.WorkDto;
import ru.rsatu.rwa.pojo.entity.Group;
import ru.rsatu.rwa.pojo.entity.User;
import ru.rsatu.rwa.pojo.entity.Work;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Mapper(componentModel = "cdi")
public abstract class WorkMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "short_description", source = "short_description")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "markup", source = "markup")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "semestr", source = "semestr")
    public abstract WorkDto toWorkDto(Work work);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "short_description", source = "short_description")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "markup", source = "markup")
    @Mapping(target = "semestr", source = "semestr")
    public abstract Work toWork(WorkDto workDto);

    @AfterMapping
    public void afterWorkMapping(@MappingTarget Work result, WorkDto workDto) {
        User author = entityManager.getReference(User.class, workDto.getAuthor());
        result.setAuthor(author);
    }

}
