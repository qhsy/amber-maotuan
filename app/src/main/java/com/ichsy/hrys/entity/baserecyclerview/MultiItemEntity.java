package com.ichsy.hrys.entity.baserecyclerview;

import java.io.Serializable;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class MultiItemEntity implements BaseMultiItem, Serializable{

    protected int itemType;

    public MultiItemEntity(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}

