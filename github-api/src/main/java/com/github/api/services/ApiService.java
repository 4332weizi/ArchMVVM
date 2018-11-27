package com.github.api.services;

import com.github.api.model.ServerStatus;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("status")
    Observable<ServerStatus> getStatus();

}
