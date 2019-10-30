package com.edu.booksearch.model;

import lombok.Data;

@Data
public class SearchMetaDto {
    private int totalDoc;
    private int currentDoc;
    private boolean isEnd;
}
