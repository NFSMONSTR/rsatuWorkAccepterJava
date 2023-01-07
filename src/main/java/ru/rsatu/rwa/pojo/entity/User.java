package ru.rsatu.rwa.pojo.entity;

import io.quarkus.security.jpa.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.wildfly.security.password.interfaces.SimpleDigestPassword;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;

/**
 * Пользователь
 */
@Getter
@Setter
@UserDefinition
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_gen_seq", initialValue = 10, allocationSize = 10)
    private Long id;
    private String first_name;
    private String second_name;
    private String third_name;
    @Username
    @Column(unique=true)
    private String username;
    @Password(value = PasswordType.CUSTOM, provider = CustomPasswordProvider.class)
    private String password;
    private Long year;
    @ColumnDefault("user")
    @Roles
    private String role;
    @ManyToOne
    @JoinColumn(name = "group_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;
}

class CustomPasswordProvider implements PasswordProvider {
    @Override
    public org.wildfly.security.password.Password getPassword(String pass) {
        byte[] digest = DatatypeConverter.parseHexBinary(pass);
        return SimpleDigestPassword.createRaw(SimpleDigestPassword.ALGORITHM_SIMPLE_DIGEST_SHA_256, digest);
    }
}