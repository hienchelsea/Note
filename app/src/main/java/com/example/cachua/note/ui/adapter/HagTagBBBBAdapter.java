package com.example.cachua.note.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.activity.MainActivity;
import com.example.cachua.note.ui.config.Constants;
import com.example.cachua.note.ui.config.SharedPreferencesUtils;
import com.example.cachua.note.ui.fragment.HagtagSearchFragment;
import com.example.cachua.note.ui.fragment.KqSearchFragment;
import com.example.cachua.note.ui.model.HagsTagDemoModel;

import java.util.ArrayList;
import java.util.Objects;

public class HagTagBBBBAdapter extends RecyclerView.Adapter<HagTagBBBBAdapter.ViewHolder> {
    private ArrayList<HagsTagDemoModel> hagsTagDemoModels;
    private Context mContext;

    public HagTagBBBBAdapter(ArrayList<HagsTagDemoModel> hagsTagDemoModels, Context mContext) {
        this.hagsTagDemoModels = hagsTagDemoModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mContext);
        View view= layoutInflater.inflate(R.layout.layout_hags_tag_search,parent,false);
        HagTagBBBBAdapter.ViewHolder viewHolder = new HagTagBBBBAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HagsTagDemoModel hagsTagDemoModel= hagsTagDemoModels.get(position);
        holder.tvName.setText(hagsTagDemoModel.getDescribe());
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.setString(mContext, Constants.CHECK_KIND,"Tag");
                SharedPreferencesUtils.setString(mContext, Constants.NAME_TAG,hagsTagDemoModel.getDescribe());
                Toast.makeText(mContext, "hhfhdhdj", Toast.LENGTH_SHORT).show();
                SharedPreferencesUtils.setInt(mContext, Constants.CHECK_TAG,hagsTagDemoModel.getId());
                Log.i("HienNGuuuuu", hagsTagDemoModel.getId()+"onClick: ");
                KqSearchFragment kqSearchFragment = new KqSearchFragment();
                ((MainActivity) Objects.requireNonNull(mContext)).nextFragment(kqSearchFragment, R.id.rc_main, 0, 0, 0, 0);

            }
        });

    }

    @Override
    public int getItemCount() {
        return hagsTagDemoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName= itemView.findViewById(R.id.tvName);
        }
    }
}
