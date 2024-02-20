package com.example.demo.controller;

import com.example.demo.Login;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.dao.ProductDaoConcrete;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class Ricerca implements Observer {
    @FXML
    private TextField testoTitolo;
    @FXML
    private TextField testoAutore;
    @FXML
    private TextArea risultatiRicerca;
    @FXML
    private TextField contatore;
    @FXML
    private TextField id;
    @FXML
    private Label infoProdotto;
    @FXML
    private TextArea prodottiAggiunti;
    @FXML
    private Label id_member;
    @FXML
    private TextArea risultatoCarrello;
    private Cart cart = new Cart();//private com.example.demo.model.Cart cart = com.example.demo.model.Cart.getInstance();
    //private com.example.demo.model.Product product=com.example.demo.model.Book.getInstance(0,"", "");
    private  Product product ;//private Product book = Book.getInstance(0,"", "");
    //private Product ebook = Ebook.getInstance(0,"", "");
    private ProductDaoConcrete pdc = new ProductDaoConcrete();
    private List<Product> products = new ArrayList<>();
    public void ricercaTitolo(ActionEvent actionEvent) {
        testoAutore.setVisible(false);
        testoAutore.setText("");
        testoTitolo.setVisible(true);
    }
    public void ricercaAutore(ActionEvent actionEvent) {
        testoTitolo.setVisible(false);
        testoTitolo.setText("");
        testoAutore.setVisible(true);
    }
    public void ricerca(ActionEvent actionEvent) {
        String ricerca = "";
        if(testoTitolo.isVisible()) {
            products = pdc.searchTitle(testoTitolo.getText());
        }
        else {
            products = pdc.searchAuthor(testoAutore.getText());
        }
        for(Product product: products){
            ricerca += product.showInfo();
        }
        risultatiRicerca.setText(ricerca);
    }

    public void addCart(){
        cart.subscribe(this);
        int value = Integer.parseInt(id.getText());
        product = pdc.getProduct(value);
        if(product.isAvailable()) {
            product.setOwner(Login.getStudent().getId_member());
            product.setAvailable(false);
            pdc.updateProduct(product);
            cart.add(product);
        }else {
            infoProdotto.setVisible(true);
            infoProdotto.setText("Prodotto non disponibile");
        }
    }
    public void mostraCarrello() {
        id_member.setVisible(true);
        id_member.setText(Login.getStudent().getId_member());
        risultatoCarrello.setVisible(true);
        String result="";
        List<Product> products1 = pdc.searchOwner(Login.getStudent().getId_member());
        for(Product product: products1){
            result += product.showInfo();
        }
        risultatoCarrello.setText(result);//risultatoCarrello.setText(cart.showCart());
        int value = cart.getTotal();
        contatore.setText(String.valueOf(value));

    }
    @Override
    public void update() {
        //cart = (com.example.demo.model.Cart) o;
        infoProdotto.setVisible(true);
        infoProdotto.setText("Prodotto aggiunto correttamente");
        String result="";
        for(Product product: cart.getProducts()){
            result += product.showInfo();
        }
        prodottiAggiunti.setText(result);//prodottiAggiunti.setText(cart.allProduct());
        mostraCarrello();
    }




}

/*public class Ricerca implements Observer {
    @FXML
    private TextField testoTitolo;
    @FXML
    private TextField testoAutore;
    @FXML
    private TextArea risultatiRicerca;
    @FXML
    private TextField contatore;
    @FXML
    private TextField id;
    @FXML
    private Label infoProdotto;
    @FXML
    private TextArea prodottiAggiunti;
    @FXML
    private Label id_member;
    @FXML
    private TextArea risultatoCarrello;
    private Cart cart = Cart.getInstance();
    //private Product product=Book.getInstance(0,"", "");
    private Product book = Book.getInstance(0,"", "");
    private Product ebook = Ebook.getInstance(0,"", "");
    public void ricercaTitolo(ActionEvent actionEvent) {
        testoAutore.setVisible(false);
        testoAutore.setText("");
        testoTitolo.setVisible(true);
    }
    public void ricercaAutore(ActionEvent actionEvent) {
        testoTitolo.setVisible(false);
        testoTitolo.setText("");
        testoAutore.setVisible(true);
    }
    public void ricerca(ActionEvent actionEvent) {
        String ricerca = "";
        if(testoTitolo.isVisible()) {
            ricerca = cart.searchTitle(testoTitolo.getText());
        }
        else {
            ricerca = cart.searchAuthor(testoAutore.getText());
        }
        risultatiRicerca.setText(ricerca);
    }

    public void addCart(){
        cart.subscribe(this);
        int value = Integer.parseInt(id.getText());
        //product = searchId_product(value);
        String p = cart.searchType_product(value);
        if(p.equals("book")){
            book=book.searchProduct(value);
            cart.add(book);
        }else if(p.equals("ebook")){
            ebook=ebook.searchProduct(value);
            cart.add(ebook);
        }else {
            infoProdotto.setVisible(true);
            infoProdotto.setText("Prodotto non disponibile");
        }
    }
    public void mostraCarrello() {
        id_member.setVisible(true);
        id_member.setText(Login.getStudent().getId_member());
        risultatoCarrello.setVisible(true);
        risultatoCarrello.setText(cart.showCart());
        int value = cart.getTotal();
        contatore.setText(String.valueOf(value));

    }
    @Override
    public void update() {
        //cart = (Cart) o;
        infoProdotto.setVisible(true);
        infoProdotto.setText("Prodotto aggiunto correttamente");
        prodottiAggiunti.setText(cart.allProduct());
        mostraCarrello();
    }




}
*/