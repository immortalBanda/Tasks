package com.example.tasks.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tasks.MainActivity;
import com.example.tasks.R;


public class ItemsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String Title;
    private String Body;
    private String author;

    public ItemsFragment() {
        // Required empty public constructor
    }

    public ItemsFragment(String title, String body, String authors){
        Title = title;
        Body = body;
        author = authors;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_items_list, container, false);

        TextView title = (TextView)root.findViewById(R.id.title);
        title.setText(this.Title);
        TextView body = (TextView)root.findViewById(R.id.body);
        body.setText(this.Body);
        TextView author = (TextView)root.findViewById(R.id.author_name);
        author.setText(this.author);
        return root;
    }

    public void insertAuthor(String Name,View root){
        Log.i("FRAGMENT", "insertAuthor: " + root.findViewById(R.id.author_name));
    }
}