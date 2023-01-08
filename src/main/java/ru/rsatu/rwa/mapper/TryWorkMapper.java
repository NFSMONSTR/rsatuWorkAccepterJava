package ru.rsatu.rwa.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.rwa.pojo.dto.TryWorkDto;
import ru.rsatu.rwa.pojo.entity.Attachment;
import ru.rsatu.rwa.pojo.entity.DoneWork;
import ru.rsatu.rwa.pojo.entity.TryWork;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "cdi")
public abstract class TryWorkMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "dworkId", source = "dwork.id")
    @Mapping(target = "version", source = "version")
    @Mapping(target = "text", source = "text")
    public abstract TryWorkDto toTryWorkDto(TryWork tryWork);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "version", source = "version")
    @Mapping(target = "text", source = "text")
    public abstract TryWork toTryWork(TryWorkDto tryWorkDto);

    @AfterMapping
    public void afterTryWorkMapping(@MappingTarget TryWork result, TryWorkDto tryWorkDto) {
        DoneWork doneWork = entityManager.getReference(DoneWork.class, tryWorkDto.getDworkId());
        result.setDwork(doneWork);
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
