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
 * 功能：标签页面 所有标签的适配器
 * ＊创建者：赵然 on 16/8/22 10:36
 * ＊
 */
public class AllLableGridAdapter extends BaseAdapter {
    private Context context;
    private List<MarsterFieldEntity> datas;
//    /**
//     * 标签添加条目的数据实体
//     */
//    private MarsterFieldEntity addItemData;


    private int selectedItemCount = 0;

    public AllLableGridAdapter(Context context, List<MarsterFieldEntity> datas) {
//        addItemData = new MarsterFieldEntity();
        this.context = context;
        this.datas = new ArrayList<>();
        this.datas.addAll(datas);
//        if (this.datas != null && this.datas.size()>0) this.datas.add(addItemData);

    }

    @Override
    public int getCount() {

        return datas == null ? 0 : datas.size();
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

        if (convertView == null) {

            convertView = View.inflate(context, R.layout.item_alllables, null);
        }


        TextView name = (TextView) convertView.findViewById(R.id.tv_itemgridlable_name);
        name.setText(datas.get(position).getFieldName());
        name.setSelected(datas.get(position).isChecked());
        return convertView;
    }

    public void notify(List<MarsterFieldEntity> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
//        if (this.datas != null && this.datas.size()>0) this.datas.add(addItemData);

    }

    /**
     * 返回数据
     *
     * @return
     */

    public List<MarsterFieldEntity> getDatas() {
        return this.datas;
    }

    /**
     * 获取已选中的条目个数
     *
     * @return
     */
    public int getSelectedItemCount() {
        selectedItemCount = 0;
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isChecked()) {
                selectedItemCount++;
            }
        }
        return selectedItemCount;
    }

    /**
     * 获取当前已选择的标签
     *
     * @return
     */
    public List<MarsterFieldEntity> getSelectedLables() {

        List<MarsterFieldEntity> selectedLablesList = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            MarsterFieldEntity marsterFieldEntity = datas.get(i);
            if (marsterFieldEntity.isChecked()) {
                selectedLablesList.add(marsterFieldEntity);
            }
        }
        return selectedLablesList;
    }
}
