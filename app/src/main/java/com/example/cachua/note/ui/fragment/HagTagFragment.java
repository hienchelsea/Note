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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.adapter.HagsTagAdapter;
import com.example.cachua.note.ui.adapter.HagsTagDemoAdapter;
import com.example.cachua.note.ui.database.MyDataBaseHelper;
import com.example.cachua.note.ui.model.HagsTagDemoModel;
import com.example.cachua.note.ui.model.HagsTagModel;

import java.util.ArrayList;
import java.util.Objects;

public class HagTagFragment extends BaseFragment {
    private ImageView imgBack;
    private ImageView imvAddTag;
    private ImageView imvDelete;
    private ImageView imvOk;
    private LinearLayout llAdd;
    private RecyclerView rcHagsTag;
    private HagsTagAdapter hagsTagAdapter;
    private ArrayList<HagsTagDemoModel> hagsTagDemoModels;
    private Dialog dialog;
    MyDataBaseHelper myDataBaseHelper;

    @Override
    protected int getLayoutResource() {
        return (R.layout.fragment_hag_tag);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState, View rootView) {
        imgBack= rootView.findViewById(R.id.imgBack);
        imvAddTag= rootView.findViewById(R.id.imvAddTag);
        rcHagsTag= rootView.findViewById(R.id.rcHagsTag);
        llAdd= rootView.findViewById(R.id.llAdd);
        imvDelete= rootView.findViewById(R.id.imvDelete);
        imvOk= rootView.findViewById(R.id.imvOk);

        imvAddTag.setOnClickListener(this);
        imvDelete.setOnClickListener(this);
        imvOk.setOnClickListener(this);
        imgBack.setOnClickListener(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        myDataBaseHelper= new MyDataBaseHelper(mContext);
        hagsTagDemoModels= myDataBaseHelper.getAllHagsTag();

        hagsTagAdapter= new HagsTagAdapter(hagsTagDemoModels,mContext);
        rcHagsTag.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        rcHagsTag.setAdapter(hagsTagAdapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imvAddTag:{
                DaiLogSetting();
                break;
            }
            case R.id.imgBack:{
                Objects.requireNonNull(getActivity()).onBackPressed();
                break;
            }

        }

    }
    public void DaiLogSetting(){
        dialog= new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_hags_tag);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        Button btnDelete= dialog.findViewById(R.id.btnDelete);
        Button btnCancel= dialog.findViewById(R.id.btnCancel);
        final EditText edtHagsTag= dialog.findViewById(R.id.edtHagsTag);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                hagsTagDemoModels.add(new HagsTagDemoModel(edtHagsTag.getText().toString()));
                hagsTagAdapter.updateHagsTag(hagsTagDemoModels);
                callBack.Update(new HagsTagDemoModel(edtHagsTag.getText().toString()));
                dialog.dismiss();

            }
        });


        dialog.show();
    }

    public interface CallBack{
        void Update(HagsTagDemoModel hagsTagDemoModel);
    }
    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
