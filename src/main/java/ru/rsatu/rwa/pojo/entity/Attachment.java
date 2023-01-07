package ru.rsatu.rwa.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "work_id")
    private User work;

}

