package com.hwamok.controller.dto;

import org.yaml.snakeyaml.scanner.ScannerImpl;

public class BoardCreateDTO {

    private String title;
    private String content;

    public BoardCreateDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
