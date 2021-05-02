package com.x.priceapp.controller;

import com.x.priceapp.response.PriceResponse;
import com.x.priceapp.response.ProductPriceListResponse;
import com.x.priceapp.response.ProductResponse;
import com.x.priceapp.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private static final Logger logger = Logger.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @GetMapping("")
    @ResponseBody
    public List<ProductResponse> getAllProducts() {
        logger.debug("Get all products request received");

        return productService.getAllProducts();
    }

    @GetMapping("/{product_code}/{quantity_type}/{quantity}/price")
    @ResponseBody
    public PriceResponse getPrice(@PathVariable("product_code") String productCode, @PathVariable("quantity_type") String quantityType,
                                  @PathVariable("quantity") Integer quantity) throws Exception{
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Get product price request received: %s, %s, %s", productCode, quantityType, quantity));
        }
        return productService.getPrice(productCode, quantityType, quantity);
    }

    @GetMapping("/{product_code}/price-list")
    @ResponseBody
    public ProductPriceListResponse getPriceList(@PathVariable("product_code") String productCode) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Get price list request received: %s", productCode));
        }
        return productService.getPriceList(productCode);
    }
}
