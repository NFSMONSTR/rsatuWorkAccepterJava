package ru.rsatu.rwa.service;

import ru.rsatu.rwa.mapper.AttachmentMapper;
import ru.rsatu.rwa.pojo.dto.AttachmentDto;
import ru.rsatu.rwa.repository.AttachmentsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Сервис для работы с прикрепленными документами
 */
@ApplicationScoped
public class AttachmentsService {

    @Inject
    AttachmentMapper attachmentMapper;

    @Inject
    AttachmentsRepository attachmentsRepository;

    /**
     * Получить все прикрепленные документы
     */
    public List<AttachmentDto> getAttachments(Long userId) {
        return attachmentsRepository.getAttachments(userId)
                .stream()
                .map(attachmentMapper::toAttachmentDto)
                .toList();
    }

    /**
     * Получить прикрепленный документ по id
     */

    public AttachmentDto getAttachment(Long attachmentId) {
        return attachmentMapper.toAttachmentDto(attachmentsRepository.getAttachment(attachmentId));
    }

    /**
     * Сохранить прикрепленный документ
     */
    public AttachmentDto saveAttachment(AttachmentDto attachmentDto) {
        return attachmentMapper.toAttachmentDto(attachmentsRepository.saveAttachment(attachmentDto));
    }

    /**
     * Удалить прикрепленный документ
     */
    public void deleteAttachment(Long attachmentId) {
        attachmentsRepository.deleteAttachment(attachmentId);
    }
}
