package com.p2AE.pdf.dto;

import lombok.Data;

@Data
public class BoardDto {
    private final String content;
    private final String title;

    public BoardDto(String content, String title) {
        this.content = content;
        this.title = title;
    }
}
