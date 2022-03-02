package com.zensar.service;

import java.util.List;



import com.zensar.dto.Stock;


public interface StockService {
public Stock createNewStock(Stock stock);
public boolean deleteAllStocks();
public boolean deleteStockById(int stockId);
public Stock updateStock(int stockId, Stock stock);
public Stock getStockById(int stockId);
public List<Stock> getAllStock();



}
