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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class addToCart extends AppCompatActivity {
    TextView textCartItemCount;
    int mCartItemCount = 10;
    JSONArray jsonArray = new JSONArray();



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
        String test = getResources().getResourceEntryName(item.getItemId());
        Log.i("log", test);
        switch (item.getItemId()) {

            case R.id.settings:
                Log.i("log", "Settings");
                return true;
            case R.id.help:
                Log.i("log", "Help");
                return true;
            case R.id.logout:
                Log.i("log", "logout");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_cart:
                Log.i("log", "cart");
                Intent cartIntent = new Intent(getApplicationContext(), addToCart.class);
                startActivity(cartIntent);
                Log.i("log", "Successful cart presses");
                return true;

            default:
                return false;
        }
    }

    public void removeItem(View view) {
        int cartTotal;
        int id = view.getId();
        String myId = view.getResources().getResourceEntryName(id);
        switch (myId) {
            case "removeBrinjal":
                ((CartValues) this.getApplication()).setBrinjalQuantity(0);
                mCartItemCount--;
                //if(jsonArray!=null &&)
                break;
            case "removeCapsicum":
                ((CartValues) this.getApplication()).setCapsicumQuantity(0);
                mCartItemCount--;
                break;
            case "removeCarrot":
                ((CartValues) this.getApplication()).setCarrotQuantity(0);
                mCartItemCount--;
                break;
            case "removePotato":
                ((CartValues) this.getApplication()).setPotatoQuantity(0);
                mCartItemCount--;
                break;
            case "removeTomato":
                ((CartValues) this.getApplication()).setTomatoQuantity(0);
                mCartItemCount--;
                break;
            default:
                break;

        }
        ((CartValues)this.getApplication()).setCountDifferentCartItems(mCartItemCount);
        setupBadge();
        ((CartValues) this.getApplication()).setTotalCartValue();
        cartTotal = ((CartValues) this.getApplication()).getTotalCartValue();
        TextView textView = (TextView) findViewById(R.id.textViewTotalCartValue);
        textView.setText(String.valueOf(cartTotal));
        displayItemsInCart(view);
    }

    public void displayItemsInCart(View view) {
        int cartTotal;
        int brinjalQ = ((CartValues) this.getApplication()).getBrinjalQuantity();
        int capsicumQ = ((CartValues) this.getApplication()).getCapsicumQuantity();
        int carrotQ = ((CartValues) this.getApplication()).getCarrotQuantity();
        int potatotQ = ((CartValues) this.getApplication()).getPotatoQuantity();
        int tomatoQ = ((CartValues) this.getApplication()).getTomatoQuantity();
        Log.i("log", "inside display function");
        TextView bq = (TextView) findViewById(R.id.brinjalQuantity);

        bq.setText(String.valueOf(((CartValues) this.getApplication()).brinjalQuantity)+"kg");
        TextView capq = (TextView) findViewById(R.id.capsicumQuantity);
        capq.setText(String.valueOf(((CartValues) this.getApplication()).capsicumQuantity)+"kg");
        TextView cq = (TextView) findViewById(R.id.carrotQuantity);
        cq.setText(String.valueOf(((CartValues) this.getApplication()).carrotQuantity)+"kg");
        TextView pq = (TextView) findViewById(R.id.potatoQuantity);
        pq.setText(String.valueOf(((CartValues) this.getApplication()).potatoQuantity)+"kg");
        TextView tq = (TextView) findViewById(R.id.tomatoQuantity);
        tq.setText(String.valueOf(((CartValues) this.getApplication()).tomatoQuantity)+"kg");
        TextView bp = (TextView) findViewById(R.id.brinjalPrice);
        bp.setText(String.valueOf(((CartValues) this.getApplication()).brinjalPrice)+"Rs/Kg");
        TextView capp = (TextView) findViewById(R.id.capsicumPrice);
        capp.setText(String.valueOf(((CartValues) this.getApplication()).capsicumPrice)+"Rs/Kg");
        TextView cp = (TextView) findViewById(R.id.carrotPrice);
        cp.setText(String.valueOf(((CartValues) this.getApplication()).carrotPrice)+"Rs/Kg");
        TextView pp = (TextView) findViewById(R.id.potatoPrice);
        pp.setText(String.valueOf(((CartValues) this.getApplication()).potatoPrice)+"Rs/Kg");
        TextView tp = (TextView) findViewById(R.id.tomatoPrice);
        tp.setText(String.valueOf(((CartValues) this.getApplication()).tomatoPrice)+"Rs/Kg");
        ((CartValues) this.getApplication()).setTotalCartValue();
        cartTotal = ((CartValues) this.getApplication()).getTotalCartValue();
        TextView textView = (TextView) findViewById(R.id.textViewTotalCartValue);
        textView.setText("- Rs " +String.valueOf(cartTotal));


        if (brinjalQ == 0) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout1);
            linearLayout.setVisibility(View.GONE);

        }
        if (capsicumQ == 0) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout2);
            linearLayout.setVisibility(View.GONE);
        }
        if (carrotQ == 0) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout3);
            linearLayout.setVisibility(View.GONE);
        }
        if (potatotQ == 0) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout4);
            linearLayout.setVisibility(View.GONE);
        }
        if (tomatoQ == 0) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout5);
            linearLayout.setVisibility(View.GONE);
        }
    }

    public void checkout(View view) {
        //if(((CartValues)this.getApplication()).getAddress()==null) {
            Intent intent = new Intent(getApplicationContext(), deliveryAddress.class);
            startActivity(intent);
        //}

      /* String type="order";
        if (((CartValues) this.getApplication()).getBrinjalQuantity() != 0) {
            JSONObject order1 = new JSONObject();
            try {
                int price = ((CartValues) this.getApplication()).brinjalPrice;
                int quantity = ((CartValues) this.getApplication()).getBrinjalQuantity();
                order1.put("name", "brinjal");
                order1.put("quantity", "2");
                int fPrice=price*quantity;
                order1.put("finalprice", String.valueOf(fPrice));

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
        try {
            JSONObject testJ = jsonArray.getJSONObject(0);
            String test1 = testJ.getString("name");
            Log.i("log",test1);
        }
        catch (JSONException e){
            e.printStackTrace();
            }


       if(jsonArray!=null) {
           String jsonString = jsonArray.toString();
           String address="653, Rani Sati Nagar, Rajasthan";
           String orderStatus="New";
           String email=((CartValues)this.getApplication()).getUserEmail();
           aSyncCall object = new aSyncCall(getApplicationContext());
           String orderToal=String.valueOf(((CartValues)this.getApplication()).getTotalCartValue());
           object.execute(type,email,address,jsonString,orderStatus,orderToal);
       }*/

    }
    private void setupBadge() {
        if(mCartItemCount!=((CartValues)this.getApplication()).getCountDifferentCartItems())
      mCartItemCount=((CartValues)this.getApplication()).getCountDifferentCartItems();

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
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
        setContentView(R.layout.activity_add_to_cart);
        Log.i("log", "before call");
        displayItemsInCart(findViewById(R.id.parent));
        /*if (((CartValues) this.getApplication()).getBrinjalQuantity() != 0) {
            JSONObject order1 = new JSONObject();
            try {
                int price = ((CartValues) this.getApplication()).brinjalPrice;
                order1.put("name", "brinjal");
                order1.put("quantity", "2");
                order1.put("price", String.valueOf(price));
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
                order2.put("name", "brinjal");
                order2.put("quantity", String.valueOf(quantity));
                order2.put("price", String.valueOf(price));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(order2);
        }
        if (((CartValues) this.getApplication()).getCarrotQuantity() != 0) {
            JSONObject order3 = new JSONObject();
            try {
                int price = ((CartValues) this.getApplication()).carrotPrice;
                order3.put("name", "brinjal");
                order3.put("quantity", "2");
                order3.put("price", String.valueOf(price));
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
                order4.put("price", String.valueOf(price));
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
                    order5.put("price", String.valueOf(price));
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                jsonArray.put(order5);
        }

           Log.i("log json",jsonArray.toString());
        try {
            JSONObject testJ = jsonArray.getJSONObject(0);
            String test1 = testJ.getString("name");
            Log.i("log",test1);
        }
        catch (JSONException e){
            e.printStackTrace();
            }

*/
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}

