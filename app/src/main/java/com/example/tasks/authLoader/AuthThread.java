package com.example.tasks.authLoader;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;

import java.io.IOException;

public class AuthThread extends Thread {

    private AuthUser authUser;

    private String Username;

    private String Password;

    private int Guest_Auth;

    public AuthThread(String username, String password, int guest_Auth) {
        Username = username;
        Password = password;
        Guest_Auth = guest_Auth;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public synchronized void run() {

        ConnectionsAuth connectionsAuth = new ConnectionsAuth(Username,Password,Guest_Auth);

        try {
            authUser = connectionsAuth.getAuth();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AuthUser getAuthUser() {
        return authUser;
    }
}
