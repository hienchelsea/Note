package com.example.cachua.note.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.database.MyDataBaseHelper;
import com.example.cachua.note.ui.model.TickBoxModel;

import java.util.ArrayList;

public class TickBoxHomeAdapter extends RecyclerView.Adapter<TickBoxHomeAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<TickBoxModel> tickBoxModelArrayList= new ArrayList<>();
    private MyDataBaseHelper myDataBaseHelper;

    public TickBoxHomeAdapter(Context mContext, ArrayList<TickBoxModel> tickBoxModelArrayList) {
        this.mContext = mContext;
        this.tickBoxModelArrayList = tickBoxModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.layout_adapter_tick_box_home, parent, false);
        TickBoxHomeAdapter.ViewHolder viewHolder = new TickBoxHomeAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        myDataBaseHelper = new MyDataBaseHelper(mContext);
        final TickBoxModel tickBoxModel= tickBoxModelArrayList.get(position);
      holder.tvDescribe.setText(tickBoxModel.getDescribe());


    }

    @Override
    public int getItemCount() {
        return tickBoxModelArrayList.size();
    }

    public void updateListCheck(ArrayList<TickBoxModel> tickBoxModels) {
        this.tickBoxModelArrayList = tickBoxModels;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDescribe;
        public ViewHolder(View itemView) {
            super(itemView);
            tvDescribe= itemView.findViewById(R.id.tvDescribe);
        }
    }
    public interface CallBack{
        void deleteCheck(TickBoxModel s);
    }

    private CallBack callback;

    public void setCallback(CallBack callback) {
        this.callback = callback;
    }
}
