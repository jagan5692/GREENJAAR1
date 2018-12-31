package com.example.jsjags.greenjaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class selectAddress extends AppCompatActivity {
    String subTye="";
    String operation="";
    public void placeOrder(View view){
        int id=view.getId();
        String address="";
        String email=((CartValues)this.getApplication()).getUserEmail();
        TextView tv=(TextView) findViewById(id);
        address=tv.getText().toString();
        Date d = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd");
        String formattedStartDate = df.format(d);
        Log.i("log start date",formattedStartDate);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String thisdate=formattedStartDate;
        try {
            c.setTime(sdf.parse(thisdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.i("log values time",c.getTime().toString());
        c.add(Calendar.DATE, 30);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        String formattedEndDate = df.format(c.getTime());
        Log.i("log end date ",formattedEndDate);
        String type="buySubscription";
        aSyncCall object=new aSyncCall(getApplicationContext());
        object.execute(type,email,subTye,address,formattedStartDate,formattedEndDate);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        Intent intent=getIntent();
        operation=intent.getStringExtra("operation");
        if(operation.equals("subscription")) {
            subTye = intent.getStringExtra("subType");
        }
        try{
            if(((CartValues)this.getApplication()).getJsonAddresses()!=null) {
                // Log.i("log arraylength", String.valueOf(jsonString.length()));
                for (int i = 0; i < ((CartValues) this.getApplication()).getJsonAddresses().length(); i++) {
                    JSONObject jsonObj = ((CartValues) this.getApplication()).getJsonAddresses().getJSONObject(i);
                    String num = String.valueOf(i + 1);// to get the textView
                    String id = "textView" + num;
                    Log.i("log id", id);
                    int textViewID = getResources().getIdentifier(id,
                            "id", getPackageName());
                    Log.i("log int id", String.valueOf(textViewID));
                    TextView tv = (TextView) findViewById(textViewID);
                    String address=jsonObj.getString("ADDRESS");
                    Log.i("log address inside for", address);
                    tv.setText(address);
                    tv.setVisibility(View.VISIBLE);

                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
