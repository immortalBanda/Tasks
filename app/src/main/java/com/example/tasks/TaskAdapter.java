package com.example.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasks.datapersistant.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView TitleView;
        private final TextView DescriptionView;
        private final TextView AuthorView;

        private TaskViewHolder(View itemView){
            super(itemView);
            TitleView = itemView.findViewById(R.id.title);
            DescriptionView = itemView.findViewById(R.id.body);
            AuthorView = itemView.findViewById(R.id.author);
        }
    }

    private final LayoutInflater _Inflator;

    private List<Task> _Tasks;

    public TaskAdapter(Context context){
        _Inflator = LayoutInflater.from(context);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView = _Inflator.inflate(R.layout.list_item,parent,false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position){
        if(_Tasks != null){
            Task current = _Tasks.get(position);
            holder.TitleView.setText(current.getTitle());
            holder.DescriptionView.setText(current.getBody());
            holder.AuthorView.setText(current.getAuthor());
        }
        else {
            holder.TitleView.setText("None");
            holder.DescriptionView.setText("None");
            holder.AuthorView.setText("None");
        }
    }

    public void setTasks(List<Task> tasks){
        _Tasks = tasks;
        Log.i("DATASET CHANGE", "setTasks: Notifying Dataset Changed");
        notifyDataSetChanged();
    }

    public int getItemCount(){
        if (_Tasks != null){
            Log.i("TAG", "getItemCount: " + _Tasks.size());
            return _Tasks.size();
        }
        else return 0;
    }
}
