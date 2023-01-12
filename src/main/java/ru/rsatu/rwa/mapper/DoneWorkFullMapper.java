package ru.rsatu.rwa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rsatu.rwa.pojo.dto.DoneWorkFullDto;
import ru.rsatu.rwa.pojo.entity.Attachment;
import ru.rsatu.rwa.pojo.entity.DoneWork;
import ru.rsatu.rwa.pojo.entity.Group;
import ru.rsatu.rwa.pojo.entity.User;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "cdi")
public abstract class DoneWorkFullMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "commentId", source = "comment.id")
    public abstract DoneWorkFullDto toDoneWorkFullDto(DoneWork doneWork);

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

    List<Long> mapGroup(Set<Group> set) {
        ArrayList<Long> arr = new ArrayList<>();
        if (set != null)
            for (Group obj: set) {
                arr.add(obj.getId());
            }
        return arr;
    }

    List<Long> mapDoneWork(List<DoneWork> list) {
        ArrayList<Long> arr = new ArrayList<>();
        if (list != null)
            for (DoneWork obj: list) {
                arr.add(obj.getId());
            }
        return arr;
    }

    Long mapUser(User u) {
        return u.getId();
    }

}
