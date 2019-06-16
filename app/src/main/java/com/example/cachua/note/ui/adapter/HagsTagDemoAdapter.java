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
import com.example.cachua.note.ui.fragment.HagsTagSelectFragment;
import com.example.cachua.note.ui.fragment.HagtagSearchFragment;
import com.example.cachua.note.ui.fragment.HomeNoteFragment;
import com.example.cachua.note.ui.model.HagsTagDemoModel;
import com.example.cachua.note.ui.model.HagsTagModel;

import java.util.ArrayList;
import java.util.Objects;

public class HagsTagDemoAdapter extends RecyclerView.Adapter<HagsTagDemoAdapter.ViewHolder> {
    private ArrayList<HagsTagDemoModel> hagsTagDemoModels;
    private Context mContext;

    public HagsTagDemoAdapter(ArrayList<HagsTagDemoModel> hagsTagDemoModels, Context mContext) {
        this.hagsTagDemoModels = hagsTagDemoModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mContext);
        View view= layoutInflater.inflate(R.layout.layout_hags_tag_one,parent,false);
        HagsTagDemoAdapter.ViewHolder viewHolder = new HagsTagDemoAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HagsTagDemoModel hagsTagDemoModel= hagsTagDemoModels.get(position);
        holder.tvHagsTag.setText(hagsTagDemoModel.getDescribe());
        holder.tvHagsTag.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "hhfhdhdj", Toast.LENGTH_SHORT).show();
                callback.downTurnOff();
                SharedPreferencesUtils.setInt(mContext, Constants.CHECK_TAG,hagsTagDemoModel.getId());
                Log.i("HienNGuuuuu", hagsTagDemoModel.getId()+"onClick: ");
                HagtagSearchFragment hagsTagSelectFragment= new HagtagSearchFragment();
                ((MainActivity) Objects.requireNonNull(mContext)).nextFragment(hagsTagSelectFragment, R.id.frHome, 0, 0, 0, 0);

            }
        });

    }

    @Override
    public int getItemCount() {
        return hagsTagDemoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHagsTag;
        public ViewHolder(View itemView) {
            super(itemView);
            tvHagsTag= itemView.findViewById(R.id.tvHagsTag);
        }
    }
    public void updateHagsTag(ArrayList<HagsTagDemoModel> hagsTagDemoModelss){
        hagsTagDemoModels=hagsTagDemoModelss;
        notifyDataSetChanged();
    }

    public interface Callback{
        void downTurnOff();
    }
    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
