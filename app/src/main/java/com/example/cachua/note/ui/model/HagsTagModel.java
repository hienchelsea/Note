package com.example.cachua.note.ui.model;

public class HagsTagModel {
    int id;
    int idHagsTag;
    int idNote;
    String name;

    public HagsTagModel() {

    }

    public HagsTagModel(int idHagsTag, int idNote, String name) {
        this.idHagsTag = idHagsTag;
        this.idNote = idNote;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHagsTag() {
        return idHagsTag;
    }

    public void setIdHagsTag(int idHagsTag) {
        this.idHagsTag = idHagsTag;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
