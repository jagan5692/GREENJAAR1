package com.example.jsjags.greenjaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class mySubscriptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_subscriptions);
        String op=getIntent().getStringExtra("status");// to identify if to disaplay subscription or get subscription
        if(op.equals("subscribed")){
            String type="getUserSub";
            aSyncCall object=new aSyncCall(getApplicationContext());
            object.execute(type,((CartValues)this.getApplication()).getUserEmail());
        }
    }
}
