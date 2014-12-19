package com.eduardodennis.bitcointicker.service;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.eduardodennis.bitcointicker.R;
import com.eduardodennis.bitcointicker.model.NewsObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eddie on 11/27/2014.
 */
public class NewsGenerator extends AsyncTask<Void, Void, List<NewsObject>> {


    private Activity activity;


    public NewsGenerator(Activity activity) {

        this.activity = activity;

    }


    @Override
    protected List<NewsObject> doInBackground(Void... params) {




        final ArrayList<NewsObject> news = new ArrayList<>();

        try {
            JSONObject response = getJSONfromURL("http://www.reddit.com/r/bitcoin/hot.json?limit=6");

            JSONObject data = response.getJSONObject("data");

            JSONArray hotTopics = data.getJSONArray("children");


            for (int i = 1; i < hotTopics.length(); i++) {

                JSONObject topic = hotTopics.getJSONObject(i).getJSONObject("data");

                String author = topic.getString("author");
                String imageUrl = topic.getString("thumbnail");
                String postTime = topic.getString("created_utc");
                String rScore = topic.getString("score");
                String url = topic.getString("url");
                String title = topic.getString("title");

                news.add(new NewsObject(title, url));

                Log.v("DEBUG",title);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return news;
    }

    @Override
    protected void onPostExecute(final List<NewsObject> results) {


        // Get ListView object from xml
        ListView listView = (ListView) activity.findViewById(R.id.listView);


        //Add news objects from reddit subreddit /r/bitcoin


        ArrayAdapter<NewsObject> adapter = new ArrayAdapter<NewsObject>(activity,
                R.layout.custom_listview, android.R.id.text1, results);


        // Assign adapter to ListView
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                NewsObject item = results.get(position);
                String url = item.getLink();
                Uri uri = Uri.parse(url);
                activity.startActivity(new Intent(Intent.ACTION_VIEW, uri));


            }
        });


    }


    private static JSONObject getJSONfromURL(String URL) throws JSONException {
        try {
            URLConnection uc;
            java.net.URL url = new URL(URL);
            uc = url.openConnection();
            uc.setConnectTimeout(10000);
            uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            uc.connect();

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(uc.getInputStream(),
                            Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }

            String jsonText = (sb.toString());

            return new JSONObject(jsonText.toString());
        } catch (IOException ex) {
            return null;
        }
    }
}

