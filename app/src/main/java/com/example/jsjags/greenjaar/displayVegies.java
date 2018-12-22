package com.example.jsjags.greenjaar;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.RelativeLayout;
import android.widget.TextView;

public class displayVegies extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.badge);
        MenuItemCompat.setActionView(item, R.layout.cart_layout);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);

        TextView tv = (TextView) notifCount.findViewById(R.id.actionbar_notifcation_textview);
        tv.setText("12");

        return super.onCreateOptionsMenu(menu);
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
        Log.i("log", String.valueOf(item.getItemId()));
        String test= getResources().getResourceEntryName(item.getItemId());
        Log.i("log",test);
        switch (item.getItemId()){

            case R.id.settings:
                Log.i("log","Settings");
                return true;
            case R.id.help:
                Log.i("log","Help");
                return true;
            case R.id.logout:
                Log.i("log","logout");
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.cart:
                Log.i("log","cart");
                Intent cartIntent= new Intent(getApplicationContext(),addToCart.class);
                startActivity(cartIntent);
                Log.i("log","Successful cart presses");
                return true;

            default:
                return false;
        }
    }
    public void addTocart(View view){
        int id=view.getId();
        String quantId="";
        String vegetable="";
        int quantityId;
        String myId=view.getResources().getResourceEntryName(id);
        if(myId.equals("brinjalAdd")) {
            quantId = "brinjalQuantity";
            vegetable="brinjal";
        }
        else if(myId.equals("capsicumAdd")) {
            quantId = "capsicumQuantity";
            vegetable="capsicum";
        }
        else if(myId.equals("carrotAdd")) {
            quantId = "carrotQuantity";
            vegetable="carrot";
        }
        else if(myId.equals("potatoAdd")){
            quantId="potatoQuantity";
            vegetable="potato";}
        else if(myId.equals("tomatoAdd")){
            quantId="tomatoQuantity";
        vegetable="tomato";}
        quantityId=getResources().getIdentifier(quantId,"id",getPackageName());
        EditText quantity= (EditText) findViewById(quantityId);
        int quantityVeg=Integer.parseInt(quantity.getText().toString());
     switch(vegetable){
         case "brinjal":
             ((CartValues) this.getApplication()).setBrinjalQuantity(quantityVeg);
             break;
         case "capsicum":
             ((CartValues) this.getApplication()).setCapsicumQuantity(quantityVeg);
             break;
         case "carrot":
             ((CartValues) this.getApplication()).setCarrotQuantity(quantityVeg);
             break;
         case "potato":
             ((CartValues) this.getApplication()).setPotatoQuantity(quantityVeg);
             break;
         case "tomato":
             ((CartValues) this.getApplication()).setTomatoQuantity(quantityVeg);
             break;
         default:
             Log.i("Switch error","default running");
             }
             int test=((CartValues) this.getApplication()).getBrinjalQuantity();
        int test1=((CartValues) this.getApplication()).getCapsicumQuantity();
             Log.i("brinjal",String.valueOf(test));
             Log.i("log capsicum",String.valueOf(test1));

    }
    public void goToCart(View view){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_vegies);

    }
}
