package com.example.demo.model;

public class Ebook implements Product {
    final private int id_product;
    final private String type;
    private String title;
    private String author;
    private boolean available;
    private String owner;


    private Ebook(int id_product, String title, String author, boolean available, String owner) {
        this.id_product = id_product;
        this.type = "ebook";
        this.title = title;
        this.author = author;
        this.available = available;
        this.owner = owner;
    }
    public static Ebook getInstance(int id_ebook, String title, String author, boolean available, String owner){
        Ebook book;
        if (owner == null){
            return book = new Ebook(id_ebook, title, author, available, "null");
        }
        return book = new Ebook(id_ebook, title, author, available, owner);
    }


    @Override
    public int getId_product() {
        return id_product;
    }


    @Override
    public String getType() {
        return type;
    }


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String showInfo() {
        return "Ebook{" +
                "id_product=" + id_product +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                ", owner='" + owner + '\'' +
                '}'+'\n';
    }
}
