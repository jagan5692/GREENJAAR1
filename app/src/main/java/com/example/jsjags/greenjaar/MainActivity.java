package com.example.jsjags.greenjaar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import android.os.AsyncTask;
import android.support.annotation.MainThread;


public class MainActivity extends AppCompatActivity {
    String Password="Journey";
    public void userSignIn(View view){
        String uName=new String();
        String passW=new String();
        EditText username= (EditText) findViewById(R.id.userNameEditText);
        EditText pwd= (EditText)findViewById(R.id.passwordEditText);
        uName=username.getText().toString();
        passW= pwd.getText().toString();
        if(passW.equals(Password)){
            Intent intent=new Intent(getApplicationContext(),HomePage.class);
            intent.putExtra("userName",uName);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Incorrect Password! try again", Toast.LENGTH_SHORT).show();
        }
    }
    public void userSignUp(View view){
        Intent intent=new Intent(getApplicationContext(),userSignUp.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

    }
}
