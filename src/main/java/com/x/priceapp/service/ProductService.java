package com.x.priceapp.service;

import com.x.priceapp.response.ProductPriceListResponse;
import com.x.priceapp.response.PriceResponse;
import com.x.priceapp.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();

    PriceResponse getPrice(String productCode, String quantityType, Integer quantity) throws Exception;

    ProductPriceListResponse getPriceList (String productCode) throws Exception;
}
