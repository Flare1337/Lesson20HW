import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("users/{user}/repos")
    Call<String> getListOfRepositories(
            @Path("user") String username);
}
