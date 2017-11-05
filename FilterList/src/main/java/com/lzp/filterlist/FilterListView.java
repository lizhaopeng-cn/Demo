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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzp48947 on 2017/11/1.
 */

public class FilterListView extends RelativeLayout{

    private Context mContext;
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

        initLabelView();

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

    private void initLabelView() {
        labelsView = findViewById(R.id.labels);
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

        mIFlightFilterRightAdapter.setOnRightMultiSelectCallbackLeftLisentener(new IFlightFilterRightAdapter.OnRightMultiSelectCallbackLeftLisentener() {
            @Override
            public void onRightMultiSelectCallbackLeft(SparseArray<SparseBooleanArray> selectedAll) {
                mIFlightFilterLeftAdapter.setSelectedAll(selectedAll);
            }
        });

        //标签的点击监听
        labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(View label, String labelText, int position) {
                //label是被点击的标签，labelText是标签的文字，position是标签的位置。
                labelsView.removeViewAt(position);
            }
        });

        mIFlightFilterRightAdapter.setOnRightMultiSelectCallbackLebelLisentener(new IFlightFilterRightAdapter.OnRightMultiSelectCallbackLebelLisentener() {
            @Override
            public void onRightMultiSelectCallbackLebel(List<String> updateTextList, boolean isAdd) {
                if(isAdd){
                    //添加
                    labelsView.addLabel(updateTextList.get(0), labelsView.getChildCount());
                }else{
                    List<View> deleteViews = new ArrayList<View>();
                    //删除
                    if(labelsView.getChildCount() > 0){
                        for(int i = 0; i < labelsView.getChildCount(); i++){
                            String label = ((TextView)labelsView.getChildAt(i)).getText().toString();
                            for(String updateText : updateTextList){
                                if(TextUtils.equals(label,updateText)){
                                    deleteViews.add(labelsView.getChildAt(i));
                                }
                            }
                        }
                        for(int i = 0; i < deleteViews.size(); i++){
                            labelsView.removeView(deleteViews.get(i));
                        }
                    }
                }
            }
        });
    }
}
