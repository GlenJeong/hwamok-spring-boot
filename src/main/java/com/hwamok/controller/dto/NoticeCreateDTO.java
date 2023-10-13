package com.hwamok.controller.dto;

public class NoticeCreateDTO {

    private String title;
    private String content;

    private String original;
    private String fileName;
    private String filePath;
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

    public String getOriginal() { return original; }

    public String getFileName() { return fileName; }

    public String getFilePath() { return filePath; }

    public void uploadOriginal(String original) {
        this.original= original;
    }

    public void uploadFileName(String fileName) {
        this.fileName=fileName;
    }

    public void uploadFilePath(String filePath) {
        this.filePath=filePath;
    }
}
