package com.example.tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tasks.authLoader.RegisterThread;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        Button registerButton = (Button)findViewById(R.id.register_form_submit);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = ((Editable)((EditText)findViewById(R.id.register_username)).getText()).toString();
                String Name = ((Editable)((EditText)findViewById(R.id.register_name)).getText()).toString();
                String Email = ((Editable)((EditText)findViewById(R.id.register_email)).getText()).toString();
                String Password = ((Editable)((EditText)findViewById(R.id.register_password)).getText()).toString();
                int Response = -1;
                RegisterThread rThread = new RegisterThread(Username,Password,Name,Email);
                rThread.start();
                try {
                    rThread.join();
                    Response = rThread.getRESPONSE();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(Response == 1){
                    Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),AuthActivity.class);
                    startActivity(intent);
                }
                else if(Response == 0){
                    Toast.makeText(getApplicationContext(),"Connection Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}