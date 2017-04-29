package me.kamil.simpleshop.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class CartProduct {


    @Id
    @GeneratedValue
    private long id;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmountPrice() {
        return product.getPrice() * this.getAmount();
    }

    public void setId(long id) {
        this.id = id;
    }

}
