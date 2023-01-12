package ru.rsatu.rwa.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Сданная лабораторная работа
 */
@Getter
@Setter
@Entity
@Table(name = "done_works")
public class DoneWork {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "done_works_id_gen")
    @SequenceGenerator(name = "done_works_id_gen", sequenceName = "done_works_id_gen_seq", initialValue = 1, allocationSize = 10)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @ManyToOne
    @JoinColumn(name = "work_id")
    private Work work;
    private String text;
    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "doneworks_attachments",
            joinColumns = { @JoinColumn(name = "done_work_id") },
            inverseJoinColumns = { @JoinColumn(name = "attachment_id") }
    )
    private Set<Attachment> attachments = new HashSet<>();

    @OneToOne
    private CommentWork comment;

}

