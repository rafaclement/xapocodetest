package com.xapo.reposcodetest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xapo.reposcodetest.R;
import com.xapo.reposcodetest.data.helpers.Tuple;
import com.xapo.reposcodetest.ui.viewholders.SingleRepoDetailViewHolder;

import java.util.List;


/**
 * Created by rclement on 10/4/18.
 */

public class SingleDetailRepoListAdapter extends RecyclerView.Adapter<SingleRepoDetailViewHolder> {

    List<Tuple> mItems;
    private Context mContext;

    public SingleDetailRepoListAdapter(List<Tuple> items, Context context) {
        this.mItems = items;
        this.mContext = context;
    }

    @Override
    public SingleRepoDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sigle_repo_list_layout, parent, false);
        return new SingleRepoDetailViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SingleRepoDetailViewHolder holder, int position) {
        final Tuple repo = mItems.get(position);
        holder.getTvName().setText(repo.getX().toString());
        holder.getTvValue().setText(repo.getY().toString());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
