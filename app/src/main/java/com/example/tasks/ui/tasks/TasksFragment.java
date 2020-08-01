package com.example.tasks.ui.tasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasks.Content;
import com.example.tasks.MainActivity;
import com.example.tasks.R;
import com.example.tasks.datapersistant.tasks.Task;
import com.example.tasks.datapersistant.tasks.TaskAdapter;
import com.example.tasks.datapersistant.tasks.TaskViewModel;
import com.example.tasks.tasksLoader.TaskLoaderThread;
import com.example.tasks.ui.ItemsFragment;

import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment {

    private TaskViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
               new ViewModelProvider(this).get(TaskViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);

        List<com.example.tasks.datapersistant.tasks.Task> contents = new ArrayList<Task>();

        SharedPreferences preferences = getActivity().getSharedPreferences("com.example.tasks.Auth", Context.MODE_PRIVATE);

        preferences.getInt("guest_auth" , 0);

        TaskLoaderThread tThread = new TaskLoaderThread(preferences.getString("username","guest"),preferences.getInt("user_id",0));

        tThread.start();
        try {
            tThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        contents = tThread.getArrayList();

        for(int i = 0; i < contents.size();i++){
            homeViewModel.insert(contents.get(i));
        }

        ListView listView = (ListView)root.findViewById(R.id.task_list);
        TaskAdapter taskAdapter = new TaskAdapter(getContext(),0,contents);
        listView.setAdapter(taskAdapter);
        homeViewModel.getAllTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> tasks) {
                taskAdapter.addAll(tasks);
            }
        });

       return root;
    }

    private void changeFragment(ItemsFragment fragment){
        getParentFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragment).addToBackStack(null).commit();
    }


}