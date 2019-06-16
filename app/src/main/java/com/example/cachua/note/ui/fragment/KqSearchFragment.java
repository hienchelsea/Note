package com.example.cachua.note.ui.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.activity.MainActivity;
import com.example.cachua.note.ui.adapter.NoteAdapter;
import com.example.cachua.note.ui.config.Constants;
import com.example.cachua.note.ui.config.SharedPreferencesUtils;
import com.example.cachua.note.ui.database.MyDataBaseHelper;
import com.example.cachua.note.ui.model.HagsTagModel;
import com.example.cachua.note.ui.model.NoteModel;

import java.util.ArrayList;
import java.util.Objects;

public class KqSearchFragment extends BaseFragment {
    private TextView tvSearch;
    private ImageView imgBack;
    private NoteAdapter noteAdapter;
    private RecyclerView rcNote;
    int idHagsTag;

    private MyDataBaseHelper myDataBaseHelper;
    ArrayList<NoteModel> noteModelArrayList = new ArrayList<>();
    ArrayList<NoteModel> noteModelArrayListTag = new ArrayList<>();
    ArrayList<HagsTagModel> hagsTagModels = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return (R.layout.fragment_home_search_kq);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState, View rootView) {
        rcNote = rootView.findViewById(R.id.rcNote);
        tvSearch = rootView.findViewById(R.id.tvSearch);
        imgBack = rootView.findViewById(R.id.imgBack);

        imgBack.setOnClickListener(this);


    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        String check;
        check = SharedPreferencesUtils.getString(mContext, Constants.CHECK_KIND);
        myDataBaseHelper = new MyDataBaseHelper(mContext);
        if (check.equals("Tag") == true) {
            idHagsTag = SharedPreferencesUtils.getInt(mContext, Constants.CHECK_TAG);
            tvSearch.setText(SharedPreferencesUtils.getString(mContext,Constants.NAME_TAG)+"");
            hagsTagModels = myDataBaseHelper.getAllHasTag(idHagsTag);
            noteModelArrayList = myDataBaseHelper.getAllNoteTags(1);
            noteModelArrayListTag = new ArrayList<>();
            for (int i = 0; i < hagsTagModels.size(); i++) {
                int idNote = hagsTagModels.get(i).getIdNote();
                for (int j = 0; j < noteModelArrayList.size(); j++) {
                    NoteModel noteModel = noteModelArrayList.get(j);
                    if (idNote == noteModel.getId()) {
                        noteModelArrayListTag.add(noteModel);
                        i++;
                    }
                }
            }
        } else {
            if (check.equals("Anh") == true) {
                tvSearch.setText("Hinh anh");
                noteModelArrayListTag = myDataBaseHelper.getAllNoteImg(1);

            } else {
                if (check.equals("Am") == true) {
                    tvSearch.setText("Am thanh");
                    noteModelArrayListTag = myDataBaseHelper.getAllNoteAm(1);
                }
                else{
                    if (check.equals("Check") == true) {
                        tvSearch.setText("Tick box");
                        noteModelArrayListTag = myDataBaseHelper.getAllNoteCheck(1);
                    }
                }
            }

            noteAdapter = new NoteAdapter(noteModelArrayListTag, mContext);
            rcNote.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            rcNote.setAdapter(noteAdapter);


        }
    }

    private void ktNote() {


    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:{
                Objects.requireNonNull(getActivity()).onBackPressed();
                break;
            }

        }


    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(mContext, "looo", Toast.LENGTH_SHORT).show();
        // noteModelArrayList = myDataBaseHelper.getAllNoteClips(0);

        // noteAdapter.updateListCheck(noteModelArrayListTag);

        ktNote();
        rcNote.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rcNote.setAdapter(noteAdapter);


    }
}
