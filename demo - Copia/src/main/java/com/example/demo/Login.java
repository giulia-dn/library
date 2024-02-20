package com.example.demo;

import com.example.demo.model.Member;
import com.example.demo.dao.MemberDaoConcrete;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    public static Member getStudent() {
        return member;
    }
    public static Member getStaff() {
        return member;
    }
    private MemberDaoConcrete mdc = new MemberDaoConcrete();
    private static Member member;

    public void switchToRicerca(ActionEvent event) throws IOException {
        String s = "";
        if( professioneText.getText().equals("studente"))
            s = "ricerca.fxml";
        else if(professioneText.getText().equals("staff"))
            s= "admin.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(s));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private TextField idEdit;
    @FXML
    private PasswordField passwordEdit;
    @FXML
    private Label professioneText;
    @FXML
    private Button ricercaBott;
    @FXML
    private Button adminBott;
    @FXML
    private Label messaggio;

    public void setStudente(ActionEvent actionEvent) {
        professioneText.setVisible(true);
        professioneText.setText("studente");
    }

    public void setStaff(ActionEvent actionEvent) {
        professioneText.setVisible(true);
        professioneText.setText("staff");
    }


    public void controllaLogin(ActionEvent actionEvent) {
        member = mdc.getMember(idEdit.getText());
        if (member == null) {
            messaggio.setVisible(true);
            messaggio.setText("Utente non trovato");
            ricercaBott.setVisible(false);
            adminBott.setVisible(false);
        }
        else if (member.getPassword().equals(passwordEdit.getText()) && member.getProfessione().equals(professioneText.getText()) && member.isAuthorized()) {
            if(member.getProfessione().equals("studente")){
                messaggio.setVisible(true);
                messaggio.setText("Benvenuto");
                ricercaBott.setVisible(true);
                adminBott.setVisible(false);
            } else if (member.getProfessione().equals("staff")) {
                messaggio.setVisible(true);
                messaggio.setText("Benvenuto");
                ricercaBott.setVisible(false);
                adminBott.setVisible(true);

            }
        } else if (member.getPassword().equals(passwordEdit.getText()) && member.getProfessione().equals(professioneText.getText()) && !member.isAuthorized()) {
            messaggio.setVisible(true);
            messaggio.setText("Utente non autorizzato");
            ricercaBott.setVisible(false);
            adminBott.setVisible(false);
        } else if (!member.getPassword().equals(passwordEdit.getText()) && member.getProfessione().equals(professioneText.getText()) && member.isAuthorized()) {
            messaggio.setVisible(true);
            messaggio.setText("Password errata");
            ricercaBott.setVisible(false);
            adminBott.setVisible(false);
        } else if(member.getId_member()==null) {
            messaggio.setVisible(true);
            messaggio.setText("Utente non trovato");
            ricercaBott.setVisible(false);
            adminBott.setVisible(false);
        }else {
            messaggio.setVisible(true);
            messaggio.setText("Utente non trovato");
            ricercaBott.setVisible(false);
            adminBott.setVisible(false);
        }
    }


    public void switchToAdmin(ActionEvent actionEvent)throws IOException {

    }
}
