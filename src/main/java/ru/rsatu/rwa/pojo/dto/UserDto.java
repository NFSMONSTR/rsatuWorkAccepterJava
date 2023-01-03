package ru.rsatu.rwa.pojo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String first_name;
    private String second_name;
    private String third_name;
    private String user_name;
    private String password;
    private Long year;
    private Long permission_level;
    private Long group;
}
