package net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices;

import android.util.Log;

import net.vrgsoft.rollinglayoutmanager.logic.datalayer.IGetData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetDataRest {
    static List<Repo> listaRepo;

    public static List<Repo> downloadRepository(String nickname, final IGetData listener) {
        listaRepo = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        service.listRepos(nickname).enqueue(new Callback<List<Repo>>() { //asincrono
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                if (response.body() != null) {
                    listaRepo.clear();
                    listaRepo.addAll(response.body());
                    listener.getRepoDownloaded(listaRepo);
                } else {
                    listener.elementi_non_disponibili();
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e("LogicOnFailure", t.toString());
                listener.errore();
            }
        });
        return listaRepo;
    }



    public static List<Repo> getRepository() {
        return listaRepo;
    }

}
