
package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Ebook;
import com.example.demo.model.Member;
import com.example.demo.model.Product;
import com.example.demo.dao.MemberDaoConcrete;
import com.example.demo.dao.ProductDaoConcrete;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class Admin {
    private ProductDaoConcrete pdc=new ProductDaoConcrete();
    private Product product;
    private MemberDaoConcrete mdc=new MemberDaoConcrete();
    private Member member;
    @FXML
    private TextField prodRestText;
    @FXML
    private TextField prodNondispText;
    @FXML
    private TextField utenteDisText;
    @FXML
    private TextField utenteRiabText;
    @FXML
    private TextArea tableProductText;
    @FXML
    private TextArea tableMemberText;
    @FXML
    private TextField idNPText;
    @FXML
    private TextField productNPText;
    @FXML
    private TextField titleNPText;
    @FXML
    private TextField authorNPText;

    public void prodRest(ActionEvent actionEvent) {
        product = pdc.getProduct(Integer.parseInt(prodRestText.getText()));
        product.setAvailable(true);
        product.setOwner(null);
        pdc.updateProduct(product);//Product.editProdDisp(Integer.parseInt(prodRestText.getText()), "true");
        //Member.resetOwner(Integer.parseInt(prodRestText.getText()));
        prodRestText.setText("");
        showTableProduct();

    }
    public void prodNondisp(ActionEvent actionEvent) {
        //Product.editProdDisp(Integer.parseInt(prodNondispText.getText()), "false");
        product = pdc.getProduct(Integer.parseInt(prodNondispText.getText()));
        product.setAvailable(false);
        pdc.updateProduct(product);
        prodNondispText.setText("");
        showTableProduct();
    }

    public void utenteDis(ActionEvent actionEvent) {
        //Member.editMemberDisp(utenteDisText.getText(), "false");
        member = mdc.getMember(utenteDisText.getText());
        member.setAuthorized(false);
        mdc.updateMember(member);
        utenteDisText.setText("");
        showTableMember();
    }

    public void utenteRiab(ActionEvent actionEvent) {
        //Member.editMemberDisp(utenteRiabText.getText(), "true");
        member = mdc.getMember(utenteRiabText.getText());
        member.setAuthorized(true);
        mdc.updateMember(member);
        utenteRiabText.setText("");
        showTableMember();
    }

    public void showTableProduct() {
        //tableProductText.setText(Product.tableProduct());
        //List<Product> products = new ArrayList<>();
        List<Product> products = pdc.getAllProducts();
        String table = "";
        for(Product product: products){
            table += product.showInfo();
        }
        tableProductText.setText(table);
    }
    public void showTableMember() {
        //tableMemberText.setText(Member.tableMember());
        List<Member> members = mdc.getAllMembers();
        String table = "";
        for(Member member: members){
            table += member.showInfo();
        }
        tableMemberText.setText(table);


    }


    public void setBook(ActionEvent actionEvent) {
        productNPText.setText("book");
    }

    public void setEbook(ActionEvent actionEvent) {
        productNPText.setText("ebook");
    }
    public void aggiungiNP(ActionEvent actionEvent) {
        if(!idNPText.getText().equals("") && !productNPText.getText().equals("") && !titleNPText.getText().equals("") && !authorNPText.getText().equals("")) {
            if(productNPText.getText().equals("book")){
                Product book = Book.getInstance(Integer.parseInt(idNPText.getText()), titleNPText.getText(), authorNPText.getText(), true, null);
                pdc.addProduct(book);
            }
            else if(productNPText.getText().equals("ebook")){
                Product ebook = Ebook.getInstance(Integer.parseInt(idNPText.getText()), titleNPText.getText(), authorNPText.getText(), true, null);
                pdc.addProduct(ebook);
            }
            showTableProduct();
        }
    }



}