package com.fr.clisson.berthelot.olivier.berthelottoolbox;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by olivier on 20/05/2017.
 */

public class JSONAsyncTaskPost extends AsyncTask<String, Void, String> {

    private String url;
    private Expense e;
    public JSONAsyncTaskPost(String url, Expense e)
    {
        this.url = url;
        this.e = e;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... urls) {
        String totalOutput = "";
        try {
            totalOutput += this.url + ";";
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            String s = new Gson().toJson(e);
            totalOutput += s +";";
            writer.write(s);

            writer.flush();
            writer.close();
            totalOutput += "apresconnect;";
            totalOutput += conn.getResponseCode()+";";

            conn.disconnect();

        } catch (MalformedURLException e) {
            totalOutput += "malformedURL : " + e.toString() + ";";
            e.printStackTrace();

        } catch (IOException e) {
            totalOutput += "IOConnection";
            e.printStackTrace();

        } catch (Exception e) {
            return e.toString();
        }
        if(totalOutput.length() < 3)
        {
            return totalOutput+"zbreh";
        }

        return totalOutput + "sbreh";
    }

    protected void onPostExecute(Boolean result) {

    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
