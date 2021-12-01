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
        TinyUrlService service = new Retrofit.Builder()
                .baseUrl("http://tiny-url.info/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TinyUrlService.class);

        Response<TinyUrlResponse> response = service.makeShortURL("json", "https://www.google.com.ua/maps/place/%D0%9D%D0%BE%D0%B2%D0%B0%D1%8F+%D0%97%D0%B5%D0%BB%D0%B0%D0%BD%D0%B4%D0%B8%D1%8F/@-33.3670439,146.0094856,13.96z/data=!4m5!3m4!1s0x6d2c200e17779687:0xb1d618e2756a4733!8m2!3d-40.900557!4d174.885971?hl=ru").execute();

        if (response.isSuccessful()) {
            System.out.println(response.body().shorturl);
        } else {
            System.out.println(response.errorBody().string());
        }
    }
}
