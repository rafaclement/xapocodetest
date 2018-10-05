package com.xapo.reposcodetest.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xapo.reposcodetest.R;

/**
 * Created by rclement on 10/4/18.
 */

public class SingleRepoDetailViewHolder extends RecyclerView.ViewHolder {
    private TextView mTvName;
    private TextView mTvValue;


    public SingleRepoDetailViewHolder(View itemView) {
        super(itemView);
        mTvName = (TextView) itemView.findViewById(R.id.tv_repo_key_item_list);
        mTvValue = (TextView) itemView.findViewById(R.id.tv_repo_value_item_list);
    }

    public TextView getTvName() {
        return mTvName;
    }

    public void setTvName(TextView tvName) {
        this.mTvName = tvName;
    }

    public TextView getTvValue() {
        return mTvValue;
    }

    public void setTvValue(TextView tvValue) {
        this.mTvValue = tvValue;
    }
}
