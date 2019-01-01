package com.example.jsjags.greenjaar;

import android.app.Application;

import org.json.JSONArray;

import java.util.Map;

public class CartValues extends Application {
    Map<String,int[]> Price;

    public void setPrice() {
        int[] a={1,2};
        Price.put("brinjal",a);
    }

    public Map<String, int[]> getPrice() {
        return Price;
    }

    int countDifferentCartItems=0;
    String address= new String();
    String userEmail=new String();
    int brinjalQuantity=0;
    int capsicumQuantity=0;
    int carrotQuantity=0;
    int potatoQuantity=0;
    int tomatoQuantity=0;
    int totalCartValue=0;
    int brinjalPrice=20;
    int capsicumPrice=60;
    int carrotPrice=15;
    int potatoPrice=25;
    int tomatoPrice=35;
    JSONArray jsonAddresses;
    JSONArray jsonArray;
    JSONArray jsonUserSubscriptionsList;

    public JSONArray getJsonAddresses() {
        return jsonAddresses;
    }

    public void setJsonAddresses(JSONArray jsonAddresses) {
        this.jsonAddresses = jsonAddresses;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public JSONArray getJsonUserSubscriptionsList() {
        return jsonUserSubscriptionsList;
    }

    public void setJsonUserSubscriptionsList(JSONArray jsonUserSubscriptionsList) {
        this.jsonUserSubscriptionsList = jsonUserSubscriptionsList;
    }

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
    public String getUserEmail(){
        return userEmail;
    }
    public void setUserEmail(String email){
        this.userEmail=email;
    }
    public void setCountDifferentCartItems(int count){
        countDifferentCartItems=count;
    }
    public int getCountDifferentCartItems(){
        countDifferentCartItems=0;
        if(brinjalQuantity>0)
            countDifferentCartItems+=1;
            if(capsicumQuantity>0)
                countDifferentCartItems+=1;
        if(carrotQuantity>0)
            countDifferentCartItems+=1;
        if(potatoQuantity>0)
            countDifferentCartItems+=1;
        if(tomatoQuantity>0)
            countDifferentCartItems+=1;
        return countDifferentCartItems;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
