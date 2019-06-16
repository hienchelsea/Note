package com.example.cachua.note.ui.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.activity.MainActivity;
import com.example.cachua.note.ui.config.Constants;
import com.example.cachua.note.ui.config.Permission;
import com.example.cachua.note.ui.config.SharedPreferencesUtils;
import com.example.cachua.note.ui.database.MyDataBaseHelper;
import com.example.cachua.note.ui.fragment.AddDrawingFragment;
import com.example.cachua.note.ui.fragment.AddNotesFragment;
import com.example.cachua.note.ui.fragment.UpdateNotesFragment;
import com.example.cachua.note.ui.model.ImgModel;
import com.example.cachua.note.ui.model.NoteModel;
import com.example.cachua.note.ui.model.RecModel;
import com.example.cachua.note.ui.model.TickBoxModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private ArrayList<NoteModel> noteModelArrayList;
    private Context mContext;
    private MyDataBaseHelper myDataBaseHelper;
    int idNote;
    Dialog dialog;

    public NoteAdapter(ArrayList<NoteModel> noteModelArrayList, Context mContext) {
        this.noteModelArrayList = noteModelArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.layout_adapter_note, parent, false);
        NoteAdapter.ViewHolder viewHolder = new NoteAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final NoteModel noteModel= noteModelArrayList.get(position);
        holder.tvName.setText(noteModel.getName());
        holder.tvDescribe.setText(noteModel.getDescribe());
        myDataBaseHelper= new MyDataBaseHelper(mContext);
        holder.llImg.setVisibility(View.GONE);
        holder.llRec.setVisibility(View.GONE);
        holder.llNoName.setVisibility(View.GONE);
        if(noteModel.getColor()==0){
            holder.cvNote.setBackgroundResource(R.color.color_white);
        }
        else{
            if(noteModel.getColor()==1){
                holder.cvNote.setBackgroundResource(R.color.color_red);
            }
            else{
                if(noteModel.getColor()==2){
                    holder.cvNote.setBackgroundResource(R.color.color_green);
                }
                else{
                    if(noteModel.getColor()==3){
                        holder.cvNote.setBackgroundResource(R.color.color_blue);
                    }
                    else{
                        if(noteModel.getColor()==4){
                            holder.cvNote.setBackgroundResource(R.color.color_yellow);
                        }
                        else{
                            if(noteModel.getColor()==5){
                                holder.cvNote.setBackgroundResource(R.color.color_pink);
                            }
                            else{
                                if(noteModel.getColor()==6){
                                    holder.cvNote.setBackgroundResource(R.color.color_brown);
                                }
                            }
                        }
                    }
                }
            }
        }
        if(noteModel.getNoteIMG()==1){
            holder.llImg.setVisibility(View.VISIBLE);
            ArrayList<ImgModel> imgModelArrayList= myDataBaseHelper.getAllImg(noteModel.getId());
            if(imgModelArrayList.size()==1){
                holder.imgNoteHomeAdapter= new ImgNoteHomeAdapter(imgModelArrayList,mContext,1);
                holder.rcImgNote.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            }
            else {
                if(imgModelArrayList.size()==2){
                    holder.imgNoteHomeAdapter= new ImgNoteHomeAdapter(imgModelArrayList,mContext,1);
                    holder.rcImgNote.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                }
                else{
                    holder.imgNoteHomeAdapter= new ImgNoteHomeAdapter(imgModelArrayList,mContext,2);
                    holder.rcImgNote.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                }

            }

            holder.rcImgNote.setAdapter(holder.imgNoteHomeAdapter);


        }

        if(noteModel.getNoteRec()==1){
            holder.llRec.setVisibility(View.VISIBLE);
        }
        if(noteModel.getNoteNox()==1){
            holder.llDescribe.setVisibility(View.VISIBLE);
            holder.rcCheckBox.setVisibility(View.VISIBLE);
            String box="";
            ArrayList<TickBoxModel> tickBoxModels= myDataBaseHelper.getAllBox(noteModel.getId());
            holder.tickBoxAdapter= new TickBoxHomeAdapter(mContext,tickBoxModels);
            holder.rcCheckBox.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            holder.rcCheckBox.setAdapter(holder.tickBoxAdapter);
        }
        else{
            if(holder.tvDescribe.getText().toString().equals("")==true){
                holder.llDescribe.setVisibility(View.GONE);
            }
            else{
                holder.llDescribe.setVisibility(View.VISIBLE);
                holder.rcCheckBox.setVisibility(View.GONE);
                if(holder.tvName.getText().toString().equals("")==true){
                    holder.tvName.setVisibility(View.GONE);
                    holder.llNoName.setVisibility(View.GONE);
                }
                else{
                    holder.tvName.setVisibility(View.VISIBLE);
                }
            }
        }

        holder.cvNote.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Hien Oc", Toast.LENGTH_SHORT).show();
                UpdateNotesFragment updateNotesFragment = new UpdateNotesFragment();
                ((MainActivity) Objects.requireNonNull(mContext)).nextFragment(updateNotesFragment, R.id.rc_main, 0, 0, 0, 0);
                SharedPreferencesUtils.setInt(mContext, Constants.CHECK_ID_NOTE,noteModel.getId());

                updateNotesFragment.setCallBack(new UpdateNotesFragment.CallBack() {
                    @Override
                    public void loadCheckBox(NoteModel noteModel) {
                        callBack.loadNote(noteModel);
                    }
                });
            }
        });

        holder.llNote.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Hien Oc", Toast.LENGTH_SHORT).show();
                UpdateNotesFragment updateNotesFragment = new UpdateNotesFragment();
                ((MainActivity) Objects.requireNonNull(mContext)).nextFragment(updateNotesFragment, R.id.rc_main, 0, 0, 0, 0);
                SharedPreferencesUtils.setInt(mContext, Constants.CHECK_ID_NOTE,noteModel.getId());

                updateNotesFragment.setCallBack(new UpdateNotesFragment.CallBack() {
                    @Override
                    public void loadCheckBox(NoteModel noteModel) {
                        callBack.loadNote(noteModel);
                    }
                });
            }
        });
        holder.imvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressLint("RestrictedApi")
            public void onClick(View view) {
                menuSetting(noteModel,holder.imvSetting);
            }
        });



    }

    @Override
    public int getItemCount() {
        return noteModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llNote;
        private TextView tvDescribe;
        private TextView tvName;
        private ImageView imvSetting;
        private LinearLayout llImg;
        private LinearLayout llRec;
        private LinearLayout llDescribe;
        private LinearLayout llNoName;
        private CardView cvNote;
        private RecyclerView rcImgNote;
        private RecyclerView rcCheckBox;
        private ImgNoteHomeAdapter imgNoteHomeAdapter;
        private TickBoxHomeAdapter tickBoxAdapter;
        public ViewHolder(View itemView) {
            super(itemView);
            llNote= itemView.findViewById(R.id.llNote);
            tvDescribe= itemView.findViewById(R.id.tvDescribe);
            tvName= itemView.findViewById(R.id.tvName);
            llImg= itemView.findViewById(R.id.llImg);
            imvSetting= itemView.findViewById(R.id.imvSetting);
            llRec= itemView.findViewById(R.id.llRec);
            rcImgNote= itemView.findViewById(R.id.rcImgNote);
            rcCheckBox= itemView.findViewById(R.id.rcCheckBox);
            llDescribe= itemView.findViewById(R.id.llDescribe);
            llNoName= itemView.findViewById(R.id.llNoName);
            cvNote= itemView.findViewById(R.id.cvNote);


        }
    }
    @SuppressLint("RestrictedApi")
    private void menuSetting(final NoteModel noteModel, ImageView imvTickBoxes) {
        MenuBuilder menuBuilder =new MenuBuilder(mContext);
        MenuInflater inflater = new MenuInflater(mContext);
        inflater.inflate(R.menu.menu_note_home, menuBuilder);
        MenuPopupHelper optionsMenu = new MenuPopupHelper(mContext, menuBuilder, imvTickBoxes);
        optionsMenu.setForceShowIcon(true);
        optionsMenu.show();
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuGhim:
                        noteModel.setClips(1);
                        myDataBaseHelper.updateTableNote(noteModel);
                        callBack.loadNote(noteModel);

                        return true;
                    case R.id.menuSetting:
                        UpdateNotesFragment updateNotesFragment = new UpdateNotesFragment();
                        ((MainActivity) Objects.requireNonNull(mContext)).nextFragment(updateNotesFragment, R.id.rc_main, 0, 0, 0, 0);
                        SharedPreferencesUtils.setInt(mContext, Constants.CHECK_ID_NOTE,noteModel.getId());

                        updateNotesFragment.setCallBack(new UpdateNotesFragment.CallBack() {
                            @Override
                            public void loadCheckBox(NoteModel noteModel) {
                                callBack.loadNote(noteModel);
                            }
                        });


                        return true;
                    case R.id.menuDelete:
                        showDialog(noteModel);

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

    public void showDialog(final NoteModel noteModel){

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
            @Override
            public void onClick(View view) {
                myDataBaseHelper.deleteTableNote(noteModel);
                myDataBaseHelper.deleteTableBoxIdNote(noteModel.getId());
                myDataBaseHelper.deleteTableImgIdNote(noteModel.getId());
                myDataBaseHelper.deleteTableRecIdNote(noteModel.getId());
                callBack.loadNote(noteModel);
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public void updateListCheck(ArrayList<NoteModel> tickBoxModelArrayList) {
        this.noteModelArrayList = tickBoxModelArrayList;
        notifyDataSetChanged();
    }

    public  interface CallBack{
        void loadNote(NoteModel noteModel);
    }
    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

}
