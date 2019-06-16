package com.example.cachua.note.ui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cachua.note.ui.model.HagsTagDemoModel;
import com.example.cachua.note.ui.model.HagsTagModel;
import com.example.cachua.note.ui.model.ImgModel;
import com.example.cachua.note.ui.model.NoteModel;
import com.example.cachua.note.ui.model.RecModel;
import com.example.cachua.note.ui.model.TickBoxModel;

import java.util.ArrayList;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_note";

    private static final String TABLE_NOTE = "table_note";
    private static final String TABLE_BOX = "table_box";
    private static final String TABLE_TICK_BOX = "table_tick_box";
    private static final String TABLE_IMG = "table_img";
    private static final String TABLE_REC = "table_rec";
    private static final String TABLE_HAGS_TAG = "table_hags_tag";
    private static final String TABLE_HAGS_TAG_SELECT = "table_hags_tag_select";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ID_NOTE = "id_note";
    private static final String COLUMN_ID_BOX = "id_note_box";
    private static final String COLUMN_ID_IMG = "id_note_img";
    private static final String COLUMN_ID_REC = "id_note_rec";
    private static final String COLUMN_ID_HAGS_TAG = "id_hags_tag";
    private static final String COLUMN_NOTE_BOX = "note_box";
    private static final String COLUMN_NOTE_IMG = "note_img";
    private static final String COLUMN_NOTE_REC = "note_rec";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NAME_IMG = "name_img";
    private static final String COLUMN_DESCRIBE = "describe";
    private static final String COLUMN_DESCRIBE_NOTE = "describe_note";
    private static final String COLUMN_DESCRIBE_HAGS = "describe_hags";
    private static final String COLUMN_PATH_IMG = "path_img";
    private static final String COLUMN_PATH_REC = "path_rec";
    private static final String COLUMN_COUNT_REC = "path_count_rec";
    private static final String COLUMN_BOX = "box";
    private static final String COLUMN_IMG = "img";
    private static final String COLUMN_REC = "rec";
    private static final String COLUMN_TAG = "tag";
    private static final String COLUMN_CLIPS = "clips";
    private static final String COLUMN_ALARM = "alarm";
    private static final String COLUMN_COLOR = "color";

    private static final String COLUMN_NAME_BOOK = "name_book";
    private static final String COLUMN_KIND_BOOK = "kind_book";
    private static final String COLUMN_PASSWORD = "password";


    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String scriptCreateTableNote = "CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_ID_NOTE + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIBE + " TEXT,"
                + COLUMN_NOTE_BOX + " INTEGER,"
                + COLUMN_NOTE_IMG + " INTEGER,"
                + COLUMN_NOTE_REC + " INTEGER,"
                + COLUMN_TAG + " INTEGER,"
                + COLUMN_CLIPS + " INTEGER,"
                + COLUMN_ALARM + " INTEGER,"
                + COLUMN_PASSWORD + " INTEGER,"
                + COLUMN_COLOR + " INTEGER " + ")";

        db.execSQL(scriptCreateTableNote);

        String scriptCreateTableBox = "CREATE TABLE " + TABLE_BOX + "("
                + COLUMN_ID_BOX + " INTEGER PRIMARY KEY,"
                + COLUMN_ID_NOTE + " INTEGER,"
                + COLUMN_DESCRIBE_NOTE + " TEXT " + ")";

        db.execSQL(scriptCreateTableBox);

        String scriptCreateTableImg = "CREATE TABLE " + TABLE_IMG + "("
                + COLUMN_ID_IMG + " INTEGER PRIMARY KEY,"
                + COLUMN_ID_NOTE + " INTEGER,"
                + COLUMN_PATH_IMG + " TEXT " + ")";

        db.execSQL(scriptCreateTableImg);

        String scriptCreateTableRec = "CREATE TABLE " + TABLE_REC + "("
                + COLUMN_ID_REC + " INTEGER PRIMARY KEY,"
                + COLUMN_ID_NOTE + " INTEGER,"
                + COLUMN_COUNT_REC + " INTEGER,"
                + COLUMN_PATH_REC + " TEXT " + ")";

        db.execSQL(scriptCreateTableRec);

        String scriptCreateTableHags = "CREATE TABLE " + TABLE_HAGS_TAG + "("
                + COLUMN_ID_HAGS_TAG + " INTEGER PRIMARY KEY,"
                + COLUMN_DESCRIBE_HAGS + " TEXT " + ")";

        db.execSQL(scriptCreateTableHags);

        String scriptCreateTableHagsTagSelect = "CREATE TABLE " + TABLE_HAGS_TAG_SELECT + "("
                + COLUMN_ID+ " INTEGER PRIMARY KEY,"
                + COLUMN_DESCRIBE_HAGS + " INTEGER,"
                + COLUMN_ID_NOTE + " INTEGER,"
                + COLUMN_DESCRIBE_NOTE + " TEXT " + ")";

        db.execSQL(scriptCreateTableHagsTagSelect);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOX);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HAGS_TAG);

    }

    public void addTableNote(NoteModel noteModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, noteModel.getName());
        values.put(COLUMN_DESCRIBE, noteModel.getDescribe());
        values.put(COLUMN_NOTE_BOX, noteModel.getNoteNox());
        values.put(COLUMN_NOTE_IMG, noteModel.getNoteIMG());
        values.put(COLUMN_NOTE_REC, noteModel.getNoteRec());
        values.put(COLUMN_TAG, noteModel.getTag());
        values.put(COLUMN_CLIPS, noteModel.getClips());
        values.put(COLUMN_ALARM, noteModel.getAlarm());
        values.put(COLUMN_PASSWORD, noteModel.getPassword());
        values.put(COLUMN_COLOR, noteModel.getColor());


        db.insert(TABLE_NOTE, null, values);
        db.close();

    }

    public void addTableBox(TickBoxModel tickBoxModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_NOTE, tickBoxModel.getIdNote());
        values.put(COLUMN_DESCRIBE_NOTE, tickBoxModel.getDescribe());


        db.insert(TABLE_BOX, null, values);
        db.close();

    }



    public void addTableImg(ImgModel imgModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_NOTE, imgModel.getIdNote());
        values.put(COLUMN_PATH_IMG, imgModel.getPath());


        db.insert(TABLE_IMG, null, values);
        db.close();

    }

    public void addTableRec(RecModel recModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_NOTE, recModel.getIdNote());
        values.put(COLUMN_COUNT_REC, recModel.getCountTime());
        values.put(COLUMN_PATH_REC, recModel.getPath());


        db.insert(TABLE_REC, null, values);
        db.close();

    }

    public void addTableHagsTag(HagsTagDemoModel hagsTagDemoModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIBE_HAGS, hagsTagDemoModel.getDescribe());


        db.insert(TABLE_HAGS_TAG, null, values);
        db.close();

    }

    public void addTableHagsTagSelect(HagsTagModel hagsTagModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIBE_HAGS, hagsTagModel.getIdHagsTag());
        values.put(COLUMN_ID_NOTE, hagsTagModel.getIdNote());
        values.put(COLUMN_DESCRIBE_NOTE, hagsTagModel.getName());


        db.insert(TABLE_HAGS_TAG_SELECT, null, values);
        db.close();

    }


    public void deleteTableNote(NoteModel noteModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTE, COLUMN_ID_NOTE + " = ?",
                new String[]{String.valueOf(noteModel.getId())});
        db.close();
    }

    public void deleteTableBox(TickBoxModel tickBoxModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOX, COLUMN_ID_BOX + " = ?",
                new String[]{String.valueOf(tickBoxModel.getIdNote())});
        db.close();
    }

    public void deleteTableBoxIdNote(int i) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOX, COLUMN_ID_NOTE + " = ?",
                new String[]{String.valueOf(i)});
        db.close();
    }

    public void deleteTableImgIdNote(int i) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_IMG, COLUMN_ID_NOTE + " = ?",
                new String[]{String.valueOf(i)});
        db.close();
    }

    public void deleteTableImgIdImg(int i) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_IMG, COLUMN_ID_IMG + " = ?",
                new String[]{String.valueOf(i)});
        db.close();
    }



    public void deleteTableRec(RecModel recModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REC, COLUMN_ID_NOTE + " = ?",
                new String[]{String.valueOf(recModel.getIdNote())});
        db.close();
    }
    public void deleteTableRecIdNote(int i) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REC, COLUMN_ID_NOTE + " = ?",
                new String[]{String.valueOf(i)});
        db.close();
    }

    public void deleteTableHagsIdNote(int i) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HAGS_TAG_SELECT, COLUMN_ID_NOTE + " = ?",
                new String[]{String.valueOf(i)});
        db.close();
    }

    public void deleteTableHagsTag(HagsTagDemoModel hagsTagDemoModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HAGS_TAG, COLUMN_ID_HAGS_TAG + " = ?",
                new String[]{String.valueOf(hagsTagDemoModel.getId())});
        db.close();
    }



    public ArrayList<NoteModel> getAllNote() {

        ArrayList<NoteModel> noteModelArrayList = new ArrayList<NoteModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(Integer.parseInt(cursor.getString(0)));
                noteModel.setName((cursor.getString(1)));
                noteModel.setDescribe((cursor.getString(2)));
                noteModel.setNoteNox(Integer.parseInt(cursor.getString(3)));
                noteModel.setNoteIMG(Integer.parseInt(cursor.getString(4)));
                noteModel.setNoteRec(Integer.parseInt(cursor.getString(5)));
                noteModel.setTag(Integer.parseInt(cursor.getString(6)));
                noteModel.setClips(Integer.parseInt(cursor.getString(7)));
                noteModel.setAlarm(Integer.parseInt(cursor.getString(8)));
                noteModel.setPassword(Integer.parseInt(cursor.getString(9)));
                noteModel.setColor(Integer.parseInt(cursor.getString(10)));


                // Thêm vào danh sách.
                noteModelArrayList.add(noteModel);
            } while (cursor.moveToNext());
        }


        return noteModelArrayList;

    }

    public ArrayList<NoteModel> getAllNote(int i) {

        ArrayList<NoteModel> noteModelArrayList = new ArrayList<NoteModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(Integer.parseInt(cursor.getString(0)));
                noteModel.setName((cursor.getString(1)));
                noteModel.setDescribe((cursor.getString(2)));
                noteModel.setNoteNox(Integer.parseInt(cursor.getString(3)));
                noteModel.setNoteIMG(Integer.parseInt(cursor.getString(4)));
                noteModel.setNoteRec(Integer.parseInt(cursor.getString(5)));
                noteModel.setTag(Integer.parseInt(cursor.getString(6)));
                noteModel.setClips(Integer.parseInt(cursor.getString(7)));
                noteModel.setAlarm(Integer.parseInt(cursor.getString(8)));
                noteModel.setPassword(Integer.parseInt(cursor.getString(9)));
                noteModel.setColor(Integer.parseInt(cursor.getString(10)));

                if(i==noteModel.getId()){
                    noteModelArrayList.add(noteModel);
                }
            } while (cursor.moveToNext());
        }


        return noteModelArrayList;

    }
    public ArrayList<NoteModel> getAllNoteClips(int i) {

        ArrayList<NoteModel> noteModelArrayList = new ArrayList<NoteModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(Integer.parseInt(cursor.getString(0)));
                noteModel.setName((cursor.getString(1)));
                noteModel.setDescribe((cursor.getString(2)));
                noteModel.setNoteNox(Integer.parseInt(cursor.getString(3)));
                noteModel.setNoteIMG(Integer.parseInt(cursor.getString(4)));
                noteModel.setNoteRec(Integer.parseInt(cursor.getString(5)));
                noteModel.setTag(Integer.parseInt(cursor.getString(6)));
                noteModel.setClips(Integer.parseInt(cursor.getString(7)));
                noteModel.setAlarm(Integer.parseInt(cursor.getString(8)));
                noteModel.setPassword(Integer.parseInt(cursor.getString(9)));
                noteModel.setColor(Integer.parseInt(cursor.getString(10)));

                if(i==noteModel.getClips()){
                    noteModelArrayList.add(noteModel);
                }
            } while (cursor.moveToNext());
        }


        return noteModelArrayList;

    }
    public ArrayList<NoteModel> getAllNoteTags(int i) {

        ArrayList<NoteModel> noteModelArrayList = new ArrayList<NoteModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(Integer.parseInt(cursor.getString(0)));
                noteModel.setName((cursor.getString(1)));
                noteModel.setDescribe((cursor.getString(2)));
                noteModel.setNoteNox(Integer.parseInt(cursor.getString(3)));
                noteModel.setNoteIMG(Integer.parseInt(cursor.getString(4)));
                noteModel.setNoteRec(Integer.parseInt(cursor.getString(5)));
                noteModel.setTag(Integer.parseInt(cursor.getString(6)));
                noteModel.setClips(Integer.parseInt(cursor.getString(7)));
                noteModel.setAlarm(Integer.parseInt(cursor.getString(8)));
                noteModel.setPassword(Integer.parseInt(cursor.getString(9)));
                noteModel.setColor(Integer.parseInt(cursor.getString(10)));

                if(i==noteModel.getTag()){
                    noteModelArrayList.add(noteModel);
                }
            } while (cursor.moveToNext());
        }


        return noteModelArrayList;

    }
    public ArrayList<NoteModel> getAllNoteImg(int i) {

        ArrayList<NoteModel> noteModelArrayList = new ArrayList<NoteModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(Integer.parseInt(cursor.getString(0)));
                noteModel.setName((cursor.getString(1)));
                noteModel.setDescribe((cursor.getString(2)));
                noteModel.setNoteNox(Integer.parseInt(cursor.getString(3)));
                noteModel.setNoteIMG(Integer.parseInt(cursor.getString(4)));
                noteModel.setNoteRec(Integer.parseInt(cursor.getString(5)));
                noteModel.setTag(Integer.parseInt(cursor.getString(6)));
                noteModel.setClips(Integer.parseInt(cursor.getString(7)));
                noteModel.setAlarm(Integer.parseInt(cursor.getString(8)));
                noteModel.setPassword(Integer.parseInt(cursor.getString(9)));
                noteModel.setColor(Integer.parseInt(cursor.getString(10)));

                if(i==noteModel.getNoteIMG()){
                    noteModelArrayList.add(noteModel);
                }
            } while (cursor.moveToNext());
        }


        return noteModelArrayList;

    }
    public ArrayList<NoteModel> getAllNoteAm(int i) {

        ArrayList<NoteModel> noteModelArrayList = new ArrayList<NoteModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(Integer.parseInt(cursor.getString(0)));
                noteModel.setName((cursor.getString(1)));
                noteModel.setDescribe((cursor.getString(2)));
                noteModel.setNoteNox(Integer.parseInt(cursor.getString(3)));
                noteModel.setNoteIMG(Integer.parseInt(cursor.getString(4)));
                noteModel.setNoteRec(Integer.parseInt(cursor.getString(5)));
                noteModel.setTag(Integer.parseInt(cursor.getString(6)));
                noteModel.setClips(Integer.parseInt(cursor.getString(7)));
                noteModel.setAlarm(Integer.parseInt(cursor.getString(8)));
                noteModel.setPassword(Integer.parseInt(cursor.getString(9)));
                noteModel.setColor(Integer.parseInt(cursor.getString(10)));

                if(i==noteModel.getNoteRec()){
                    noteModelArrayList.add(noteModel);
                }
            } while (cursor.moveToNext());
        }


        return noteModelArrayList;

    }
    public ArrayList<NoteModel> getAllNoteCheck(int i) {

        ArrayList<NoteModel> noteModelArrayList = new ArrayList<NoteModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(Integer.parseInt(cursor.getString(0)));
                noteModel.setName((cursor.getString(1)));
                noteModel.setDescribe((cursor.getString(2)));
                noteModel.setNoteNox(Integer.parseInt(cursor.getString(3)));
                noteModel.setNoteIMG(Integer.parseInt(cursor.getString(4)));
                noteModel.setNoteRec(Integer.parseInt(cursor.getString(5)));
                noteModel.setTag(Integer.parseInt(cursor.getString(6)));
                noteModel.setClips(Integer.parseInt(cursor.getString(7)));
                noteModel.setAlarm(Integer.parseInt(cursor.getString(8)));
                noteModel.setPassword(Integer.parseInt(cursor.getString(9)));
                noteModel.setColor(Integer.parseInt(cursor.getString(10)));

                if(i==noteModel.getNoteNox()){
                    noteModelArrayList.add(noteModel);
                }
            } while (cursor.moveToNext());
        }


        return noteModelArrayList;

    }
    public ArrayList<NoteModel> getAllNoteSearch(String s) {

        ArrayList<NoteModel> noteModelArrayList = new ArrayList<NoteModel>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NOTE,
                null,
                COLUMN_NAME+" like '%"+s+"%';",
                null,
                null,
                null,
                null);


        if (cursor.moveToFirst()) {
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(Integer.parseInt(cursor.getString(0)));
                noteModel.setName((cursor.getString(1)));
                noteModel.setDescribe((cursor.getString(2)));
                noteModel.setNoteNox(Integer.parseInt(cursor.getString(3)));
                noteModel.setNoteIMG(Integer.parseInt(cursor.getString(4)));
                noteModel.setNoteRec(Integer.parseInt(cursor.getString(5)));
                noteModel.setTag(Integer.parseInt(cursor.getString(6)));
                noteModel.setClips(Integer.parseInt(cursor.getString(7)));
                noteModel.setAlarm(Integer.parseInt(cursor.getString(8)));
                noteModel.setPassword(Integer.parseInt(cursor.getString(9)));
                noteModel.setColor(Integer.parseInt(cursor.getString(10)));
                noteModelArrayList.add(noteModel);

            } while (cursor.moveToNext());
        }


        return noteModelArrayList;

    }

    public ArrayList<TickBoxModel> getAllBox() {

        ArrayList<TickBoxModel> tickBoxModelArrayList = new ArrayList<TickBoxModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_BOX;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TickBoxModel tickBoxModel = new TickBoxModel();
                tickBoxModel.setIdBox(Integer.parseInt(cursor.getString(0)));
                tickBoxModel.setIdNote(Integer.parseInt(cursor.getString(1)));
                tickBoxModel.setDescribe((cursor.getString(2)));


                // Thêm vào danh sách.
                tickBoxModelArrayList.add(tickBoxModel);
            } while (cursor.moveToNext());
        }


        return tickBoxModelArrayList;

    }

    public ArrayList<TickBoxModel> getAllBox(int id) {

        ArrayList<TickBoxModel> tickBoxModelArrayList = new ArrayList<TickBoxModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_BOX;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TickBoxModel tickBoxModel = new TickBoxModel();
                tickBoxModel.setIdBox(Integer.parseInt(cursor.getString(0)));
                tickBoxModel.setIdNote(Integer.parseInt(cursor.getString(1)));
                tickBoxModel.setDescribe((cursor.getString(2)));
                if(id==tickBoxModel.getIdNote()){
                    tickBoxModelArrayList.add(tickBoxModel);
                    Log.i("getAllBox", tickBoxModel.getDescribe());
                    Log.i("getAllBox", tickBoxModel.getIdBox()+"");
                    Log.i("getAllBox", tickBoxModel.getIdNote()+"");
                }


                // Thêm vào danh sách.
            } while (cursor.moveToNext());
        }


        return tickBoxModelArrayList;

    }

    public ArrayList<ImgModel> getAllImg() {

        ArrayList<ImgModel> imgModelArrayList = new ArrayList<ImgModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_IMG;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ImgModel imgModel = new ImgModel();
                imgModel.setId(Integer.parseInt(cursor.getString(0)));
                imgModel.setIdNote(Integer.parseInt(cursor.getString(1)));
                imgModel.setPath((cursor.getString(2)));


                // Thêm vào danh sách.
                imgModelArrayList.add(imgModel);
            } while (cursor.moveToNext());
        }


        return imgModelArrayList;

    }

    public ArrayList<ImgModel> getAllImg(int i) {

        ArrayList<ImgModel> imgModelArrayList = new ArrayList<ImgModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_IMG;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ImgModel imgModel = new ImgModel();
                imgModel.setId(Integer.parseInt(cursor.getString(0)));
                imgModel.setIdNote(Integer.parseInt(cursor.getString(1)));
                imgModel.setPath((cursor.getString(2)));

                if(i==imgModel.getIdNote()){
                    imgModelArrayList.add(imgModel);
                }
                // Thêm vào danh sách.

            } while (cursor.moveToNext());
        }


        return imgModelArrayList;

    }



    public ArrayList<RecModel> getAllRec() {

        ArrayList<RecModel> recModelArrayList = new ArrayList<RecModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_REC;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                RecModel recModel = new RecModel();
                recModel.setId(Integer.parseInt(cursor.getString(0)));
                recModel.setIdNote(Integer.parseInt(cursor.getString(1)));
                recModel.setCountTime(Integer.parseInt(cursor.getString(2)));
                recModel.setPath((cursor.getString(3)));


                // Thêm vào danh sách.
                recModelArrayList.add(recModel);
            } while (cursor.moveToNext());
        }


        return recModelArrayList;

    }

    public ArrayList<RecModel> getAllRec(int i) {

        ArrayList<RecModel> recModelArrayList = new ArrayList<RecModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_REC;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                RecModel recModel = new RecModel();
                recModel.setId(Integer.parseInt(cursor.getString(0)));
                recModel.setIdNote(Integer.parseInt(cursor.getString(1)));
                recModel.setCountTime(Integer.parseInt(cursor.getString(2)));
                recModel.setPath((cursor.getString(3)));

                if(i==recModel.getIdNote()){
                    recModelArrayList.add(recModel);
                }
                // Thêm vào danh sách.

            } while (cursor.moveToNext());
        }


        return recModelArrayList;

    }

    public ArrayList<HagsTagDemoModel> getAllHagsTag() {

        ArrayList<HagsTagDemoModel> hagsTagDemoModelArrayList = new ArrayList<HagsTagDemoModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_HAGS_TAG;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HagsTagDemoModel hagsTagDemoModel = new HagsTagDemoModel();
                hagsTagDemoModel.setId(Integer.parseInt(cursor.getString(0)));
                hagsTagDemoModel.setDescribe((cursor.getString(1)));


                // Thêm vào danh sách.
                hagsTagDemoModelArrayList.add(hagsTagDemoModel);
            } while (cursor.moveToNext());
        }


        return hagsTagDemoModelArrayList;

    }
    public ArrayList<HagsTagModel> getAllHasTag(int id) {

        ArrayList<HagsTagModel> hagsTagModelArrayList = new ArrayList<HagsTagModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_HAGS_TAG_SELECT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HagsTagModel hagsTagModel = new HagsTagModel();
                hagsTagModel.setId(Integer.parseInt(cursor.getString(0)));
                hagsTagModel.setIdHagsTag(Integer.parseInt(cursor.getString(1)));
                hagsTagModel.setIdNote(Integer.parseInt(cursor.getString(2)));
                hagsTagModel.setName((cursor.getString(3)));
                if (id == hagsTagModel.getIdHagsTag()) {
                    hagsTagModelArrayList.add(hagsTagModel);
                }


                // Thêm vào danh sách.
            } while (cursor.moveToNext());
        }

        return hagsTagModelArrayList;
    }

    public int  updateTableBox(TickBoxModel tickBoxModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIBE_NOTE,tickBoxModel.getDescribe());
        values.put(COLUMN_ID_NOTE,tickBoxModel.getIdNote());



        return db.update(TABLE_BOX, values, COLUMN_ID_BOX + " = ?",
                new String[]{String.valueOf(tickBoxModel.getIdBox())});


    }

    public int  updateTableImg(ImgModel imgModel) {
        // Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + note.getNoteTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_NOTE,imgModel.getIdNote());



        return db.update(TABLE_IMG, values, COLUMN_ID_IMG + " = ?",
                new String[]{String.valueOf(imgModel.getId())});


    }


    public int  updateTableNote(NoteModel noteModel) {
        // Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + note.getNoteTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, noteModel.getName());
        values.put(COLUMN_DESCRIBE, noteModel.getDescribe());
        values.put(COLUMN_NOTE_BOX, noteModel.getNoteNox());
        values.put(COLUMN_NOTE_IMG, noteModel.getNoteIMG());
        values.put(COLUMN_NOTE_REC, noteModel.getNoteRec());
        values.put(COLUMN_TAG, noteModel.getTag());
        values.put(COLUMN_CLIPS, noteModel.getClips());
        values.put(COLUMN_ALARM, noteModel.getAlarm());
        values.put(COLUMN_PASSWORD, noteModel.getPassword());
        values.put(COLUMN_COLOR, noteModel.getColor());



        return db.update(TABLE_NOTE, values, COLUMN_ID_NOTE + " = ?",
                new String[]{String.valueOf(noteModel.getId())});


    }




}
