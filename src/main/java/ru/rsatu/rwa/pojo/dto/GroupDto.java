package ru.rsatu.rwa.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupDto {
    private Long id;
    private String name;
    private List<Long> works;
}
