package com.example.tasks.authLoader;

import org.jetbrains.annotations.NotNull;

public class AuthUser {
    private String username;
    private int id;
    private int guest_auth;

    public AuthUser(String username, int id, int guest_auth) {
        this.username = username;
        this.id = id;
        this.guest_auth = guest_auth;
    }

    @NotNull
    @Override
    public String toString() {
        return "Auth{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", guest_auth=" + guest_auth +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public int getGuest_auth() {
        return guest_auth;
    }
}
