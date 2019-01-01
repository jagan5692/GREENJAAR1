package com.example.jsjags.greenjaar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.jsjags.greenjaar.HomePage;
import com.example.jsjags.greenjaar.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class aSyncCall extends AsyncTask<String,Void,String> {
    Context context;
    String result =new String();
    String type=new String();
    String subType=new String();
    public aSyncCall(Context ctx){
        context = ctx;
        result="";
        type="";
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (type.equals("register")) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            if (s.equals("success")) {
                Intent intent = new Intent(context.getApplicationContext(), HomePage.class);
                context.startActivity(intent);
            }
        } else if (type.equals("login")) {
            if (s != null && s.equals("Invalid Email/Password")) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            } else if (s != null) {
                Log.i("log login respone", s);
                String email = "";
                try {
                    JSONObject response = new JSONObject(s);
                    email = response.getString("EMAIL");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ((CartValues) context.getApplicationContext()).setUserEmail(email);
                Log.i("log user email", ((CartValues) context.getApplicationContext()).getUserEmail());
                Intent intent = new Intent(context.getApplicationContext(), HomePage.class);
                context.startActivity(intent);
            }

        } else if (type.equals("order")) {
            if (s != null && s.equals("success")) {
                Toast.makeText(context, "order placed successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        } else if (type.equals("sendmail")) {
            if (s.equals("Message has been sent")) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                context.startActivity(intent);
            } else
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        } else if (type.equals("basket")) {
            Log.i("log result(s)", s + "coming");
            Intent intent = new Intent(context.getApplicationContext(), basketSubscription.class);
            intent.putExtra("response", s);
            context.startActivity(intent);
        } else if (type.equals("getAddresses")) {
            if (s != null && s.equals("No added addresses")) {
                Intent intent = new Intent(context.getApplicationContext(), deliveryAddress.class);
                intent.putExtra("operation", "subscription");
                intent.putExtra("value", subType);
                context.startActivity(intent);
            } else if (s != null && s.equals("Email not Registered")) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            } else if (s != null) {
                try {
                    JSONArray jArray = new JSONArray(s);
                    ((CartValues) context.getApplicationContext()).setJsonAddresses(jArray);
                    Intent intent = new Intent(context.getApplicationContext(), selectAddress.class);
                    intent.putExtra("operation", "subscription");
                    intent.putExtra("subType", subType);
                    context.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (type.equals("buySubscription")) {
            if (s != null && s.equals("Some error occurred while subscribing")) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            } else if (s != null && s.equals("Email not registered")) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            } else if (s != null && s.equals("subscribed")) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(), mySubscriptions.class);
                intent.putExtra("status","subscribed");
                context.startActivity(intent);
            } else if (s == null) {
                Toast.makeText(context, "connection not successful", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    protected String doInBackground(String... params) {
        String urlString="";
        type=params[0];
        if(type.equals("login")){
            urlString="https://greenjaar.com/ap_gj/ap_login.php";
        }
        else if(type.equals("register")){
            urlString="https://greenjaar.com/ap_gj/ap_register.php";
        }
        else if(type.equals("sendmail")){
            urlString="https://greenjaar.com/ap_gj/PHPMailer-5.2.26/ap_mail.php";
        }
        else if(type.equals("order")){
            urlString="https://greenjaar.com/ap_gj/ap_orderDetails.php";
        }
        else if(type.equals("getOrderList")){
            urlString="https://greenjaar.com/ap_gj/ap_getUserOrderDetails.php";
        }
        else if(type.equals("basket")){
            urlString="https://greenjaar.com/ap_gj/ap_getSubDetails.php";
        }
        else if(type.equals("getAddresses")){
            urlString="https://greenjaar.com/ap_gj/ap_getUserAddresses.php";
        }
        else if(type.equals("buySubscription")){
            urlString="https://greenjaar.com/ap_gj/ap_subDetails.php";
        }

        if (type.equals("login")) {
            try {
                String uName=params[1];
                String password=params[2];
                OutputStream out=null;
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //out = new OutputStream(urlConnection.getOutputStream());

               // HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                out = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                //encryption
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(uName, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                writer.write(post_data);
                writer.flush();
                writer.close();
                out.close();
                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line);
                            result += line + "\n";
                }
                result = stringBuilder.toString();
                bufferedReader.close();
                inputStream.close();
                urlConnection.disconnect();
                Log.i("log result",result);
               // Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                return result;
            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         else if (type.equals("register")){

            try {

                String email = params[1];
                String password = params[2];
                String phone = params[3];
                String fName = params[4];
                String mName=params[5];
                String lName=params[6];

                URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                //encryption
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                        + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&"
                        + URLEncoder.encode("fName", "UTF-8") + "=" + URLEncoder.encode(fName, "UTF-8") + '&'
                + URLEncoder.encode("mName", "UTF-8") + "=" + URLEncoder.encode(mName, "UTF-8")+'&'
                + URLEncoder.encode("lName", "UTF-8") + "=" + URLEncoder.encode(lName, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();

                //Decryption
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

        else if (type.equals("sendmail")){
            try {

                String email = params[1];


                URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                //encryption
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =  URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();

                //Decryption
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();

            }
        }
        else if (type.equals("order")){
            try {
                String email=params[1];
                String address=params[2];
                String jsonString = params[3];
                String orderStatus=params[4];
                String orderTotal=params[5];

                URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                //encryption
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =  URLEncoder.encode("itemList", "UTF-8") + "=" + URLEncoder.encode(jsonString, "UTF-8") + "&"
                        +URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        +URLEncoder.encode("order_value", "UTF-8") + "=" + URLEncoder.encode(orderTotal, "UTF-8") + "&"
                        +URLEncoder.encode("order_status", "UTF-8") + "=" + URLEncoder.encode(orderStatus, "UTF-8") + "&"
                        +URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") ;

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();

                //Decryption
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();

            }
        }
        else if (type.equals("basket")){// to get available subscriptions
            try {

                String request="request";
                URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                //encryption
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =  URLEncoder.encode("request", "UTF-8") + "=" + URLEncoder.encode(request, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();

                //Decryption
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();

            }
        }
        else if (type.equals("getAddresses")){
            try {

                String email = params[1];
                subType=params[2];

                URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                //encryption
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =  URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();

                //Decryption
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();

            }
        }
        else if (type.equals("buySubscription")){

            try {

                String email = params[1];
                String subName = params[2];
                String address = params[3];
                String subStartDate = params[4];
                String subEndDate=params[5];
                //String lName=params[6];

                URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                //encryption
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("sub_name", "UTF-8") + "=" + URLEncoder.encode(subName, "UTF-8") + "&"
                        + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&"
                        + URLEncoder.encode("sub_startDate", "UTF-8") + "=" + URLEncoder.encode(subStartDate, "UTF-8") + '&'
                        + URLEncoder.encode("sub_endDate", "UTF-8") + "=" + URLEncoder.encode(subEndDate, "UTF-8");


                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();

                //Decryption
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;

    }
}
