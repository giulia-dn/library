package com.example.demo.model;

public interface Member{
    /*String id_member;
    String password;
    String professione;
    String authorized;*/

    String getId_member() ;

    String getPassword();

    String getProfessione();

    boolean isAuthorized();

    void setAuthorized(boolean authorized);
    public String showInfo();


}
