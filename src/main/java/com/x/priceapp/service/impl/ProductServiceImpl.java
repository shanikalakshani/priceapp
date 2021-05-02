package com.x.priceapp.service.impl;

import com.x.priceapp.entity.Product;
import com.x.priceapp.exception.BadRequestException;
import com.x.priceapp.repository.ProductRepository;
import com.x.priceapp.response.ProductPriceListResponse;
import com.x.priceapp.response.PriceResponse;
import com.x.priceapp.response.ProductResponse;
import com.x.priceapp.response.PriceListResponse;
import com.x.priceapp.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return buildProductResponseList(productList);
    }

    @Override
    public PriceResponse getPrice(String productCode, String quantityType, Integer quantity) throws Exception{

        Optional<Product> product = productRepository.findByCode(productCode);
        if (!product.isPresent()) {
            logger.error("Invalid product code: %s" + productCode);
            throw new BadRequestException("Invalid product code");
        }
        Double totalPrice = 0.0;
        switch(quantityType)
        {
            case "units":
                totalPrice = calculateTotalPrice(product.get().getCartonPrice(), product.get().getCartonSize(), quantity);
                break;
            case "cartons":
                totalPrice = calculateTotalCartonPrice(quantity, product.get().getCartonPrice());
                break;
            default:
                throw new BadRequestException("Invalid quantity type");
        }
        return buildPriceResponse(product.get(), quantityType, quantity, totalPrice);
    }

    @Override
    public ProductPriceListResponse getPriceList(String productCode) throws Exception {
        Optional<Product> product = productRepository.findByCode(productCode);
        if (!product.isPresent())
            throw new BadRequestException("Invalid product code");

        return buildPriceListResponse(product.get());
    }

    private List<ProductResponse> buildProductResponseList(List<Product> productList) {
        List<ProductResponse> responseList = new ArrayList<>();
        productList.forEach(product -> responseList.add(buildProductResponse(product)));
        return responseList;
    }

    private ProductResponse buildProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).code(product.getCode()).name(product.getName())
                .cartonSize(product.getCartonSize()).cartonPrice(product.getCartonPrice()).build();
    }

    private PriceResponse buildPriceResponse(Product product, String quantityType, Integer units, Double totalPrice) {
        return PriceResponse.builder().code(product.getCode()).name(product.getName()).cartonSize(product.getCartonSize())
                .cartonPrice(product.getCartonPrice()).quantityType(quantityType).requiredQuantity(units).totalPrice(totalPrice).build();
    }

    private ProductPriceListResponse buildPriceListResponse(Product product) {

        return ProductPriceListResponse.builder().id(product.getId()).code(product.getCode()).name(product.getName())
                .priceList(buildPriceListResponses(product)).build();
    }

    private List<PriceListResponse> buildPriceListResponses(Product product) {
        List<PriceListResponse> responseList = new ArrayList<>();

        for (int i = 1 ; i <= 50 ; i++) {
            responseList.add(buildPriceListResponse(i, product));
        }
        return responseList;
    }

    private PriceListResponse buildPriceListResponse(Integer units, Product product) {
        return PriceListResponse.builder().requiredUnits(units)
                .totalPrice(calculateTotalPrice(product.getCartonPrice(), product.getCartonSize(), units)).build();
    }

    public Double calculateTotalPrice(Double cartonPrice, int cartonSize, int units) {
        int requiredCartons = units / cartonSize;
        int requiredSingleUnits = units % cartonSize;

        Double totalPrice = 0.0;
        totalPrice += calculateTotalUnitPrice(requiredSingleUnits, cartonPrice, cartonSize);

        totalPrice += calculateTotalCartonPrice(requiredCartons, cartonPrice);

        return (double) Math.round(totalPrice * 100) / 100;
    }

    public Double calculateTotalUnitPrice(int units, Double cartonPrice, int cartonSize) {
        return ((cartonPrice * 1.3) / cartonSize) * units;
    }

    public Double calculateTotalCartonPrice(int cartons, Double cartonPrice) {
        if (cartons >= 3)
            return (cartonPrice * 0.9) * cartons;

        return cartonPrice * cartons;
    }
}
