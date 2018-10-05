package com.xapo.reposcodetest.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.xapo.reposcodetest.R;
import com.xapo.reposcodetest.data.helpers.Tuple;
import com.xapo.reposcodetest.network.pojos.Item;
import com.xapo.reposcodetest.network.pojos.Owner;
import com.xapo.reposcodetest.ui.adapters.SingleDetailRepoListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rclement on 10/5/18.
 */

public class TrendingRepoDetailActivity extends Activity {

    private Item mSingle_repo;
    private SingleDetailRepoListAdapter mAdapter;
    private RecyclerView mRvRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trending_repo);

    }

    @Override
    protected void onResume() {
        mSingle_repo = (Item) getIntent().getSerializableExtra("item");
        List<Tuple> detailData = setDataOfSingleRepo();
        mAdapter = new SingleDetailRepoListAdapter(detailData, TrendingRepoDetailActivity.this);
        mRvRepos = (RecyclerView) findViewById(R.id.rv_detail_trending_repo);
        mRvRepos.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRvRepos.setLayoutManager(layoutManager);
        mRvRepos.setAdapter(mAdapter);
        super.onResume();
    }

    private List<Tuple> setDataOfSingleRepo(){

        //To add new values on the detail view:
        // 1) Add the resource name as a string in the string resources file
        // 2) Add a new tuple to the list containing string (name) and string (value) to display
        
        TextView tvSingleRepoName = (TextView) findViewById(R.id.tv_title_sigle_trending_repo_name);
        tvSingleRepoName.setText(mSingle_repo.getName() + " ");

        List<Tuple> listKeyValues = new ArrayList<>();

        String privacy = mSingle_repo.getPrivate().toString();
        listKeyValues.add(getItemTuple(getString(R.string.repoDetailPrivate),privacy));

        Owner owner = mSingle_repo.getOwner();
        String login = owner.getLogin();
        listKeyValues.add(getItemTuple(getString(R.string.repoDetailOwner),login));

        String url = mSingle_repo.getUrl();
        listKeyValues.add(getItemTuple(getString(R.string.repoDetailUrl),url));

        String createdAt = mSingle_repo.getCreatedAt();
        listKeyValues.add(getItemTuple(getString(R.string.repoDetailCreatedAt),createdAt));

        String updatedAt = mSingle_repo.getUpdatedAt();
        listKeyValues.add(getItemTuple(getString(R.string.repoDetailUpdatedAt),updatedAt));

        Double score = mSingle_repo.getScore();
        listKeyValues.add(getItemTuple(getString(R.string.repoDetailScore),score.toString()));

        Integer forks = mSingle_repo.getForks();
        listKeyValues.add(getItemTuple(getString(R.string.repoDetailFork),forks.toString()));

        String defaultBranch = mSingle_repo.getDefaultBranch();
        listKeyValues.add(getItemTuple(getString(R.string.repoDetailDefaultBranch),defaultBranch));

        Integer openIssuesCount = mSingle_repo.getOpenIssuesCount();
        listKeyValues.add(getItemTuple(getString(R.string.repoDetailIsuesCount),openIssuesCount.toString()));

        String description = mSingle_repo.getDescription();
        listKeyValues.add(getItemTuple(getString(R.string.repoDetailDescription),description));

        return listKeyValues;
    }

    private Tuple getItemTuple(String key, String value){
        Tuple tupleOwner = new Tuple<>(key,value);
        return tupleOwner;
    }
}
