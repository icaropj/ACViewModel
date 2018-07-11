package br.com.icaropinho.acviewmodel.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.util.Log;

import br.com.icaropinho.acviewmodel.model.Repo;
import br.com.icaropinho.acviewmodel.networking.RepoApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by icaro on 11/07/2018.
 */

public class SelectedRepoViewModel extends ViewModel {

    private final MutableLiveData<Repo> selectedRepo = new MutableLiveData<>();
    private Call<Repo> repoCall;

    public MutableLiveData<Repo> getSelectedRepo() {
        return selectedRepo;
    }

    void setSelectedRepo(Repo repo) {
        selectedRepo.setValue(repo);
    }

    public void saveToBundle(Bundle bundle) {
        if(selectedRepo.getValue() != null) {
            bundle.putStringArray("repo_details", new String[]{
                    selectedRepo.getValue().getOwner().getLogin(),
                    selectedRepo.getValue().getName()
            });
        }
    }

    public void restoreFromBundle(Bundle bundle) {
        if(selectedRepo.getValue() == null) {
            if(bundle != null && bundle.containsKey("repo_details")) {
                loadRepo(bundle.getStringArray("repo_details"));
            }
        }
    }

    private void loadRepo(String[] repoDetails) {
        repoCall = RepoApi.getInstance().getRepo(repoDetails[0], repoDetails[1]);
        repoCall.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                selectedRepo.setValue(response.body());
                repoCall = null;
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Log.d("Debug", t.getMessage());
                repoCall = null;
            }
        });
    }
}
