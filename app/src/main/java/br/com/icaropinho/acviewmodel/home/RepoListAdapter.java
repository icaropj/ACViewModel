package br.com.icaropinho.acviewmodel.home;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.icaropinho.acviewmodel.R;
import br.com.icaropinho.acviewmodel.model.Repo;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by icaro on 08/07/2018.
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>{

    private List<Repo> mRepos = new ArrayList<>();

    public RepoListAdapter(RepoListViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            mRepos.clear();
            if (repos != null) {
                mRepos.addAll(repos);
                notifyDataSetChanged();
                //TODO: DiffUtil
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(mRepos.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    @Override
    public long getItemId(int position) {
        return mRepos.get(position).getId();
    }

    static class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_repo_name) TextView tvRepoName;
        @BindView(R.id.tv_repo_description) TextView tvRepoDescription;
        @BindView(R.id.tv_forks) TextView tvForks;
        @BindView(R.id.tv_stars) TextView tvStars;

        RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Repo repo) {
            tvRepoName.setText(repo.getName());
            tvRepoDescription.setText(repo.getDescription());
            tvForks.setText(String.valueOf(repo.getForks()));
            tvStars.setText(String.valueOf(repo.getStars()));
        }
    }

}
