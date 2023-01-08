package ru.rsatu.rwa.service;

import ru.rsatu.rwa.mapper.DoneWorkFullMapper;
import ru.rsatu.rwa.mapper.DoneWorkMapper;
import ru.rsatu.rwa.pojo.dto.DoneWorkDto;
import ru.rsatu.rwa.pojo.dto.DoneWorkFullDto;
import ru.rsatu.rwa.repository.DoneWorksRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

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
    public List<DoneWorkFullDto> getDoneWorks(Long page, Long size) {
        return doneWorksRepository.getDoneWorks(page, size)
                .stream()
                .map(doneWorkFullMapper::toDoneWorkFullDto)
                .toList();
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

    public Long getCount(Long size) {
        return doneWorksRepository.getCount(size);
    }
}
