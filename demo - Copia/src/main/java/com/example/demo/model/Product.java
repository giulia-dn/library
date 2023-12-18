package com.example.demo.model;

public interface Product {

    public int getId_product();

    public String getType();

    public String getTitle();

    public void setTitle(String title);

    public String getAuthor();

    public void setAuthor(String author);

    public boolean isAvailable();
    public void setAvailable(boolean available);
    public String getOwner();
    public void setOwner(String owner);

    public String showInfo();
}
