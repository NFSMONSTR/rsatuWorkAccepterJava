package ru.rsatu.rwa.service;

import ru.rsatu.rwa.mapper.WorkMapper;
import ru.rsatu.rwa.pojo.dto.WorkDto;
import ru.rsatu.rwa.repository.WorksRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Сервис для работы с лаб работами
 */
@ApplicationScoped
public class WorksService {

    @Inject
    WorkMapper workMapper;

    @Inject
    WorksRepository worksRepository;

    /**
     * Получить все работы
     */
    public List<WorkDto> getWorks(Long page, Long size) {
        return worksRepository.getWorks(page, size)
                .stream()
                .map(workMapper::toWorkDto)
                .toList();
    }

    /**
     * Получить работу по id
     */

    public WorkDto getWork(Long workId) {
        return workMapper.toWorkDto(worksRepository.getWork(workId));
    }

    /**
     * Сохранить работу
     */
    public WorkDto saveWork(WorkDto workDto) {
        return workMapper.toWorkDto(worksRepository.saveWork(workDto));
    }

    /**
     * Удалить работу
     */
    public void deleteWork(Long workId) {
        worksRepository.deleteWork(workId);
    }

    /**
     * Получить username автора работы
     */

    public String getWorkAuthorUsername(Long workId) {
        return worksRepository.getWorkAuthorUsername(workId);
    }

}
