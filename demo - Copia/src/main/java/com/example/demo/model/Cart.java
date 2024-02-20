package com.example.demo.model;

import com.example.demo.controller.Observer;
import com.example.demo.dao.ProductDaoConcrete;

import java.util.ArrayList;
import java.util.List;

public class Cart implements Subject {
    private List<Product> products = new ArrayList<>();
    private int total=0;

    private ProductDaoConcrete productDaoConcrete = new ProductDaoConcrete();


    public void add(Product product){
        products.add(product);
        total += 1;
        product.setAvailable(false);
        productDaoConcrete.updateProduct(product);
        notif();
    }
    public void remove(Product product){
        products.remove(product);
        total -= 1;
        product.setAvailable(true);
        productDaoConcrete.updateProduct(product);
        notif();
    }
    public int getTotal() {
        return total;
    }
    public List<Product> getProducts() {
        return products;
    }
   
    private final List<Observer> observers = new ArrayList<>();


    @Override
    public void subscribe(Observer o) {
            observers.add(o);
    }
    public void notif() {
        for(Observer o: observers){
            o.update();
        }
    }

}

