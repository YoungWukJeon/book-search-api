package com.edu.booksearch.model.search.kakao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class KakaoApiResponseDto {
    @JsonProperty(value = "documents")
    private List<KakaoBookInfoDto> documents;
    @JsonProperty(value = "meta")
    private KakaoBookMetaDto meta;
}
