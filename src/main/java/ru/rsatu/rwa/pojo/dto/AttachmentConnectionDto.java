package ru.rsatu.rwa.pojo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentConnectionDto {
    private Long connectionId;
    private Long attachmentId;
    private ConnectionTypeDto connectionType;
}

