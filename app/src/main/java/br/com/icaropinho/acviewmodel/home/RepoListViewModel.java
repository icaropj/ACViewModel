package br.com.icaropinho.acviewmodel.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import br.com.icaropinho.acviewmodel.model.Repo;
import br.com.icaropinho.acviewmodel.networking.RepoApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by icaro on 03/07/2018.
 */

public class RepoListViewModel extends ViewModel {

    private MutableLiveData<List<Repo>> repos = new MutableLiveData<>();
    private MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private Call<List<Repo>> call;

    public RepoListViewModel() {
        fetchRepos();
    }

    private void fetchRepos() {
        isLoading.setValue(true);
        call = RepoApi.getInstance().getRepositories();
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                repoLoadError.setValue(false);
                repos.setValue(response.body());
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                repoLoadError.setValue(true);
                isLoading.setValue(false);
                call = null;
            }
        });
    }

    @Override
    protected void onCleared() {
        if(call != null) {
            call.cancel();
            call = null;
        }
    }

    public LiveData<List<Repo>> getRepos() {
        return repos;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Boolean> getRepoLoadError() {
        return repoLoadError;
    }
}
