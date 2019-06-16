package com.example.cachua.note.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.adapter.HagsTagDemoAdapter;
import com.example.cachua.note.ui.config.AlphaAnimator;
import com.example.cachua.note.ui.config.Constants;
import com.example.cachua.note.ui.config.Permission;
import com.example.cachua.note.ui.config.SharedPreferencesUtils;
import com.example.cachua.note.ui.database.MyDataBaseHelper;
import com.example.cachua.note.ui.fragment.HagTagFragment;
import com.example.cachua.note.ui.fragment.HomeNoteFragment;
import com.example.cachua.note.ui.fragment.SearchFragment;
import com.example.cachua.note.ui.model.HagsTagDemoModel;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    private RelativeLayout rlSplashView;
    DrawerLayout drawer;
    ImageView imgMenu;
    ImageView imgSearch;
    ImageView imgAdd;
    TextView tvSearch;
    TextView tvNotee;
    TextView edtSetting;
    RecyclerView rcReminder;
    private InputMethodManager imm;
    private HagsTagDemoAdapter hagsTagDemoAdapter;
    private ArrayList<HagsTagDemoModel> hagsTagDemoModels;
    MyDataBaseHelper myDataBaseHelper;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        initSplashView();
        myDataBaseHelper= new MyDataBaseHelper(this);
        hagsTagDemoModels= new ArrayList<>();
        hagsTagDemoModels= myDataBaseHelper.getAllHagsTag();

        hagsTagDemoAdapter= new HagsTagDemoAdapter(hagsTagDemoModels,this);
        rcReminder.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        rcReminder.setAdapter(hagsTagDemoAdapter);

        hagsTagDemoAdapter.setCallback(new HagsTagDemoAdapter.Callback() {
            @Override
            public void downTurnOff() {
                drawer.closeDrawers();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initView() {
        SharedPreferencesUtils.setBoolean(this, Constants.CHECK_FIRST,true);
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED  ){
            Permission.getPermissionRead(MainActivity.this);
        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        imgMenu= findViewById(R.id.imgMenu);
        imgSearch= findViewById(R.id.imgSearch);
        tvSearch= findViewById(R.id.tvSearch);
        tvNotee= findViewById(R.id.tvNotee);
        edtSetting= findViewById(R.id.edtSetting);
        imgAdd= findViewById(R.id.imgAdd);
        rcReminder= findViewById(R.id.rcReminder);
        this.rlSplashView = findViewById(R.id.rlSplashView);



        imgMenu.setOnClickListener(this);
        imgSearch.setOnClickListener(this);
        edtSetting.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        tvNotee.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgMenu:{
                drawer.openDrawer(Gravity.LEFT);
                break;
            }
            case R.id.tvNotee:{
                drawer.closeDrawers();
                HomeNoteFragment hagTagFragment= new HomeNoteFragment();
                ((MainActivity) Objects.requireNonNull(this)).nextFragment(hagTagFragment,R.id.frHome,0,0,0,0);
                break;
            }
            case R.id.imgSearch:{
                SearchFragment searchFragment = new SearchFragment();
                ((MainActivity) Objects.requireNonNull(this)).nextFragment(searchFragment, R.id.rc_main, 0, 0, 0, 0);
                break;
            }
            case R.id.edtSetting:{
                drawer.closeDrawers();
                HagTagFragment hagTagFragment= new HagTagFragment();
                ((MainActivity) Objects.requireNonNull(this)).nextFragment(hagTagFragment,R.id.rc_main,0,0,0,0);
                hagTagFragment.setCallBack(new HagTagFragment.CallBack() {
                    @Override
                    public void Update(HagsTagDemoModel hagsTagDemoModel) {
                        myDataBaseHelper.addTableHagsTag(hagsTagDemoModel);
                        hagsTagDemoModels= myDataBaseHelper.getAllHagsTag();
                        hagsTagDemoAdapter.updateHagsTag(hagsTagDemoModels);
                        Toast.makeText(MainActivity.this, "Hien", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
            case R.id.imgAdd:{
                drawer.closeDrawers();
                HagTagFragment hagTagFragment= new HagTagFragment();
                ((MainActivity) Objects.requireNonNull(this)).nextFragment(hagTagFragment,R.id.rc_main,0,0,0,0);
                hagTagFragment.setCallBack(new HagTagFragment.CallBack() {
                    @Override
                    public void Update(HagsTagDemoModel hagsTagDemoModel) {
                        myDataBaseHelper.addTableHagsTag(hagsTagDemoModel);
                        hagsTagDemoModels= myDataBaseHelper.getAllHagsTag();
                        hagsTagDemoAdapter.updateHagsTag(hagsTagDemoModels);
                        Toast.makeText(MainActivity.this, "Hien", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
        }

    }

    public void nextFragment(Fragment fragment, int id, int anim1, int anim2, int anim3, int anim4) {
        String backStateName = fragment.getClass().getName();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(anim1, anim2, anim3, anim4);
        transaction.replace(id, fragment);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }
    public void initSplashView() {
        this.rlSplashView.setVisibility(View.VISIBLE);
        new CountDownTimer(1000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                AlphaAnimator.goneAnimation(rlSplashView, 600);
            }
        }.start();

    }
}
