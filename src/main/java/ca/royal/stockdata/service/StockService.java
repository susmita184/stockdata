package ca.royal.stockdata.service;

import ca.royal.stockdata.model.Stock;
import ca.royal.stockdata.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    /**
     * To get stock list based on ticker
     * @param ticker
     * @return
     */
    public List<Stock> getStocks(String ticker) {
        log.info("==getStocks " + ticker);
        List<Stock> stocks = new ArrayList<>();
        stockRepository.findAll().forEach(stocks::add);
        return stocks.stream().filter(s -> s.getStock().equals(ticker)).collect(Collectors.toList());
    }

    /**
     * Getting all stocks
     * @param stocks
     * @return
     */
    public List<Stock> importStocks(List<Stock> stocks) {
        List<Stock> importedStocks = new ArrayList<>();
        stockRepository.saveAll(stocks).forEach(importedStocks::add);
        return importedStocks;
    }

    /**
     * Adding a new record(stock)
     * @param stock
     * @return
     */
    public Stock addStock(Stock stock) {
        log.info("==addStock");
        return stockRepository.save(stock);
    }

    /**
     * Import stocks asynchronously
     * @param stocks
     * @return
     */
    public List<Stock> importStocksAsync(List<Stock> stocks) {
        log.info("getting stocks");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<Stock>> futures = stocks.stream().map(stock -> executorService.submit(() -> stockRepository.save(stock))).collect(Collectors.toList());
        List importedStocks = futures.stream().filter(Future::isDone).collect(Collectors.toList());
        executorService.shutdown();
        return importedStocks;
    }


}
