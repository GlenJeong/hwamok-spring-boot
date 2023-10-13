package com.hwamok.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notice extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//기본키 생성을 데이터베이스에게 위임하는 방식으로 id값을 따로 할당하지 않아도
    // 데이터베이스가 자동으로 AUTO_INCREMENT를 하여 기본키를 생성해준다.
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String name;
    private String original;
    private String fileName;
    private String filePath;




    protected Notice() {}

    public Notice(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public Notice(String title, String content, String name, Long userId, String original, String fileName, String filePath) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.userId=userId;
        this.original=original;
        this.fileName=fileName;
        this.filePath=filePath;
    }



    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    public Long getUserId() {
        return userId;
    }
    public String getUserName() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getOriginal() {
        return original;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void changeTitle(String title) {
        this.title=title;
    }

    public void changeContent(String content) {
        this.content=content;
    }

    public void changeName(String name) { this.name = name; }

    public void uploadOriginal(String original) {
        this.original=original;
    }

    public void uploadFileName(String fileName) {
        this.fileName=fileName;
    }

    public void uploadFilePath(String filePath) {
        this.filePath=filePath;
    }
}
