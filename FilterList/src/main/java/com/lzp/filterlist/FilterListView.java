package com.lzp.filterlist;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzp48947 on 2017/11/1.
 */

public class FilterListView extends RelativeLayout{

    private Context mContext;
    private List<String> mLefts;
//    private List<String> rightsValue;
    private SparseArray<List<String>> mRights;
    private List<IFlightFilter> iFlightFilterList;
    private RecyclerView rvLeft;
    private RecyclerView rvRight;
    private IFlightFilterLeftAdapter mIFlightFilterLeftAdapter;
    private IFlightFilterRightAdapter mIFlightFilterRightAdapter;
    private RelativeLayout dlgFilter;

    public FilterListView(Context context, List<IFlightFilter> iFlightFilterList) {
        super(context);
        this.mContext = context;
        this.iFlightFilterList = iFlightFilterList;
        init();
    }

    public FilterListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FilterListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dlgFilter = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.dialog_filter, this);

        initData();

        initLeftView();

        initRightView();

        setClick();
    }

    private void initData() {
        mLefts = new ArrayList<>();
        mRights = new SparseArray<>();
        for(int i = 0; i < iFlightFilterList.size(); i++){
            IFlightFilter iFlightFilter = iFlightFilterList.get(i);
            mLefts.add(iFlightFilter.getLeftName());
            mRights.put(i, iFlightFilter.getRights());
        }
    }

    private void initLeftView() {
        rvLeft = dlgFilter.findViewById(R.id.rv_iflight_filter_left);
        rvLeft.setLayoutManager(new LinearLayoutManager(mContext));
        rvLeft.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        mIFlightFilterLeftAdapter = new IFlightFilterLeftAdapter(mContext);
        mIFlightFilterLeftAdapter.setLeftValue(mLefts);
        rvLeft.setAdapter(mIFlightFilterLeftAdapter);
    }

    private void initRightView() {
        rvRight = dlgFilter.findViewById(R.id.rv_iflight_filter_right);
        rvRight.setLayoutManager(new LinearLayoutManager(mContext));
        rvRight.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        mIFlightFilterRightAdapter = new IFlightFilterRightAdapter(mContext);
        mIFlightFilterRightAdapter.setRightValue(mRights.get(0));
        mIFlightFilterRightAdapter.setIFlightFilterDate(iFlightFilterList);
        rvRight.setAdapter(mIFlightFilterRightAdapter);
    }

    private void setClick() {
        mIFlightFilterLeftAdapter.setOnLeftSelectListener(new IFlightFilterLeftAdapter.OnLeftSelectListener() {
            @Override
            public void onLeftSelect(int position) {
                if(mIFlightFilterRightAdapter != null){
                    mIFlightFilterRightAdapter.setLeftId(position);
                    mIFlightFilterRightAdapter.setRightValue(mRights.get(position));
                    mIFlightFilterLeftAdapter.notifyDataSetChanged();
                    mIFlightFilterRightAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}
