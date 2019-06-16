package com.example.cachua.note.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cachua.note.R;
import com.example.cachua.note.ui.database.MyDataBaseHelper;
import com.example.cachua.note.ui.model.TickBoxModel;

import java.util.ArrayList;

public class TickBoxAdapter extends RecyclerView.Adapter<TickBoxAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<TickBoxModel> tickBoxModelArrayList= new ArrayList<>();
    private MyDataBaseHelper myDataBaseHelper;

    public TickBoxAdapter(Context mContext, ArrayList<TickBoxModel> tickBoxModelArrayList) {
        this.mContext = mContext;
        this.tickBoxModelArrayList = tickBoxModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.layout_adapter_tick_box, parent, false);
        TickBoxAdapter.ViewHolder viewHolder = new TickBoxAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        myDataBaseHelper = new MyDataBaseHelper(mContext);
        final TickBoxModel tickBoxModel= tickBoxModelArrayList.get(position);
        Log.i("tickBoxModel", tickBoxModel.getDescribe());
        if(tickBoxModel.getDescribe().equals("")==false){
            holder.edtDescribe.setText(tickBoxModel.getDescribe()+"");
        }


        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("HienNgu", tickBoxModel.getIdNote()+"");
                Log.i("HienNgu", tickBoxModel.getDescribe()+"");
                callback.deleteCheck(tickBoxModel);
            //    notifyDataSetChanged();

            }
        });

        holder.edtDescribe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("TickBox", holder.edtDescribe.getText().toString());
                tickBoxModel.setDescribe(holder.edtDescribe.getText().toString());
                myDataBaseHelper.updateTableBox(tickBoxModel);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tickBoxModel.setDescribe(holder.edtDescribe.getText().toString());


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
        ImageView imvDelete;
        EditText edtDescribe;
        public ViewHolder(View itemView) {
            super(itemView);
            imvDelete= itemView.findViewById(R.id.imvDelete);
            edtDescribe= itemView.findViewById(R.id.edtDescribe);
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
