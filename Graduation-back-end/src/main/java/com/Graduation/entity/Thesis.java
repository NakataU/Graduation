package com.Graduation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

//Дипломната теза има заглавие, текст и се пази датата на нейното качване в системата.
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Thesis extends BaseEntity{

    private String title;
    private String text;
    private LocalDate uploadDate;
    @OneToOne
    private ApplicationDocument applicationDocument;

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ApplicationDocument getApplicationDocument() {
        return applicationDocument;
    }

    public void setApplicationDocument(ApplicationDocument applicationDocument) {
        this.applicationDocument = applicationDocument;
    }

    public String getText() {
        return text;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }
}
