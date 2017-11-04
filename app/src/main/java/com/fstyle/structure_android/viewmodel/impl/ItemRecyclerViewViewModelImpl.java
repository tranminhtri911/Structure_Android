package com.fstyle.structure_android.viewmodel.impl;

import android.view.View;
import com.fstyle.structure_android.view.OnRecyclerViewItemClickListener;
import com.fstyle.structure_android.viewmodel.ItemRecyclerViewViewModel;

/**
 * Created by daolq on 11/3/17.
 */

public class ItemRecyclerViewViewModelImpl implements ItemRecyclerViewViewModel {

    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    private Object mObjectData;

    public ItemRecyclerViewViewModelImpl(Object objectData,
            OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mObjectData = objectData;
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public void onItemRecyclerViewClick(View view) {
        if (mOnRecyclerViewItemClickListener != null) {
            mOnRecyclerViewItemClickListener.onItemRecyclerViewClick(mObjectData);
        }
    }
}
