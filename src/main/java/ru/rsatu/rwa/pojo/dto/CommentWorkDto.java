package ru.rsatu.rwa.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CommentWorkDto {
    private Long id;
    private Long tworkId;
    private String text;
    private List<Long> attachments;
}
