package com.example.administrator.apolloblechat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;

import java.util.List;

/**
 * Created by zayh_yf20160909 on 2017/1/9.
 */

public class PairedBleAdapter extends RecyclerView.Adapter<PairedBleAdapter.MyHolder> {
    private Context context;
    private List<String> datas;
    private final LayoutInflater inflater;

    public PairedBleAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(inflater.inflate(R.layout.ble_list_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tv.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_ble_list);
        }
    }
}
