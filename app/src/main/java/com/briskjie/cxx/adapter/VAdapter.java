package com.briskjie.cxx.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.briskjie.cxx.R;

public class VAdapter extends RecyclerView.Adapter<VAdapter.Holder> {

    private List<String> data = new ArrayList<>();
    private Context mContext;

    @Override
    public int getItemCount() {
        return 30;
    }

    public VAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.v_item_layout, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tv.setText(String.valueOf(position));
    }


    class Holder extends RecyclerView.ViewHolder {
        TextView tv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }

    }
}
