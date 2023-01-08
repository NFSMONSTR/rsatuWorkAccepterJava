package ru.rsatu.rwa.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Попытка сдачи лабораторной работы
 */
@Getter
@Setter
@Entity
@Table(name = "tryworks")
public class TryWork {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "try_works_id_gen")
    @SequenceGenerator(name = "try_works_id_gen", sequenceName = "try_works_id_gen_seq", initialValue = 1, allocationSize = 10)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "done_work_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DoneWork dwork;
    private Long version;
    private String text;
    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "tryworks_attachments",
            joinColumns = { @JoinColumn(name = "trywork_id") },
            inverseJoinColumns = { @JoinColumn(name = "attachment_id") }
    )
    private Set<Attachment> attachments = new HashSet<>();

}

