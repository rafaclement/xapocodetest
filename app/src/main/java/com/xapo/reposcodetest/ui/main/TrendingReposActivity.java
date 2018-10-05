package com.xapo.reposcodetest.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xapo.reposcodetest.R;
import com.xapo.reposcodetest.network.clients.RestRepoClient;
import com.xapo.reposcodetest.network.pojos.Item;
import com.xapo.reposcodetest.network.pojos.RepoFeed;
import com.xapo.reposcodetest.ui.adapters.ReposListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrendingReposActivity extends Activity {

    private ReposListAdapter mAdapter;
    private RecyclerView mRvRepos;

    private List<Item> mListRepos;
    private EditText mEtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_repos);

        mListRepos = new ArrayList<>();
        mAdapter = new ReposListAdapter(mListRepos, TrendingReposActivity.this);
        mRvRepos = (RecyclerView) findViewById(R.id.rv_trending_repos);
        mRvRepos.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRvRepos.setLayoutManager(layoutManager);
        mRvRepos.setAdapter(mAdapter);
        mRvRepos.addItemDecoration(new DividerItemDecoration(mRvRepos.getContext(), DividerItemDecoration.VERTICAL));

        mEtSearch = (EditText) findViewById(R.id.et_search_trending_repos);

        loadJSON();
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

    private void loadJSON() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestRepoClient restClient = retrofit.create(RestRepoClient.class);
        Map<String, String> params = new HashMap<>();
        params.put("sort", "stars");
        params.put("order", "desc");
        params.put("q", "language:kotlin" +
                "");

        Call<RepoFeed> call = restClient.getData(params);
        call.enqueue(new Callback<RepoFeed>() {

            @Override
            public void onResponse(Call<RepoFeed> call, Response<RepoFeed> response) {
                switch (response.code()) {
                    case 200:
                        RepoFeed data = response.body();
                        mAdapter.setRepos(data.getItems());
                        mAdapter.notifyDataSetChanged();
                        break;
                    case 400:
                        System.out.println(response.errorBody());
                        Toast.makeText(TrendingReposActivity.this, R.string.error400, Toast.LENGTH_LONG).show();
                        break;
                    case 500:
                        System.out.println(response.errorBody());
                        Toast.makeText(TrendingReposActivity.this, R.string.error500, Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<RepoFeed> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
