package com.example.tasks.datapersistant.tasks;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    public TaskViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        _Repository = new TaskRepository(application);
        _AllTasks = _Repository.getAllTasks();
    }

    Application application;

    public LiveData<List<Task>> getAllTasks() {
        return _AllTasks;
    }

    public void insert(Task task){
        Log.i("TAG", "insert: Call From VM");
        _Repository.insert(task);
    }

    /*------------------ Variables -------------------*/

    private TaskRepository _Repository;
    private LiveData<List<Task>> _AllTasks;
}
