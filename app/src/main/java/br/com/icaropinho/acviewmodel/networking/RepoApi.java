package br.com.icaropinho.acviewmodel.networking;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by icaro on 03/07/2018.
 */

public class RepoApi {

    private static Retrofit retrofit;
    private static RepoService repoService;

//    private static void initializeRetrofit() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(MoshiConverterFactory.create())
//                .build();
//    }
//
//    public static RepoService getInstance() {
//        if(repoService != null) {
//            return repoService;
//        }
//        if(retrofit == null) {
//            initializeRetrofit();
//        }
//        repoService = retrofit.create(RepoService.class);
//        return repoService;
//    }

    private RepoApi(){
    }

}
