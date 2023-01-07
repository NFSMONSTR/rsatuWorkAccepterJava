package ru.rsatu.rwa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rsatu.rwa.pojo.dto.WorkDto;
import ru.rsatu.rwa.pojo.entity.Attachment;
import ru.rsatu.rwa.pojo.entity.DoneWork;
import ru.rsatu.rwa.pojo.entity.User;
import ru.rsatu.rwa.pojo.entity.Work;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
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

//    @AfterMapping
//    public void afterWorkMapping(@MappingTarget Work result, WorkDto workDto) {
//        User author = entityManager.getReference(User.class, workDto.getAuthorId());
//        result.setAuthor(author);
//    }

    List<Long> mapAttachment(List<Attachment> list) {
        ArrayList<Long> arr = new ArrayList<>();
        for (Attachment obj: list) {
            arr.add(obj.getId());
        }
        return arr;
    }

    List<Long> mapDoneWork(List<DoneWork> list) {
        ArrayList<Long> arr = new ArrayList<>();
        for (DoneWork obj: list) {
            arr.add(obj.getId());
        }
        return arr;
    }

    List<DoneWork> mapDoneWorkId(List<Long> idList) {
        ArrayList<DoneWork> arr = new ArrayList<>();
        for (Long id: idList) {
            arr.add(entityManager.getReference(DoneWork.class, id));
        }
        return arr;
    }

    List<Attachment> mapAttachmentId(List<Long> idList) {
        ArrayList<Attachment> arr = new ArrayList<>();
        for (Long id: idList) {
            arr.add(entityManager.getReference(Attachment.class, id));
        }
        return arr;
    }

    User map(Long id) {
        return entityManager.getReference(User.class, id);
    }

}
