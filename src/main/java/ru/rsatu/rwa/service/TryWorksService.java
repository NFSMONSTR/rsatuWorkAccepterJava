package ru.rsatu.rwa.service;

import ru.rsatu.rwa.mapper.TryWorkMapper;
import ru.rsatu.rwa.pojo.dto.TryWorkDto;
import ru.rsatu.rwa.repository.TryWorksRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Сервис для работы с попытками сдать лаб работами
 */
@ApplicationScoped
public class TryWorksService {

    @Inject
    TryWorkMapper tryWorkMapper;

    @Inject
    TryWorksRepository tryWorksRepository;

    /**
     * Получить все
     */
    public List<TryWorkDto> getTryWorks(Long page, Long size) {
        return tryWorksRepository.getTryWorks(page, size)
                .stream()
                .map(tryWorkMapper::toTryWorkDto)
                .toList();
    }

    /**
     * Получить по id
     */

    public TryWorkDto getTryWork(Long tryWorkId) {
        return tryWorkMapper.toTryWorkDto(tryWorksRepository.getTryWork(tryWorkId));
    }

    /**
     * Сохранить
     */
    public TryWorkDto saveTryWork(TryWorkDto tryWorkDto) {
        return tryWorkMapper.toTryWorkDto(tryWorksRepository.saveTryWork(tryWorkDto));
    }

    /**
     * Удалить
     */
    public void deleteTryWork(Long tryWorkId) {
        tryWorksRepository.deleteTryWork(tryWorkId);
    }

}
