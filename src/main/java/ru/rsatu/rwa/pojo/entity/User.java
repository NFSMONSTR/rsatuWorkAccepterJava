package ru.rsatu.rwa.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Пользователь
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_gen_seq", initialValue = 1, allocationSize = 10)
    private Long id;
    private String first_name;
    private String second_name;
    private String third_name;
    @Column(unique=true)
    private String username;
    private String password;
    private Long year;
    @ColumnDefault("0")
    private Long permission_level;
    @ManyToOne
    @JoinColumn(name = "group_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;

}

