package ru.rsatu.rwa.pojo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentDto {
    private Long id;
    private Boolean is_link;
    private String name;
    private Long author;
    private String link;
}
