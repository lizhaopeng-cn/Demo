package com.lzp.filterlist;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

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

    private LabelsView labelsView;

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
//        mLefts = new ArrayList<>();
//        mRights = new SparseArray<>();
//        for(int i = 0; i < iFlightFilterList.size(); i++){
//            IFlightFilter iFlightFilter = iFlightFilterList.get(i);
//            mLefts.add(iFlightFilter.getLeftName());
//            mRights.put(i, iFlightFilter.getRights());
//        }
        labelsView = (LabelsView) findViewById(R.id.labels);
        ArrayList<String> label = new ArrayList<>();
        label.add("Android");
        label.add("IOS");
        label.add("前端");
        label.add("后台");
        label.add("微信开发");
        label.add("游戏开发");
        label.add("Java");
        label.add("JavaScript");
        label.add("C++");
        label.add("PHP");
        label.add("Python");
        label.add("Swift");
        labelsView.setLabels(label); //直接设置一个字符串数组就可以了。

        //标签的点击监听
        labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(View label, String labelText, int position) {
                //label是被点击的标签，labelText是标签的文字，position是标签的位置。
//                labelsView.removeViewAt(position);
                labelsView.addLabel("你好 X",position);
            }
        });
        //标签的选中监听
        labelsView.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(View label, String labelText, boolean isSelect, int position) {
                //label是被点击的标签，labelText是标签的文字，isSelect是是否选中，position是标签的位置。
            }
        });
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

        mIFlightFilterRightAdapter.setOnRightMultiSelectLisentener(new IFlightFilterRightAdapter.OnRightMultiSelectLisentener() {
            @Override
            public void onRightMultiSelect(SparseArray<SparseBooleanArray> selectedAll) {
                mIFlightFilterLeftAdapter.setSelectedAll(selectedAll);
            }
        });
    }

}
