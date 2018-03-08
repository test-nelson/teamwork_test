package test.nelson.teamwork.net;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class AuthInterceptor implements Interceptor {
    private String credentials;

    AuthInterceptor(String username, String password) {
        this.credentials = Credentials.basic(username, password);
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
    }

}
