package br.com.icaropinho.acviewmodel.networking;

import java.util.List;

import br.com.icaropinho.acviewmodel.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by icaro on 03/07/2018.
 */

public interface RepoService {

    @GET("orgs/google/repos")
    Call<List<Repo>> getRepositories();

    @GET("repos/{owner}/{name}")
    Call<Repo> getRepo(@Path("owner") String owner, @Path("name") String name);

}
