package com.xapo.reposcodetest.network.clients;

import com.xapo.reposcodetest.network.pojos.RepoFeed;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by rclement on 10/4/18.
 */

public interface RestRepoClient {
    //https://api.github.com/users/xapo/repos
    //curl -G https://api.github.com/search/repositories --data-urlencode "sort=stars" --data-urlencode "order=desc" --data-urlencode "q=language:java"  --data-urlencode "q=created:>`date -v-7d '+%Y-%m-%d'`"
    @GET("search/repositories")
    Call<RepoFeed> getData(@QueryMap(encoded=true)Map<String, String> options);
}
