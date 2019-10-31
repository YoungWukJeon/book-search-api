package com.edu.booksearch.model.search.kakao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KakaoBookMetaDto  {
    @JsonProperty(value = "total_count")
    int totalCount;
    @JsonProperty(value = "pageable_count")
    int pageableCount;
    @JsonProperty(value = "is_end")
    boolean isEnd;
}