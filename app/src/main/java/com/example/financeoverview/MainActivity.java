package com.example.financeoverview;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.financeoverview.model.Stock;
import com.example.financeoverview.utils.JSONUtils;
import com.example.financeoverview.utils.NetworkUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        new getSearchResultsTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class getSearchResultsTask extends AsyncTask<String, Void, ArrayList<Stock>> {
        private final String LOG_TAG = getSearchResultsTask.class.getName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected ArrayList<Stock> doInBackground(String... urls) {
            NetworkUtils.buildUrl("IBM");
            ArrayList<Stock> searchResults = null;
            try {
                String searchUrl = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildSearchUrl("IB"));
                searchResults = JSONUtils.extractStockFromSeachString(searchUrl);
                Log.d(LOG_TAG, "Search size: " + searchResults.size());
                String stockUrl = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl(searchResults.get(1).getSymbol()));
                JSONUtils.extractStockFromJSON(stockUrl, searchResults.get(1));
                Log.d(LOG_TAG, "Price of [" + searchResults.get(1).getName() + "](" + searchResults.get(1).getSymbol() + "): " + searchResults.get(1).getPrice() + " " + searchResults.get(1).getCurrency());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return searchResults;
        }

        protected void onPostExecute(ArrayList<Stock> stocks) {
            Log.d(LOG_TAG,"network task finished");

        }
    }
}