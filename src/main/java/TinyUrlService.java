import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TinyUrlService {
    @POST("open_api.html")
    @FormUrlEncoded
    Call<TinyUrlResponse> makeShortURL(@Field("format") String format, @Field("url") String originalURL);
}
