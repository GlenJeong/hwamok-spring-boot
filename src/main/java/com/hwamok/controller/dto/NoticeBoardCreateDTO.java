package com.hwamok.controller.dto;

public class NoticeBoardCreateDTO {

    private String title;
    private String writer;
    private String password;
    private String content;

    public NoticeBoardCreateDTO(String title, String writer, String password, String content) {
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getPassword() {
        return password;
    }

    public String getContent() {
        return content;
    }
}
