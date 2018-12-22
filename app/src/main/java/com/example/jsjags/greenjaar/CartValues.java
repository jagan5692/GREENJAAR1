package com.example.jsjags.greenjaar;

import android.app.Application;

public class CartValues extends Application {

    int brinjalQuantity=0;
    int capsicumQuantity=0;
    int carrotQuantity=0;
    int potatoQuantity=0;
    int tomatoQuantity=0;
    int totalCartValue=0;
    int brinjalPrice=5;
    int capsicumPrice=5;
    int carrotPrice=5;
    int potatoPrice=5;
    int tomatoPrice=5;
    public int getBrinjalQuantity(){
        return brinjalQuantity;
    }
    public void setBrinjalQuantity(int quantity){
        this.brinjalQuantity=quantity;
    }
    public int getCapsicumQuantity(){
        return capsicumQuantity;
    }
    public void setCapsicumQuantity(int quantity){
        this.capsicumQuantity=quantity;
    }
    public int getCarrotQuantity(){
        return carrotQuantity;
    }
    public void setCarrotQuantity(int quantity){
        this.carrotQuantity=quantity;
    }
    public int getPotatoQuantity(){
        return potatoQuantity;
    }
    public void setPotatoQuantity(int quantity){
        this.potatoQuantity=quantity;
    }
    public int getTomatoQuantity(){
        return tomatoQuantity;
    }
    public void setTomatoQuantity(int quantity){
        this.tomatoQuantity=quantity;
    }
    public int getTotalCartValue(){return totalCartValue;}
    public void setTotalCartValue(){
        this.totalCartValue=(brinjalQuantity*brinjalPrice)+(capsicumQuantity*capsicumPrice)+(carrotQuantity*carrotPrice)+(potatoQuantity*potatoPrice)+(tomatoQuantity*tomatoPrice);
    }

}
