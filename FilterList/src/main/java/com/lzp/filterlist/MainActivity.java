package com.lzp.filterlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnFilter;
    private RecyclerView mRecyclerView;
    private PopupWindow popupWindowFilter;
    private FilterListView filterView;
    private List<IFlightFilter> iFlightFilters;
    private HomeAdapter homeAdapter;

    private List<String> rights1;
    private List<String> rights2;
    private List<String> rights3;
    private List<String> rights4;
    private List<String> rights5;
    private List<String> iFlightDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFilterData();
        configurePopupWindow();
        initIFlightDate();
        initRecyclerView();
        setClick();
    }

    private void initView() {
        btnFilter = (Button) findViewById(R.id.btn_filter);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        homeAdapter = new HomeAdapter();
        mRecyclerView.setAdapter(homeAdapter);
    }

    private void configurePopupWindow() {
        filterView = new FilterListView(this, iFlightFilters);
        filterView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupWindowFilter = new PopupWindow(filterView, ViewGroup.LayoutParams.MATCH_PARENT, filterView.getMeasuredHeight());
        popupWindowFilter.setContentView(filterView);
        popupWindowFilter.setOutsideTouchable(true);
    }

    public void closePopupWindow(){
        popupWindowFilter.dismiss();
    }

    private void setClick() {
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindowFilter.showAtLocation(btnFilter, Gravity.BOTTOM , 0,0);
            }
        });

    }

    private void initFilterData() {
        IFlightFilter iFlightFilter1 = new IFlightFilter();
        iFlightFilter1.setSelectType(IFlightFilter.MULTI_SELECT);
        iFlightFilter1.setLeftName("起飞时间");
        rights1 = new ArrayList<>();
        rights1.add("不限");
        rights1.add("00:00-06:00");
        rights1.add("06:00-12:00");
        rights1.add("12:00-18:00");
        rights1.add("18:00-24:00");
        iFlightFilter1.setRights(rights1);

        IFlightFilter iFlightFilter2 = new IFlightFilter();
        iFlightFilter2.setSelectType(IFlightFilter.SINGLE_SELECT);
        iFlightFilter2.setLeftName("舱位");
        rights2 = new ArrayList<>();
        rights2.add("经济/超级经济舱");
        rights2.add("公务/头等舱");
        rights2.add("公务舱");
        rights2.add("头等舱");
        iFlightFilter2.setRights(rights2);

        IFlightFilter iFlightFilter3 = new IFlightFilter();
        iFlightFilter3.setSelectType(IFlightFilter.MULTI_SELECT);
        iFlightFilter3.setLeftName("中转城市");
        rights3 = new ArrayList<>();
        rights3.add("不限");
        rights3.add("直飞");
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
        iFlightFilter4.setLeftName("起飞机场");
        rights4 = new ArrayList<>();
        rights4.add("不限");
        rights4.add("北京起飞机场");
        rights4.add("上海起飞机场");
        rights4.add("广州起飞机场");
        rights4.add("深圳起飞机场");
        rights4.add("南京起飞机场");
        rights4.add("苏州起飞机场");
        rights4.add("杭州起飞机场");
        rights4.add("天津起飞机场");
        rights4.add("成都起飞机场");
        rights4.add("武汉起飞机场");
        rights4.add("青岛起飞机场");
        iFlightFilter4.setRights(rights4);

        IFlightFilter iFlightFilter5 = new IFlightFilter();
        iFlightFilter5.setSelectType(IFlightFilter.MULTI_SELECT);
        iFlightFilter5.setLeftName("到达机场");
        rights5 = new ArrayList<>();
        rights5.add("不限");
        rights5.add("北京到达机场");
        rights5.add("上海到达机场");
        rights5.add("广州到达机场");
        rights5.add("深圳到达机场");
        rights5.add("南京到达机场");
        rights5.add("苏州到达机场");
        rights5.add("杭州到达机场");
        rights5.add("天津到达机场");
        rights5.add("成都到达机场");
        rights5.add("武汉到达机场");
        rights5.add("青岛到达机场");
        iFlightFilter5.setRights(rights5);

        iFlightFilters = new ArrayList<>();
        iFlightFilters.add(iFlightFilter1);
        iFlightFilters.add(iFlightFilter2);
        iFlightFilters.add(iFlightFilter3);
        iFlightFilters.add(iFlightFilter4);
        iFlightFilters.add(iFlightFilter5);
    }

    private void initIFlightDate() {
        iFlightDatas = new ArrayList<>();
        String text = null;
        for(int i = 0; i < 100; i++){
            text = rights1.get((int) (Math.random() * rights1.size()));
            iFlightDatas.add(text);
            text = rights2.get((int) (Math.random() * rights2.size()));
            iFlightDatas.add(text);
            text = rights3.get((int) (Math.random() * rights3.size()));
            iFlightDatas.add(text);
            text = rights4.get((int) (Math.random() * rights4.size()));
            iFlightDatas.add(text);
            text = rights5.get((int) (Math.random() * rights5.size()));
            iFlightDatas.add(text);
        }
        filterView.setIFlightDatas(iFlightDatas);
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(iFlightDatas.get(position));
        }

        @Override
        public int getItemCount()
        {
            return iFlightDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.tv);
            }
        }
    }
}
