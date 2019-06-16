package com.example.cachua.note.ui.model;

public class NoteModel {
    int id;
    String name;
    String describe;
    int noteNox;
    int noteIMG;
    int noteRec;
    int tag;
    int clips;
    int alarm;
    int password;
    int color;


    public NoteModel(int id, String name, String describe, int noteNox, int noteIMG, int noteRec, int tag, int clips, int alarm, int password, int color) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.noteNox = noteNox;
        this.noteIMG = noteIMG;
        this.noteRec = noteRec;
        this.tag = tag;
        this.clips = clips;
        this.alarm = alarm;
        this.password = password;
        this.color = color;
    }

    public NoteModel(String name, String describe, int noteNox, int noteIMG, int noteRec, int tag, int clips, int alarm, int password, int color) {
        this.name = name;
        this.describe = describe;
        this.noteNox = noteNox;
        this.noteIMG = noteIMG;
        this.noteRec = noteRec;
        this.tag = tag;
        this.clips = clips;
        this.alarm = alarm;
        this.password = password;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public NoteModel() {

    }

    public int getClips() {
        return clips;
    }

    public void setClips(int clips) {
        this.clips = clips;
    }

    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getNoteNox() {
        return noteNox;
    }

    public void setNoteNox(int noteNox) {
        this.noteNox = noteNox;
    }

    public int getNoteIMG() {
        return noteIMG;
    }

    public void setNoteIMG(int noteIMG) {
        this.noteIMG = noteIMG;
    }

    public int getNoteRec() {
        return noteRec;
    }

    public void setNoteRec(int noteRec) {
        this.noteRec = noteRec;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
