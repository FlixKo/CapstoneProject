package com.example.financeoverview.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.financeoverview.model.Stock;

import java.util.List;

@Dao
public interface StockDao {

    @Query("SELECT * FROM stock ORDER BY symbol")
    LiveData<List<Stock>> loadAllStocks();

    @Insert
    void insertStock(Stock stock);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateStock(Stock stock);

    @Delete
    void deleteStock(Stock stock);

    @Query("SELECT * FROM stock WHERE symbol = :symbol")
    LiveData<Stock> loadLiveStockBySymbol(String symbol);

    @Query("SELECT * FROM stock WHERE symbol = :symbol")
    Stock loadStockBySymbol(String symbol);
}
