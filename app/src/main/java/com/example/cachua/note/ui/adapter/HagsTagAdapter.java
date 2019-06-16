package com.example.cachua.note.ui.adapter;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.model.HagsTagDemoModel;
import com.example.cachua.note.ui.model.HagsTagModel;

import java.util.ArrayList;
import java.util.Objects;

public class HagsTagAdapter extends RecyclerView.Adapter<HagsTagAdapter.ViewHolder> {
    private ArrayList<HagsTagDemoModel> hagsTagDemoModels;
    private Context mContext;
    private Dialog dialog;

    public HagsTagAdapter(ArrayList<HagsTagDemoModel> hagsTagDemoModels, Context mContext) {
        this.hagsTagDemoModels = hagsTagDemoModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mContext);
        View view= layoutInflater.inflate(R.layout.layout_hags_tag,parent,false);
        HagsTagAdapter.ViewHolder viewHolder = new HagsTagAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        HagsTagDemoModel hagsTagModel= hagsTagDemoModels.get(position);
        holder.tvHagsTag.setText(hagsTagModel.getDescribe());

        holder.imvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDelete(holder.imvMenu);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hagsTagDemoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHagsTag;
        ImageView imvMenu;
        public ViewHolder(View itemView) {
            super(itemView);
            tvHagsTag= itemView.findViewById(R.id.tvHagsTag);
            imvMenu= itemView.findViewById(R.id.imvMenu);
        }
    }
    public void updateHagsTag(ArrayList<HagsTagDemoModel> hagsTagModelss){
        hagsTagDemoModels=hagsTagModelss;
        notifyDataSetChanged();
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
                dialog.dismiss();

            }
        });


        dialog.show();
    }
    @SuppressLint("RestrictedApi")
    private void menuDelete(ImageView imvMenu) {
        MenuBuilder menuBuilder = new MenuBuilder(mContext);
        MenuInflater inflater = new MenuInflater(mContext);
        inflater.inflate(R.menu.menu_hags_tag, menuBuilder);
        MenuPopupHelper optionsMenu = new MenuPopupHelper(mContext, menuBuilder, imvMenu);
        optionsMenu.setForceShowIcon(false);
        optionsMenu.show();
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuSetting:
                        DaiLogSetting();
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
                //Objects.requireNonNull(getActivity()).onBackPressed();
                dialog.dismiss();

            }
        });


        dialog.show();
    }
}
