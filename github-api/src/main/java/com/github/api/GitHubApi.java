package com.github.api;

import com.github.api.authorization.AuthorizationExpiredCallback;
import com.github.api.authorization.AuthorizationProvider;
import com.github.api.interceptor.AuthorizationInterceptor;
import com.github.api.interceptor.LogInterceptor;
import com.github.api.services.ApiService;
import com.github.api.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;

public class GitHubApi {

    private static String GITHUB_API_BASE_URL = "https://api.github.com";
    public static Map<String, Object> mServiceStore = new HashMap<>();

    private static boolean hasInit = false;

    private GitHubApi() {
    }

    public synchronized static void init(AuthorizationProvider provider, AuthorizationExpiredCallback callback) {
        if (!hasInit) {
            List<Interceptor> interceptors = new ArrayList<>();
            interceptors.add(new AuthorizationInterceptor(provider, callback));
            interceptors.add(new LogInterceptor(BuildConfig.DEBUG));
            RetrofitClient.init(GITHUB_API_BASE_URL, null, interceptors);
            hasInit = true;
        }
    }

    public static ApiService getApiService() {
        return getService(ApiService.class);
    }

    public static UserService getUserService() {
        return getService(UserService.class);
    }

    public static <T> T getService(Class<T> clazz) {
        String key = clazz.getCanonicalName();
        T service;
        synchronized (clazz) {
            service = (T) mServiceStore.get(key);
            if (service == null) {
                service = createService(clazz);
            }
        }
        return service;
    }

    private static <T> T createService(Class<T> clazz) {
        if (!hasInit) {
            throw new IllegalStateException("you must init GitHubApi first");
        }
        return RetrofitClient.client()
                .retrofit()
                .create(clazz);
    }
}
