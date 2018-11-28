package io.auxo.arch.sample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.github.api.GitHubApi;
import com.github.api.model.User;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Victor Chiu
 */
public class UserManager {

    private static UserManager mInstance = new UserManager();

    private final MutableLiveData<User> mUser = new MutableLiveData<>();

    private UserManager() {
    }

    public static UserManager get() {
        return mInstance;
    }

    public synchronized Observable<User> syncUserFromServer() {
        return GitHubApi.getUserService()
                .getUser()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .doOnNext(result -> mUser.postValue(result));
    }

    public LiveData<User> getLiveUser() {
        return mUser;
    }

    public User getUser() {
        return mUser.getValue();
    }

    public boolean isLoggedIn() {
        return getUser() != null;
    }
}
