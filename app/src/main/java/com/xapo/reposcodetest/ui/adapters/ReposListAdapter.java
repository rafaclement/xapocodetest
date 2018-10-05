package com.xapo.reposcodetest.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.xapo.reposcodetest.R;
import com.xapo.reposcodetest.network.pojos.Item;
import com.xapo.reposcodetest.ui.main.TrendingRepoDetailActivity;
import com.xapo.reposcodetest.ui.viewholders.RepoViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rclement on 10/4/18.
 */

public class ReposListAdapter extends RecyclerView.Adapter<RepoViewHolder> implements Filterable {

    private List<Item> mRepos;
    private List<Item> mReposFiltred;
    private Context mContext;
    private ReposFilter mFilter;

    public ReposListAdapter(List<Item> repos, Context context) {
        this.mRepos = repos;
        this.mReposFiltred = (List)((ArrayList)repos).clone();
        this.mContext = context;
        getFilter();
    }

    public List<Item> getRepos() {
        return mRepos;
    }

    public void setRepos(List<Item> mRepos) {
        this.mReposFiltred = (List)((ArrayList)mRepos).clone();
        this.mRepos = mRepos;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_repo_list_layout, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        final Item repo = mReposFiltred.get(position);
        holder.getRepoName().setText(repo.getName());
        holder.getParent().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetail = new Intent(mContext, TrendingRepoDetailActivity.class);
                intentDetail.putExtra("item", repo);
                mContext.startActivity(intentDetail);
            }
        });


    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return mReposFiltred.size();
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ReposFilter();
        }

        return mFilter;
    }

    private class ReposFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if(constraint.length()==0){
                filterResults.values = mRepos;
            } else {

                if (constraint!=null && constraint.length()>0) {
                    List<Item> tempList = new ArrayList<Item>();

                    // search content in friend list
                    for (Item repo: mRepos) {
                        if ((repo.getName()+ "").contains(constraint)) {
                            tempList.add(repo);
                        }
                    }

                    filterResults.count = tempList.size();
                    filterResults.values = tempList;
                } else {
                    filterResults.count = mRepos.size();
                    filterResults.values = mRepos;
                }

            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mReposFiltred = (List<Item>) results.values;
            notifyDataSetChanged();
        }
    }
}
