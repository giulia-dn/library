package com.example.demo.model;

public class Staff implements Member {
    private final String id_member;
    private String password;
    private final String professione;
    private boolean authorized;

    private Staff(String id_member, String password, boolean authorized){
        this.id_member=id_member;
        this.password=password;
        this.professione="staff";
        this.authorized=authorized;
    }
    public static Staff getInstance(String id_member, String password, boolean authorized){
        Staff staff;
        return staff= new Staff(id_member, password, authorized);
    }

    @Override
    public String getId_member() {
        return id_member;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getProfessione() {
        return professione;
    }

    @Override
    public boolean isAuthorized() {
        return authorized;
    }

    @Override
    public void setAuthorized(boolean value) {
        authorized=value;
    }

    @Override
    public String showInfo() {
        return "Staff{" +
                "id_member='" + id_member + '\'' +
                ", password='" + password + '\'' +
                ", authorized=" + authorized +
                '}'+'\n';
    }
}

