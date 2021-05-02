package com.x.priceapp.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("carton_size")
    private Integer cartonSize;

    @JsonProperty("carton_price")
    private Double cartonPrice;
}
