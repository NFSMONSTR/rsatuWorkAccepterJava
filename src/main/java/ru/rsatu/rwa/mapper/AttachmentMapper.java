package ru.rsatu.rwa.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.rwa.pojo.dto.AttachmentDto;
import ru.rsatu.rwa.pojo.entity.Attachment;
import ru.rsatu.rwa.pojo.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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

    @AfterMapping
    public void afterAttachmentMapping(@MappingTarget Attachment result, AttachmentDto attachmentDto) {
        User author = entityManager.getReference(User.class, attachmentDto.getAuthor());
        result.setAuthor(author);
    }

}