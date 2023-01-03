package ru.rsatu.rwa.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.rwa.pojo.dto.TryWorkDto;
import ru.rsatu.rwa.pojo.entity.DoneWork;
import ru.rsatu.rwa.pojo.entity.TryWork;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Mapper(componentModel = "cdi")
public abstract class TryWorkMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "dwork", source = "dwork.id")
    @Mapping(target = "version", source = "version")
    @Mapping(target = "text", source = "text")
    public abstract TryWorkDto toTryWorkDto(TryWork tryWork);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "version", source = "version")
    @Mapping(target = "text", source = "text")
    public abstract TryWork toTryWork(TryWorkDto tryWorkDto);

    @AfterMapping
    public void afterTryWorkMapping(@MappingTarget TryWork result, TryWorkDto tryWorkDto) {
        DoneWork doneWork = entityManager.getReference(DoneWork.class, tryWorkDto.getDwork());
        result.setDwork(doneWork);
    }

}
