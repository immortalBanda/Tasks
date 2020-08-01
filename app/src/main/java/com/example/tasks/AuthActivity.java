package com.example.tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tasks.authLoader.AuthThread;
import com.example.tasks.authLoader.AuthUser;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText eUsername = (EditText)findViewById(R.id.username);
                Editable tUsername = eUsername.getText();
                Editable tPassword = ((EditText)findViewById(R.id.password)).getText();
                boolean t1GuestAuth = ((CheckBox)findViewById(R.id.open_tasks_display)).isChecked();
                int tGuestAuth = t1GuestAuth ? 1 : 0;

                authUser(tUsername.toString(),tPassword.toString(),tGuestAuth);
            }
        });

        Button GuestLogin = (Button)findViewById(R.id.guest_login);
        GuestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guestAuth();
            }
        });

        Button SignUpButton = (Button)findViewById(R.id.sign_up_button);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void authUser(String username,String password,int GuestAuthCode){

        Toast.makeText(getApplicationContext(),"Connecting...",Toast.LENGTH_LONG).show();

        AuthThread aThread = new AuthThread(username,password,GuestAuthCode);
        aThread.start();
        try {
            aThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AuthUser authUser = aThread.getAuthUser();

        if(authUser != null) {
            if (authUser.getId() >= -1) {
                SharedPreferences preferences = getSharedPreferences("com.example.tasks.Auth", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("user_id", authUser.getId());
                editor.putString("username",authUser.getUsername());
                editor.putInt("guest_auth", authUser.getGuest_auth());
                editor.apply();
                Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivityIntent);
                this.finishAffinity();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Invalid Request",Toast.LENGTH_LONG ).show();
        }
    }


    private void guestAuth(){
        SharedPreferences preferences = getSharedPreferences("com.example.tasks.Auth", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("user_id", 0);
        editor.putString("username","guest");
        editor.putInt("guest_auth", 0);
        editor.apply();
        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivityIntent);
    }


}