package io.auxo.arch.sample;

import android.app.Application;

import com.github.api.GitHubApi;

/**
 * @author Victor Chiu
 */
public class GitHubApp extends Application {

    private static GitHubApp mInstance;
    private AuthorizationManager mAuthorizationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mAuthorizationManager = new AuthorizationManager(this);
        GitHubApi.init(mAuthorizationManager, mAuthorizationManager);
    }

    public AuthorizationManager getAuthorizationManager(){
        return mAuthorizationManager;
    }

    public boolean isUserLoggedIn() {
        return mAuthorizationManager.isLoggedIn();
    }

    public static GitHubApp get() {
        return mInstance;
    }
}
