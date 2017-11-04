package com.fstyle.structure_android.view.searchresult;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fstyle.structure_android.R;
import com.fstyle.structure_android.data.model.User;
import com.fstyle.structure_android.databinding.ItemSearchResultBinding;
import com.fstyle.structure_android.view.BaseRecyclerViewAdapter;

import com.fstyle.structure_android.viewmodel.impl.ItemRecyclerViewViewModelImpl;
import com.fstyle.structure_android.viewmodel.impl.UserViewModelImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by le.quang.dao on 14/03/2017.
 */

public class SearchResultAdapter
        extends BaseRecyclerViewAdapter<SearchResultAdapter.ItemViewHolder> {

    private List<User> mUsers;
    private SearchResultRecyclerViewListener mSearchResultRecyclerViewListener;

    SearchResultAdapter(@NonNull Context context, List<User> users) {
        super(context);
        mUsers = new ArrayList<>();
        if (users == null) {
            return;
        }
        mUsers.addAll(users);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSearchResultBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_search_result, parent, false);
        return new ItemViewHolder(binding, mSearchResultRecyclerViewListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setItemClickListener(SearchResultRecyclerViewListener listener) {
        mSearchResultRecyclerViewListener = listener;
    }

    /**
     * ItemViewHolder
     */
    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemSearchResultBinding mBinding;
        private SearchResultRecyclerViewListener mSearchResultRecyclerViewListener;

        ItemViewHolder(ItemSearchResultBinding binding, SearchResultRecyclerViewListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mSearchResultRecyclerViewListener = listener;
        }

        private void bind(User user) {
            mBinding.setUserViewModel(new UserViewModelImpl(user));
            mBinding.setItemRecyclerViewViewModel(
                    new ItemRecyclerViewViewModelImpl(user, mSearchResultRecyclerViewListener));
            mBinding.executePendingBindings();
        }
    }
}
