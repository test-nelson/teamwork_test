package test.nelson.teamwork.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class RetrofitProvider {
    private static final String BASE_URL = "https://yat.teamwork.com/";
    private Retrofit retrofit;
    private static RetrofitProvider provider;


    Retrofit getRetrofit(String username, String password) {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getHttpClient(username, password))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return retrofit;
    }

    private OkHttpClient getHttpClient(String username, String password) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new AuthInterceptor(username, password));
        return builder.build();
    }

    public static RetrofitProvider getInstance() {
        if (provider == null)
            provider = new RetrofitProvider();
        return provider;
    }
}
