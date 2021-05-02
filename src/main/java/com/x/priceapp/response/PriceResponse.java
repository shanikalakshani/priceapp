package com.x.priceapp.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceResponse {
    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("carton_size")
    private Integer cartonSize;

    @JsonProperty("carton_price")
    private Double cartonPrice;

    @JsonProperty("quantity_type")
    private String quantityType;

    @JsonProperty("required_quantity")
    private Integer requiredQuantity;

    @JsonProperty("total_price")
    private Double totalPrice;
}
