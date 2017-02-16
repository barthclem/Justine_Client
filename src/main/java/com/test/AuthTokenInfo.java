package com.test;

/**
 * Created by aanu.oyeyemi on 1/6/17.
 * Project name -> GamePlayer
 */
public class AuthTokenInfo {

    private String access_token;
    private String refresh_token;
    private String scopes;
    private String token_type;
    private int expires_in;

    public AuthTokenInfo() {
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "AuthTokenInfo{" +
                "access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", scopes='" + scopes + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
