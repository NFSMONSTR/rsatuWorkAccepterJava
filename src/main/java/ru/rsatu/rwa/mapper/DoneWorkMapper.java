package ru.rsatu.rwa.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.rwa.pojo.dto.DoneWorkDto;
import ru.rsatu.rwa.pojo.entity.DoneWork;
import ru.rsatu.rwa.pojo.entity.User;
import ru.rsatu.rwa.pojo.entity.Work;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Mapper(componentModel = "cdi")
public abstract class DoneWorkMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "work", source = "work.id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "mark", source = "mark")
    public abstract DoneWorkDto toDoneWorkDto(DoneWork doneWork);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "mark", source = "mark")
    public abstract DoneWork toDoneWork(DoneWorkDto doneWorkDto);

    @AfterMapping
    public void afterDoneWorkMapping(@MappingTarget DoneWork result, DoneWorkDto doneWorkDto) {
        User author = entityManager.getReference(User.class, doneWorkDto.getAuthor());
        result.setAuthor(author);
        Work work = entityManager.getReference(Work.class, doneWorkDto.getWork());
        result.setWork(work);
    }

}
