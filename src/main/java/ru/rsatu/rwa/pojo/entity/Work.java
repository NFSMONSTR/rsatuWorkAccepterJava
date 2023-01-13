package ru.rsatu.rwa.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Задание на лабораторную работу
 */
@Getter
@Setter
@Entity
@Table(name = "works")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "works_id_gen")
    @SequenceGenerator(name = "works_id_gen", sequenceName = "works_id_gen_seq", initialValue = 1, allocationSize = 10)
    private Long id;
    private String name;
    private String short_description;
    @Column(name="description", columnDefinition="TEXT")
    private String description;
    private String subject;
    private Long markup;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "works_attachments",
            joinColumns = { @JoinColumn(name = "work_id") },
            inverseJoinColumns = { @JoinColumn(name = "attachment_id") }
    )
    private Set<Attachment> attachments = new HashSet<>();
    @OneToMany(
            mappedBy = "work",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DoneWork> done_works;
    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "group_works",
            inverseJoinColumns = { @JoinColumn(name = "group_id") },
            joinColumns = { @JoinColumn(name = "work_id") }
    )
    private Set<Group> groups = new HashSet<>();

}

