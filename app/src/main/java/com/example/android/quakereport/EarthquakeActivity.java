/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    itemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        background ob=new background();
        ob.execute(QueryUtils.USGS_REQUEST_URL);

        // Create a fake list of earthquake locations.
        //ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        adapter = new itemsAdapter(this,new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }

    private class background extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            URL url= QueryUtils.createUrl(strings[0]);
            String jsonResponse="";
            try {
                    jsonResponse=QueryUtils.makeHttpRequest(url);
                }
                catch (IOException e)
                {
                    Log.e(LOG_TAG, "Problem making the HTTP request.", e);
                }
                return jsonResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            ArrayList<Earthquake> abc=QueryUtils.extractFeatureFromJson(s);
            adapter.clear();
            adapter.addAll(abc);
        }
    }
}
