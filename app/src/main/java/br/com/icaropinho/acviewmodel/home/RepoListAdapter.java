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
    private RepoSelectedListener mListener;

    public RepoListAdapter(RepoListViewModel viewModel, LifecycleOwner lifecycleOwner, RepoSelectedListener listener) {
        mListener = listener;
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
        return new RepoViewHolder(view, mListener);
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

        private Repo repo;

        RepoViewHolder(View itemView, RepoSelectedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (repo != null) {
                    listener.onRepoSelected(repo);
                }
            });
        }

        void bind(Repo repo) {
            this.repo = repo;
            tvRepoName.setText(repo.getName());
            tvRepoDescription.setText(repo.getDescription());
            tvForks.setText(String.valueOf(repo.getForks()));
            tvStars.setText(String.valueOf(repo.getStars()));
        }
    }

}
