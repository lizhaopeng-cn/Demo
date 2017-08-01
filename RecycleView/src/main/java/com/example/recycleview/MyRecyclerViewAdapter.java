package com.example.recycleview;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lzp on 2017/5/18.
 */

class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> datas;
    private TimeOnDeleteListener timeOnDeleteListener;

    public MyRecyclerViewAdapter(Context mContext, List<String> datas){
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText("text-"+datas.get(position));
        holder.btn.setText("button-"+datas.get(position));

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"test",Toast.LENGTH_SHORT).show();
                timeOnDeleteListener.onDelete(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;
        public Button btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            btn = (Button) itemView.findViewById(R.id.btn);
        }
    }

    //删除指定签到时间的接口
    public static interface TimeOnDeleteListener{
        public void onDelete(int position);
    }

    public void setTimeOnDeleteListener(TimeOnDeleteListener timeOnDeleteListener){
        this.timeOnDeleteListener = timeOnDeleteListener;
    }

}
