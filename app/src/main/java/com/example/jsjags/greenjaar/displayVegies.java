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
import android.widget.ArrayAdapter;
import android.widget.EditText;

import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class displayVegies extends AppCompatActivity {
    TextView textCartItemCount;
    int mCartItemCount = 0;
    int bcount=0;
    int capcount=0;
    int carcount=0;
    int pcount=0;
    int tcount=0;
    public void ItemCounter(View view){

        int id=view.getId();
        TextView tv=(TextView) findViewById(R.id.brinjalQuantity);
        TextView tv1=(TextView) findViewById(R.id.capsicumQuantity);
        TextView tv2=(TextView) findViewById(R.id.carrotQuantity);
        TextView tv3=(TextView) findViewById(R.id.potatoQuantity);
        TextView tv4=(TextView) findViewById(R.id.tomatoQuantity);
        String myId=view.getResources().getResourceEntryName(id);
        switch(myId){
            case "bPlus":
               bcount+=1;
               tv.setText(String.valueOf(bcount));
               break;
            case "bMinus":
                if(bcount>0) {
                    bcount -= 1;
                    //TextView tv=(TextView) findViewById(R.id.brinjalQuantity);
                    tv.setText(String.valueOf(bcount));
                }
                break;
            case "capPlus":
                capcount+=1;
                tv1.setText(String.valueOf(capcount));
                break;
            case "capMinus":
                if(capcount>0) {
                    capcount -= 1;
                    tv1.setText(String.valueOf(capcount));
                }
                break;
            case "carPlus":
                carcount+=1;
                tv2.setText(String.valueOf(carcount));
                break;
            case "carMinus":
                if(carcount>0) {
                    carcount -= 1;
                    tv2.setText(String.valueOf(carcount));
                }
                break;
            case "pPlus":
                pcount+=1;
                tv3.setText(String.valueOf(pcount));
                break;
            case "pMinus":
                if(pcount>0) {
                    pcount -= 1;
                    tv3.setText(String.valueOf(pcount));
                }
                break;
            case "tPlus":
                tcount+=1;
                tv4.setText(String.valueOf(tcount));
                break;
            case "tMinus":
                if(tcount>0) {
                    tcount -= 1;
                    tv4.setText(String.valueOf(tcount));
                }
                break;
            default:
                Log.i("log", "switch default");

        }

    }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.main_menu, menu);
    final MenuItem menuItem = menu.findItem(R.id.action_cart);

    //View actionView = MenuItemCompat.getActionView(menuItem);
    View actionView=menuItem.getActionView();
    textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

    setupBadge();

    actionView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onOptionsItemSelected(menuItem);
        }
    });
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
            case R.id.action_cart:
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
        int quantityVeg=0;
        String myId=view.getResources().getResourceEntryName(id);
        if(myId.equals("brinjalAdd")) {
            quantityVeg = bcount;
            vegetable="brinjal";
        }
        else if(myId.equals("capsicumAdd")) {
            quantityVeg = capcount;
            vegetable="capsicum";
        }
        else if(myId.equals("carrotAdd")) {
            quantityVeg = carcount;
            vegetable="carrot";
        }
        else if(myId.equals("potatoAdd")){
            quantityVeg = pcount;
            vegetable="potato";}
        else if(myId.equals("tomatoAdd")){
            quantityVeg = tcount;
        vegetable="tomato";}

        //quantityId=getResources().getIdentifier(quantId,"id",getPackageName());
        //EditText quantity= (EditText) findViewById(quantityId);
       // int quantityVeg=Integer.parseInt(quantity.getText().toString());
         if(quantityVeg!=0) {
             switch (vegetable) {
                 case "brinjal":
                     if (((CartValues) this.getApplication()).getBrinjalQuantity() == 0) {
                         mCartItemCount++;
                     }
                     ((CartValues) this.getApplication()).setBrinjalQuantity(quantityVeg);
                     break;
                 case "capsicum":
                     if (((CartValues) this.getApplication()).getCapsicumQuantity() == 0) {
                         mCartItemCount++;
                     }
                     ((CartValues) this.getApplication()).setCapsicumQuantity(quantityVeg);
                     break;
                 case "carrot":
                     if (((CartValues) this.getApplication()).getCarrotQuantity() == 0) {
                         mCartItemCount++;
                     }
                     ((CartValues) this.getApplication()).setCarrotQuantity(quantityVeg);
                     break;
                 case "potato":
                     if (((CartValues) this.getApplication()).getPotatoQuantity() == 0) {
                         mCartItemCount++;
                     }
                     ((CartValues) this.getApplication()).setPotatoQuantity(quantityVeg);
                     break;
                 case "tomato":
                     if (((CartValues) this.getApplication()).getTomatoQuantity() == 0) {
                         mCartItemCount++;
                     }
                     ((CartValues) this.getApplication()).setTomatoQuantity(quantityVeg);
                     break;
                 default:
                     Log.i("Switch error", "default running");
             }
             ((CartValues) this.getApplication()).setCountDifferentCartItems(mCartItemCount);
             // textCartItemCount.setText(String.valueOf(((CartValues)this.getApplication()).getCountDifferentCartItems()));
             setupBadge();
         }
    }
    public void goToCart(View view){

    }
    private void setupBadge() {
        if(mCartItemCount!=((CartValues)this.getApplication()).getCountDifferentCartItems())
            mCartItemCount=((CartValues)this.getApplication()).getCountDifferentCartItems();
        if (textCartItemCount != null) {
            if (((CartValues)this.getApplication()).getCountDifferentCartItems() == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_vegies);
        Log.i("log","back pressed");
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"Kg"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        Spinner dropdown2 = findViewById(R.id.spinner2);
        dropdown2.setAdapter(adapter);
        Spinner dropdown3 = findViewById(R.id.spinner3);
        dropdown3.setAdapter(adapter);
        Spinner dropdown4 = findViewById(R.id.spinner4);
        dropdown4.setAdapter(adapter);
        Spinner dropdown5 = findViewById(R.id.spinner5);
        dropdown5.setAdapter(adapter);
        TextView tv= (TextView)findViewById(R.id.brinjalPrice);
        tv.setText("@"+String.valueOf(((CartValues)this.getApplication()).brinjalPrice+" Rs/Kg"));
        TextView tv1= (TextView)findViewById(R.id.capsicumPrice);
        tv1.setText("@"+String.valueOf(((CartValues)this.getApplication()).capsicumPrice)+" Rs/Kg");
        TextView tv2= (TextView)findViewById(R.id.carrotPrice);
        tv2.setText("@"+String.valueOf(((CartValues)this.getApplication()).carrotPrice)+" Rs/Kg");
        TextView tv3= (TextView)findViewById(R.id.potatoPrice);
        tv3.setText("@"+String.valueOf(((CartValues)this.getApplication()).potatoPrice)+" Rs/Kg");
        TextView tv4= (TextView)findViewById(R.id.tomatoPrice);
        tv4.setText("@"+String.valueOf(((CartValues)this.getApplication()).tomatoPrice)+" Rs/Kg");

    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
