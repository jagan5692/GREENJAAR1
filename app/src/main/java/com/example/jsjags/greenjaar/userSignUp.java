package com.example.jsjags.greenjaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class userSignUp extends AppCompatActivity {
    public void registerUser(View view){
        String uName= new String();
        String pwd= new String();
        String mobNo= new String();
        String email= new String();
        String confPwd= new String();
        EditText userName=(EditText)findViewById(R.id.fullNameInput);
        EditText pass=(EditText)findViewById(R.id.pwdInput);
        EditText mobileN=(EditText)findViewById(R.id.mobileNumberInput);
        EditText emailId=(EditText)findViewById(R.id.emailInput);
        EditText confirmPass=(EditText)findViewById(R.id.confirmPwdInput);
        //EditText userName=(EditText)findViewById(R.id.fullNameInput);
        uName=userName.getText().toString();
        mobNo=mobileN.getText().toString();
        email=emailId.getText().toString();
        pwd=pass.getText().toString();
        confPwd=confirmPass.getText().toString();
        if(pwd.equals(confPwd)){
            //Push to Database
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
        }
        else{
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
    }
}
