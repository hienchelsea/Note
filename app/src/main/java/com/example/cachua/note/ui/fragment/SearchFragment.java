package com.example.cachua.note.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.activity.MainActivity;
import com.example.cachua.note.ui.adapter.HagTagBBBBAdapter;
import com.example.cachua.note.ui.adapter.HagsTagDemoAdapter;
import com.example.cachua.note.ui.adapter.NoteAdapter;
import com.example.cachua.note.ui.config.Constants;
import com.example.cachua.note.ui.config.SharedPreferencesUtils;
import com.example.cachua.note.ui.database.MyDataBaseHelper;
import com.example.cachua.note.ui.model.HagsTagDemoModel;
import com.example.cachua.note.ui.model.NoteModel;

import java.util.ArrayList;
import java.util.Objects;

public class SearchFragment extends BaseFragment implements TextWatcher {
    EditText edtSearch;
    ImageView imgBack;
    private LinearLayout llImg;
    private LinearLayout llRec;
    private LinearLayout llCheck;
    private LinearLayout llDeoBiet;
    private RecyclerView rcTag;
    private RecyclerView rcNote;
    private InputMethodManager imm;
    private ArrayList<HagsTagDemoModel> hagsTagDemoModels;
    private HagTagBBBBAdapter hagTagBBBBAdapter;
    ArrayList<NoteModel> noteModelArrayList = new ArrayList<>();
    private NoteAdapter noteAdapter;
    MyDataBaseHelper myDataBaseHelper;
    @Override
    protected int getLayoutResource() {
            return (R.layout.fragment_search);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState, View rootView) {
        llImg= rootView.findViewById(R.id.llImg);
        llRec= rootView.findViewById(R.id.llRec);
        llCheck= rootView.findViewById(R.id.llCheck);
        llDeoBiet= rootView.findViewById(R.id.llDeoBiet);
        rcTag= rootView.findViewById(R.id.rcTag);
        rcNote= rootView.findViewById(R.id.rcNote);
        edtSearch= rootView.findViewById(R.id.edtSearch);
        imgBack= rootView.findViewById(R.id.imgBack);

        imgBack.setOnClickListener(this);
        llImg.setOnClickListener(this);
        llRec.setOnClickListener(this);
        llCheck.setOnClickListener(this);
        edtSearch.addTextChangedListener(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        myDataBaseHelper= new MyDataBaseHelper(mContext);
        hagsTagDemoModels= new ArrayList<>();
        hagsTagDemoModels= myDataBaseHelper.getAllHagsTag();

        hagTagBBBBAdapter= new HagTagBBBBAdapter(hagsTagDemoModels,mContext);
        rcTag.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        rcTag.setAdapter(hagTagBBBBAdapter);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:{
                hideDefaultKeyboard(view);
                Objects.requireNonNull(getActivity()).onBackPressed();
                break;
            }
            case R.id.llImg:{
                SharedPreferencesUtils.setString(mContext, Constants.CHECK_KIND,"Anh");
                KqSearchFragment kqSearchFragment = new KqSearchFragment();
                ((MainActivity) Objects.requireNonNull(getActivity())).nextFragment(kqSearchFragment, R.id.rc_main, 0, 0, 0, 0);

                break;
            }
            case R.id.llRec:{
                SharedPreferencesUtils.setString(mContext, Constants.CHECK_KIND,"Am");
                KqSearchFragment kqSearchFragment = new KqSearchFragment();
                ((MainActivity) Objects.requireNonNull(getActivity())).nextFragment(kqSearchFragment, R.id.rc_main, 0, 0, 0, 0);

                break;
            }
            case R.id.llCheck:{
                SharedPreferencesUtils.setString(mContext, Constants.CHECK_KIND,"Check");
                KqSearchFragment kqSearchFragment = new KqSearchFragment();
                ((MainActivity) Objects.requireNonNull(getActivity())).nextFragment(kqSearchFragment, R.id.rc_main, 0, 0, 0, 0);

                break;
            }
        }

    }
    private void hideDefaultKeyboard(View et) {
        getMethodManager().hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    private InputMethodManager getMethodManager() {
        if (this.imm == null) {
            this.imm = (InputMethodManager)  getContext().getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
        }
        return this.imm;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            String chuoi= edtSearch.getText().toString();
            if(chuoi.equals("")==true){
                rcNote.setVisibility(View.GONE);
                llDeoBiet.setVisibility(View.VISIBLE);

            }else{
                rcNote.setVisibility(View.VISIBLE);
                llDeoBiet.setVisibility(View.GONE);
                noteModelArrayList= myDataBaseHelper.getAllNoteSearch(chuoi);
                noteAdapter= new NoteAdapter(noteModelArrayList,mContext);
                rcNote.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                rcNote.setAdapter(noteAdapter);
            }
            Log.i("HienNguuuu", noteModelArrayList.size()+"onTextChanged: ");
        }catch (Exception e){

        }


    }

    @Override
    public void afterTextChanged(Editable s) {
        Toast.makeText(mContext, "dd", Toast.LENGTH_SHORT).show();

    }
}
