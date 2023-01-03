package ru.rsatu.rwa.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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
    private String description;
    private String subject;
    private Long markup;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;
    private Long semestr;

}

