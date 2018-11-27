package com.github.api.authorization;

/**
 * @author Victor Chiu
 */
public interface AuthorizationExpiredCallback {
    /**
     * authorization expired
     */
    void onAuthorizationExpired();
}