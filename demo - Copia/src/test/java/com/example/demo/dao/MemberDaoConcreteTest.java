package com.example.demo.dao;

import com.example.demo.model.Member;
import com.example.demo.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberDaoConcreteTest {

    @Test
    void updateMember(){
        MemberDaoConcrete mdc = new MemberDaoConcrete();
        Member m = Student.getInstance("id", "password", true);
        try{
            mdc.addMember(m);
            Member m1 = Student.getInstance("id", "newpassword", true);
            mdc.updateMember(m1);
            Member m2 = mdc.getMember(m.getId_member());
            assertEquals(m1.getId_member(), m2.getId_member());
            assertEquals(m1.getPassword(), m2.getPassword());
            assertEquals(m1.getProfessione(), m2.getProfessione());
            assertEquals(m1.isAuthorized(), m2.isAuthorized());
        }catch (Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        finally {
            try{
                mdc.removeMember(m);
            } catch (Exception e){
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    @Test
    void getMember() {
        MemberDaoConcrete mdc = new MemberDaoConcrete();
        Member m = Student.getInstance("id", "password", true);
        try{
            mdc.addMember(m);
            Member m1 = mdc.getMember(m.getId_member());
            assertEquals(m.getId_member(), m1.getId_member());
            assertEquals(m.getPassword(), m1.getPassword());
            assertEquals(m.getProfessione(), m1.getProfessione());
            assertEquals(m.isAuthorized(), m1.isAuthorized());
        }catch (Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        finally {
            try{
                mdc.removeMember(m);
            } catch (Exception e){
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }


}
    /*
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/libray2";
    MemberDaoConcrete mdc = new MemberDaoConcrete();

    @Test
    void addMember() {
        Member m = Student.getInstance("id", "password", true);
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
            String id = m.getId_member();
            String password = m.getPassword();
            String professione = m.getProfessione();
            String authorized;
            if(m.isAuthorized())
                authorized = "true";
            else
                authorized = "false";

            sql = "INSERT INTO member(id_member, password, professione, authorized) VALUES ('"+id+"', '"+password+"', '"+professione+"', "+authorized+");";
            st = cn.createStatement();
            st.executeUpdate(sql);
            cn.close();
        }catch (SQLException e ){
            System.out.println("errore:" + e.getMessage());
        }
        finally {
            Member m1 = mdc.getMember(m.getId_member());
            if(m1.getId_member().equals(m.getId_member()) && m1.getPassword().equals(m.getPassword()) && m1.getProfessione().equals(m.getProfessione()) && m1.isAuthorized() == m.isAuthorized())
                System.out.println("Member added");
            mdc.removeMember(m);

        }

    }

    @Test
    void removeMember() {
        Member m = Student.getInstance("id", "password", true);
        mdc.addMember(m);
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
            String id = m.getId_member();
            sql = "DELETE FROM member WHERE id_member='"+id+"';";
            st = cn.createStatement();
            st.executeUpdate(sql);
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        finally {
            Member m1 = mdc.getMember(m.getId_member());
            if(m1 == null)
                System.out.println("Member removed");
        }

    }

    @Test
    void updateMember() {
        Member m = Student.getInstance("id", "password", true);
        mdc.addMember(m);
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
            String id = m.getId_member();
            String password = "newpassword";
            String professione = m.getProfessione();
            String authorized;
            if (m.isAuthorized())
                authorized = "true";
            else
                authorized = "false";
            sql = "UPDATE member SET password='" + password + "' WHERE id_member='" + id + "';";
            st = cn.createStatement();
            st.executeUpdate(sql);
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        finally {
            Member m1 = mdc.getMember(m.getId_member());
            if(m1.getPassword().equals("newpassword"))
                System.out.println("Member updated");
            mdc.removeMember(m);
        }

    }

    @Test
    void getMember() {
        Member m = Student.getInstance("id", "password", true);
        Member m1 = null;
        mdc.addMember(m);
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
            String id = m.getId_member();
            sql = "SELECT * FROM member WHERE id_member='" + id + "';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(Objects.equals(rs.getString("professione"), "staff")) {
                    m1 = Staff.getInstance(rs.getString("id_member"), rs.getString("password"), rs.getBoolean("authorized"));
                }else if (Objects.equals(rs.getString("professione"), "studente")) {
                    m1 = Student.getInstance(rs.getString("id_member"), rs.getString("password"), rs.getBoolean("authorized"));
                }else
                    System.out.println("errore");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        finally{
            if(m1.getId_member().equals(m.getId_member()) && m1.getPassword().equals(m.getPassword()) && m1.getProfessione().equals(m.getProfessione()) && m1.isAuthorized() == m.isAuthorized())
                System.out.println("Member found");
            mdc.removeMember(m);
        }

    }
    */


