package ru.rsatu.rwa.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Группа(по направлению)
 */
@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groups_id_gen")
    @SequenceGenerator(name = "groups_id_gen", sequenceName = "groups_id_gen_seq", initialValue = 1, allocationSize = 10)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "groups")
    private Set<Work> works = new HashSet<>();

}

