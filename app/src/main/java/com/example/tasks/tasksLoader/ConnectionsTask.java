package com.example.tasks.tasksLoader;

import android.app.Application;
import android.util.Log;

import com.example.tasks.MainActivity;
import com.example.tasks.datapersistant.tasks.Task;
import com.example.tasks.datapersistant.tasks.TaskRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ConnectionsTask {
    /**
     * Change this address to actual address (IP Address of your host machine) or the domain name
     */
    private String DOMAIN_ADDRESS = "http://192.168.56.1";

    private String JSON_RESPONSE_QUERY = DOMAIN_ADDRESS + "/tasks?format=json&id=";

    private int USER_ID = 0;

    private String USER_NAME = null;

    public ConnectionsTask(int id,String username) {

        USER_ID = id;

        USER_NAME = username;

    }

    public List<com.example.tasks.datapersistant.tasks.Task> getTasklist() throws IOException, JSONException {
        List<Task> RetList = new ArrayList<>();

        String query_url = this.JSON_RESPONSE_QUERY + this.USER_ID + "&u_name=" + this.USER_NAME;

        URL url = new URL(query_url);

        HttpURLConnection connection = null;

        String json = null;

        InputStream inputStream = null;

        connection = (HttpURLConnection)url.openConnection();

        connection.setConnectTimeout(10000);

        connection.setReadTimeout(15000);

        connection.setRequestMethod("GET");

        connection.connect();

        if(connection.getResponseCode() == 200){
            inputStream = connection.getInputStream();
            String jsonString = getInputStream(inputStream);
            if(jsonString != null) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(jsonString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray tasklist = null;
                try {
                    assert jsonObject != null;
                    tasklist = jsonObject.getJSONArray("tasklist");
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                assert tasklist != null;
                for (int i = 0; i < tasklist.length(); i++) {
                    JSONObject arrayObject = null;
                    try {
                        arrayObject = tasklist.getJSONObject(i);
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }

                    assert arrayObject != null;
                    Log.i("TASK", "getTasklist: Task : " + arrayObject.getInt("id") + "\n" + arrayObject.getString("title") + "\n" + arrayObject.getString("description") + "\n" + arrayObject.getString("author") );
                    RetList.add(new com.example.tasks.datapersistant.tasks.Task(arrayObject.getInt("id"), arrayObject.getString("title"),arrayObject.getString("description"),arrayObject.getString("author")));
                }
                return RetList;
            }
        }

        return null;


    }

    private String getInputStream(InputStream stream) throws IOException {
        if (stream == null){
            return null;
        }
        else{
            StringBuilder retval = new StringBuilder();
            InputStreamReader inputStreamReader = new InputStreamReader(stream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            if (line != null){
                retval.append(reader.readLine());
                return line;
            }
        }
        return null;
    }

}
