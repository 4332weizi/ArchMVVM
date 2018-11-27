package com.github.api.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Victor Chiu
 */
public class ApiConverterFactory extends Converter.Factory {

    private Gson gson;

    public ApiConverterFactory(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("parameter gson can not be null");
        }
        this.gson = gson;
    }

    public static ApiConverterFactory create() {
        return create(new Gson());
    }

    public static ApiConverterFactory create(Gson gson) {
        return new ApiConverterFactory(gson);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new ApiResponseBodyConverter(gson, adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new ApiRequestBodyConverter(gson, adapter);
    }
}
