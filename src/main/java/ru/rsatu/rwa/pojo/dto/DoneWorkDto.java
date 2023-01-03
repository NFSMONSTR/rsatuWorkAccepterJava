package ru.rsatu.rwa.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoneWorkDto {
    private Long id;
    private Long author;
    private Long work;
    private Long mark;
    private List<Long> try_works;
}
