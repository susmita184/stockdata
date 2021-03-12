package ca.royal.stockdata;

import ca.royal.stockdata.model.Stock;
import ca.royal.stockdata.repository.StockRepository;
import ca.royal.stockdata.service.StockService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@SpringBootTest
class StockdataApplicationTests {

    @Autowired
    private StockService stockService;

    @MockBean
    private StockRepository stockRepository;


    @Test
    public void testGetStocksByTicker(){
        String ticker = "AA";
        when(stockRepository.findAll()).thenReturn(Stream.of(new Stock(0, 1,"AA","1/7/2011","$15.82","$16.72","$15.78","$16.42",239655616,"3.79267","",0,"$16.71","$15.97","-4.42849",26,"0.182704")
                , new Stock(1, 1,"BAC","2/11/2011","$14.82","$14.72","$14.78","$35.42",239695616,"3.59267","",0,"$16.71","$15.97","-4.42849",25,"0.189704"),
                new Stock(2, 1,"AA","3/4/2011","$16.82","$14.72","$14.78","$35.42",239688616,"3.79897","",0,"$16.71","$15.97","-4.42849",28,"0.188704")).collect(Collectors.toList()));
        Assert.assertEquals(2, stockService.getStocks(ticker).size());
    }

    @Test
    public void testAddStock(){
        Stock stock = new Stock(0, 1,"AA","1/7/2011","$15.82","$16.72","$15.78","$16.42",239655616,"3.79267","",0,"$16.71","$15.97","-4.42849",26,"0.182704");
        when(stockRepository.save(stock)).thenReturn(stock);
        Assert.assertEquals(stock, stockService.addStock(stock));
    }


}
