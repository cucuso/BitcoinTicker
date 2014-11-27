package com.eduardodennis.bitcointicker.service;

/**
 * Get chart data for Bitcoin in order to render graph.
 *
 * Created by Eddie on 11/26/2014.
 */

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import org.json.JSONTokener;

        import com.eduardodennis.bitcointicker.R;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.widget.TextView;

public class Graph extends AsyncTask<String,Void, String> {



    double price = 0.0;



    private Activity activity;

    public Graph(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {

        Double x = 0.0;
        Double y = 0.0;
        try {
            URL url = new URL("https://blockchain.info/charts/market-price?format=json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    (InputStream) conn.getContent(), "UTF-8"));
            String json = reader.readLine();
            JSONObject jsonResponse = new JSONObject(new String(json));
            x = jsonResponse.getJSONObject("values").getDouble("x");
            y = jsonResponse.getDouble("y");

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return String.valueOf(x);
    }



    @Override
    protected void onPostExecute(String results) {

        System.out.println(results);

    }



}
