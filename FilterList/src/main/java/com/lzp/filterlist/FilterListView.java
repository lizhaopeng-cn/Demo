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
    private Button btnClear;
    private Button btnConfirm;
    private TextView tvResult;
    private List<String> iFlightDatas;
//    private List<String> mLefts;
//    private SparseArray<List<String>> mRights;
//    private List<String> rightsValue;
    private List<IFlightFilter> iFlightFilterList;
    private RecyclerView rvLeft;
    private RecyclerView rvRight;
    private IFlightFilterLeftAdapter mIFlightFilterLeftAdapter;
    private IFlightFilterRightAdapter mIFlightFilterRightAdapter;
    private RelativeLayout dlgFilter;

    private LabelsView labelsView; //自定义头部标签筛选控件

    private SparseArray<SparseBooleanArray> selectedAll; //记录全部的选中状态

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

        initView();

        initLeftView();

        initRightView();

        setClick();
    }

    private void initData() {
//        mLefts = new ArrayList<>();
//        mRights = new SparseArray<>();
//        for(int i = 0; i < iFlightFilterList.size(); i++){
//            IFlightFilter iFlightFilter = iFlightFilterList.get(i);
//            mLefts.add(iFlightFilter.getLeftName());
//            mRights.put(i, iFlightFilter.getRights());
//        }
    }

    private void initView() {
        tvResult = findViewById(R.id.tv_result);
        labelsView = findViewById(R.id.labels);
        btnClear = findViewById(R.id.btn_clear);
        btnConfirm = findViewById(R.id.btn_confirm);
    }

    private void initLeftView() {
        rvLeft = dlgFilter.findViewById(R.id.rv_iflight_filter_left);
        rvLeft.setLayoutManager(new LinearLayoutManager(mContext));
        rvLeft.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        mIFlightFilterLeftAdapter = new IFlightFilterLeftAdapter(mContext, iFlightFilterList);
        rvLeft.setAdapter(mIFlightFilterLeftAdapter);
    }

    private void initRightView() {
        rvRight = dlgFilter.findViewById(R.id.rv_iflight_filter_right);
        rvRight.setLayoutManager(new LinearLayoutManager(mContext));
        rvRight.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        mIFlightFilterRightAdapter = new IFlightFilterRightAdapter(mContext, iFlightFilterList);
//        mIFlightFilterRightAdapter.setLebelsView(labelsView);
        rvRight.setAdapter(mIFlightFilterRightAdapter);
    }

    private void setClick() {
        //清空
        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //确认
        btnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mContext).closePopupWindow();
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
                if(isAdd){
                    //添加
                    labelsView.addLabel(updateTextList.get(0), labelsView.getLabels().size());
                }else{
                    //删除
                    labelsView.removeLabels(updateTextList);
                }
                showOrCloseLabelsView();
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
        }else{
            ((ScrollView)labelsView.getParent()).setVisibility(View.GONE);
        }
    }

    public void setIFlightDatas(List<String> iFlightDatas){
        this.iFlightDatas = iFlightDatas;
    }

    public List<String> getFilterLables(){
        return labelsView.getLabels();
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
                        if(!TextUtils.equals(iFlightFilterList.get(i).getRights().get(j),"直飞")){
                            filters.add(iFlightFilterList.get(i).getRights().get(j));
                        }
                    }else{
                        if(selectRight.get(j)){
                            filters.add(iFlightFilterList.get(i).getRights().get(j));
                        }
                    }
//                    if(j == 0){
//                        if(isSelect){
//                            if(!selectRight.get(j)){
//                                if(!TextUtils.equals(iFlightFilterList.get(i).getRights().get(j),"直飞")){
//                                    filters.add(iFlightFilterList.get(i).getRights().get(j));
//                                }
//                            }
//                        }else{
//                            if(selectRight.get(j)){
//                                filters.add(iFlightFilterList.get(i).getRights().get(j));
//                            }
//                        }
//                    }else{
//                        if(selectRight.get(j)){
//                            filters.add(iFlightFilterList.get(i).getRights().get(j));
//                        }
//                    }
                }
            }
        }

        for(String iFlightData : iFlightDatas){
            for(String filter : filters){
                if(TextUtils.equals(iFlightData, filter)){
                    count++;
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
