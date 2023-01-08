package ru.rsatu.rwa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rsatu.rwa.pojo.dto.WorkDto;
import ru.rsatu.rwa.pojo.entity.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public abstract WorkDto toWorkDto(Work work);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "short_description", source = "short_description")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "markup", source = "markup")
    public abstract Work toWork(WorkDto workDto);

    List<Long> mapAttachment(Set<Attachment> list) {
        ArrayList<Long> arr = new ArrayList<>();
        if (list != null)
            for (Attachment obj: list) {
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

    List<DoneWork> mapDoneWorkId(List<Long> idList) {
        ArrayList<DoneWork> arr = new ArrayList<>();
        if (idList != null)
            for (Long id: idList) {
                arr.add(entityManager.getReference(DoneWork.class, id));
            }
        return arr;
    }

    Set<Attachment> mapAttachmentId(List<Long> idList) {
        HashSet<Attachment> arr = new HashSet<>();
        if (idList != null)
            for (Long id: idList) {
                arr.add(entityManager.getReference(Attachment.class, id));
            }
        return arr;
    }

    User map(Long id) {
        return entityManager.getReference(User.class, id);
    }

    List<Long> mapGroup(Set<Group> set) {
        ArrayList<Long> arr = new ArrayList<>();
        if (set != null)
            for (Group obj: set) {
                arr.add(obj.getId());
            }
        return arr;
    }

    Set<Group> mapGroupId(List<Long> idList) {
        HashSet<Group> set = new HashSet<>();
        if (idList != null)
            for (Long id: idList) {
                set.add(entityManager.getReference(Group.class, id));
            }
        return set;
    }

}
