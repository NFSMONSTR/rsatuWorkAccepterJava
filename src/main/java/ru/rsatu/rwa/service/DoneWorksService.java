package ru.rsatu.rwa.service;

import ru.rsatu.rwa.mapper.DoneWorkFullMapper;
import ru.rsatu.rwa.mapper.DoneWorkMapper;
import ru.rsatu.rwa.pojo.dto.DoneWorkDto;
import ru.rsatu.rwa.pojo.dto.DoneWorkFullDto;
import ru.rsatu.rwa.pojo.entity.DoneWork;
import ru.rsatu.rwa.repository.DoneWorksRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * 
 */
@ApplicationScoped
public class DoneWorksService {

    @Inject
    DoneWorkMapper doneWorkMapper;

    @Inject
    DoneWorkFullMapper doneWorkFullMapper;

    @Inject
    DoneWorksRepository doneWorksRepository;

    /**
     * Получить все
     */
    public Map.Entry<Long,List<DoneWorkFullDto>> getDoneWorks(Long page, Long size, Boolean rated) {
        Map.Entry<Long, List<DoneWork>> doneWorks = doneWorksRepository.getDoneWorks(page, size, rated);
        return Map.entry(doneWorks.getKey(),doneWorks.getValue().stream().map(doneWorkFullMapper::toDoneWorkFullDto).toList());

    }

    public Map.Entry<Long,List<DoneWorkFullDto>> getDoneWorksByUser(Long userId, Long page, Long size, Boolean rated) {
        Map.Entry<Long, List<DoneWork>> doneWorks = doneWorksRepository.getDoneWorksByUser(userId, page, size, rated);
        return Map.entry(doneWorks.getKey(),doneWorks.getValue().stream().map(doneWorkFullMapper::toDoneWorkFullDto).toList());
    }

    public Map.Entry<Long,List<DoneWorkFullDto>> getDoneWorksByTeacher(Long userId, Long page, Long size, Boolean rated) {
        Map.Entry<Long, List<DoneWork>> doneWorks = doneWorksRepository.getDoneWorksByTeacher(userId, page, size, rated);
        return Map.entry(doneWorks.getKey(),doneWorks.getValue().stream().map(doneWorkFullMapper::toDoneWorkFullDto).toList());
    }

    /**
     * Получить по id
     */

    public DoneWorkFullDto getDoneWork(Long doneWorkId) {
        return doneWorkFullMapper.toDoneWorkFullDto(doneWorksRepository.getDoneWork(doneWorkId));
    }

    public DoneWorkDto getDoneWork_(Long doneWorkId) {
        return doneWorkMapper.toDoneWorkDto(doneWorksRepository.getDoneWork(doneWorkId));
    }

    /**
     * Сохранить
     */
    public DoneWorkDto saveDoneWork(DoneWorkDto doneWorkDto) {
        return doneWorkMapper.toDoneWorkDto(doneWorksRepository.saveDoneWork(doneWorkDto));
    }

    /**
     * Удалить
     */
    public void deleteDoneWork(Long doneWorkId) {
        doneWorksRepository.deleteDoneWork(doneWorkId);
    }
}
