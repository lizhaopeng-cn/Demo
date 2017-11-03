package com.lzp.filterlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnFilter;
    private RecyclerView mRecyclerView;
    private PopupWindow popupWindowFilter;
    private FilterListView filterView;
    private List<IFlightFilter> iFlightFilters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        configurePopupWindow();
        setClick();
    }

    private void initView() {
        btnFilter = (Button) findViewById(R.id.btn_filter);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
    }

    private void initData() {
        IFlightFilter iFlightFilter1 = new IFlightFilter();
        iFlightFilter1.setSelectType(IFlightFilter.MULTI_SELECT);
        List<String> lefts1 = new ArrayList<>();
        iFlightFilter1.setLeftName("起飞时间");
        List<String> rights1 = new ArrayList<>();
        rights1.add("不限");
        rights1.add("00:00-06:00");
        rights1.add("06:00-12:00");
        rights1.add("12:00-18:00");
        rights1.add("18:00-24:00");
        iFlightFilter1.setRights(rights1);

        IFlightFilter iFlightFilter2 = new IFlightFilter();
        iFlightFilter2.setSelectType(IFlightFilter.SINGLE_SELECT);
        iFlightFilter2.setLeftName("舱位");
        List<String> rights2 = new ArrayList<>();
        rights2.add("经济/超级经济舱");
        rights2.add("公务/头等舱");
        rights2.add("公务舱");
        rights2.add("头等舱");
        iFlightFilter2.setRights(rights2);

        IFlightFilter iFlightFilter3 = new IFlightFilter();
        iFlightFilter3.setSelectType(IFlightFilter.MULTI_SELECT);
        iFlightFilter3.setLeftName("起飞机场");
        List<String> rights3 = new ArrayList<>();
        rights3.add("不限");
        rights3.add("北京");
        rights3.add("上海");
        rights3.add("广州");
        rights3.add("深圳");
        rights3.add("南京");
        rights3.add("苏州");
        rights3.add("杭州");
        rights3.add("天津");
        rights3.add("成都");
        rights3.add("武汉");
        rights3.add("青岛");
        iFlightFilter3.setRights(rights3);

        IFlightFilter iFlightFilter4 = new IFlightFilter();
        iFlightFilter4.setSelectType(IFlightFilter.MULTI_SELECT);
        iFlightFilter4.setLeftName("到达机场");
        List<String> rights4 = new ArrayList<>();
        rights4.add("不限");
        rights4.add("北京");
        rights4.add("上海");
        rights4.add("广州");
        rights4.add("深圳");
        rights4.add("南京");
        rights4.add("苏州");
        rights4.add("杭州");
        rights4.add("天津");
        rights4.add("成都");
        rights4.add("武汉");
        rights4.add("青岛");
        iFlightFilter4.setRights(rights4);

        IFlightFilter iFlightFilter5 = new IFlightFilter();
        iFlightFilter5.setSelectType(IFlightFilter.MULTI_SELECT);
        iFlightFilter5.setLeftName("中转城市");
        List<String> rights5 = new ArrayList<>();
        rights5.add("不限");
        rights5.add("直飞");
        rights5.add("北京");
        rights5.add("上海");
        rights5.add("广州");
        rights5.add("深圳");
        rights5.add("南京");
        rights5.add("苏州");
        rights5.add("杭州");
        rights5.add("天津");
        rights5.add("成都");
        rights5.add("武汉");
        rights5.add("青岛");
        iFlightFilter5.setRights(rights5);

        iFlightFilters = new ArrayList<>();
        iFlightFilters.add(iFlightFilter1);
        iFlightFilters.add(iFlightFilter2);
        iFlightFilters.add(iFlightFilter3);
        iFlightFilters.add(iFlightFilter4);
        iFlightFilters.add(iFlightFilter5);

    }

    private void configurePopupWindow() {
        filterView = new FilterListView(this, iFlightFilters);
        popupWindowFilter = new PopupWindow(this);
        popupWindowFilter.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindowFilter.setHeight(1000);
        popupWindowFilter.setContentView(filterView);
        popupWindowFilter.setOutsideTouchable(true);
    }
    private void setClick() {
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindowFilter.showAtLocation(btnFilter, Gravity.BOTTOM , 0,0);
            }
        });
    }
}
