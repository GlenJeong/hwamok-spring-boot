package com.hwamok.controller.dto;

import org.springframework.web.multipart.MultipartFile;

public class NoticeCreateDTO {

    private String title;
    private String content;
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

    public String getFileName() { return fileName; }

    public String getFilePath() { return filePath; }

    public void uploadFileName(String fileName) {
        this.fileName=fileName;
    }

    public void uploadFilePath(String filePath) {
        this.filePath=filePath;
    }
}
