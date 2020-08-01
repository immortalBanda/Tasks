package com.example.tasks.authLoader;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ConnectionsAuth {
    private String DOMAIN_ADDRESS = "http://192.168.56.1";

    private String JSON_REQUEST_QUERY = DOMAIN_ADDRESS + "/tasks/auth/index.php?format=json";

    private int GUEST_AUTH = 0;

    private String USER_NAME = null;

    private String PASSWORD = null;

    public ConnectionsAuth(String username, String password, int guestAuth) {

        USER_NAME = username;
        PASSWORD = password;
        GUEST_AUTH = guestAuth;


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public AuthUser getAuth() throws Exception {
        AuthUser authenticatedUser = null;
        String jsonString = run();

        if (jsonString != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            authenticatedUser = new AuthUser(jsonObject.getString("username"), Integer.parseInt(jsonObject.getString("userid")), jsonObject.getInt("guest_auth"));
        }

        return authenticatedUser;


    }

    private final OkHttpClient client = new OkHttpClient();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String run() throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("username", USER_NAME)
                .add("password",PASSWORD)
                .add("guest_auth",Integer.toString(GUEST_AUTH))
                .add("submit","login")
                .build();
        Request request = new Request.Builder()
                .url(JSON_REQUEST_QUERY)
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            return response.body().string();
        }
    }


}


