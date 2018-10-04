package com.xapo.reposcodetest.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xapo.reposcodetest.R;

/**
 * Created by rclement on 10/4/18.
 */

public class RepoViewHolder extends RecyclerView.ViewHolder {
    private TextView mTvRepoName;


    public RepoViewHolder(View itemView) {
        super(itemView);
        mTvRepoName = (TextView) itemView.findViewById(R.id.tv_repo_name_item_list);
    }

    public TextView getRepoName() {
        return mTvRepoName;
    }

    public void setRepoName(TextView repoName) {
        this.mTvRepoName = repoName;
    }

}
