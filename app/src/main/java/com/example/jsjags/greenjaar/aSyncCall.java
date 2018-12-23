package com.example.jsjags.greenjaar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.jsjags.greenjaar.HomePage;
import com.example.jsjags.greenjaar.MainActivity;

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
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        if(s.equals("success")&& !type.equals("sendmail")){
            if(type.equals("register")){
                Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show();
            }
            Intent intent= new Intent(context.getApplicationContext(),HomePage.class);
            context.startActivity(intent);
        }
        else if(s.equals("success") && type.equals("sendmail")){
            Intent intent= new Intent(context.getApplicationContext(),MainActivity.class);
            context.startActivity(intent);
        }
        else{
            Toast.makeText(context,s+"user id not registered error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString="";
        type=params[0];
        if(type.equals("login")){
            urlString="https://greenjaar.com/Login.php";
        }
        else if(type.equals("register")){
            urlString="https://greenjaar.com/register.php";
        }
        else if(type.equals("sendmail")){
            urlString="https://greenjaar.com/forgotPass.php";
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
                String name = params[4];

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
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");

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
        return null;

    }
}
