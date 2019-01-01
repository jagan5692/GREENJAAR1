package com.example.jsjags.greenjaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class displayMyOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_my_orders);
        String type="getOrderList";
       String uEmail=((CartValues)this.getApplication()).getUserEmail();
        aSyncCall obj=new aSyncCall(getApplicationContext());
        obj.execute(type,uEmail);
    }
}
