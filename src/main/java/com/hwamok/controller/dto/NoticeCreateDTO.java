package com.hwamok.controller.dto;

public class NoticeCreateDTO {

    private String title;

    private String content;

    public NoticeCreateDTO(String title, String content) {
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
