package ru.rsatu.rwa.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.rwa.pojo.dto.UserDto;
import ru.rsatu.rwa.pojo.entity.Group;
import ru.rsatu.rwa.pojo.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Mapper(componentModel = "cdi")
public abstract class UserMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "first_name", source = "first_name")
    @Mapping(target = "second_name", source = "second_name")
    @Mapping(target = "third_name", source = "third_name")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "year", source = "year")
    //@Mapping(target = "role", source = "role")
    //@Mapping(target = "group", source = "group.id")
    public abstract UserDto toUserDto(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "first_name", source = "first_name")
    @Mapping(target = "second_name", source = "second_name")
    @Mapping(target = "third_name", source = "third_name")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "year", source = "year")
    @Mapping(target = "role", source = "role")
    public abstract User toUser(UserDto userDto);


//    @AfterMapping
//    public void afterUserMapping(@MappingTarget User result, UserDto userDto) {
//        Group group = entityManager.getReference(Group.class, userDto.getGroup());
//        result.setGroup(group);
//    }

}
