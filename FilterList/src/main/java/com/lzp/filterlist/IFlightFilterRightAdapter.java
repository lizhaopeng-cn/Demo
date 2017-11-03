package com.lzp.filterlist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lzp48947 on 2017/11/1.
 */

public class IFlightFilterRightAdapter extends RecyclerView.Adapter<IFlightFilterRightAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mRights;
//    private boolean isSingleSelect;
    private SparseArray<SparseBooleanArray> isSelect;

    public IFlightFilterRightAdapter(Context context){
        this.mContext = context;
    }

    public void setRightValue(List<String> rights){
        this.mRights = rights;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_right, parent, false);
        IFlightFilterRightAdapter.MyViewHolder myViewHolder = new IFlightFilterRightAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tvRightName.setText(mRights.get(position));
        holder.rlRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                    holder.checkBox.setChecked(false);
                    holder.tvRightName.setTextColor(Color.parseColor("#666666"));
                }else{
                    holder.checkBox.setChecked(true);
                    holder.tvRightName.setTextColor(Color.parseColor("#23beae"));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRights.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout rlRight;
        public TextView tvRightName;
        public CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            rlRight = itemView.findViewById(R.id.rl_right);
            tvRightName = itemView.findViewById(R.id.tv_right_name);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
