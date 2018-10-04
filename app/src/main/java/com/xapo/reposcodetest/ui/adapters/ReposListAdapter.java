package com.xapo.reposcodetest.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.xapo.reposcodetest.R;
import com.xapo.reposcodetest.data.model.Repo;
import com.xapo.reposcodetest.ui.viewholders.RepoViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rclement on 10/4/18.
 */

public class ReposListAdapter extends BaseAdapter implements Filterable {

    private List<Repo> mRepos;
    private List<Repo> mReposFiltred;
    private Context mContext;
    private ReposFilter mFilter;

    public ReposListAdapter(List<Repo> repos, Context context) {
        this.mRepos = repos;
        this.mReposFiltred = (List)((ArrayList)repos).clone();
        this.mContext = context;
        getFilter();
    }

    @Override
    public int getCount() {
        return mReposFiltred.size();
    }

    @Override
    public Object getItem(int i) {
        return mReposFiltred.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final RepoViewHolder holder;
        final Repo repo = (Repo) getItem(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_repo_list_layout, null);
            holder = new RepoViewHolder();
            holder.setRepoName((TextView) view.findViewById(R.id.tv_repo_name_item_list));
            holder.setUserName((TextView) view.findViewById(R.id.tv_user_name_item_list));
            view.setTag(holder);
        } else {
            holder = (RepoViewHolder) view.getTag();
        }

        holder.getRepoName().setText("#");
        holder.getUserName().setText(repo.getId()+"");

        return view;
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
                    List<Repo> tempList = new ArrayList<Repo>();

                    // search content in friend list
                    for (Repo repo: mRepos) {
                        if ((repo.getId() + "").equals(constraint)) {
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
            mReposFiltred = (List<Repo>) results.values;
            notifyDataSetChanged();
        }
    }
}
