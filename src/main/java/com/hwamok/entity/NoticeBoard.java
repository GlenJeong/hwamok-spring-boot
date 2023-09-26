package com.hwamok.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NoticeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String writer;
    private String password;
    private String content;

    public NoticeBoard() {

    }
    public NoticeBoard(String title, String writer, String password, String content) {
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

