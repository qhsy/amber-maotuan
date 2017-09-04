package com.chad.library.adapter.base.entity;

import java.io.Serializable;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class MultiItemEntity implements BaseMultiItem, Serializable {

    private int itemType;

    public MultiItemEntity(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}