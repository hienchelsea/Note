package com.example.cachua.note.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cachua.note.R;
import com.example.cachua.note.ui.model.ImgModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImgNoteHomeAdapter extends RecyclerView.Adapter<ImgNoteHomeAdapter.ViewHolder> {
    private ArrayList<ImgModel> imgModelArrayList;
    private Context mContext;
    int position;
    int m;

    public ImgNoteHomeAdapter(ArrayList<ImgModel>  imagesModelArrayList, Context mContext, int m) {
        this.imgModelArrayList = imagesModelArrayList;
        this.mContext = mContext;
        this.m = m;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mContext);
        if(m==1){
            View view= layoutInflater.inflate(R.layout.layout_adapter_album_one_note,parent,false);
            ImgNoteHomeAdapter.ViewHolder viewHolder = new ImgNoteHomeAdapter.ViewHolder(view);
            return viewHolder;
        }
        else{
            View view= layoutInflater.inflate(R.layout.layout_adapter_album_note,parent,false);
            ImgNoteHomeAdapter.ViewHolder viewHolder = new ImgNoteHomeAdapter.ViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImgModel imgMode= imgModelArrayList.get(position);

        Picasso.with(mContext).load(Uri.parse(imgMode.getPath())).into(holder.imgIMG);
        Log.i("imgModel", imgMode.getPath());
        if(m==1){
            Glide
                    .with( mContext )
                    .load( imgMode.getPath() )
                    .skipMemoryCache( true )
                    .centerCrop()
                    .into( holder.imgIMG );
        }
        else{
            Glide
                    .with( mContext )
                    .load( imgMode.getPath() )
                    .skipMemoryCache( true )
                    .into( holder.imgIMG );
        }




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
}
