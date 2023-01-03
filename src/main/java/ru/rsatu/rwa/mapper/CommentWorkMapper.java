package ru.rsatu.rwa.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.rwa.pojo.dto.CommentWorkDto;
import ru.rsatu.rwa.pojo.entity.CommentWork;
import ru.rsatu.rwa.pojo.entity.TryWork;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Mapper(componentModel = "cdi")
public abstract class CommentWorkMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "twork", source = "twork.id")
    public abstract CommentWorkDto toCommentWorkDto(CommentWork commentWork);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "text", source = "text")
    public abstract CommentWork toCommentWork(CommentWorkDto commentWorkDto);

    @AfterMapping
    public void afterCommentWorkMapping(@MappingTarget CommentWork result, CommentWorkDto commentWorkDto) {
        TryWork twork = entityManager.getReference(TryWork.class, commentWorkDto.getTwork());
        result.setTwork(twork);
    }

}
