package ru.rsatu.rwa.pojo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageDto<T> {
    public PageDto(Long page, Long size, Long count, T data) {
        setPage(page);
        setSize(size);
        setCount(count);
        setData(data);
    }
    private Long page;
    private Long size;
    private Long count;
    private T data;
}
