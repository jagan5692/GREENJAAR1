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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class basketSubscription extends AppCompatActivity {
    //String subscriptionType="";
    TextView textCartItemCount;
    int mCartItemCount = 0;

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
    }
    public void subscriptionSelect(View view){
        String subscriptionType="";
        int id=view.getId();
        String myId=view.getResources().getResourceEntryName(id);
        TextView tv=(TextView) findViewById(id);
        String text=tv.getText().toString();
        if(myId.equals("textView1")){
            subscriptionType="Basic";
        }
        else if(myId.equals("textView2")){
            subscriptionType="Medium";
        }
        else if(myId.equals("textView3")){
              subscriptionType="Basic Veg & fruits";
        }
        else if(myId.equals("textView4")){
            subscriptionType="Medium Veg and fruits";
        }
        else if(myId.equals("textView5")){
            subscriptionType="Premium Basket";
        }
        Intent intent= new Intent(getApplicationContext(),subscriptionConfirm.class);
        intent.putExtra("subType",subscriptionType);
        intent.putExtra("text",text);
        startActivity(intent);
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

    private void setupBadge() {

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
        setContentView(R.layout.activity_basket_subscription);
        Intent intent=getIntent();
        String response=intent.getStringExtra("response");
        if(response!=null) {
            Log.i("log basketSub response", response + " coming");
            try {
                JSONArray jsonString = new JSONArray(response);
                ((CartValues)this.getApplication()).setJsonArray(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try{
               // Log.i("log arraylength", String.valueOf(jsonString.length()));
                for (int i = 0; i < ((CartValues)this.getApplication()).getJsonArray().length(); i++) {
                    JSONObject jsonObj = ((CartValues)this.getApplication()).getJsonArray().getJSONObject(i);
                    String num = String.valueOf(i + 1);// to get the textView
                    String id = "textView" + num;
                    Log.i("log id", id);
                    int viewID = getResources().getIdentifier(id,
                            "id", getPackageName());
                    Log.i("log int id", String.valueOf(viewID));
                    TextView tv = (TextView) findViewById(viewID);
                    Spanned string = Html.fromHtml(
                            "<br><br><br><font color='red' size='15'><b>" + jsonObj.getString("SUB_NAME") + " @Rs." +
                                    jsonObj.getString("SUB_PRICE") + "</b></font><br>" +
                                    jsonObj.getString("SUB_DETAILS") + "."
                    );
                    Log.i("log strin html", string.toString());
                    tv.setText(string);
                }

            }
            catch (JSONException e) {
                e.printStackTrace();
            }

    }
}
