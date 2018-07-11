package br.com.icaropinho.acviewmodel.details;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.icaropinho.acviewmodel.R;
import br.com.icaropinho.acviewmodel.home.SelectedRepoViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by icaro on 11/07/2018.
 */

public class RepoDetailsFragment extends Fragment {

    @BindView(R.id.tv_repo_name) TextView tvRepoName;
    @BindView(R.id.tv_repo_description) TextView tvRepoDescription;
    @BindView(R.id.tv_forks) TextView tvForks;
    @BindView(R.id.tv_stars) TextView tvStars;

    private Unbinder unbinder;
    private SelectedRepoViewModel selectedRepoViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.repo_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectedRepoViewModel = ViewModelProviders.of(getActivity()).get(SelectedRepoViewModel.class);
        selectedRepoViewModel.restoreFromBundle(savedInstanceState);
        displayRepo();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        selectedRepoViewModel.saveToBundle(outState);
    }

    private void displayRepo() {
        selectedRepoViewModel.getSelectedRepo().observe(this, repo -> {
            tvRepoName.setText(repo.getName());
            tvRepoDescription.setText(repo.getDescription());
            tvForks.setText(String.valueOf(repo.getForks()));
            tvStars.setText(String.valueOf(repo.getStars()));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}
