package com.lzp.filterlist;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzp48947 on 2017/11/1.
 */

public class IFlightFilterLeftAdapter extends RecyclerView.Adapter<IFlightFilterLeftAdapter.MyViewHolder>{
    private Context mContext;
    private List<IFlightFilter> iFlightFilterList;
    private List<String> mLefts;
    private OnLeftSelectListener mOnLeftSelectListener;
    private int selectedPosition;

    public IFlightFilterLeftAdapter(Context context, List<IFlightFilter> iFlightFilterList){
        this.mContext = context;
        this.iFlightFilterList = iFlightFilterList;
        mLefts = new ArrayList<>();
        for(int i = 0; i < iFlightFilterList.size(); i++){
            IFlightFilter iFlightFilter = iFlightFilterList.get(i);
            mLefts.add(iFlightFilter.getLeftName());
        }
    }

//    public void setLeftValue(List<String> lefts){
//        this.mLefts = lefts;
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_left, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
//        mOnLeftSelectListener.onLeftSelect(0);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvLeftName.setText(mLefts.get(position));
//        if(){
//
//        }
        if(selectedPosition == position){
            holder.tvLineSelected.setVisibility(View.VISIBLE);
            holder.llLeft.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tvLeftName.setTextColor(Color.parseColor("#333333"));
        }else{
            holder.tvLineSelected.setVisibility(View.INVISIBLE);
            holder.llLeft.setBackgroundColor(Color.parseColor("#dfdfdf"));
            holder.tvLeftName.setTextColor(Color.parseColor("#666666"));
        }
        holder.llLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnLeftSelectListener.onLeftSelect(position);
                selectedPosition = position;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLefts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout llLeft;
        public TextView tvLineSelected;
        public TextView tvLeftName;
        public TextView tvCircleLeftTag;

        public MyViewHolder(View itemView) {
            super(itemView);
            llLeft = itemView.findViewById(R.id.ll_left);
            tvLineSelected = itemView.findViewById(R.id.tv_line_selected);
            tvLeftName = itemView.findViewById(R.id.tv_left_name);
            tvCircleLeftTag = itemView.findViewById(R.id.tv_circle_left_tag);
        }
    }

    public interface OnLeftSelectListener{
        void onLeftSelect(int position);
    }

    public void setOnLeftSelectListener(OnLeftSelectListener onLeftSelectListener){
        this.mOnLeftSelectListener = onLeftSelectListener;
    }
}
