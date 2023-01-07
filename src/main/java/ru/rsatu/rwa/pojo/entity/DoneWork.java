package ru.rsatu.rwa.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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
    @ColumnDefault("-1")
    private Long mark;

}

