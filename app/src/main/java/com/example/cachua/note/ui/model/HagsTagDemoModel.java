package com.example.cachua.note.ui.model;

public class HagsTagDemoModel {
    int id;
    String describe;

    public HagsTagDemoModel(String describe) {
        this.describe = describe;
    }

    public HagsTagDemoModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
