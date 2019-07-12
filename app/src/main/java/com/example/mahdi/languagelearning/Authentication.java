package com.example.mahdi.languagelearning;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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

public class Authentication extends AsyncTask<String,String,String> {
    Context context;
    Loading loading;

    public Authentication(Context context , Loading loading) {
        this.context = context;
        this.loading=loading;

    }

    @Override
    protected void onPreExecute() {

        /*dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Login Status");*/
        loading.showDialog();




    }

    @Override
    protected void onPostExecute(String s) {
        loading.hideDialog();

        //start activity-main
        if(s.contains("login successful"))
        {

            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(),MainActivity.class);
            intent_name.getExtras();
            context.startActivity(intent_name);

        }*/
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(String... arg0) {
        StringBuilder result = new StringBuilder();
        String email = arg0[0];
        String pass = arg0[1];


        String connectionlink = "http://10.0.2.2:8080/android/login.php";
        try {
            URL url = new URL(connectionlink);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setDoInput(true);
            http.setDoOutput(true);
            OutputStream outputStream = http.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
                    + "&&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();


            InputStream inputStream = http.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            bufferedReader.close();
            inputStream.close();
            http.disconnect();
            return result.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
