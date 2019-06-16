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
import com.example.cachua.note.ui.config.Constants;
import com.example.cachua.note.ui.config.SharedPreferencesUtils;
import com.example.cachua.note.ui.database.MyDataBaseHelper;
import com.example.cachua.note.ui.model.HagsTagModel;
import com.example.cachua.note.ui.model.NoteModel;

import java.util.ArrayList;
import java.util.Objects;

public class HagtagSearchFragment extends BaseFragment {
    private NoteAdapter noteAdapter;
    private NoteAdapter noteAdapterClips;
    private RecyclerView rcNote;
    int idHagsTag;

    private MyDataBaseHelper myDataBaseHelper;
    private ImageView imvAdd;
    ArrayList<NoteModel> noteModelArrayList = new ArrayList<>();
    ArrayList<NoteModel> noteModelArrayListTag = new ArrayList<>();
    ArrayList<HagsTagModel> hagsTagModels= new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return (R.layout.fragment_home_note_tag);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState, View rootView) {
        rcNote = rootView.findViewById(R.id.rcNote);
        imvAdd = rootView.findViewById(R.id.imvAdd);


        imvAdd.setOnClickListener(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        myDataBaseHelper = new MyDataBaseHelper(mContext);
        idHagsTag= SharedPreferencesUtils.getInt(mContext, Constants.CHECK_TAG);
        Log.i("HienNGuuuuu", idHagsTag+"");
        hagsTagModels= myDataBaseHelper.getAllHasTag(idHagsTag);
        noteModelArrayList = myDataBaseHelper.getAllNoteTags(1);
        noteModelArrayListTag= new ArrayList<>();
        for(int i=0;i<hagsTagModels.size();i++){
            int idNote= hagsTagModels.get(i).getIdNote();
            for(int j=0;j<noteModelArrayList.size();j++){
                NoteModel noteModel= noteModelArrayList.get(j);
                if(idNote==noteModel.getId()){
                    noteModelArrayListTag.add(noteModel);
                    i++;
                    Log.i("HienNGuuuuu", "initData: ");
                }
            }
        }


        noteAdapter = new NoteAdapter(noteModelArrayListTag, mContext);

        ktNote();
        rcNote.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rcNote.setAdapter(noteAdapter);


        noteAdapter.setCallBack(new NoteAdapter.CallBack() {
            @Override
            public void loadNote(NoteModel noteModel) {
                noteModelArrayList = myDataBaseHelper.getAllNoteClips(0);
               // noteAdapter.updateListCheck(noteModelArrayList);

                ktNote();
            }
        });


    }

    private void ktNote() {


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
                        noteModelArrayListTag.add(noteModel);
                        noteModel.setTag(1);
                        HagsTagModel hagsTagModel= new HagsTagModel(idHagsTag,noteModel.getId(),"");
                        myDataBaseHelper.addTableHagsTagSelect(hagsTagModel);
                        myDataBaseHelper.updateTableNote(noteModel);
                        Log.i("HienNGuuuuu", noteModel.getTag()+"loadCheckBox: ");
                        noteAdapter.updateListCheck(noteModelArrayListTag);

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
       // noteModelArrayList = myDataBaseHelper.getAllNoteClips(0);

       // noteAdapter.updateListCheck(noteModelArrayListTag);

        ktNote();
        rcNote.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rcNote.setAdapter(noteAdapter);


    }
}
