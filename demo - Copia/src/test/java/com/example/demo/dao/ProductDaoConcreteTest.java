package com.example.demo.dao;

import com.example.demo.model.Book;
import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

class ProductDaoConcreteTest {
    @Test
    void updateProduct() {
        ProductDaoConcrete pdc = new ProductDaoConcrete();
        Product p = Book.getInstance(0, "title", "author", true, "owner");
        try{
            pdc.addProduct(p);
            Product p1 = Book.getInstance(0, "newTitle", "newAuthor", true, "newOwner");
            pdc.updateProduct(p1);
            Product p2 = pdc.getProduct(p.getId_product());
            assert(p1.getId_product() == p2.getId_product());
            assert(p1.getType().equals(p2.getType()));
            assert(p1.getTitle().equals(p2.getTitle()));
            assert(p1.getAuthor().equals(p2.getAuthor()));
            assert(p1.isAvailable() == p2.isAvailable());
            assert(p1.getOwner().equals(p2.getOwner()));

        } catch (Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        finally {
            try{
                pdc.removeProduct(p);
            } catch (Exception e){
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    @Test
    void getProduct() {
        ProductDaoConcrete pdc = new ProductDaoConcrete();
        Product p = Book.getInstance(0, "title", "author", true, "owner");
        try{
            pdc.addProduct(p);
            Product p1 = pdc.getProduct(p.getId_product());
            assert(p1.getId_product() == p.getId_product());
            assert(p1.getType().equals(p.getType()));
            assert(p1.getTitle().equals(p.getTitle()));
            assert(p1.getAuthor().equals(p.getAuthor()));
            assert(p1.isAvailable() == p.isAvailable());
            assert(p1.getOwner().equals(p.getOwner()));

        } catch (Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        finally {
            try{
                pdc.removeProduct(p);
            } catch (Exception e){
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    @Test
    void searchTitle() {
        ProductDaoConcrete pdc = new ProductDaoConcrete();
        Product p = Book.getInstance(0, "title", "author", true, "owner");
        try{
            pdc.addProduct(p);
            List <Product> p2 = pdc.searchTitle(p.getTitle());
            Product p1 = p2.get(0);
            assert(p1.getId_product() == p.getId_product());
            assert(p1.getType().equals(p.getType()));
            assert(p1.getTitle().equals(p.getTitle()));
            assert(p1.getAuthor().equals(p.getAuthor()));
            assert(p1.isAvailable() == p.isAvailable());
            assert(p1.getOwner().equals(p.getOwner()));

        } catch (Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        finally {
            try{
                pdc.removeProduct(p);
            } catch (Exception e){
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }
    @Test
    void searchAuthor(){
        ProductDaoConcrete pdc = new ProductDaoConcrete();
        Product p = Book.getInstance(0, "title", "author", true, "owner");
        try{
            pdc.addProduct(p);
            List <Product> p2 = pdc.searchAuthor(p.getAuthor());
            Product p1 = p2.get(0);
            assert(p1.getId_product() == p.getId_product());
            assert(p1.getType().equals(p.getType()));
            assert(p1.getTitle().equals(p.getTitle()));
            assert(p1.getAuthor().equals(p.getAuthor()));
            assert(p1.isAvailable() == p.isAvailable());
            assert(p1.getOwner().equals(p.getOwner()));

        } catch (Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        finally {
            try{
                pdc.removeProduct(p);
            } catch (Exception e){
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }
    @Test
    void searchOwner(){
        ProductDaoConcrete pdc = new ProductDaoConcrete();
        Product p = Book.getInstance(0, "title", "author", true, "owner");
        try{
            pdc.addProduct(p);
            List <Product> p2 = pdc.searchOwner(p.getOwner());
            Product p1 = p2.get(0);
            assert(p1.getId_product() == p.getId_product());
            assert(p1.getType().equals(p.getType()));
            assert(p1.getTitle().equals(p.getTitle()));
            assert(p1.getAuthor().equals(p.getAuthor()));
            assert(p1.isAvailable() == p.isAvailable());
            assert(p1.getOwner().equals(p.getOwner()));

        } catch (Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        finally {
            try{
                pdc.removeProduct(p);
            } catch (Exception e){
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }
}
   /* final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/libray2";
    ProductDaoConcrete pdc = new ProductDaoConcrete();

    @Test
    void addProduct() {
        Product p = Book.getInstance(0, "title", "author", true, "owner");
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

            sql = "INSERT INTO product(id_product, product, title, author, available) VALUES ("+id+", '"+product+"', '"+title+"', '"+author+"', "+available+");";
            st = cn.createStatement();
            st.executeUpdate(sql);
            cn.close();
        }catch (SQLException e ){
            System.out.println("errore:" + e.getMessage());
        }
        finally {
            Product p1 = pdc.getProduct(p.getId_product());
            if(p1.getId_product() == p.getId_product() && p1.getType().equals(p.getType()) && p1.getTitle().equals(p.getTitle()) && p1.getAuthor().equals(p.getAuthor()) && p1.isAvailable() == p.isAvailable())
                System.out.println("Product added");
            pdc.removeProduct(p);

        }
    }

    @Test
    void removeProduct() {
        Product p = Book.getInstance(0, "title", "author", true, "owner");
        pdc.addProduct(p);
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
        finally {
            Product p1 = pdc.getProduct(p.getId_product());
            if(p1 == null)
                System.out.println("Product removed");

        }

    }

    @Test
    void updateProduct() {
        Product p = Book.getInstance(0, "title", "author", true, "owner");
        pdc.addProduct(p);
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
            String title = "newTitle";
            String author = "newAuthor";
            String owner = "newOwner";
            sql = "UPDATE product SET title='"+title+"', author='"+author+"', owner='"+owner+"' WHERE id_product="+id+";";
            st = cn.createStatement();
            st.executeUpdate(sql);
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        finally{
            Product p1 = pdc.getProduct(p.getId_product());
            if(p1.getId_product() == p.getId_product() && p1.getType().equals(p.getType()) && p1.getTitle().equals("newTitle") && p1.getAuthor().equals("newAuthor") && p1.isAvailable() == p.isAvailable() && p1.getOwner().equals("newOwner"))
                System.out.println("Product updated");
            pdc.removeProduct(p);
        }


    }

    @Test
    void getProduct() {
        Product p = Book.getInstance(0, "title", "author", true, "owner");
        Product p1=null;
        pdc.addProduct(p);
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
            sql = "SELECT * FROM product WHERE id_product='" + id + "';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(Objects.equals(rs.getString("product"), "book")) {
                    p1 = Book.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner"));
                }else if (Objects.equals(rs.getString("product"), "ebook")) {
                    p1 = Ebook.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner"));
                }else
                    System.out.println("errore");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        finally {
            if(p1.getId_product() == p.getId_product() && p1.getType().equals(p.getType()) && p1.getTitle().equals(p.getTitle()) && p1.getAuthor().equals(p.getAuthor()) && p1.isAvailable() == p.isAvailable() )
                System.out.println("Product get");
            pdc.removeProduct(p);
        }

    }


    @Test
    void searchTitle() {
        Product p = Book.getInstance(0, "title", "author", true, "owner");
        Product p1=null;
        pdc.addProduct(p);
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
            String title = p.getTitle();
            sql = "SELECT * FROM product WHERE title='" + title + "';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(Objects.equals(rs.getString("product"), "book")) {
                    p1 = Book.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner"));
                }else if (Objects.equals(rs.getString("product"), "ebook")) {
                    p1 = Ebook.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner"));
                }else
                    System.out.println("errore");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        finally {
            if(p1.getId_product() == p.getId_product() && p1.getType().equals(p.getType()) && p1.getTitle().equals(p.getTitle()) && p1.getAuthor().equals(p.getAuthor()) && p1.isAvailable() == p.isAvailable())
                System.out.println("Product get");
            pdc.removeProduct(p);
        }

    }

    @Test
    void searchAuthor() {
        Product p = Book.getInstance(0, "title", "author", true, "owner");
        Product p1=null;
        pdc.addProduct(p);
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
            String author = p.getAuthor();
            sql = "SELECT * FROM product WHERE author='" + author + "';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(Objects.equals(rs.getString("product"), "book")) {
                    p1 = Book.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner"));
                }else if (Objects.equals(rs.getString("product"), "ebook")) {
                    p1 = Ebook.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), rs.getString("owner"));
                }else
                    System.out.println("errore");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        finally {
            if(p1.getId_product() == p.getId_product() && p1.getType().equals(p.getType()) && p1.getTitle().equals(p.getTitle()) && p1.getAuthor().equals(p.getAuthor()) && p1.isAvailable() == p.isAvailable())
                System.out.println("Product get");
            pdc.removeProduct(p);
        }
    }
    */
