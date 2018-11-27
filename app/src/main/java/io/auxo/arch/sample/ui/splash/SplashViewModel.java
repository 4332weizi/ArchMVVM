package io.auxo.arch.sample.ui.splash;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import io.auxo.arch.mvvm.viewmodel.livedata.SingleLiveEvent;
import io.auxo.arch.sample.GitHubApp;
import io.auxo.arch.sample.UserManager;

public class SplashViewModel extends ViewModel {

    private final SingleLiveEvent mNavigateLoginEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent mNavigateMainEvent = new SingleLiveEvent<>();

    public SplashViewModel() {
        GitHubApp.get().getAuthorizationManager().disableExpiredHandler();
        syncUser();
    }

    private void syncUser() {
        UserManager.get()
                .syncUserFromServer()
                .subscribe(user -> mNavigateMainEvent.call(),
                        throwable -> mNavigateLoginEvent.call());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        GitHubApp.get().getAuthorizationManager().enableExpiredHandler();
    }

    public LiveData getNavigateLoginEvent() {
        return mNavigateLoginEvent;
    }

    public LiveData getNavigateMainEvent() {
        return mNavigateMainEvent;
    }
}
