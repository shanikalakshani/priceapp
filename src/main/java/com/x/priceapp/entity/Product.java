package com.x.priceapp.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name="code", length = 50, nullable = false)
    private String code;

    @Column(name="name", length = 255, nullable = false)
    private String name;

    @Column(name = "carton_size")
    private Integer cartonSize;

    @Column(name = "carton_price")
    private Double cartonPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCartonSize() {
        return cartonSize;
    }

    public void setCartonSize(Integer cartonSize) {
        this.cartonSize = cartonSize;
    }

    public Double getCartonPrice() {
        return cartonPrice;
    }

    public void setCartonPrice(Double cartonPrice) {
        this.cartonPrice = cartonPrice;
    }
}
