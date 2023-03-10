package ru.rsatu.rwa.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoneWorkDto {
    private Long id;
    private Long authorId;
    private Long workId;
    private String text;
    @JsonProperty
    private CommentWorkDto comment;
    private List<Long> attachments;

    @JsonIgnore
    void setComment(CommentWorkDto comment) {
        this.comment = comment;
    }

    @JsonProperty
    CommentWorkDto getComment() {
        return comment;
    }
}
