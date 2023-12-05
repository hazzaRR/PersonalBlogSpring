package com.hazr.personalblog.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageDTO {


    private MultipartFile file;


    private String fileName;


    protected ImageDTO() {
    }

    @Override
    public String toString() {
        return "ImageDTO{" +
                "file=" + file +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
