package com.github.api;


import com.github.api.converter.ApiConverterFactory;

import java.net.Proxy;
import java.util.List;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author Victor Chiu
 */
public class RetrofitClient {

    private static RetrofitClient mInstance;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;

    private RetrofitClient() {
    }

    private RetrofitClient(String baseUrl, Authenticator authenticator, List<Interceptor> interceptors) {
        // init OkHttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (!BuildConfig.PROXY_ENABLE) {
            builder.proxy(Proxy.NO_PROXY);//是否可抓包
        }

        if (authenticator != null) {
            builder.authenticator(authenticator);
        }

        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        mOkHttpClient = builder.build();

        // init Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ApiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    public static RetrofitClient init(String baseUrl, Authenticator authenticator, List<Interceptor> interceptors) {
        if (mInstance == null) {
            synchronized (RetrofitClient.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitClient(baseUrl, authenticator, interceptors);
                }
            }
        }
        return mInstance;
    }

    public static RetrofitClient client() {
        return mInstance;
    }

    public Retrofit retrofit() {
        return mRetrofit;
    }

    public OkHttpClient okHttpClient() {
        return mOkHttpClient;
    }
}
