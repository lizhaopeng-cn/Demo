package com.lzp.filterlist;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzp48947 on 2017/11/1.
 */

public class FilterListView extends RelativeLayout{

    private Context mContext;
    private RelativeLayout rlFillLabelWeight;
    private Button btnClear;
    private Button btnConfirm;
    private TextView tvResult;
    private ScrollView svIflightFilterSelected;
    private List<String> iFlightDatas;
    private List<String> iFlightFilterDatas;
    private List<IFlightFilter> iFlightFilterList;
    private RecyclerView rvLeft;
    private RecyclerView rvRight;
    private IFlightFilterLeftAdapter mIFlightFilterLeftAdapter;
    private IFlightFilterRightAdapter mIFlightFilterRightAdapter;
    private RelativeLayout dlgFilter;

    private LabelsView labelsView; //自定义头部标签筛选控件
    private List<String> oldLabels;

    private SparseArray<SparseBooleanArray> selectedAll; //记录全部的选中状态
    private SparseArray<SparseBooleanArray> oldSelectedAll; //记录上一次全部的选中状态

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
        dlgFilter = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.dlg_filter, this);

        initSelectedAll();

        initView();

        initData();

        initLeftView();

        initRightView();

        setClick();
    }

    private void initView() {
        rlFillLabelWeight = findViewById(R.id.rl_fill_label_weight);
        tvResult = findViewById(R.id.tv_result);
        svIflightFilterSelected = findViewById(R.id.sv_iflight_filter_selected);
        labelsView = findViewById(R.id.labels);
        btnClear = findViewById(R.id.btn_clear);
        btnConfirm = findViewById(R.id.btn_confirm);
    }

    private void initData() {
        labelsView.setFilterListView(this);
        labelsView.setMaxLine(2);
    }

    private void initSelectedAll() {
        selectedAll = new SparseArray<>();
        for(int i = 0; i < iFlightFilterList.size(); i++){
            SparseBooleanArray selectedRight = new SparseBooleanArray();
            for(int j = 0; j < iFlightFilterList.get(i).getRights().size(); j++){
                if(j == 0){
                    selectedRight.put(j, true);
                }else{
                    selectedRight.put(j, false);
                    selectedAll.put(i, selectedRight);
                }
            }
        }
    }

    private void initLeftView() {
        rvLeft = dlgFilter.findViewById(R.id.rv_iflight_filter_left);
        rvLeft.setLayoutManager(new LinearLayoutManager(mContext));
        rvLeft.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        mIFlightFilterLeftAdapter = new IFlightFilterLeftAdapter(mContext, iFlightFilterList);
        mIFlightFilterLeftAdapter.setSelectedAll(selectedAll);
        rvLeft.setAdapter(mIFlightFilterLeftAdapter);
    }

    private void initRightView() {
        rvRight = dlgFilter.findViewById(R.id.rv_iflight_filter_right);
        rvRight.setLayoutManager(new LinearLayoutManager(mContext));
        rvRight.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        mIFlightFilterRightAdapter = new IFlightFilterRightAdapter(mContext, iFlightFilterList);
        //如果上次有记录说明没点确定，则用上次的
        mIFlightFilterRightAdapter.setSelectedAll(selectedAll);
        rvRight.setAdapter(mIFlightFilterRightAdapter);
    }

    public void setIFlightDatas(List<String> iFlightDatas){
        this.iFlightDatas = iFlightDatas;
    }

    /**
     * 加载上一次的数据
     */
    public void setOldSelect(){
        if(oldSelectedAll != null){
//            selectedAll = oldSelectedAll.clone();
            for(int i = 0; i < oldSelectedAll.size(); i++){
                SparseBooleanArray seleteRight = new SparseBooleanArray();
                for(int j = 0; j < oldSelectedAll.get(i).size(); j ++){
                    seleteRight.put(j,oldSelectedAll.get(i).get(j));
                    selectedAll.put(i,seleteRight);
                }
            }
            mIFlightFilterRightAdapter.setSelectedAll(selectedAll);
            mIFlightFilterRightAdapter.notifyDataSetChanged();
        }

        labelsView.setLabels(oldLabels);
        showOrCloseLabelsView();
        showResult();
    }

    private void setClick() {
        //占位控件关闭筛选框
        rlFillLabelWeight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mContext).closePopupWindow(false);
            }
        });

        //确认
        btnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                oldSelectedAll = selectedAll.clone();
                oldSelectedAll = new SparseArray<SparseBooleanArray>();
                for(int i = 0; i < selectedAll.size(); i++){
                    SparseBooleanArray seleteRight = new SparseBooleanArray();
                    for(int j = 0; j < selectedAll.get(i).size(); j ++){
                        seleteRight.put(j,selectedAll.get(i).get(j));
                        oldSelectedAll.put(i,seleteRight);
                    }
                }
                oldLabels = labelsView.getLabels();
                ((MainActivity)mContext).closePopupWindowConfirm(iFlightFilterDatas, true);
            }
        });

        //清空
        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                initSelectedAll();
                mIFlightFilterRightAdapter.setSelectedAll(selectedAll);
                mIFlightFilterRightAdapter.notifyDataSetChanged();
                labelsView.removeAllViews();
                showOrCloseLabelsView();
                showResult();
            }
        });

        //左侧列表点击的回调
        mIFlightFilterLeftAdapter.setOnLeftSelectListener(new IFlightFilterLeftAdapter.OnLeftSelectListener() {
            @Override
            public void onLeftSelect(int position) {
                if(mIFlightFilterRightAdapter != null){
                    mIFlightFilterRightAdapter.setLeftId(position);
                    mIFlightFilterLeftAdapter.notifyDataSetChanged();
                    mIFlightFilterRightAdapter.notifyDataSetChanged();
                }
            }
        });

        //右侧列表点击的回调
        mIFlightFilterRightAdapter.setOnRightMultiSelectCallbackLeftLisentener(new IFlightFilterRightAdapter.OnRightMultiSelectCallbackLeftLisentener() {
            @Override
            public void onRightMultiSelectCallbackLeft(SparseArray<SparseBooleanArray> selectedAll) {
                mIFlightFilterLeftAdapter.setSelectedAll(selectedAll);
            }
        });

        //标签的点击删除回调
        labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(View label, String labelText, int position) {
                //label是被点击的标签，labelText是标签的文字，position是标签的位置。
                for(int i = 0; i < labelsView.getChildCount(); i ++){
                    if(position == (int)labelsView.getChildAt(i).getTag()){
                        labelsView.removeView(labelsView.getChildAt(i));
                    }
                }
                mIFlightFilterRightAdapter.labelUpdateRight(labelText);
                showOrCloseLabelsView();
                showResult();
            }
        });

        //点击右侧筛选标签列表的回调
        mIFlightFilterRightAdapter.setOnRightMultiSelectCallbackLebelLisentener(new IFlightFilterRightAdapter.OnRightMultiSelectCallbackLebelLisentener() {
            @Override
            public void onRightMultiSelectCallbackLebel(List<String> updateTextList, boolean isAdd) {
                if(updateTextList != null && updateTextList.size() > 0){
                    if(isAdd){
                        //添加
                        labelsView.addLabel(updateTextList.get(0), labelsView.getLabels().size());
                    }else{
                        //删除
                        labelsView.removeLabels(updateTextList);
                    }
                }
                showOrCloseLabelsView();
                showResult();
            }
        });

        //点击右侧单选显示结果的回调
        mIFlightFilterRightAdapter.setOnRightSingleSelectCallbackResultLisentener(new IFlightFilterRightAdapter.OnRightSingleSelectCallbackResultLisentener() {
            @Override
            public void onRightSingleSelectCallbackResult() {
                showResult();
            }
        });
    }

    /**
     * 标签列表的显示或隐藏
     */
    public void showOrCloseLabelsView(){
        if(labelsView.getChildCount() > 0){
            ((ScrollView)labelsView.getParent()).setVisibility(View.VISIBLE);
//          rlFillLabelWeight.setVisibility(View.GONE);
        }else{
            ((ScrollView)labelsView.getParent()).setVisibility(View.GONE);
//            rlFillLabelWeight.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 动态设置ScrollViewHeight高度
     */
    public void setScrollViewHeight() {
        int labelHeight = labelsView.getLabelHeight();
        if(labelHeight > 0){
            RelativeLayout.LayoutParams params = (LayoutParams) svIflightFilterSelected.getLayoutParams();
            params.height = labelHeight;
//            svIflightFilterSelected.setLayoutParams(params);
//            svIflightFilterSelected.invalidate();
        }
    }

    /**
     * 展示顶部结果
     */
    public void showResult(){
        int count = 0;
        List<String> filters = new ArrayList<>();
        selectedAll = mIFlightFilterRightAdapter.getSelectedAll();
        for(int i = 0; i < selectedAll.size(); i++){
            SparseBooleanArray selectRight = selectedAll.get(i);
            for(int j = 0; j < selectRight.size(); j++){
                boolean isSelect = selectRight.get(j);
                if(i == 1){
                    if(isSelect){
                        filters.add(iFlightFilterList.get(i).getRights().get(j));
                    }
                }else{
                    if(selectRight.get(0)){
//                        if(!TextUtils.equals(iFlightFilterList.get(i).getRights().get(j),"直飞")){
                            filters.add(iFlightFilterList.get(i).getRights().get(j));
//                        }
                    }else{
                        if(selectRight.get(j)){
                            filters.add(iFlightFilterList.get(i).getRights().get(j));
                        }
                    }
                }
            }
        }

        for(int i = 0; i < filters.size(); i++){
            if(TextUtils.equals(filters.get(i),"不限")){
                filters.remove(i);
                if(i != filters.size()){
                    i--;
                }
            }
        }

        iFlightFilterDatas = new ArrayList<>();
        for(String iFlightData : iFlightDatas){
            for(String filter : filters){
                if(TextUtils.equals(iFlightData, filter)){
                    count++;
                    iFlightFilterDatas.add(iFlightData);
                    break;
                }
            }
        }
        if(count > 0){
            tvResult.setText("共"+count+"个结果");
        }else{
            tvResult.setText("筛选无结果啦"+"删掉一些条件试试");
        }
    }

}
