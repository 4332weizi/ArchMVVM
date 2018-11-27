package com.github.api.authorization;

/**
 * @author Victor Chiu
 */
public interface AuthorizationProvider {
    /**
     * get authorization value
     *
     * @return authorization
     */
    String getAuthorization();
}