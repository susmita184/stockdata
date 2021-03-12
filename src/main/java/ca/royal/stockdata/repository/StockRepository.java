package ca.royal.stockdata.repository;

import ca.royal.stockdata.model.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {
}
