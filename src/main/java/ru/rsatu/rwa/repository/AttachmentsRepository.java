package ru.rsatu.rwa.repository;

import ru.rsatu.rwa.mapper.AttachmentMapper;
import ru.rsatu.rwa.pojo.dto.AttachmentDto;
import ru.rsatu.rwa.pojo.entity.Attachment;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с прикрепленными файлами
 */
@ApplicationScoped
public class AttachmentsRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    AttachmentMapper attachmentMapper;

    /**
     * Получить все прикрепленные документы
     */
    public List<Attachment> getAttachments() {
        return entityManager.createQuery("select a from Attachment a", Attachment.class)
                .getResultList();
    }

    /**
     * Получить прикрепленный документ по id
     */

    public Attachment getAttachment(Long attachmentId) {
        return entityManager.find(Attachment.class, attachmentId);
    }

    /**
     * Сохранение прикрепленного документа
     */
    @Transactional
    public Attachment saveAttachment(AttachmentDto attachmentDto) {
        Attachment attachment = attachmentMapper.toAttachment(attachmentDto);
        if (attachment.getId() != null) {
            entityManager.merge(attachment);
        } else {
            entityManager.persist(attachment);
        }
        entityManager.flush();
        return attachment;
    }

    /**
     * Удаление прикрепленного документа
     */
    @Transactional
    public void deleteAttachment(Long attachmentId) {
        Attachment attachment = entityManager.find(Attachment.class, attachmentId);
        entityManager.remove(attachment);
    }
}
