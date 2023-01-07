package ru.rsatu.rwa.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private String file;

    @JsonIgnore
    public String getFile() {
        return file;
    }

    @JsonProperty
    public void setFile(final String file) {
        this.file = file;
    }

}
