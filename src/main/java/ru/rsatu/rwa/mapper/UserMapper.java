package ru.rsatu.rwa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rsatu.rwa.pojo.dto.UserDto;
import ru.rsatu.rwa.pojo.entity.User;
import ru.rsatu.rwa.pojo.entity.Work;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

}
