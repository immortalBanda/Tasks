package com.example.tasks.datapersistant.tasks;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "tasks")
public class Task {

    public Task(int _Id,@NonNull String _Title, @Nullable String _Body, @NonNull String _Author) {
        this._Author = _Author;
        this._Title = _Title;
        this._Body = _Body;
        this._Id = _Id;
    }

    public String getTitle() {
        return _Title;
    }

    public void setTitle(String title) {
        _Title = title;
    }

    public String getBody() {
        return _Body;
    }

    public void setBody(String body) {
        _Body = body;
    }

    public int getId() {
        return _Id;
    }

    public void setId(int _Id) {
        this._Id = _Id;
    }

    public String getAuthor() {
        return _Author;
    }

    public void setAuthor(@NonNull String _Author) {
        this._Author = _Author;
    }

    /*----------------------------------- Variable Declarations ----------------------------------------*/

    @NonNull
    @ColumnInfo(name = "author")
    private String _Author;

    @NonNull
    @ColumnInfo(name = "title")
    private String _Title;

    @Nullable
    @ColumnInfo(name = "body")
    private String _Body;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int _Id;


}
