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
    private Cart cart = new Cart();
    private  Product product ;
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
        risultatoCarrello.setText(result);
        int value = cart.getTotal();
        contatore.setText(String.valueOf(value));

    }
    @Override
    public void update() {
        infoProdotto.setVisible(true);
        infoProdotto.setText("Prodotto aggiunto correttamente");
        String result="";
        for(Product product: cart.getProducts()){
            result += product.showInfo();
        }
        prodottiAggiunti.setText(result);
        mostraCarrello();
    }




}

