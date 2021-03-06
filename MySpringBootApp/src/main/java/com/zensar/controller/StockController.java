package com.zensar.controller;

import java.awt.PageAttributes.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.zensar.dto.Stock;
import com.zensar.exception.InvalidStockIdException;
import com.zensar.service.StockService;

import io.swagger.annotations.ApiOperation;

@RestController
public class StockController {

	@Autowired
	private StockService stockService;

	private static int lastIndexId = 3;

	@PostMapping(value = "/stock", consumes = { org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			org.springframework.http.MediaType.APPLICATION_XML_VALUE }, produces = {
					org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
					org.springframework.http.MediaType.APPLICATION_XML_VALUE })
	public Stock createStock(@RequestBody Stock stock) {

//		lastIndexId++;
//		stock.setId(lastIndexId);
//		stockMap.put(lastIndexId, stock);

		return stockService.createNewStock(stock);

	}

	@PutMapping(value = "/stock/{id}", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)

	public Stock updateStockById(@PathVariable("id") int stockId, @RequestBody Stock stock) {

		return stockService.updateStock(stockId, stock);
	}

	@GetMapping(value = "/stocks", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public Collection<Stock> getAllStocks() {

		return stockService.getAllStock();
	}

	@DeleteMapping(value = "/stock/{id}", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)

	public boolean deleteStock(@PathVariable("id") int stockId) {
		
		return stockService.deleteStockById(stockId);
	}

}
