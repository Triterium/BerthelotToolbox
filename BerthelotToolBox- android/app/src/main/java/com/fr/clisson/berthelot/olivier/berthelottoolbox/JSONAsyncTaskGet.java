package com.fr.clisson.berthelot.olivier.berthelottoolbox;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by olivier on 20/05/2017.
 */

class JSONAsyncTaskGet extends AsyncTask<String, Void, String> {

    private String url;
    public JSONAsyncTaskGet(String url)
    {
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... urls) {
        String totalOutput = "";
        try {

            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                totalOutput += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (Exception e) {
            return e.toString();
        }
        if(totalOutput.length() < 3)
        {
            return totalOutput;
        }
        return totalOutput.substring(totalOutput.indexOf("{"), totalOutput.lastIndexOf("}") + 1).replace('\\', ' ').replace("Name ", "Name").replace("BudgetInitial ", "BudgetInitial").replace("TotalEnCours ", "TotalEnCours");
    }

    protected void onPostExecute(Boolean result) {

    }
}