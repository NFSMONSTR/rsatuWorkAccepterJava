package ru.rsatu.rwa.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String first_name;
    private String second_name;
    private String third_name;
    private String username;
    @JsonIgnore
    private String password;
    private Long year;
    private RoleDto role;
    private GroupDto group;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(final String password) {
        this.password = password;
    }
}
