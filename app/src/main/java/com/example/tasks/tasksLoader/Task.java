package com.example.tasks.tasksLoader;

public class Task {
    String Title;
    String Body;
    String Author;

    public Task(String title, String body, String author) {
        Title = title;
        Body = body;
        Author = author;
    }

    @Override
    public String toString() {
        return "Task{" +
                "Title='" + Title + '\'' +
                ", Body='" + Body + '\'' +
                ", Author='" + Author + '\'' +
                '}';
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
}
