package io.auxo.arch.sample;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.github.api.authorization.AuthorizationExpiredCallback;
import com.github.api.authorization.AuthorizationProvider;

import java.io.UnsupportedEncodingException;

import io.auxo.arch.sample.exception.AppException;

/**
 * @author Victor Chiu
 */
public class AuthorizationManager implements AuthorizationProvider, AuthorizationExpiredCallback {

    private final String USER_AUTHORIZATION_FILE_NAME = "user_authorization";
    private final String USER_AUTHORIZATION_KEY = "authorization";

    private boolean isExpiredHandlerEnabled = true;

    private SharedPreferences mPreferences;
    private String mAuthorization;

    public AuthorizationManager(Context context) {
        mPreferences = context.getSharedPreferences(USER_AUTHORIZATION_FILE_NAME, Context.MODE_PRIVATE);
        mAuthorization = mPreferences.getString(USER_AUTHORIZATION_KEY, null);
    }

    @Override
    public void onAuthorizationExpired() {
        logout();
        if (isExpiredHandlerEnabled) {
            // navigate to login
        }
    }

    @Override
    public String getAuthorization() {
        return mAuthorization;
    }

    public void enableExpiredHandler() {
        isExpiredHandlerEnabled = true;
    }

    public void disableExpiredHandler() {
        isExpiredHandlerEnabled = false;
    }

    public void login(String username, String password) throws AppException {
        try {
            String value = username + ":" + password;
            mAuthorization = Base64.encodeToString(value.getBytes("UTF-8"), Base64.NO_WRAP);
            mPreferences.edit().putString(USER_AUTHORIZATION_KEY, mAuthorization).commit();
        } catch (UnsupportedEncodingException e) {
            throw new AppException("用户名密码格式有误");
        }
    }

    public void logout() {
        mPreferences.edit().putString(USER_AUTHORIZATION_KEY, null).commit();
        mAuthorization = null;
    }

    public boolean isLoggedIn() {
        return mAuthorization != null;
    }
}
