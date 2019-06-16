package com.example.cachua.note.ui.model;

public class TickBoxModel {
    int idBox;
    int idNote;
    String describe;

    public TickBoxModel(int idNote, String describe) {
        this.idNote = idNote;
        this.describe = describe;
    }

    public TickBoxModel() {

    }

    public int getIdBox() {
        return idBox;
    }

    public void setIdBox(int idBox) {
        this.idBox = idBox;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
