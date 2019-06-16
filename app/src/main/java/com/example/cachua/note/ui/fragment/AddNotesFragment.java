package com.example.cachua.note.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.activity.MainActivity;
import com.example.cachua.note.ui.adapter.HagsTagSeclectAdapter;
import com.example.cachua.note.ui.adapter.ImgAdapter;
import com.example.cachua.note.ui.adapter.TickBoxAdapter;
import com.example.cachua.note.ui.config.Constants;
import com.example.cachua.note.ui.config.Permission;
import com.example.cachua.note.ui.config.SharedPreferencesUtils;
import com.example.cachua.note.ui.database.MyDataBaseHelper;
import com.example.cachua.note.ui.model.HagsTagModel;
import com.example.cachua.note.ui.model.ImgModel;
import com.example.cachua.note.ui.model.NoteModel;
import com.example.cachua.note.ui.model.RecModel;
import com.example.cachua.note.ui.model.TickBoxModel;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class AddNotesFragment extends BaseFragment implements View.OnLongClickListener {

    ImageView imgBack;
    ImageView imvTickBoxes;
    ImageView imvAddCheck;
    ImageView imvCamera;
    ImageView imvClips;
    ImageView imvPlay;
    ImageView imvPause;
    ImageView imvDelete;
    ImageView imvProgress;
    ImageView imvNoName;
    ImageView imvHagsTag;
    EditText edtTitle;
    EditText edtNote;
    TextView tvSave;
    TextView tvOk;
    LinearLayout llCheck;
    LinearLayout llRecording;
    LinearLayout llColor;

    RecyclerView rcIMG;
    TickBoxAdapter tickBoxAdapter;
    ProgressBar progress_bar;
    RecyclerView rcCheck;

    RelativeLayout rc_layout;
    private ImageView image_color_black;
    private ImageView image_color_red;
    private ImageView image_color_yellow;
    private ImageView image_color_green;
    private ImageView image_color_blue;
    private ImageView image_color_pink;
    private ImageView image_color_brown;

    ArrayList<ImgModel> imgModelArrayList = new ArrayList<>();
    ArrayList<NoteModel> noteModels = new ArrayList<>();
    ArrayList<TickBoxModel> tickBoxModelArrayList = new ArrayList<>();
    ArrayList<HagsTagModel> hagsTagModels= new ArrayList<>();
    RecModel recModel;

    private MediaRecorder myAudioRecorder;
    private InputMethodManager imm;
    private String outputFile;
    private DecimalFormat df = new DecimalFormat("#00");
    private Handler handler = new Handler();
    MediaPlayer m;
    int id = 0;
    int kt = 0;
    int nameRecordingg;
    int nameTake;
    int countTime;
    int countTimeOne = 0;
    int ktTake = 0;
    int checkTickBox;
    int checkTick;
    int checkImg;
    int checkRec;
    int checkClips;
    int checkTag;
    int checkAlarm;
    int color=0;
    int colorSelect=0;
    String link;
    Dialog dialog;
    ImgAdapter imgAdapter;
    MyDataBaseHelper myDataBaseHelper;

    @Override
    protected int getLayoutResource() {
        return (R.layout.fragment_add_notes);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState, View rootView) {
        imgBack = rootView.findViewById(R.id.imgBack);
        imvPlay = rootView.findViewById(R.id.imvPlay);
        imvPause = rootView.findViewById(R.id.imvPause);
        imvTickBoxes = rootView.findViewById(R.id.imvTickBoxes);
        imvAddCheck = rootView.findViewById(R.id.imvAddCheck);
        imvClips = rootView.findViewById(R.id.imvClips);
        imvNoName = rootView.findViewById(R.id.imvNoName);
        imvHagsTag = rootView.findViewById(R.id.imvHagsTag);

        imvProgress = rootView.findViewById(R.id.imvProgress);
        imvDelete = rootView.findViewById(R.id.imvDelete);

        edtTitle = rootView.findViewById(R.id.edtTitle);
        edtNote = rootView.findViewById(R.id.edtNote);
        tvSave = rootView.findViewById(R.id.tvSave);
        tvOk = rootView.findViewById(R.id.tvOk);
        llCheck = rootView.findViewById(R.id.llCheck);
        llRecording = rootView.findViewById(R.id.llRecording);
        rcCheck = rootView.findViewById(R.id.rcCheck);
        llColor = rootView.findViewById(R.id.llColor);

        image_color_black= rootView.findViewById(R.id.image_color_black);
        image_color_red= rootView.findViewById(R.id.image_color_red);
        image_color_yellow= rootView.findViewById(R.id.image_color_yellow);
        image_color_green= rootView.findViewById(R.id.image_color_green);
        image_color_blue= rootView.findViewById(R.id.image_color_blue);
        image_color_pink= rootView.findViewById(R.id.image_color_pink);
        image_color_brown= rootView.findViewById(R.id.image_color_brown);
        rc_layout= rootView.findViewById(R.id.rc_layout);


        rcIMG = rootView.findViewById(R.id.rcIMG);
        progress_bar = rootView.findViewById(R.id.progress_bar);
        edtTitle.setSingleLine(true);
        edtNote.setSingleLine(true);

        imgBack.setOnClickListener(this);
        imvPlay.setOnClickListener(this);
        imvPause.setOnClickListener(this);
        imvClips.setOnClickListener(this);
        imvTickBoxes.setOnClickListener(this);
        imvAddCheck.setOnClickListener(this);
        imvDelete.setOnClickListener(this);
        imvNoName.setOnClickListener(this);
        imvHagsTag.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvOk.setOnClickListener(this);
        llRecording.setOnClickListener(this);
        llRecording.setOnLongClickListener(this);

        image_color_black.setOnClickListener(this);
        image_color_red.setOnClickListener(this);
        image_color_yellow.setOnClickListener(this);
        image_color_green.setOnClickListener(this);
        image_color_blue.setOnClickListener(this);
        image_color_pink.setOnClickListener(this);
        image_color_brown.setOnClickListener(this);


    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        SharedPreferencesUtils.setInt(mContext, Constants.CHECK_CAMERA, 0);
        myDataBaseHelper = new MyDataBaseHelper(mContext);
        checkTick = 0;
        checkImg = 0;
        checkRec = 0;
        checkAlarm = 0;
        checkClips = 0;
        checkTag = 0;



    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack: {
                getMethodManager().hideSoftInputFromWindow(view.getWindowToken(), 0);
                Objects.requireNonNull(getActivity()).onBackPressed();

                break;
            }


            case R.id.imvAddCheck: {
                checkTickBox++;
                tickBoxModelArrayList.add(new TickBoxModel(SharedPreferencesUtils.getInt(mContext, Constants.CHECK_ID_NOTE), ""));
                tickBoxAdapter.updateListCheck(tickBoxModelArrayList);
                rcCheck.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                rcCheck.setAdapter(tickBoxAdapter);

                tickBoxAdapter.setCallback(new TickBoxAdapter.CallBack() {
                    @Override
                    public void deleteCheck(TickBoxModel tickBoxModel) {
                        tickBoxModelArrayList.remove(tickBoxModel);
                        Log.i("idTickBox", tickBoxModel.getIdBox() + "");
                        tickBoxAdapter.updateListCheck(tickBoxModelArrayList);
                        rcCheck.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                        rcCheck.setAdapter(tickBoxAdapter);
                    }
                });

                break;
            }
            case R.id.imvTickBoxes: {

                menuClock();
                break;
            }
            case R.id.imvClips: {
                if (checkClips == 0) {
                    checkClips = 1;
                    imvClips.setBackgroundResource(R.color.colorPrimaryDark);
                } else {
                    Toast.makeText(mContext, "ahihi", Toast.LENGTH_SHORT).show();
                    checkClips = 0;
                    imvClips.setBackgroundResource(R.color.colorPrimary);
                }
                break;
            }
            case R.id.imvPlay: {
                progress_bar.setProgress(countTimeOne / ((recModel.getCountTime() - 1) * 10));
                m = new MediaPlayer();
                imvPlay.setVisibility(View.GONE);
                imvPause.setVisibility(View.VISIBLE);
                try {
                    m.setDataSource(recModel.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    m.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                m.start();
                int k = 0;
                countTimeOne = 0;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (m.isPlaying() == true) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    countTimeOne = countTimeOne + 100;

                                    if ((countTime * 1000 - 1000) == countTimeOne) {
                                        imvPlay.setVisibility(View.VISIBLE);
                                        imvPause.setVisibility(View.GONE);
                                    }
                                    Log.i("countTime", countTimeOne + "");
                                    progress_bar.setProgress(countTimeOne / ((recModel.getCountTime() - 1) * 10));

                                }
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                break;
            }
            case R.id.imvPause: {
                imvPlay.setVisibility(View.VISIBLE);
                imvPause.setVisibility(View.GONE);
                m.stop();
                break;
            }
            case R.id.llRecording: {
                imvDelete.setVisibility(View.GONE);
                imvProgress.setVisibility(View.VISIBLE);

                break;
            }
            case R.id.imvNoName:{
                menuDelete();
                break;
            }
            case R.id.imvDelete: {
                llRecording.setVisibility(View.GONE);
                imvDelete.setVisibility(View.GONE);
                imvProgress.setVisibility(View.VISIBLE);
                nameRecordingg--;
                SharedPreferencesUtils.setInt(getContext(), Constants.NAME_RECORDING, nameRecordingg);
                break;
            }
            case R.id.tvSave: {
                getMethodManager().hideSoftInputFromWindow(view.getWindowToken(), 0);
                //int id = SharedPreferencesUtils.getInt(mContext, Constants.CHECK_ID);
                NoteModel noteModel = new NoteModel(edtTitle.getText().toString(), edtNote.getText().toString(),
                        checkTick, checkImg, checkRec, checkTag, checkClips, checkAlarm, 0, colorSelect);
                myDataBaseHelper.addTableNote(noteModel);
                ArrayList<NoteModel> noteModelArrayList = myDataBaseHelper.getAllNote();
                int id= noteModelArrayList.get(noteModelArrayList.size()-1).getId();


                if (checkTick == 1) {
                    for (int i = 0; i < tickBoxModelArrayList.size(); i++) {
                        TickBoxModel tickBoxModel = tickBoxModelArrayList.get(i);
                        tickBoxModel.setIdNote(id );
                        myDataBaseHelper.addTableBox(tickBoxModel);

                    }

                }
                if (checkImg == 1) {
                    for (int i = 0; i < imgModelArrayList.size(); i++) {
                        ImgModel imgModel = imgModelArrayList.get(i);
                        imgModel.setIdNote(id );
                        myDataBaseHelper.addTableImg(imgModel);
                        Log.i("imgModelArrayList", imgModel.getPath());

                    }
                }
                if (checkRec == 1) {
                    recModel.setIdNote(id);
                    myDataBaseHelper.addTableRec(recModel);
                }
                if(checkTag==1){
                    for(int i=0;i<hagsTagModels.size();i++){
                        HagsTagModel hagsTagModel= hagsTagModels.get(i);
                        hagsTagModel.setIdNote(id);
                        myDataBaseHelper.addTableHagsTagSelect(hagsTagModel);
                        Log.i("PhuocMl", hagsTagModel.getIdNote()+"onClick: ");
                    }
                    Log.i("PhuocMl", id+"onClick: ");
                }

                callBack.loadCheckBox(noteModel);
                Objects.requireNonNull(getActivity()).onBackPressed();

                break;
            }
            case R.id.image_color_black:{
               rc_layout.setBackgroundResource(R.color.color_white);
               color=0;
                break;
            }
            case R.id.image_color_red:{
                rc_layout.setBackgroundResource(R.color.color_red);
                color=1;
                break;
            }
            case R.id.image_color_green:{
                rc_layout.setBackgroundResource(R.color.color_green);
                color=2;
                break;
            }
            case R.id.image_color_blue:{
                rc_layout.setBackgroundResource(R.color.color_blue);
                color=3;
                break;
            }
            case R.id.image_color_yellow:{
                rc_layout.setBackgroundResource(R.color.color_yellow);
                color=4;
                break;
            }
            case R.id.image_color_pink:{
                rc_layout.setBackgroundResource(R.color.color_pink);
                color=5;
                break;
            }
            case R.id.image_color_brown:{
                rc_layout.setBackgroundResource(R.color.color_brown);
                color=6;
                break;
            }
            case R.id.tvOk:{
                llColor.setVisibility(View.GONE);
                tvOk.setVisibility(View.GONE);
                colorSelect=color;
                break;
            }
            case R.id.imvHagsTag:{
                HagsTagSelectFragment hagsTagSelectFragment= new HagsTagSelectFragment();
                ((MainActivity) Objects.requireNonNull(getActivity())).nextFragment(hagsTagSelectFragment, R.id.rc_main, 0, 0, 0, 0);
                hagsTagSelectFragment.setCallBack(new HagsTagSelectFragment.CallBack() {
                    @Override
                    public void hagstagUp(ArrayList<HagsTagModel> hagsTagModelss) {
                        Toast.makeText(mContext, "HienOc", Toast.LENGTH_SHORT).show();
                        hagsTagModels=hagsTagModelss;
                        if(hagsTagModels.size()>0){
                            checkTag=1;
                        }
                    }
                });
                break;

            }

        }

    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.llRecording: {
                Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
                imvDelete.setVisibility(View.VISIBLE);
                imvProgress.setVisibility(View.GONE);
                return true;
            }

        }
        return false;
    }

    @SuppressLint("RestrictedApi")
    private void menuClock() {
        MenuBuilder menuBuilder = new MenuBuilder(getContext());
        MenuInflater inflater = new MenuInflater(getContext());
        inflater.inflate(R.menu.menu_add_note, menuBuilder);
        MenuPopupHelper optionsMenu = new MenuPopupHelper(getContext(), menuBuilder, imvTickBoxes);
        optionsMenu.setForceShowIcon(true);
        optionsMenu.show();
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuList:
                        checkTick = 1;
                        edtNote.setVisibility(View.GONE);
                        llCheck.setVisibility(View.VISIBLE);
                        if (checkTickBox == 0) {
                            tickBoxModelArrayList.add(new TickBoxModel(id + 1, edtNote.getText().toString()));
                            edtNote.setText("");
                            tickBoxAdapter = new TickBoxAdapter(getContext(), tickBoxModelArrayList);
                            rcCheck.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                            rcCheck.setAdapter(tickBoxAdapter);

                            tickBoxAdapter.setCallback(new TickBoxAdapter.CallBack() {
                                @Override
                                public void deleteCheck(TickBoxModel tickBoxModel) {
                                    tickBoxModelArrayList.remove(tickBoxModel);
                                    tickBoxAdapter.updateListCheck(tickBoxModelArrayList);
                                    rcCheck.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                                    rcCheck.setAdapter(tickBoxAdapter);
                                }
                            });
                        }
                        return true;
                    case R.id.menuImg:

                        if (ContextCompat.checkSelfPermission(mContext,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            Permission.getPermissionRead(getActivity());
                        } else {
                            checkImg = 1;
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                            startActivityForResult(intent, 1);
                        }

                        return true;
                    case R.id.menuCamera:

                        if (ContextCompat.checkSelfPermission(mContext,
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            Permission.getPermissionCamera(getActivity());
                        } else {
                            checkImg = 1;
                            captureImage();
                        }
                        return true;
                    case R.id.menuMic: // Handle option1 Click

                        if (ContextCompat.checkSelfPermission(mContext,
                                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                            Permission.getPermissionRec(getActivity());
                        } else {
                            checkRec = 1;
                            showDialogMic();
                        }

                        return true;
                    case R.id.menuDraw: {
                        AddDrawingFragment addDrawingFragment = new AddDrawingFragment();
                        ((MainActivity) Objects.requireNonNull(getActivity())).nextFragment(addDrawingFragment, R.id.rc_layout, 0, 0, 0, 0);
                        addDrawingFragment.setCallBack(new AddDrawingFragment.CallBack() {
                            @Override
                            public void setIMG(String s) {
                                if (SharedPreferencesUtils.getInt(mContext, Constants.CHECK_DRAW) == 1) {
                                    checkImg = 1;
                                    SharedPreferencesUtils.setInt(mContext, Constants.CHECK_DRAW, 0);
                                    imgModelArrayList.add(new ImgModel(id + 1, s));
                                    rcIMG.setVisibility(View.VISIBLE);
                                    if (imgModelArrayList.size() == 1) {
                                        imgAdapter = new ImgAdapter(imgModelArrayList, mContext, 1);
                                        rcIMG.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                                    } else {
                                        imgAdapter = new ImgAdapter(imgModelArrayList, mContext, 2);
                                        if (imgModelArrayList.size() == 2) {
                                            rcIMG.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                                        } else {
                                            rcIMG.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                                        }

                                    }
                                    rcIMG.setAdapter(imgAdapter);

                                }

                            }
                        });
                        return true;
                    }
                    default:
                        return false;
                }
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {

            }
        });
    }
    @SuppressLint("RestrictedApi")
    private void menuDelete() {
        MenuBuilder menuBuilder = new MenuBuilder(getContext());
        MenuInflater inflater = new MenuInflater(getContext());
        inflater.inflate(R.menu.menu_note_add_update, menuBuilder);
        MenuPopupHelper optionsMenu = new MenuPopupHelper(getContext(), menuBuilder, imvNoName);
        optionsMenu.setForceShowIcon(false);
        optionsMenu.show();
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuColor:
                        llColor.setVisibility(View.VISIBLE);
                        tvOk.setVisibility(View.VISIBLE);
                        return true;
                    case R.id.menuDelete:
                        showDialog();
                        return true;

                    default:
                        return false;
                }
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {

            }
        });
    }


    public void showDialogMic() {
        nameRecordingg = SharedPreferencesUtils.getInt(getContext(), Constants.NAME_RECORDING);

        Log.i("name_recording", nameRecordingg + "");
        String nameRecording = "/recording" + String.valueOf(nameRecordingg);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + nameRecording + ".3gp";
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);


        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_record);
        dialog.setCanceledOnTouchOutside(false);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        final ImageView imvMic = dialog.findViewById(R.id.imvMic);
        final ImageView imvMicOn = dialog.findViewById(R.id.imvMicOn);
        final TextView tvTime = dialog.findViewById(R.id.tvTime);
        imvMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kt = 0;
                countTime = 0;
                imvMicOn.setVisibility(View.VISIBLE);
                imvMic.setVisibility(View.GONE);
                tvTime.setVisibility(View.VISIBLE);
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IllegalStateException ise) {
                    // make something ...
                } catch (IOException ioe) {
                    // make something
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (kt == 0) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    int m, s;
                                    m = countTime / 60;
                                    s = countTime - m * 60;
                                    tvTime.setText(df.format(m) + ":" + df.format(s));
                                    countTime++;
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                }).start();


            }
        });
        imvMicOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                dialog.dismiss();
                kt = 1;
                nameRecordingg++;
                SharedPreferencesUtils.setInt(getContext(), Constants.NAME_RECORDING, nameRecordingg);
                recModel = new RecModel(id + 1, countTime, outputFile);
                Log.i("recModelArrayList", id + "");
                llRecording.setVisibility(View.VISIBLE);

            }
        });

        dialog.show();
    }
    public void showDialog(){

        dialog= new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_delete_note);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        Button btnDelete= dialog.findViewById(R.id.btnDelete);
        Button btnCancel= dialog.findViewById(R.id.btnCancel);
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
                Objects.requireNonNull(getActivity()).onBackPressed();
                dialog.dismiss();

            }
        });


        dialog.show();
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File dir = Environment.getExternalStorageDirectory();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        nameTake = SharedPreferencesUtils.getInt(getContext(), Constants.NAME_TAKE);
        String namePhoto = "/note_img_" + String.valueOf(nameTake);
        String savePath = dir.getAbsolutePath() + namePhoto + ".jpg";
        link = savePath;
        File videoFile = new File(savePath);
        Uri videoUri = Uri.fromFile(videoFile);

        // Chỉ định vị trí lưu file video khi quay.
        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);

        // Start Activity quay video, và chờ đợi kết quả trả về.
        this.startActivityForResult(intent, 2);
    }

    @Override
    public void onResume() {
        super.onResume();

        llColor.setVisibility(View.GONE);
        tvOk.setVisibility(View.GONE);

        Log.i("imgModelArrayList", "onResume: ");
        if(hagsTagModels.size()>0){
            imvHagsTag.setBackgroundResource(R.color.colorPrimaryDark);
            checkTag=1;
        }
        if (imgModelArrayList.size() == 1) {
            imgAdapter = new ImgAdapter(imgModelArrayList, mContext, 1);
            rcIMG.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        } else {
            imgAdapter = new ImgAdapter(imgModelArrayList, mContext, 2);
            if (imgModelArrayList.size() == 2) {
                rcIMG.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            } else {
                rcIMG.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
            }

        }
        rcIMG.setAdapter(imgAdapter);
        imgAdapter.setCallBack(new ImgAdapter.CallBack() {
            @Override
            public void loadImg(ImgModel imgModel) {
                imgModelArrayList.remove(imgModel);
                if (imgModelArrayList.size() == 1) {
                    imgAdapter.updateImg(imgModelArrayList, 1);
                } else {
                    imgAdapter.updateImg(imgModelArrayList, 2);
                }
            }
        });
    }

    @SuppressLint("NewApi")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {

                Uri uri = data.getData();
                String path = "";
                String[] project = {MediaStore.Images.Media.DATA};
                Cursor cursor = mContext.getContentResolver().query(uri, project, null, null, null);
                if (cursor != null) {
                    int i = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    path = cursor.getString(i);
                    cursor.close();
                }

                imgModelArrayList.add(new ImgModel(id + 1, path));
                Log.i("imgModelArrayList", imgModelArrayList.size()+"onActivityResult: ");

            }
        }
        if (requestCode == 2) {

            if (resultCode == RESULT_OK) {
                nameTake++;
                SharedPreferencesUtils.setInt(getContext(), Constants.NAME_TAKE, nameTake);
                ktTake = 1;
                imgModelArrayList.add(new ImgModel(id + 1, link));
            }
        }
        rcIMG.setVisibility(View.VISIBLE);


        super.onActivityResult(requestCode, resultCode, data);
    }

    public interface CallBack {
        void loadCheckBox(NoteModel noteModel);
    }

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
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
}
