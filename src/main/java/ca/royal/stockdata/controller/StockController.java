package ca.royal.stockdata.controller;

import ca.royal.stockdata.model.Stock;
import ca.royal.stockdata.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stockData")
@Slf4j
@Api(value = "Stocks")
public class StockController {

    @Autowired
    private StockService stockService;


    @ApiOperation(value = "Stock",
            nickname = "GetStock",
            notes = "Get list of stocks based on ticker")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 500, message = "Failed transaction due to server/database error")})
    @GetMapping("/stocks/{ticker}")
    public ResponseEntity<List<Stock>> getStocks(@PathVariable String ticker){
        log.info("==getStocks");
        List<Stock> stocks = stockService.getStocks(ticker);
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }
    @ApiOperation(value = "Stock",
            nickname = "AddStock",
            notes = "Add a new Stock")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 500, message = "Failed transaction due to server/database error")})
    @PostMapping("/stocks")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock){
        log.info("==addStock");
        stockService.addStock(stock);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Stocks",
            nickname = "AddStocks",
            notes = "Import bulk data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 500, message = "Failed transaction due to server/database error")})
    @PostMapping
    public ResponseEntity importStocksAsync(@RequestBody List<Stock> stocks){
        log.info("upload stocks "+stocks.size());
        stockService.importStocksAsync(stocks);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
