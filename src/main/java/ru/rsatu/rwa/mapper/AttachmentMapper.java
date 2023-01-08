package ru.rsatu.rwa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rsatu.rwa.pojo.dto.AttachmentDto;
import ru.rsatu.rwa.pojo.entity.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "cdi")
public abstract class AttachmentMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "is_link", source = "is_link")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "link", source = "link")
    public abstract AttachmentDto toAttachmentDto(Attachment attachment);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "is_link", source = "is_link")
    @Mapping(target = "link", source = "link")
    public abstract Attachment toAttachment(AttachmentDto attachmentDto);

    User map(Long id) {
        return entityManager.getReference(User.class, id);
    }

    List<Long> mapWork(Set<Work> set) {
        ArrayList<Long> arr = new ArrayList<>();
        if (set != null)
            for (Work obj: set) {
                arr.add(obj.getId());
            }
        return arr;
    }

    Set<Work> mapWorkId(List<Long> idList) {
        HashSet<Work> set = new HashSet<>();
        if (idList != null)
            for (Long id: idList) {
                set.add(entityManager.getReference(Work.class, id));
            }
        return set;
    }

    List<Long> mapDoneWork(Set<DoneWork> set) {
        ArrayList<Long> arr = new ArrayList<>();
        if (set != null)
            for (DoneWork obj: set) {
                arr.add(obj.getId());
            }
        return arr;
    }

    Set<DoneWork> mapDoneWorkId(List<Long> idList) {
        HashSet<DoneWork> set = new HashSet<>();
        if (idList != null)
            for (Long id: idList) {
                set.add(entityManager.getReference(DoneWork.class, id));
            }
        return set;
    }

    List<Long> mapCommentWork(Set<CommentWork> set) {
        ArrayList<Long> arr = new ArrayList<>();
        if (set != null)
            for (CommentWork obj: set) {
                arr.add(obj.getId());
            }
        return arr;
    }

    Set<CommentWork> mapCommentWorkId(List<Long> idList) {
        HashSet<CommentWork> set = new HashSet<>();
        if (idList != null)
            for (Long id: idList) {
                set.add(entityManager.getReference(CommentWork.class, id));
            }
        return set;
    }


}
