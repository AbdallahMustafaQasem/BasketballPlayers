package abdallah.qasem.basketballplayers.repositories;



import abdallah.qasem.basketballplayers.models.PlayersData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlayersApi {
    @GET("players/")
    Call<PlayersData> getNewsList(@Query("page") int page,
                                  @Query("per_page") String per_page);
}
