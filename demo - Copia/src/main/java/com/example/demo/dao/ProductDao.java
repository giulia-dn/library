package com.example.demo.dao;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductDao {
    public void addProduct(Product product);
    public void removeProduct(Product product);
    public void updateProduct(Product product);
    public Product getProduct(int id);
    public List<Product> getAllProducts();
    public List<Product> searchTitle(String title);
    public List<Product> searchAuthor(String author);
    public List<Product> searchOwner(String owner);

}
