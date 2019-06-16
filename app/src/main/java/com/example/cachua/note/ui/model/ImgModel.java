package com.example.cachua.note.ui.model;

public class ImgModel {
    int id;
    int idNote;
    String path;

    public ImgModel(int idNote, String path) {
        this.idNote = idNote;
        this.path = path;
    }

    public ImgModel() {

    }

    public ImgModel(int id, int idNote, String path) {
        this.id = id;
        this.idNote = idNote;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
