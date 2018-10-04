package com.xapo.reposcodetest.ui.viewholders;

import android.widget.TextView;

/**
 * Created by rclement on 10/4/18.
 */

public class RepoViewHolder {
    private TextView mTvRepoName;
    private TextView mTvUserName;

    public TextView getRepoName() {
        return mTvRepoName;
    }

    public void setRepoName(TextView repoName) {
        this.mTvRepoName = repoName;
    }

    public TextView getUserName() {
        return mTvUserName;
    }

    public void setUserName(TextView userName) {
        this.mTvUserName = userName;
    }
}
