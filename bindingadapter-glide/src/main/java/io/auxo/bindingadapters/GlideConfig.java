package io.auxo.bindingadapters;

import com.bumptech.glide.request.RequestOptions;

import java.util.Objects;

public final class GlideConfig {

    private static GlideConfig mInstance = new GlideConfig();

    private RequestOptions mDefaultRequestOptions = newOptions();

    private GlideConfig() {
    }

    private static GlideConfig get() {
        return mInstance;
    }

    public static void setDefaultRequestOptions(RequestOptions requestOptions) {
        Objects.requireNonNull(requestOptions);
        get().mDefaultRequestOptions = requestOptions;
    }

    public static RequestOptions getDefaultRequestOptions() {
        return get().mDefaultRequestOptions.clone();
    }

    public static RequestOptions newOptions() {
        return new RequestOptions();
    }
}
