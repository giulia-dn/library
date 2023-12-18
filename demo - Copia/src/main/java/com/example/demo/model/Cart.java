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
    ////////////////////////////////


  //  private final List<Product> products = new ArrayList<>();
  //  private static int total;
  //  private final String product;
  //  private String available;
    private final List<Observer> observers = new ArrayList<>();

// METODI CARDINE DI CART_______________________________________________________________________________________________
  /*  private Cart() {
        total = 0;
        this.product = "cart";
        this.available = "true";
    }
    public static Cart getInstance(){//factor method.
        Cart cart;
        return cart = new Cart();
    }
    public void add(Product product){
        products.add(product);
        //total += 1;
        editProduct(product);
        editOwner(product);
        notif();
    }
    public String allProduct(){
        StringBuilder result= new StringBuilder();
        for(Product product: products){
            //result = result + "id_product=" + rs.getString("id_product") + ", product='" + rs.getString("product") + "', title='" + rs.getString("title") + "', author='" + rs.getString("author") + "', available=" + rs.getString("available") + "\n";
            result.append("id_product=").append(product.getId_product()).append(", product='").append(product.getProduct()).append("', title='").append(product.getTitle()).append("', author='").append(product.getAuthor()).append("'\n");
        }
        return result.toString();
    }
    public int getTotal() {
        return total;
    }*/
// SUBJECT______________________________________________________________________________________________________________
    @Override
    public void subscribe(Observer o) {
            observers.add(o);
    }
    public void notif() {
        for(Observer o: observers){
            o.update();
        }
    }

// DATABASE_____________________________________________________________________________________________________________
  /*  public void editProduct(Product p) {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            int id = p.getId_product();
            sql = "UPDATE product SET available='false' WHERE id_product='"+id+"';";
            st = cn.createStatement();
            st.executeUpdate(sql);
            cn.close();

        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
    }
    public void editOwner(Product p) {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            int id = p.getId_product();
            String owner = Login.getStudent().getId_member();
            sql = "UPDATE product SET owner ='"+owner+"' WHERE id_product='"+id+"';";
            st = cn.createStatement();
            st.executeUpdate(sql);
            cn.close();

        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
    }
    public String showCart() {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        StringBuilder result = new StringBuilder();
        Member meber = Login.getStudent();
        total =0;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            sql = "SELECT id_product, product, title, author FROM product WHERE owner='" + meber.getId_member() + "';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                result.append("id_product=").append(rs.getString("id_product")).append(", product='").append(rs.getString("product")).append("', title='").append(rs.getString("title")).append("', author='").append(rs.getString("author")).append("\n");
                total +=1;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }

        return result.toString();
    }
    public String searchTitle(String title) {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        StringBuilder result = new StringBuilder();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            sql = "SELECT * FROM product WHERE title='" + title + "';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                result.append("id_product=").append(rs.getString("id_product")).append(", product='").append(rs.getString("product")).append("', title='").append(rs.getString("title")).append("', author='").append(rs.getString("author")).append("', available=").append(rs.getString("available")).append("\n");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        return result.toString();
    }
    public String searchAuthor(String author) {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        StringBuilder result = new StringBuilder();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            sql = "SELECT * FROM product WHERE author='" + author + "';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                result.append("id_product=").append(rs.getString("id_product")).append(", product='").append(rs.getString("product")).append("', title='").append(rs.getString("title")).append("', author='").append(rs.getString("author")).append("', available=").append(rs.getString("available")).append("\n");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        return result.toString();
    }
    public String searchType_product(int id) {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        String p = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            sql = "SELECT * FROM product WHERE id_product='" + id + "'&& available = '"+ true +"';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                if(rs.getString("product").equals("book")) {
                    p="book";
                    //product = Book.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"));
                    //result = result + "id_product=" + rs.getString("id_product") + ", product='" + rs.getString("product") + "', title='" + rs.getString("title") + "', author='" + rs.getString("author") + "', available=" + rs.getString("available") + "\n";
                }
                else if(rs.getString("product").equals("ebook")){
                    p="ebook";
                    //product = Ebook.getInstance(rs.getInt("id_product"), rs.getString("title"), rs.getString("author"));
                }

            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        return p;
    }

// inutili______________________________________________________________________________________________________________
    @Override
    public int getId_product() {//finta implementazione
        return 0;
    }
    @Override
    public String getProduct() { //mai usato
        return product;
    }
    @Override
    public String getAvailable() {//mai usato
        return available;
    }
    @Override
    public void setAvailable(String value) {//mai usato
        available = value;
    }
    @Override
    public String getTitle() {//mai usato ma con possibile utilizzo
        String result="";
        for(Product product: products){
            result+=product.getTitle()+"\n";
        }
        return result;
    }
    @Override
    public String getAuthor() {//mai usato ma con possibile utilizzo
        String result="";
        for(Product product: products){
            result+=product.getAuthor()+"\n";
        }
        return result;
    }
    @Override
    public Product searchProduct(int id_product) {//mai usato ma possibile utilizzo
        return this;
    }


    /*
    public String getAllId_product() {//inutile
        String result="";
        for(Product product: products){
            result+=product.getId_product()+"\n";
        }
        return result;
    }
    public String getProduct() { //finta implementazione
        String result=product +":\n";
        for(Product product: products){
            result+=product.getTitle()+"\n";
        }
        return result;
    }
    @Override
    public String getTitle() {//finta implementazione
        String result="";
        for(Product product: products){
            result+=product.getTitle()+"\n";
        }
        return result;
    }

    @Override
    public String getAuthor() {//finta implementazione
        String result="";
        for(Product product: products){
            result+=product.getAuthor()+"\n";
        }
        return result;
    }

     */





}

