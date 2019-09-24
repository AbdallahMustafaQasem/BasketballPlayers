package abdallah.qasem.basketballplayers.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import abdallah.qasem.basketballplayers.models.PlayersData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayersRepositories {

    private static PlayersRepositories instance;
    private PlayersApi newsApi;


    public static PlayersRepositories getInstance() {
        if (instance == null) {
            instance = new PlayersRepositories();
        }
        return instance;
    }

    public PlayersRepositories(){
        newsApi = RetrofitService.cteateService(PlayersApi.class);
    }


    public MutableLiveData<PlayersData> getPlayersData(int page , String per_page){
        final MutableLiveData<PlayersData> playersDataMutableLiveData = new MutableLiveData<>();
        newsApi.getNewsList(page,per_page).enqueue(new Callback<PlayersData>() {
            @Override
            public void onResponse(Call<PlayersData> call, Response<PlayersData> response) {
                if (response.isSuccessful()){
                  playersDataMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PlayersData> call, Throwable t) {
             playersDataMutableLiveData.setValue(null);
            }
        });
        return playersDataMutableLiveData;
    }




}



