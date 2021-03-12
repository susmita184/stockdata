package ca.royal.stockdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Stock", description = "Stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stockId;
    private int quarter;
    private String stock;
    @JsonProperty("date")
    private String stockDate;
    @JsonProperty("open")
    private String openingPrice;
    @JsonProperty("high")
    private String highestPrice;
    @JsonProperty("low")
    private String lowestPrice;
    @JsonProperty("close")
    private String closingPrice;
    @JsonProperty("volume")
    private int volume;
    @JsonProperty("percent_change_price")
    private String changePricePercent;
    @JsonProperty("percent_change_volume_over_last_wk")
    private String changeVolumePercent;
    @JsonProperty("previous_weeks_volume")
    private int previousWeekVolume;
    @JsonProperty("next_weeks_open")
    private String nxtWeekOpeningPrice;
    @JsonProperty("next_weeks_close")
    private String nxtWeekClosingPrice;
    @JsonProperty("percent_change_next_weeks_price")
    private String nxtWeekChangePricePercent;
    @JsonProperty("days_to_next_dividend")
    private int daysToNxtDividend;
    @JsonProperty("percent_return_next_dividend")
    private String nxtWeekDividendReturnPercent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock1 = (Stock) o;
        return stockId == stock1.stockId &&
                stock.equals(stock1.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockId, stock);
    }
}
