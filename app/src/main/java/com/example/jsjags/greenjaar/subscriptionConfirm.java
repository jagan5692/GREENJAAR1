package com.example.jsjags.greenjaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class subscriptionConfirm extends AppCompatActivity {
    String subScriptionTextviewId=new String();
    String subType="";
    String text="";
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
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
            case R.id.action_cart:
                Log.i("log","cart");
                Intent cartIntent=new Intent(getApplicationContext(),addToCart.class);
                startActivity(cartIntent);
                return true;
            default:
                return false;
        }
    }*/
public void buySubscription(View view){
    int id=view.getId();
    String type="getAddresses";
    String email=((CartValues)this.getApplication()).getUserEmail();
    aSyncCall object=new aSyncCall(getApplicationContext());
    Log.i("log email",email);
    object.execute(type,email,subType);
    /*Intent intent=new Intent(getApplicationContext(),selectAddress.class);
    intent.putExtra("subType",subType);
    startActivity(intent);
*/
}
    public void changePlan(View view){
        Intent intent= new Intent(getApplicationContext(),basketSubscription.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_confirm);
        Intent intent=getIntent();
        subType=intent.getStringExtra("subType");
        text=intent.getStringExtra("text");
        Log.i("log subscription",subType);
        TextView tv=(TextView) findViewById(R.id.text1);
        tv.setText(text);

    }
}
