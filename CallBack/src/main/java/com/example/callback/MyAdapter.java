package com.example.callback;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lzp on 2017/5/26.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<String> datas;
    private UpdataCallBack updataCallBack;

    public MyAdapter(Context context, List<String> datas, UpdataCallBack updataCallBack){
        this.context = context;
        this.datas = datas;
        this.updataCallBack = updataCallBack;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = null;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_adapter,null);
            myViewHolder = new MyViewHolder();
            myViewHolder.tvItem = (TextView) view.findViewById(R.id.tv_item);
            myViewHolder.btnItem = (Button) view.findViewById(R.id.btn_item);
            view.setTag(myViewHolder);
        }else{
            myViewHolder = (MyViewHolder) view.getTag();
        }
        final String data = datas.get(i);
        myViewHolder.tvItem.setText(data);

        myViewHolder.btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("test","onClick");
                updataCallBack.getText(data);
            }
        });

        return view;
    }

    class MyViewHolder{
        private TextView tvItem;
        private Button btnItem;
    }

//    public interface UpdataCallBack{
//        public void getText();
//    }
}
