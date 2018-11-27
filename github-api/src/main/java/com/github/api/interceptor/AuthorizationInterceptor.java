package com.github.api.interceptor;

import android.support.annotation.NonNull;

import com.github.api.authorization.AuthorizationExpiredCallback;
import com.github.api.authorization.AuthorizationProvider;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * an authorization interceptor to add authorization value to header
 *
 * @author Victor Chiu
 */
public class AuthorizationInterceptor implements Interceptor {

    protected final int HTTP_STATUS_CODE_UNAUTHORIZED = 401;

    private final String HEADER_AUTHORIZATION_KEY = "Authorization";
    private final String HEADER_AUTHORIZATION_VALUE_PREFIX = "Basic ";

    private AuthorizationExpiredCallback mAuthorizationExpiredCallback;
    private AuthorizationProvider mAuthorizationProvider;

    public AuthorizationInterceptor(@NonNull AuthorizationProvider provider,
                                    @NonNull AuthorizationExpiredCallback callback) {
        mAuthorizationProvider = provider;
        mAuthorizationExpiredCallback = callback;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Headers originHeaders = request.headers();

        Headers newHeaders = new Headers.Builder()
                .add(HEADER_AUTHORIZATION_KEY,
                        HEADER_AUTHORIZATION_VALUE_PREFIX + mAuthorizationProvider.getAuthorization())
                .build();

        request = request.newBuilder()
                .headers(originHeaders)
                .headers(newHeaders)
                .build();

        Response response = chain.proceed(request);

        if (response.code() == HTTP_STATUS_CODE_UNAUTHORIZED) {
            mAuthorizationExpiredCallback.onAuthorizationExpired();
        }

        return response;
    }
}
