package com.x.priceapp.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceListResponse {
    @JsonProperty("required_units")
    private Integer requiredUnits;

    @JsonProperty("total_price")
    private Double totalPrice;
}
