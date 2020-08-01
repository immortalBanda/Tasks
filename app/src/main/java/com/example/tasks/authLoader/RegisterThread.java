package com.example.tasks.authLoader;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterThread extends Thread{

    private String USER_NAME = null;
    private String PASSWORD = null;
    private String NAME = null;
    private String EMAIL = null;
    private int RESPONSE = -1;

    private final OkHttpClient client = new OkHttpClient();

    public RegisterThread(String USER_NAME, String PASSWORD, String NAME, String EMAIL) {
        this.USER_NAME = USER_NAME;
        this.PASSWORD = PASSWORD;
        this.NAME = NAME;
        this.EMAIL = EMAIL;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void run() {
        try {
            RESPONSE = Integer.parseInt(tryAdd());
            Log.i("Prajwal", "run: " + tryAdd());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public synchronized String tryAdd() throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("username", USER_NAME)
                .add("name",NAME)
                .add("password",PASSWORD)
                .add("email",EMAIL)
                .add("submit","signup")
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.56.1/tasks/create/index.php")
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            return response.body().string();
        }
    }

    public int getRESPONSE() {
        return RESPONSE == -1 ? 0 : RESPONSE;
    }
}
