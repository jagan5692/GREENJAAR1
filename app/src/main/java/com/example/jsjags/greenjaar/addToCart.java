package com.example.jsjags.greenjaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class addToCart extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

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
    public void removeItem(View view){
        int cartTotal;
        int id= view.getId();
        String myId=view.getResources().getResourceEntryName(id);
        switch(myId){
            case "removeBrinjal":
                ((CartValues) this.getApplication()).setBrinjalQuantity(0);
                break;
            case "removeCapsicum":
                ((CartValues) this.getApplication()).setCapsicumQuantity(0);
                break;
            case "removeCarrot":
                ((CartValues) this.getApplication()).setCarrotQuantity(0);
                break;
            case "removePotato":
                ((CartValues) this.getApplication()).setPotatoQuantity(0);
                break;
            case "removeTomato":
                ((CartValues) this.getApplication()).setTomatoQuantity(0);
                break;
            default:
                break;
        }
        ((CartValues)this.getApplication()).setTotalCartValue();
        cartTotal=((CartValues)this.getApplication()).getTotalCartValue();
        TextView textView=(TextView) findViewById(R.id.textViewTotalCartValue);
        textView.setText(String.valueOf(cartTotal));
        displayItemsInCart(view);
    }
    public void displayItemsInCart(View view){
        int cartTotal;
        int brinjalQ=((CartValues) this.getApplication()).getBrinjalQuantity();
        int capsicumQ=((CartValues) this.getApplication()).getCapsicumQuantity();
        int carrotQ=((CartValues) this.getApplication()).getCarrotQuantity();
        int potatotQ=((CartValues) this.getApplication()).getPotatoQuantity();
        int tomatoQ=((CartValues) this.getApplication()).getTomatoQuantity();
        Log.i("log", "inside display function");
        TextView bq=(TextView) findViewById(R.id.brinjalQuantity);

        bq.setText(String.valueOf(((CartValues)this.getApplication()).brinjalQuantity));
        TextView capq=(TextView) findViewById(R.id.capsicumQuantity);
        capq.setText(String.valueOf(((CartValues)this.getApplication()).capsicumQuantity));
        TextView cq=(TextView) findViewById(R.id.carrotQuantity);
        cq.setText(String.valueOf(((CartValues)this.getApplication()).carrotQuantity));
        TextView pq=(TextView) findViewById(R.id.potatoQuantity);
        pq.setText(String.valueOf(((CartValues)this.getApplication()).potatoQuantity));
        TextView tq=(TextView) findViewById(R.id.tomatoQuantity);
        tq.setText(String.valueOf(((CartValues)this.getApplication()).tomatoQuantity));
        TextView bp=(TextView) findViewById(R.id.brinjalPrice);
        bp.setText(String.valueOf(((CartValues)this.getApplication()).brinjalPrice));
        TextView capp=(TextView) findViewById(R.id.capsicumPrice);
        capp.setText(String.valueOf(((CartValues)this.getApplication()).capsicumPrice));
        TextView cp=(TextView) findViewById(R.id.carrotPrice);
        cp.setText(String.valueOf(((CartValues)this.getApplication()).carrotPrice));
        TextView pp=(TextView) findViewById(R.id.potatoPrice);
        pp.setText(String.valueOf(((CartValues)this.getApplication()).potatoPrice));
        TextView tp=(TextView) findViewById(R.id.tomatoPrice);
        tp.setText(String.valueOf(((CartValues)this.getApplication()).tomatoPrice));
        ((CartValues)this.getApplication()).setTotalCartValue();
       cartTotal=((CartValues)this.getApplication()).getTotalCartValue();
        TextView textView=(TextView) findViewById(R.id.textViewTotalCartValue);
        textView.setText(String.valueOf(cartTotal));


        if(brinjalQ==0){
            LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linearLayout1);
            linearLayout.setVisibility(View.GONE);

        }
        if(capsicumQ==0){
            LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linearLayout2);
            linearLayout.setVisibility(View.GONE);
        }
        if(carrotQ==0){
            LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linearLayout3);
            linearLayout.setVisibility(View.GONE);
        }
        if(potatotQ==0){
            LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linearLayout4);
            linearLayout.setVisibility(View.GONE);
        }
        if(tomatoQ==0){
            LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linearLayout5);
            linearLayout.setVisibility(View.GONE);
        }
    }
    public void checkout(View view){
        Intent intent= new Intent(getApplicationContext(), transactionPage.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        Log.i("log","before call");
        displayItemsInCart(findViewById(R.id.parent));

    }
}
