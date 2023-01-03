package ru.rsatu.rwa.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 */
@Getter
@Setter
@Entity
@Table(name = "comment_work_attachments_rel")
public class CommentWorkAttachmentRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_work_attachments_rel_id_gen")
    @SequenceGenerator(name = "comment_work_attachments_rel_id_gen", sequenceName = "comment_work_attachments_rel_id_gen_seq", initialValue = 1, allocationSize = 10)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_work_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CommentWork comment_work;

    @ManyToOne
    @JoinColumn(name = "attachment_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Attachment attachment;


}

