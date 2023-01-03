package ru.rsatu.rwa.pojo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentWorkDto {
    private Long id;
    private Long twork;
    private String text;
}
