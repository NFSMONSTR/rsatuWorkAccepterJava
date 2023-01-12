package ru.rsatu.rwa.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoneWorkFullDto {
    private Long id;
    private Long authorId;
    private WorkDto work;
    private String text;

    private Long commentId;
    private List<Long> attachments;
}
