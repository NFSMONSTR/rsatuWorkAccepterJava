package ru.rsatu.rwa.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TryWorkDto {
    private Long id;
    private Long version;
    private Long dworkId;
    private String text;
    private List<Long> attachments;
}
