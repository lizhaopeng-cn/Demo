package com.lzp.filterlist;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lzp48947 on 2017/11/1.
 */

public class IFlightFilterRightAdapter extends RecyclerView.Adapter<IFlightFilterRightAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mRights;
    private int mLeftId;
    private List<IFlightFilter> mIFlightFilterList;
    private SparseArray<SparseBooleanArray> selectedAll;

    private boolean isSelectUnlimited = true; //是否选择的不限
    private boolean isSelectDirectFlight; //是否选择的直飞

    private OnRightMultiSelectLisentener onRightMultiSelectLisentener; //多选的回调

    public IFlightFilterRightAdapter(Context context, List<IFlightFilter> iFlightFilterList){
        this.mContext = context;
        this.mIFlightFilterList = iFlightFilterList;

        mRights = iFlightFilterList.get(mLeftId).getRights();

        selectedAll = new SparseArray<>();
        for(int i = 0; i < mIFlightFilterList.size(); i++){
            SparseBooleanArray selectedRight = new SparseBooleanArray();
            for(int j = 0; j < mIFlightFilterList.get(i).getRights().size(); j++){
                if(j == 0){
                    selectedRight.put(j, true);
                }else{
                    selectedRight.put(j, false);
                    selectedAll.put(i, selectedRight);
                }
            }
        }
    }

    public void setLeftId(int leftId){
        mLeftId = leftId;
        mRights = mIFlightFilterList.get(mLeftId).getRights();
    }

    public List<String> getRightsValue(){
        return mRights = mIFlightFilterList.get(mLeftId).getRights();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_right, parent, false);
        IFlightFilterRightAdapter.MyViewHolder myViewHolder = new IFlightFilterRightAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvRightName.setText(mRights.get(position));
        final SparseBooleanArray selectedRight = selectedAll.get(mLeftId);
//        for(int i = 0; i < selectedRight.size(); i++){
            if(selectedRight.get(position)){
                holder.checkBox.setChecked(true);
                holder.tvRightName.setTextColor(Color.parseColor("#23beae"));
            }else{
                holder.checkBox.setChecked(false);
                holder.tvRightName.setTextColor(Color.parseColor("#666666"));
            }
//        }
        holder.rlRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIFlightFilterList != null && mIFlightFilterList.get(mLeftId) != null
                        && !TextUtils.isEmpty(mIFlightFilterList.get(mLeftId).getSelectType())){
                    String type = mIFlightFilterList.get(mLeftId).getSelectType();
                    if(TextUtils.equals(type, IFlightFilter.MULTI_SELECT)){
                        if(position == 0 && TextUtils.equals(mIFlightFilterList.get(mLeftId).getRights().get(0), "不限")
                                || position == 1 && TextUtils.equals(mIFlightFilterList.get(mLeftId).getRights().get(1), "直飞")){
                            isSelectUnlimited = position == 0 && TextUtils.equals(mIFlightFilterList.get(mLeftId).getRights().get(0), "不限") ? true : false;
                            isSelectDirectFlight = position == 1 && TextUtils.equals(mIFlightFilterList.get(mLeftId).getRights().get(1), "直飞") ? true : false;
                            if(!holder.checkBox.isChecked()){
                                for(int i = 0; i < selectedRight.size(); i++){
                                    selectedRight.put(i, false);
                                }
                                holder.checkBox.setChecked(true);
                                holder.tvRightName.setTextColor(Color.parseColor("#23beae"));
                                selectedRight.put(position, true);
                                selectedAll.put(mLeftId, selectedRight);
                                onRightMultiSelectLisentener.onRightMultiSelect(selectedAll);
                                notifyDataSetChanged();
                            }
                        }else if(holder.checkBox.isChecked()){
                            holder.checkBox.setChecked(false);
                            holder.tvRightName.setTextColor(Color.parseColor("#666666"));
                            selectedRight.put(position, false);
                            boolean b = false;
                            for(int i = 0; i < selectedRight.size(); i++){
                                if(selectedRight.get(i)){
                                    b = true;
                                    break;
                                }
                            }
                            if(!b){
                                selectedRight.put(0, true);
                            }
                            onRightMultiSelectLisentener.onRightMultiSelect(selectedAll);
                            notifyDataSetChanged();
                        }else if(!holder.checkBox.isChecked()){
                            holder.checkBox.setChecked(true);
                            holder.tvRightName.setTextColor(Color.parseColor("#23beae"));
                            selectedRight.put(position, true);
                            if(isSelectUnlimited)
                                selectedRight.put(0, false);
                            if(isSelectDirectFlight)
                                selectedRight.put(1, false);
                            onRightMultiSelectLisentener.onRightMultiSelect(selectedAll);
                            notifyDataSetChanged();
                        }
                        selectedAll.put(mLeftId, selectedRight);
                    }else if(TextUtils.equals(type, IFlightFilter.SINGLE_SELECT)){
                        if(!holder.checkBox.isChecked()){
                            for(int i = 0; i < selectedRight.size(); i++){
                                selectedRight.put(i, false);
                            }
                            holder.checkBox.setChecked(true);
                            holder.tvRightName.setTextColor(Color.parseColor("#23beae"));
                            selectedRight.put(position, true);
                            selectedAll.put(mLeftId, selectedRight);
                            notifyDataSetChanged();
                        }
                    }
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

    public interface OnRightMultiSelectLisentener{
        void onRightMultiSelect(SparseArray<SparseBooleanArray> selectedAll);
    }

    public void setOnRightMultiSelectLisentener(OnRightMultiSelectLisentener onRightMultiSelectLisentener){
        this.onRightMultiSelectLisentener = onRightMultiSelectLisentener;
    }
}