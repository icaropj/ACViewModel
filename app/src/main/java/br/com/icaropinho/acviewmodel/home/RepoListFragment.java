package br.com.icaropinho.acviewmodel.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import br.com.icaropinho.acviewmodel.R;
import br.com.icaropinho.acviewmodel.model.Repo;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by icaro on 03/07/2018.
 */

public class RepoListFragment extends Fragment {

    @BindView(R.id.rv_repos)
    RecyclerView mRvRepos;

    @BindView(R.id.tv_error)
    TextView mTvError;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private Unbinder mUnbinder;

    private RepoListViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.repo_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(RepoListViewModel.class);

        mRvRepos.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRvRepos.setAdapter(new RepoListAdapter(mViewModel, this));
        mRvRepos.setLayoutManager(new LinearLayoutManager(getContext()));
        observeViewModel();
    }

    private void observeViewModel() {
        mViewModel.getRepos().observe(this, repos -> {
            if (repos != null) {
                mRvRepos.setVisibility(View.VISIBLE);
            }
        });
        mViewModel.getRepoLoadError().observe(this, isError -> {
            if(isError) {
                mTvError.setVisibility(View.VISIBLE);
                mRvRepos.setVisibility(View.GONE);
                mTvError.setText(R.string.api_error_repo);
            } else {
                mTvError.setVisibility(View.GONE);
                mTvError.setText("");
            }
        });
        mViewModel.getIsLoading().observe(this, isLoading -> {
            mProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if(isLoading) {
                mTvError.setVisibility(View.GONE);
                mRvRepos.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }
}
