package com.example.tasks.datapersistant.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.tasks.R;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private List<Task> _Tasks;

    public TaskAdapter(@NonNull Context context, int resource, @NonNull List<Task> tasks) {
        super(context, 0, tasks);
        _Tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }

        Task task = getItem(position);

        assert task != null;
        ((TextView)listItem.findViewById(R.id.title)).setText(task.getTitle());
        ((TextView)listItem.findViewById(R.id.body)).setText(task.getBody());
        ((TextView)listItem.findViewById(R.id.author)).setText(task.getAuthor());

        return listItem;
    }

    public void addAll(List<Task> items) {
        super.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void add(@NonNull Task object) {
        super.add(object);
        notifyDataSetChanged();
    }


}
