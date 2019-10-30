package com.edu.booksearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class KakaoApiResponseDto {
    @JsonFormat(pattern = "documents")
    private List<KakaoBookInfoDto> documents;
    @JsonFormat(pattern = "meta")
    private KakaoBookMetaDto meta;
}
