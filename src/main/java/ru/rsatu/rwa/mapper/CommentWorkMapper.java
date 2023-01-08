package ru.rsatu.rwa.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.rwa.pojo.dto.CommentWorkDto;
import ru.rsatu.rwa.pojo.entity.Attachment;
import ru.rsatu.rwa.pojo.entity.CommentWork;
import ru.rsatu.rwa.pojo.entity.DoneWork;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "cdi")
public abstract class CommentWorkMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "dworkId", source = "dwork.id")
    public abstract CommentWorkDto toCommentWorkDto(CommentWork commentWork);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "text", source = "text")
    public abstract CommentWork toCommentWork(CommentWorkDto commentWorkDto);

    @AfterMapping
    public void afterCommentWorkMapping(@MappingTarget CommentWork result, CommentWorkDto commentWorkDto) {
        DoneWork dwork = entityManager.getReference(DoneWork.class, commentWorkDto.getDworkId());
        result.setDwork(dwork);
    }

    Set<Attachment> mapAttachmentId(List<Long> idList) {
        HashSet<Attachment> arr = new HashSet<>();
        if (idList != null)
            for (Long id: idList) {
                arr.add(entityManager.getReference(Attachment.class, id));
            }
        return arr;
    }

    List<Long> mapAttachment(Set<Attachment> list) {
        ArrayList<Long> arr = new ArrayList<>();
        if (list != null)
            for (Attachment obj: list) {
                arr.add(obj.getId());
            }
        return arr;
    }

}
