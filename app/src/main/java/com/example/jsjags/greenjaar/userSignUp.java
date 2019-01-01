package com.example.jsjags.greenjaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class userSignUp extends AppCompatActivity {
    public void registerUser(View view){
        String type="register";
        String firstName= new String();
        String middleNamme= new String();
        String lastName= new String();
        String pwd= new String();
        String mobNo= new String();
        String email= new String();
        String confPwd= new String();
        EditText fName=(EditText)findViewById(R.id.firstNameInput);
        EditText mName=(EditText)findViewById(R.id.middleNameInput);
        EditText lName=(EditText)findViewById(R.id.lastNameInput);
        EditText pass=(EditText)findViewById(R.id.pwdInput);
        EditText mobileN=(EditText)findViewById(R.id.mobileNumberInput);
        EditText emailId=(EditText)findViewById(R.id.emailInput);
        EditText confirmPass=(EditText)findViewById(R.id.confirmPwdInput);
        //EditText userName=(EditText)findViewById(R.id.fullNameInput);
        firstName=fName.getText().toString();
        middleNamme=mName.getText().toString();
        lastName=lName.getText().toString();
        mobNo=mobileN.getText().toString();
        email=emailId.getText().toString();
        pwd=pass.getText().toString();
        confPwd=confirmPass.getText().toString();
        Log.i("log",email);
        Log.i("log",pwd);
        Log.i("log",mobNo);
        Log.i("log",firstName);

        if(pwd.equals(confPwd)){
            //Push to Database
           /* Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);*/
            aSyncCall object=new aSyncCall(getApplicationContext());
            object.execute(type,email,pwd,mobNo,firstName,middleNamme,lastName);

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
