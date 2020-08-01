package com.example.tasks.datapersistant.tasks;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDao _TaskDao;
    private LiveData<List<Task>> _AllTasks;

    public TaskRepository(Application application){
        TaskDatabase db = TaskDatabase.getDatabase(application);
        _TaskDao = db.taskDao();
        _AllTasks = _TaskDao.getAllTasks();
    }

    LiveData<List<Task>> getAllTasks(){
        return _AllTasks;
    }

    void insert(Task task){
        TaskDatabase.databaseWriteExecutors.execute(()->{
            Log.i("TAG", "insert: Call From Repo");
            _TaskDao.insert(task);
        });
    }

}
