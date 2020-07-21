package com.example.financeoverview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financeoverview.model.Stock;
import com.example.financeoverview.model.StockViewModel;
import com.example.financeoverview.utils.SearchStocksOnNetwork;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment implements SearchResultsAdapter.SearchResultsAdapterOnClickHandler{

    private RecyclerView recyclerView;
    private SearchResultsAdapter searchAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        recyclerView = container.findViewById(R.id.search_results_recycler_view);
        ArrayList<Stock> emptyList = new ArrayList<>();
        searchAdapter = new SearchResultsAdapter(emptyList, this, getContext());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
  //      recyclerView.setLayoutManager(linearLayoutManager);
        String searchTerm = "BMW";
        SearchStocksOnNetwork.searchStocks(getContext(),searchTerm);
        searchStocks();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    private void searchStocks() {
        MainActivity.stockViewModel.getStocks().observe(getViewLifecycleOwner(), new Observer<List<Stock>>() {
            @Override
            public void onChanged(List<Stock> stocks) {
                searchAdapter.setStocksList(stocks);
                recyclerView.setAdapter(searchAdapter);
            }
        });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onClick(Stock stock) {

    }
}