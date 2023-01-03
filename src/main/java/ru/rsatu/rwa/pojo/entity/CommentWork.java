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
@Table(name = "commentworks")
public class CommentWork {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentworks_id_gen")
    @SequenceGenerator(name = "commentworks_id_gen", sequenceName = "commentworks_id_gen_seq", initialValue = 1, allocationSize = 10)
    private Long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "try_work_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TryWork twork;

}

