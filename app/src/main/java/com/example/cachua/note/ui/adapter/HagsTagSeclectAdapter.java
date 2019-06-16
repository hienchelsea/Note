package com.example.cachua.note.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.model.HagsTagDemoModel;

import java.util.ArrayList;

public class HagsTagSeclectAdapter extends RecyclerView.Adapter<HagsTagSeclectAdapter.ViewHolder> {
    private ArrayList<HagsTagDemoModel> hagsTagDemoModels;
    private Context mContext;

    public HagsTagSeclectAdapter(ArrayList<HagsTagDemoModel> hagsTagDemoModels, Context mContext) {
        this.hagsTagDemoModels = hagsTagDemoModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mContext);
        View view= layoutInflater.inflate(R.layout.layout_hags_tag_seclect,parent,false);
        HagsTagSeclectAdapter.ViewHolder viewHolder = new HagsTagSeclectAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HagsTagDemoModel hagsTagDemoModel= hagsTagDemoModels.get(position);
        holder.tvHagsTag.setText(hagsTagDemoModel.getDescribe());
        holder.cbHagsTag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int i=0;
                if(isChecked==true){
                    callBack.updateHagsTag(hagsTagDemoModel.getDescribe(),hagsTagDemoModel.getId());
                    i=1;
                }
                else{
                    if(i==1){
                        callBack.deleteHagsTag(hagsTagDemoModel.getDescribe(),hagsTagDemoModel.getId());
                    }

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return hagsTagDemoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHagsTag;
        CheckBox cbHagsTag;
        public ViewHolder(View itemView) {
            super(itemView);
            tvHagsTag= itemView.findViewById(R.id.tvHagsTag);
            cbHagsTag= itemView.findViewById(R.id.cbHagsTag);
        }
    }
    public void updateHagsTag(ArrayList<HagsTagDemoModel> hagsTagDemoModelss){
        hagsTagDemoModels=hagsTagDemoModelss;
        notifyDataSetChanged();
    }

    public interface CallBack{
        void updateHagsTag(String s,int m);
        void deleteHagsTag(String s,int m);
    }
    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
