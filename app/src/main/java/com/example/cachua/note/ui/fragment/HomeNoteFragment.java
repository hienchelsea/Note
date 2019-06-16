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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.activity.MainActivity;
import com.example.cachua.note.ui.adapter.NoteAdapter;
import com.example.cachua.note.ui.database.MyDataBaseHelper;
import com.example.cachua.note.ui.model.NoteModel;

import java.util.ArrayList;
import java.util.Objects;

public class HomeNoteFragment extends BaseFragment {
    private NoteAdapter noteAdapter;
    private NoteAdapter noteAdapterClips;
    private RecyclerView rcNote;
    private RecyclerView rcNoteClips;
    private LinearLayout llClips;
    private MyDataBaseHelper myDataBaseHelper;
    private ImageView imvAdd;
    private TextView tvClips;
    private TextView tvNote;
    ArrayList<NoteModel> noteModelArrayList = new ArrayList<>();
    ArrayList<NoteModel> noteModelArrayListClips = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return (R.layout.fragment_home_note);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState, View rootView) {
        rcNote = rootView.findViewById(R.id.rcNote);
        rcNoteClips = rootView.findViewById(R.id.rcNoteClips);
        llClips = rootView.findViewById(R.id.llClips);
        imvAdd = rootView.findViewById(R.id.imvAdd);
        tvClips = rootView.findViewById(R.id.tvClips);
        tvNote = rootView.findViewById(R.id.tvNote);

        imvAdd.setOnClickListener(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        myDataBaseHelper = new MyDataBaseHelper(mContext);
        noteModelArrayList = myDataBaseHelper.getAllNoteClips(0);
        noteModelArrayListClips = myDataBaseHelper.getAllNoteClips(1);
        noteAdapter = new NoteAdapter(noteModelArrayList, mContext);
        noteAdapterClips = new NoteAdapter(noteModelArrayListClips, mContext);
        ktNote();
        rcNote.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rcNote.setAdapter(noteAdapter);

        rcNoteClips.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rcNoteClips.setAdapter(noteAdapterClips);

        noteAdapter.setCallBack(new NoteAdapter.CallBack() {
            @Override
            public void loadNote(NoteModel noteModel) {
                noteModelArrayList = myDataBaseHelper.getAllNoteClips(0);
                noteModelArrayListClips = myDataBaseHelper.getAllNoteClips(1);
                noteAdapter.updateListCheck(noteModelArrayList);
                noteAdapterClips.updateListCheck(noteModelArrayListClips);
                ktNote();
            }
        });
        noteAdapterClips.setCallBack(new NoteAdapter.CallBack() {
            @Override
            public void loadNote(NoteModel noteModel) {
                noteModelArrayList = myDataBaseHelper.getAllNoteClips(0);
                noteModelArrayListClips = myDataBaseHelper.getAllNoteClips(1);
                noteAdapter.updateListCheck(noteModelArrayList);
                noteAdapterClips.updateListCheck(noteModelArrayListClips);
                ktNote();
            }
        });

    }

    private void ktNote() {
        if (noteModelArrayListClips.size() == 0) {
            llClips.setVisibility(View.GONE);
            tvNote.setVisibility(View.GONE);

        } else {
            llClips.setVisibility(View.VISIBLE);
            if (noteModelArrayList.size() == 0) {
                tvNote.setVisibility(View.GONE);
            } else {
                tvNote.setVisibility(View.VISIBLE);
            }
        }

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imvAdd: {
                AddNotesFragment addDrawingFragment = new AddNotesFragment();
                ((MainActivity) Objects.requireNonNull(getActivity())).nextFragment(addDrawingFragment, R.id.rc_main, 0, 0, 0, 0);
                addDrawingFragment.setCallBack(new AddNotesFragment.CallBack() {
                    @Override
                    public void loadCheckBox(NoteModel noteModel) {
                        noteModelArrayList = myDataBaseHelper.getAllNoteClips(0);
                        noteModelArrayListClips = myDataBaseHelper.getAllNoteClips(1);
                        noteAdapter.updateListCheck(noteModelArrayList);
                        noteAdapterClips.updateListCheck(noteModelArrayListClips);
                        ktNote();
//                        rcNote.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//                        rcNote.setAdapter(noteAdapter);
                    }
                });
                break;
            }
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(mContext, "looo", Toast.LENGTH_SHORT).show();
        noteModelArrayList = myDataBaseHelper.getAllNoteClips(0);
        noteModelArrayListClips = myDataBaseHelper.getAllNoteClips(1);
        noteAdapter.updateListCheck(noteModelArrayList);
        noteAdapterClips.updateListCheck(noteModelArrayListClips);
        ktNote();
        rcNote.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rcNote.setAdapter(noteAdapter);
        rcNoteClips.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rcNoteClips.setAdapter(noteAdapterClips);


    }
}
