package com.mytoys.product.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @CsvBindByName(column = "ID")
    private long id;
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


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
        Product product = (Product) o;
        return Objects.equals(this.id, product.id) && Objects.equals(this.name, product.name)
                && Objects.equals(this.brand, product.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.brand);
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + this.id + ", name='" + this.name + '\'' + ", brand='" + this.brand + '\'' + '}';
    }
}
