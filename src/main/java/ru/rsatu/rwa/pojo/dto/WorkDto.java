package ru.rsatu.rwa.pojo.dto;

import com.fasterxml.jackson.annotation.*;
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
    private List<Long> attachments;
    @JsonIgnore
    private List<Long> done_works;
    @JsonIgnore
    private List<Long> groups;

    @JsonProperty
    List<Long> getGroups() {
      return this.groups;
    }
}
