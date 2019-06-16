package com.example.cachua.note.ui.model;

public class RecModel {
    int id;
    int idNote;
    int countTime;
    String path;

    public RecModel() {

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

    public int getCountTime() {
        return countTime;
    }

    public void setCountTime(int countTime) {
        this.countTime = countTime;
    }

    public RecModel(int idNote, int countTime, String path) {
        this.idNote = idNote;
        this.countTime = countTime;
        this.path = path;
    }
}
