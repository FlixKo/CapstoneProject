package com.example.financeoverview;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.example.financeoverview.database.StockDatabase;
import com.example.financeoverview.model.Stock;
import com.example.financeoverview.model.StockViewModel;
import com.example.financeoverview.utils.FetchStockFromNetwork;
import com.example.financeoverview.utils.JSONUtils;
import com.example.financeoverview.utils.NetworkUtils;
import com.example.financeoverview.utils.SearchStocksOnNetwork;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static StockViewModel stockViewModel;
    private static String LOG_TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        stockViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(
               getApplication()).create(StockViewModel.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchTerm = "BMW";
                if (isConnected(getApplicationContext())) {

                    //searchStocks();
                    Snackbar.make(view, "Stocks searched with search term " + searchTerm, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, R.string.no_network, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });

    }
    private void searchStocks() {
        stockViewModel.getStocks().observe(this, new Observer<List<Stock>>() {
            @Override
            public void onChanged(List<Stock> stocks) {
                //searchAdapter.setStocksList(stocks);
                //recyclerView.setAdapter(searchAdapter);
                //setTitle(R.string.popular_movies);
                //mAdapter.setMovieList(new ArrayList<>(stocks));
                //mAdapter.notifyDataSetChanged();
                //for(int i = 0; i<stocks.size(); i++){
                    //Log.d(LOG_TAG, stocks.get(i).getName());

                //}
                //FetchStockFromNetwork.getStock(getApplicationContext(),stocks.get(0),stocks.get(0).getSymbol());
                //getStock(stocks.get(0).getSymbol());

            }
        });
    }

    private void getStock(String symbol){
        stockViewModel.getStock(symbol).observe(this, new Observer<Stock>() {
            @Override
            public void onChanged(Stock stock) {
                Log.d(LOG_TAG, "stock price: " + stock.getPrice());
            }
        });
    }

    private void showNoNetworkErrorMessage() {
        //scrollView.setVisibility(View.INVISIBLE);
        //mErrorMessageDisplay.setText(getString(R.string.no_network));
        //mErrorMessageDisplay.setVisibility(View.VISIBLE);
        Log.e(LOG_TAG,"No Network");
    }

    private boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cm != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (capabilities != null) {
                    return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
                }
            }
        } else {
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    return (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) ||
                            (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE);
                }
            }
        }
        return false;
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


    private void getStocks() {
        stockViewModel.getStocks().observe(this, new Observer<List<Stock>>() {
            @Override
            public void onChanged(List<Stock> stocks) {
                //setTitle(R.string.popular_movies);
                //mAdapter.setMovieList(new ArrayList<>(movies));
                //mAdapter.notifyDataSetChanged();
            }
        });
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


                for(int i = 0; i < searchResults.size(); i++){
                    //stockDb.stockDao().insertStock(searchResults.get(i));
                    //Log.d(LOG_TAG,searchResults.get(i).getName());
                }
                //LiveData<List<Stock>> stocks = stockDb.stockDao().loadAllStocks();
                //Stock dbStock = stockDb.stockDao().loadStockBySymbol(searchResults.get(1).getSymbol());

                //Log.d(LOG_TAG,"DB Stock Name: " + dbStock.getName());

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