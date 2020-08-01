package com.example.tasks.tasksLoader;

import android.app.Application;

import com.example.tasks.MainActivity;
import com.example.tasks.datapersistant.tasks.Task;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskLoaderThread extends Thread {
    @Override
    public synchronized void run() {
        ConnectionsTask connectionsTask = new ConnectionsTask(UserId,Username);

        try {
            arrayList = connectionsTask.getTasklist();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<com.example.tasks.datapersistant.tasks.Task> getArrayList() {
        return arrayList;
    }

    private List<com.example.tasks.datapersistant.tasks.Task> arrayList = new ArrayList<>();

    private String Username = null;

    private int UserId = -1;

    private Application App;

    public TaskLoaderThread(String username, int userId) {
        Username = username;
        UserId = userId;
    }
}
