package com.edu.booksearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class KakaoBookMetaDto  {
    @JsonFormat(pattern = "total_count")
    int totalCount;
    @JsonFormat(pattern = "pageable_count")
    int pageableCount;
    @JsonFormat(pattern = "is_end")
    boolean isEnd;
}