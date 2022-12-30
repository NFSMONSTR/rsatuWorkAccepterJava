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
@Table(name = "year_groups")
public class YearGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "year_groups_id_gen")
    @SequenceGenerator(name = "year_groups_id_gen", sequenceName = "year_groups_id_gen_seq", initialValue = 1, allocationSize = 10)
    private Long id;
    private Long semestr;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;

}

