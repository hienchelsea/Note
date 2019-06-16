package com.example.cachua.note.ui.adapter;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cachua.note.R;
import com.example.cachua.note.ui.database.MyDataBaseHelper;
import com.example.cachua.note.ui.model.ImgModel;
import com.example.cachua.note.ui.model.NoteModel;

import java.util.ArrayList;

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ViewHolder> {
    private ArrayList<ImgModel> imgModelArrayList;
    private Context mContext;
    int m;
    Dialog dialog;
    MyDataBaseHelper myDataBaseHelper;

    public ImgAdapter(ArrayList<ImgModel>  imagesModelArrayList, Context mContext,int m) {
        this.imgModelArrayList = imagesModelArrayList;
        this.mContext = mContext;
        this.m = m;
    }
    public void updateImg(ArrayList<ImgModel> imgModelArrayList,int m) {
        this.imgModelArrayList = imgModelArrayList;
        this.m = m;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mContext);
        if(m==1){
            View view= layoutInflater.inflate(R.layout.layout_adapter_album_one,parent,false);
            ImgAdapter.ViewHolder viewHolder = new ImgAdapter.ViewHolder(view);
            return viewHolder;
        }
        else{
            View view= layoutInflater.inflate(R.layout.layout_adapter_album,parent,false);
            ImgAdapter.ViewHolder viewHolder = new ImgAdapter.ViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ImgModel imgMode= imgModelArrayList.get(position);
        Glide
                .with( mContext )
                .load( imgMode.getPath() )
                .skipMemoryCache( true )
                .into( holder.imgIMG );

        holder.imgIMG.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDialog(imgMode);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgModelArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIMG;
        public ViewHolder(View itemView) {
            super(itemView);
            imgIMG= itemView.findViewById(R.id.imgIMG);
        }
    }
    public void showDialog(final ImgModel imgModel){
        myDataBaseHelper= new MyDataBaseHelper(mContext);
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
                //myDataBaseHelper.deleteTableImgIdImg(imgModel.getId());
                callBack.loadImg(imgModel);
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public interface CallBack{
        void loadImg(ImgModel imgModel);
    }
    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
