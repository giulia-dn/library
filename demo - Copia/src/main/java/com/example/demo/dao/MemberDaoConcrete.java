package com.example.demo.dao;

import com.example.demo.model.Member;
import com.example.demo.model.Staff;
import com.example.demo.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberDaoConcrete implements MemberDao {
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/libray2";
    @Override
    public void addMember(Member m) {
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



    }

    @Override
    public void removeMember(Member member) {
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
            String id = member.getId_member();
            sql = "DELETE FROM member WHERE id_member='"+id+"';";
            st = cn.createStatement();
            st.executeUpdate(sql);
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }

    }

    @Override
    public void updateMember(Member member) {
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
            String id = member.getId_member();
            String password = member.getPassword();
            String professione = member.getProfessione();
            String authorized;
            if(member.isAuthorized())
                authorized = "true";
            else
                authorized = "false";
            sql = "UPDATE member SET password='"+password+"', professione='"+professione+"', authorized="+authorized+" WHERE id_member='"+id+"';";
            st = cn.createStatement();
            st.executeUpdate(sql);
            cn.close();

        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }


    }

    @Override
    public Member getMember(String id) {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        Member member = null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection(DB_URL, "root", "");
            sql = "SELECT * FROM member WHERE id_member='" + id + "';";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(Objects.equals(rs.getString("professione"), "staff")) {
                    member = Staff.getInstance(rs.getString("id_member"), rs.getString("password"), rs.getBoolean("authorized"));
                }else if (Objects.equals(rs.getString("professione"), "studente")) {
                    member = Student.getInstance(rs.getString("id_member"), rs.getString("password"), rs.getBoolean("authorized"));
                }else
                    System.out.println("errore");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        return member;
    }

    @Override
    public List<Member> getAllMembers() {
        Connection cn;
        Statement st;
        ResultSet rs;
        String sql;
        List<Member> members = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :");
            System.err.println(e.getMessage());
        }
        try {//crea connessione al database
            cn = DriverManager.getConnection(DB_URL, "root", "");
            sql = "SELECT * FROM member;";
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if (Objects.equals(rs.getString("professione"), "staff")) {
                    members.add(Staff.getInstance(rs.getString("id_member"), rs.getString("password"), rs.getBoolean("authorized")));
                } else if (Objects.equals(rs.getString("professione"), "studente")) {
                    members.add(Student.getInstance(rs.getString("id_member"), rs.getString("password"), rs.getBoolean("authorized")));
                } else
                    System.out.println("errore");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        }
        return members;
    }
}
