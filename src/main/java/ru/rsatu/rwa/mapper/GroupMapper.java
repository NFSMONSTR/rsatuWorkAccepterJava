package ru.rsatu.rwa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rsatu.rwa.pojo.dto.GroupDto;
import ru.rsatu.rwa.pojo.entity.Group;
import ru.rsatu.rwa.pojo.entity.Work;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "cdi")
public abstract class GroupMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    public abstract GroupDto toGroupDto(Group group);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    public abstract Group toGroup(GroupDto groupDto);

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
