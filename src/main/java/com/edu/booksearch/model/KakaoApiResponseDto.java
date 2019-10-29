package com.edu.booksearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class KakaoApiResponseDto {
    @JsonFormat(pattern = "documents")
    private List<BookInfoDto> documents;
    @JsonFormat(pattern = "meta")
    private MetaInfo meta;

    @Data
    class MetaInfo {
        @JsonFormat(pattern = "total_count")
        int totalCount;
        @JsonFormat(pattern = "pageable_count")
        int pageableCount;
        @JsonFormat(pattern = "is_end")
        boolean isEnd;
    }
}
