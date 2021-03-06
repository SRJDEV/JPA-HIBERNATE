package com.zensar.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zensar.dto.Stock;
import com.zensar.entity.StockEntity;
import com.zensar.repo.StockRepo;

@Service(value = "JPA_SERVICE")
public class ServiceImp implements StockService {

	@Autowired
	StockRepo stockRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public Stock createNewStock(Stock stock) {

		StockEntity stockEntity = this.modelMapper.map(stock, StockEntity.class);

		stockEntity = stockRepo.save(stockEntity);

		Stock stocks = this.modelMapper.map(stockEntity, Stock.class);

		return stocks;
	}

	private Stock getStock(StockEntity stockEntity) {

		return this.modelMapper.map(stockEntity, Stock.class);
	}

	private StockEntity getStockEntity(Stock stock) {

		StockEntity stockEntity = this.modelMapper.map(stock, StockEntity.class);

		return stockEntity;
	}

	@Override
	public boolean deleteAllStocks() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStockById(int stockId) {

		Optional<StockEntity> opStockEntity = stockRepo.findById(stockId);

		if (opStockEntity.isPresent()) {
			stockRepo.deleteById(stockId);
			
		}else {
			return false;
		}
		
		if(stockRepo.findById(stockId).isPresent()) {
			return false;
		}

		return true;
	}

	@Override
	public Stock updateStock(int stockId, Stock stock) {

		if (stockRepo.findById(stockId).isPresent()) {

			Optional<StockEntity> opStockEntity = stockRepo.findById(stockId);

			if (opStockEntity.isPresent()) { // Correct stockId
				StockEntity stockEntity = opStockEntity.get();

				stockEntity.setName(stock.getName());
				stockEntity.setMarket(stock.getMarket());
				stockEntity.setPrice(stock.getPrice());

				stockEntity = stockRepo.save(stockEntity);
				Stock stockDto = this.modelMapper.map(stockEntity, Stock.class);
				return stockDto;
			}
		}

		return null;
	}

	@Override
	public Stock getStockById(int stockId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> getAllStock() {

		List<StockEntity> list = stockRepo.findAll();

		return getStockListFromEntity(list);
	}

	private List<Stock> getStockListFromEntity(List<StockEntity> stockEntityList) {

		List<Stock> stockList = new ArrayList<Stock>();

		for (StockEntity stockEntity : stockEntityList) {

			stockList.add(getStock(stockEntity));
		}
		return stockList;
	}

}
