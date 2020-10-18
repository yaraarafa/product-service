package com.mytoys.product.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @NotNull
    @CsvBindByName(column = "ID")
    private long id;
    @NotNull
    @CsvBindByName(column = "NAME")
    private String name;
    @CsvBindByName(column = "PRICE")
    private double price;
    @CsvBindByName(column = "OLD_PRICE")
    private double oldPrice;
    @CsvBindByName(column = "STOCK")
    private int stock;
    @CsvBindByName(column = "BRAND")
    private String brand;
}
