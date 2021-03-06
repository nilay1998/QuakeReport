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
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;
import android.app.LoaderManager.LoaderCallbacks;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<String> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    itemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        //ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        // Create a new {@link ArrayAdapter} of earthquakes
        adapter = new itemsAdapter(this, new ArrayList<Earthquake>());
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null,this);
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        // TODO: Create a new loader for the given URL
        return new EarthquakeLoader(EarthquakeActivity.this, QueryUtils.USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        // TODO: Update the UI with the result
        adapter.clear();
        ArrayList<Earthquake> abc = QueryUtils.extractFeatureFromJson(s);
        adapter.clear();
        adapter.addAll(abc);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        // TODO: Loader reset, so we can clear out our existing data.
        adapter.clear();
    }
}
