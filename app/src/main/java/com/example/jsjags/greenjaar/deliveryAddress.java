package com.example.jsjags.greenjaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class deliveryAddress extends AppCompatActivity {
    JSONArray jsonArray = new JSONArray();
public void catchAddress(View view) {
    String add = "";
    EditText et = (EditText) findViewById(R.id.address);
    add = et.getText().toString();
    if (!add.isEmpty()){
        ((CartValues) this.getApplication()).setAddress(add);
         String type="order";
        if (((CartValues) this.getApplication()).getBrinjalQuantity() != 0) {
            JSONObject order1 = new JSONObject();
            try {
                int price = ((CartValues) this.getApplication()).brinjalPrice;
                int quantity = ((CartValues) this.getApplication()).getBrinjalQuantity();
                order1.put("productName", "brinjal");
                order1.put("productQyt", "2");
                int fPrice=price*quantity;
                order1.put("productFinalAmt", String.valueOf(fPrice));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(order1);
        }
        if (((CartValues) this.getApplication()).getCapsicumQuantity() != 0) {
            JSONObject order2 = new JSONObject();
            try {
                int price = ((CartValues) this.getApplication()).capsicumPrice;
                int quantity = ((CartValues) this.getApplication()).getCapsicumQuantity();
                order2.put("name", "capsicum");
                order2.put("quantity", String.valueOf(quantity));
                int fPrice=price*quantity;
                order2.put("finalprice", String.valueOf(fPrice));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(order2);
        }
        if (((CartValues) this.getApplication()).getCarrotQuantity() != 0) {
            JSONObject order3 = new JSONObject();
            try {
                int price = ((CartValues) this.getApplication()).carrotPrice;
                int quantity = ((CartValues) this.getApplication()).getCarrotQuantity();
                order3.put("name", "carrot");
                order3.put("quantity", "2");
                int fPrice=price*quantity;
                order3.put("finalprice", String.valueOf(fPrice));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(order3);
        }
        if (((CartValues) this.getApplication()).getPotatoQuantity() != 0) {
            JSONObject order4 = new JSONObject();
            try {
                int price = ((CartValues) this.getApplication()).potatoPrice;
                int quantity = ((CartValues) this.getApplication()).getPotatoQuantity();
                order4.put("name", "potato");
                order4.put("quantity", String.valueOf(quantity));
                int fPrice=price*quantity;
                order4.put("finalprice", String.valueOf(fPrice));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(order4);
        }
            if (((CartValues) this.getApplication()).getTomatoQuantity() != 0) {
                JSONObject order5 = new JSONObject();
                try {
                    int price = ((CartValues) this.getApplication()).tomatoPrice;
                    int quantity = ((CartValues) this.getApplication()).getTomatoQuantity();

                    order5.put("name", "tomato");
                    order5.put("quantity", String.valueOf(quantity));
                    int fPrice=price*quantity;
                    order5.put("finalprice", String.valueOf(fPrice));
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                jsonArray.put(order5);
        }

           Log.i("log json",jsonArray.toString());



       if(jsonArray!=null) {
           String jsonString = jsonArray.toString();
           String address=((CartValues)this.getApplication()).getAddress();
           String orderStatus="New";
           String email=((CartValues)this.getApplication()).getUserEmail();
           aSyncCall object = new aSyncCall(getApplicationContext());
           String orderToal=String.valueOf(((CartValues)this.getApplication()).getTotalCartValue());
           object.execute(type,email,address,jsonString,orderStatus,orderToal);
       }

    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);
    }
}
