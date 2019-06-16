package com.example.cachua.note.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.config.Constants;
import com.example.cachua.note.ui.config.PaintView;
import com.example.cachua.note.ui.config.Permission;
import com.example.cachua.note.ui.config.SharedPreferencesUtils;

import java.util.Objects;

public class AddDrawingFragment extends BaseFragment {
    private LinearLayout llColor;
    private ImageView imvSave;
    private ImageView imgBack;
    private ImageView imvEraser;
    private ImageView imvSize;
    private ImageView imvColor;
    private ImageView image_color_black;
    private ImageView image_color_red;
    private ImageView image_color_yellow;
    private ImageView image_color_green;
    private ImageView image_color_blue;
    private ImageView image_color_pink;
    private ImageView image_color_brown;
    private SeekBar sbSize;
    PaintView paintView;
    int kt=1;
    int ktSize=1;
    int ktColor=1;
    int progress = 20;

    @Override
    protected int getLayoutResource() {
        return (R.layout.fragment_add_drawing);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState, View rootView) {
        llColor= rootView.findViewById(R.id.llColor);
        paintView= rootView.findViewById(R.id.paintView);
        imvSave= rootView.findViewById(R.id.imvSave);
        imgBack= rootView.findViewById(R.id.imgBack);
        imvEraser= rootView.findViewById(R.id.imvEraser);
        imvSize= rootView.findViewById(R.id.imvSize);
        imvColor= rootView.findViewById(R.id.imvColor);
        image_color_black= rootView.findViewById(R.id.image_color_black);
        image_color_red= rootView.findViewById(R.id.image_color_red);
        image_color_yellow= rootView.findViewById(R.id.image_color_yellow);
        image_color_green= rootView.findViewById(R.id.image_color_green);
        image_color_blue= rootView.findViewById(R.id.image_color_blue);
        image_color_pink= rootView.findViewById(R.id.image_color_pink);
        image_color_brown= rootView.findViewById(R.id.image_color_brown);
        sbSize= rootView.findViewById(R.id.sbSize);

        imvSave.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        imvEraser.setOnClickListener(this);
        imvSize.setOnClickListener(this);
        imvColor.setOnClickListener(this);
        image_color_black.setOnClickListener(this);
        image_color_red.setOnClickListener(this);
        image_color_yellow.setOnClickListener(this);
        image_color_green.setOnClickListener(this);
        image_color_blue.setOnClickListener(this);
        image_color_pink.setOnClickListener(this);
        image_color_brown.setOnClickListener(this);
        sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                progress = i;
                paintView.size(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED  ){
            Permission.getPermissionRead(getActivity());
        }

        DisplayMetrics displayMetrics= new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        paintView.init(displayMetrics,20);
        sbSize.setProgress(progress);
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imvSave:{
                paintView.save();
                SharedPreferencesUtils.setInt(mContext,Constants.CHECK_DRAW,1);
                callBack.setIMG(SharedPreferencesUtils.getString(mContext, Constants.NAME_DRAW));
                Objects.requireNonNull(getActivity()).onBackPressed();

                break;
            }
            case R.id.imgBack:{
                Objects.requireNonNull(getActivity()).onBackPressed();
                SharedPreferencesUtils.setInt(mContext,Constants.CHECK_DRAW,0);
                break;
            }
            case R.id.imvEraser:{
                if(kt==1){
                    imvEraser.setImageResource(R.drawable.icon_rubbe_oner);
                    paintView.clearBlur();
                    kt=0;
                }
                else{
                    imvEraser.setImageResource(R.drawable.icon_rubber);
                    paintView.normal();
                    kt=1;
                }

                break;
            }
            case R.id.imvSize:{
                if(ktSize==1){
                    sbSize.setVisibility(View.VISIBLE);
                    ktSize=0;
                }
                else{
                    sbSize.setVisibility(View.GONE);
                    ktSize=1;
                }
                break;
            }
            case R.id.imvColor:{
                if(ktColor==1){
                    llColor.setVisibility(View.VISIBLE);
                    ktColor=0;
                }
                else{
                    llColor.setVisibility(View.GONE);
                    ktColor=1;
                }
                break;
            }
            case R.id.image_color_black:{
                paintView.color(Color.BLACK);
                break;
            }
            case R.id.image_color_red:{
                paintView.color(Color.RED);
                break;
            }
            case R.id.image_color_green:{
                paintView.color(Color.GREEN);
                break;
            }
            case R.id.image_color_blue:{
                paintView.color(Color.BLUE);
                break;
            }
            case R.id.image_color_yellow:{
                paintView.color(Color.YELLOW);
                break;
            }
            case R.id.image_color_pink:{
                paintView.color(Color.parseColor("#D500F9"));
                break;
            }
            case R.id.image_color_brown:{
                paintView.color(Color.parseColor("#8D6E63"));
                break;
            }


        }
    }
    public interface CallBack{
         void setIMG(String s);
    }
    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
