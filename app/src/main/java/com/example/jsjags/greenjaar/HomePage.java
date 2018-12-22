package com.example.jsjags.greenjaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.TextView;

public class HomePage extends AppCompatActivity {
    String uName;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void captureClick(View view){
        int id= view.getId();
        String myId=view.getResources().getResourceEntryName(id);
        Log.i("image",myId);
        if(myId.equals("vegetables")){
            Log.i("myId","Success");
            Intent intent= new Intent(getApplicationContext(),displayVegies.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // super.onOptionsItemSelected(item);
         super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.settings:
                Log.i("item type","Settings");
                return true;
            case R.id.help:
                Log.i("Item","Help");
                return true;
            case R.id.logout:
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.cart:
                Log.i("log","cart");
                Intent cartIntent=new Intent(getApplicationContext(),addToCart.class);
                return true;
                default:
                    return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Intent intent=getIntent();
        uName=intent.getStringExtra("userName");
        //TextView textView= (TextView)findViewById(R.id.textViewUserName);
        //textView.setText("Welcome " + uName);

    }
}
