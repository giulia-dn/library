package com.example.demo.dao;

import com.example.demo.model.Book;
import com.example.demo.model.Ebook;
import com.example.demo.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDaoConcrete implements ProductDao {
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/libray2";

    @Override
    public void addProduct(Product p){
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        try{
            Class.forName(JDBC_DRIVER);
        }catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection(DB_URL, "root", "");
            int id = p.getId_product();
            String product = p.getType();
            String title = p.getTitle();
            String author = p.getAuthor();
            String available;
            if(p.isAvailable())
                available = "true";
            else
                available = "false";
            if(!Objects.equals(p.getOwner(), "null"))
                sql = "INSERT INTO product(id_product, product, title, author, available, owner) VALUES ("+id+", '"+product+"', '"+title+"', '"+author+"', "+available+", '"+p.getOwner()+"');";
            else
                sql = "INSERT INTO product(id_product, product, title, author, available) VALUES ("+id+", '"+product+"', '"+title+"', '"+author+"', "+available+");";
            st = cn.createStatement();
            st.executeUpdate(sql);
            cn.close();
        }catch (SQLException e ){
            System.out.println("errore:" + e.getMessage());
        }


    }

    @Override
    public void removeProduct(Product p) {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection(DB_URL, "root", "");
            int id = p.getId_product();
            sql = "DELETE FROM product WHERE id_product='"+id+"';";
            st = cn.createStatement();
            st.executeUpdate(sql);
            cn.close();

        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }

    }

    @Override
    public void updateProduct(Product product) {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection(DB_URL, "root", "");
            int id = product.getId_product();
            String title = product.getTitle();
            String author = product.getAuthor();
            String owner = product.getOwner();
            String available;
            if(product.isAvailable())
                available = "true";
            else
                available = "false";
            if(owner == null)
                sql = "UPDATE product SET title='"+title+"', author='"+author+"', available="+available+", owner="+owner+" WHERE id_product="+id+";";
            else
                sql = "UPDATE product SET title='"+title+"', author='"+author+"', available="+available+", owner='"+owner+"' WHERE id_product="+id+";";
            st = cn.createStatement();
            st.executeUpdate(sql);
            cn.close();

        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }

    }
    @Override
    public Product getProduct(int id) {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        Product product = null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection(DB_URL, "root", "");
            sql = "SELECT * FROM product WHERE id_product='" + id + "';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {

                if(Objects.equals(rs.getString("product"), "book")) {
                    product = Book.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner"));
                }else if (Objects.equals(rs.getString("product"), "ebook")) {
                    product = Ebook.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner"));
                }else
                    System.out.println("errore");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        StringBuilder result = new StringBuilder();
        List<Product> products = new ArrayList<Product>();
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection(DB_URL, "root", "");
            sql = "SELECT * FROM product;";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(Objects.equals(rs.getString("product"), "book"))
                    products.add(Book.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner")));
                else if (Objects.equals(rs.getString("product"), "ebook")) {
                    products.add(Ebook.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner")));
                }
                else
                    System.out.println("errore");

                //products.add(new com.example.demo.model.Product(rs.getInt("id_product"), rs.getString("product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available")));
                result.append("id_product=").append(rs.getString("id_product")).append(", product='").append(rs.getString("product")).append("', title='").append(rs.getString("title")).append("', author='").append(rs.getString("author")).append("', available='").append(rs.getString("available")).append("', owner='").append(rs.getString("owner")).append("'\n");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        return products;
    }

    @Override
    public List<Product> searchTitle(String t) {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        StringBuilder result = new StringBuilder();
        List<Product> products = new ArrayList<Product>();
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection(DB_URL, "root", "");
            sql = "SELECT * FROM product WHERE title='"+t+"';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(Objects.equals(rs.getString("product"), "book"))
                    products.add(Book.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner")));
                else if (Objects.equals(rs.getString("product"), "ebook")) {
                    products.add(Ebook.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner")));
                } else
                    System.out.println("errore");
                //products.add(new com.example.demo.model.Product(rs.getInt("id_product"), rs.getString("product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available")));
                result.append("id_product=").append(rs.getString("id_product")).append(", product='").append(rs.getString("product")).append("', title='").append(rs.getString("title")).append("', author='").append(rs.getString("author")).append("', available='").append(rs.getString("available")).append("', owner='").append(rs.getString("owner")).append("'\n");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        return products;
    }

    @Override
    public List<Product> searchAuthor(String a) {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        StringBuilder result = new StringBuilder();
        List<Product> products = new ArrayList<Product>();
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection(DB_URL, "root", "");
            sql = "SELECT * FROM product WHERE author='"+a+"';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(Objects.equals(rs.getString("product"), "book"))
                    products.add(Book.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner")));
                else if (Objects.equals(rs.getString("product"), "ebook")) {
                    products.add(Ebook.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner")));
                }else
                    System.out.println("errore");
                result.append("id_product=").append(rs.getString("id_product")).append(", product='").append(rs.getString("product")).append("', title='").append(rs.getString("title")).append("', author='").append(rs.getString("author")).append("', available='").append(rs.getString("available")).append("', owner='").append(rs.getString("owner")).append("'\n");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        return products;
    }
    @Override
    public List<Product> searchOwner(String o){
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        StringBuilder result = new StringBuilder();
        List<Product> products = new ArrayList<Product>();
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection(DB_URL, "root", "");
            sql = "SELECT * FROM product WHERE owner='"+o+"';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(Objects.equals(rs.getString("product"), "book"))
                    products.add(Book.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner")));
                else if (Objects.equals(rs.getString("product"), "ebook")) {
                    products.add(Ebook.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner")));
                }else
                    System.out.println("errore");
                result.append("id_product=").append(rs.getString("id_product")).append(", product='").append(rs.getString("product")).append("', title='").append(rs.getString("title")).append("', author='").append(rs.getString("author")).append("', available='").append(rs.getString("available")).append("', owner='").append(rs.getString("owner")).append("'\n");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        return products;

    }
}
