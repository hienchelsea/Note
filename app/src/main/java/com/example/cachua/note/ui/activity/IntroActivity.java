package com.example.cachua.note.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.adapter.IntroViewPagerAdapter;
import com.example.cachua.note.ui.config.Constants;
import com.example.cachua.note.ui.config.SharedPreferencesUtils;

public class IntroActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private IntroViewPagerAdapter mPagerAdapter;
    private LinearLayout mDotsLayout;
    private TextView[] mDots;
    private int[] mLayouts;
    private Button mBtnSkip;
    private Button mBtnNext;
  //  private PrefManager mPrefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(SharedPreferencesUtils.getBoolean(this, Constants.CHECK_FIRST)==true){
            launchHomeScreen();
        }
        else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            }
            setContentView(R.layout.activity_intro);

            mViewPager = (ViewPager) findViewById(R.id.view_pager);
            mDotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
            mBtnSkip = (Button) findViewById(R.id.btn_skip);
            mBtnNext = (Button) findViewById(R.id.btn_next);

            /**
             * Layouts of all welcome slides
             * add few more layouts if you want
             */
            mLayouts = new int[]{R.layout.welcome_slide1, R.layout.welcome_slide2, R.layout.welcome_slide3};
            addBottomDots(0);
            changeStatusBarColor();

            mPagerAdapter = new IntroViewPagerAdapter(this,mLayouts);
            mViewPager.setAdapter(mPagerAdapter);
            mViewPager.addOnPageChangeListener(mViewPagerChangeListener);

            mBtnSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchHomeScreen();
                }
            });

            mBtnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * Checking for last page if last page home screen will be launched
                     */
                    int current = getItem(1);
                    if (current < mLayouts.length) {
                        // move to nex screen
                        mViewPager.setCurrentItem(current);
                    } else {
                        launchHomeScreen();
                    }

                }
            });
        }

//        if (!PrefManager.getInstance(this).isFirstTimeLaunch()) {
//            launchHomeScreen();
//        }

        //Making notification bar transparent

    }

    private ViewPager.OnPageChangeListener mViewPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            //Change the next button text 'NEXT'/'GOT IT'
            if (position == mLayouts.length - 1) {
                mBtnNext.setText("Bắt đầu");
                mBtnSkip.setVisibility(View.GONE);
            } else {
                //Still pages are left
                mBtnNext.setText("Tiếp theo");
                mBtnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void addBottomDots(int currentPage) {
        mDots = new TextView[mLayouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInActive = getResources().getIntArray(R.array.array_dot_inactive);

        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("•"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(colorsInActive[currentPage]);
            mDotsLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[currentPage].setTextColor(colorsActive[currentPage]);
        }
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
