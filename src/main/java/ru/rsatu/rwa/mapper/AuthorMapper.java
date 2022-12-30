package ru.rsatu.rwa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rsatu.rwa.pojo.dto.AuthorDto;
import ru.rsatu.rwa.pojo.entity.Author;

@Mapper(componentModel = "cdi")
public abstract class AuthorMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd.MM.yyyy")
    @Mapping(target = "nickName", source = "nickName")
    public abstract AuthorDto toAuthorDto(Author author);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd.MM.yyyy")
    @Mapping(target = "nickName", source = "nickName")
    public abstract Author toAuthor(AuthorDto authorDto);
}
