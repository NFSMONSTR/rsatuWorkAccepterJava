package ru.rsatu.rwa.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Книга
 */
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_id_gen")
    @SequenceGenerator(name = "books_id_gen", sequenceName = "books_id_gen_seq", initialValue = 1, allocationSize = 10)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Author author;

}
