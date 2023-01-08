package ru.rsatu.rwa.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.rwa.pojo.dto.DoneWorkDto;
import ru.rsatu.rwa.pojo.entity.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "cdi")
public abstract class DoneWorkMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "workId", source = "work.id")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "text", source = "text")
    public abstract DoneWorkDto toDoneWorkDto(DoneWork doneWork);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "text", source = "text")
    public abstract DoneWork toDoneWork(DoneWorkDto doneWorkDto);

    @AfterMapping
    public void afterDoneWorkMapping(@MappingTarget DoneWork result, DoneWorkDto doneWorkDto) {
        User author = entityManager.getReference(User.class, doneWorkDto.getAuthorId());
        result.setAuthor(author);
        Work work = entityManager.getReference(Work.class, doneWorkDto.getWorkId());
        result.setWork(work);
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
