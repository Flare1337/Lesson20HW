import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new Main().runGoogle();
            new Main().runGithub();
            new Main().runTiny();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runGoogle() throws IOException {
        GoogleWebService service = new Retrofit.Builder()
                .baseUrl("https://google.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GoogleWebService.class);

        Response<String> response = service.index().execute();
        if (response.isSuccessful()) {
            System.out.println(response.body());
        } else {
            System.out.println(response.errorBody().string());
        }
    }

    private void runGithub() throws IOException {
        GitHubService service = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GitHubService.class);

        Response<String> response = service.getListOfRepositories("octocat").execute();
        if (response.isSuccessful()) {
            System.out.println(response.body());
        } else {
            System.out.println(response.errorBody().string());
        }
    }

    private void runTiny() throws IOException {
        final TinyUrlService service = new Retrofit.Builder()
                .baseUrl("http://tiny-url.info/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TinyUrlService.class);

        Response<TinyUrlResponse> response = service.makeShortURL("json", "http://www.tiny-url.info/open_api.html").execute();

        if (response.isSuccessful()) {
            System.out.println(response.body().shorturl);
        } else {
            System.out.println(response.errorBody().string());
        }
    }
}
