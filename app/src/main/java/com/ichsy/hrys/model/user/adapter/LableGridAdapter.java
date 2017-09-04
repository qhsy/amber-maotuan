package com.ichsy.hrys.model.user.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ichsy.hrys.R;
import com.ichsy.hrys.entity.MarsterFieldEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：已选中的标签
 * ＊创建者：赵然 on 16/8/22 10:36
 * ＊
 */
public class LableGridAdapter extends BaseAdapter {
    private Context context;
    private List<MarsterFieldEntity> datas;
    /**
     * 标签添加条目的数据实体
     */
//    private MarsterFieldEntity addItemData;
    /**
     * 条目个数  取值范围：0-6
     */
    private int itemCount;
    public LableGridAdapter(Context context, List<MarsterFieldEntity> datas){
//        addItemData = new MarsterFieldEntity();

        this.context = context;
        this.datas = new ArrayList<>();
        this.datas.addAll(datas);
//        if (this.datas != null && this.datas.size()>0) this.datas.add(addItemData);

    }
    @Override
    public int getCount() {
        if ( datas == null ){
            itemCount = 0;
        } else if(datas.size()> 6){

            itemCount = 6;
        }else{
            itemCount = datas.size();
        }

        return itemCount;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){

            convertView = View.inflate(context, R.layout.item_selectedlable,null);
        }
        TextView name = (TextView) convertView.findViewById(R.id.tv_itemalllable_name);
        name.setText(datas.get(position).getFieldName());
        return convertView;
    }


    public void  notify(List<MarsterFieldEntity> datas){
        this.datas.clear();
        this.datas.addAll(datas);
//        if (this.datas != null && this.datas.size()>0) this.datas.add(addItemData);
        notifyDataSetChanged();

    }

    /**
     * 返回数据
     * @return
     */

    public List<MarsterFieldEntity> getDatas(){
        return this.datas;
    }
}
