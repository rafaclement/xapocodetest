package com.xapo.reposcodetest.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

import com.xapo.reposcodetest.R;
import com.xapo.reposcodetest.data.model.Repo;
import com.xapo.reposcodetest.ui.adapters.ReposListAdapter;

import java.util.ArrayList;
import java.util.List;

public class TrendingReposActivity extends Activity {

    private ReposListAdapter mAdapter;
    private ListView mLvRepos;

    private List<Repo> mListRepos;
    private SearchView mSearchView;
    private EditText mEtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_repos);

        mListRepos = new ArrayList<>();
        loadFakeList();
        mAdapter = new ReposListAdapter(mListRepos, TrendingReposActivity.this);
        mLvRepos = (ListView) findViewById(R.id.lv_trending_repos);
        mLvRepos.setAdapter(mAdapter);

        mEtSearch = (EditText) findViewById(R.id.et_search_trending_repos);

    }

    private void loadFakeList() {
        Repo repo1 = new Repo(1);
        Repo repo2 = new Repo(2);
        Repo repo3 = new Repo(3);
        mListRepos.add(repo1);
        mListRepos.add(repo2);
        mListRepos.add(repo3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_repos_list_activity, menu);

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mEtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                String text = mEtSearch.getText().toString().toLowerCase();
                mAdapter.getFilter().filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            }
        });
    }


}
