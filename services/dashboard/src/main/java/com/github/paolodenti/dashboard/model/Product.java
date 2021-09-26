package com.github.paolodenti.dashboard.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Product {

    private long id;

    private String sku;

    private String description;

    private BigDecimal unitPrice;
}
