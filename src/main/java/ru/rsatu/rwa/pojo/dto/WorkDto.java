package ru.rsatu.rwa.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkDto {
    private Long id;
    private String name;
    private String short_description;
    private String description;
    private String subject;
    private Long markup;
    private Long author;
    private Long semestr;
    private List<Long> attachments;
    private List<Long> done_works;
}
