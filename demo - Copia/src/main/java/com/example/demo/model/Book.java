package com.example.demo.model;

public class Book implements Product {
    private final int id_product;
    private final String type;
    private String title;
    private String author;
    private boolean available;
    private String owner;

    private Book(int id_book, String title, String author, boolean available, String owner) {
        this.id_product = id_book;
        this.type="book";
        this.title = title;
        this.author = author;
        this.available = available;
        this.owner = owner;
    }
    public static Book getInstance(int id_book, String title, String author, boolean available, String owner){
        Book book;
        return book = new Book(id_book, title, author, available, owner);
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
        return "Book{" +
                "id_product=" + id_product +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                ", owner='" + owner + '\'' +
                '}'+'\n';
    }
}
