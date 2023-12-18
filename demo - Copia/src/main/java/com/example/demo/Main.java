package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    public static void main(String[] args) {
        String r = searchAuthor("Edgar Allan Poe");
        System.out.println("___________________________________________________________");
        System.out.println(r);
        launch(args);
    }

    public static String searchAuthor(String author) {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        String result = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/libray2", "root", "");
            sql = "SELECT id_product, title, author, product, available FROM product WHERE author='" + author + "';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("id_product=" + rs.getString("id_product") + ", product='" + rs.getString("product") + "', title='" + rs.getString("title") + "', author='" + rs.getString("author") + "', available=" + rs.getString("available"));
                result = result + "id_product=" + rs.getString("id_product") + ", product='" + rs.getString("product") + "', title='" + rs.getString("title") + "', author='" + rs.getString("author") + "', available=" + rs.getString("available") + "\n";
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        return result;
    }

 */
}