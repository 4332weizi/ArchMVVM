package com.github.api.services;

import com.github.api.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UserService {

    @GET("user")
    Observable<User> getUser();
}
