package abdallah.qasem.basketballplayers.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import abdallah.qasem.basketballplayers.models.PlayersData;
import abdallah.qasem.basketballplayers.repositories.PlayersRepositories;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<PlayersData> mPlayersData;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mPlayersData = PlayersRepositories.getInstance().getPlayersData(0, "20");
    }


    public  LiveData<PlayersData> getPage (int page , String per_page)
    {

        mPlayersData =  PlayersRepositories.getInstance().getPlayersData(page, per_page);
        return mPlayersData;
    }



    public LiveData<PlayersData> getPlayersData() {
        return mPlayersData;
    }
}
