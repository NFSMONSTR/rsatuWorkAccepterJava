package ru.rsatu.rwa.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Группа(ПИМ-22)
 */
@Getter
@Setter
@Entity
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachments_id_gen")
    @SequenceGenerator(name = "attachments_id_gen", sequenceName = "attachments_id_gen_seq", initialValue = 1, allocationSize = 10)
    private Long id;
    private Boolean is_link;
    private String name;
    private String link;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @ManyToMany(mappedBy = "attachments", cascade = { CascadeType.MERGE })
    private Set<Work> works = new HashSet<>();
    @ManyToMany(mappedBy = "attachments", cascade = { CascadeType.MERGE })
    private Set<DoneWork> doneWorks = new HashSet<>();
    @ManyToMany(mappedBy = "attachments", cascade = { CascadeType.MERGE })
    private Set<CommentWork> commentWorks = new HashSet<>();
    @PreRemove
    private void removeAttachmentConnections() {
        for (Work w : works) {
            w.getAttachments().remove(this);
        }
        for (DoneWork w : doneWorks) {
            w.getAttachments().remove(this);
        }
        for (CommentWork w : commentWorks) {
            w.getAttachments().remove(this);
        }
    }

}

