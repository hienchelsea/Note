package com.example.cachua.note.ui.fragment;

import android.app.ActionBar;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.adapter.HagsTagAdapter;
import com.example.cachua.note.ui.adapter.HagsTagSeclectAdapter;
import com.example.cachua.note.ui.database.MyDataBaseHelper;
import com.example.cachua.note.ui.model.HagsTagDemoModel;
import com.example.cachua.note.ui.model.HagsTagModel;

import java.util.ArrayList;
import java.util.Objects;

public class HagsTagSelectFragment extends BaseFragment {

    private ImageView imgBack;
    private TextView tvSave;
    private RecyclerView rcHagsTag;
    private HagsTagSeclectAdapter hagsTagAdapter;
    private ArrayList<HagsTagDemoModel> hagsTagDemoModels;
    private ArrayList<HagsTagModel> hagsTagModelArrayList;
    MyDataBaseHelper myDataBaseHelper;
    @Override
    protected int getLayoutResource() {
        return (R.layout.fragment_hag_tag_select);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState, View rootView) {
        imgBack= rootView.findViewById(R.id.imgBack);
        rcHagsTag= rootView.findViewById(R.id.rcHagsTag);
        tvSave= rootView.findViewById(R.id.tvSave);

        imgBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        hagsTagModelArrayList= new ArrayList<>();
        myDataBaseHelper= new MyDataBaseHelper(mContext);
        hagsTagDemoModels= myDataBaseHelper.getAllHagsTag();

        hagsTagAdapter= new HagsTagSeclectAdapter(hagsTagDemoModels,mContext);
        rcHagsTag.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        rcHagsTag.setAdapter(hagsTagAdapter);

        hagsTagAdapter.setCallBack(new HagsTagSeclectAdapter.CallBack() {
            @Override
            public void updateHagsTag(String s, int m) {
                hagsTagModelArrayList.add(new HagsTagModel(m,0,s));
            }

            @Override
            public void deleteHagsTag(String s, int m) {
                hagsTagModelArrayList.remove(new HagsTagModel(m,0,s));
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBack:{
                Objects.requireNonNull(getActivity()).onBackPressed();
                break;
            }
            case R.id.tvSave:{
               callBack.hagstagUp(hagsTagModelArrayList);
                Objects.requireNonNull(getActivity()).onBackPressed();
                break;
            }

        }

    }
    public interface CallBack{
        void hagstagUp(ArrayList<HagsTagModel> hagsTagModels);
    }
    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
