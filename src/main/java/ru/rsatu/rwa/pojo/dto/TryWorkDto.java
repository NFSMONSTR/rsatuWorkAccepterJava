package ru.rsatu.rwa.pojo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TryWorkDto {
    private Long id;
    private Long version;
    private Long dwork;
    private String text;
}
