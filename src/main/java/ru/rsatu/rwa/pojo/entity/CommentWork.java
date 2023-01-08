package ru.rsatu.rwa.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Getter
@Setter
@Entity
@Table(name = "comment_works")
public class CommentWork {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_works_id_gen")
    @SequenceGenerator(name = "comment_works_id_gen", sequenceName = "comment_works_id_gen_seq", initialValue = 1, allocationSize = 10)
    private Long id;
    private String text;
    private String mark;

    @OneToOne
    @JoinColumn(name = "done_work_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DoneWork dwork;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "comment_works_attachments",
            joinColumns = { @JoinColumn(name = "commentwork_id") },
            inverseJoinColumns = { @JoinColumn(name = "attachment_id") }
    )
    private Set<Attachment> attachments = new HashSet<>();

}

