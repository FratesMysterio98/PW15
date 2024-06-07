package com.mycompany.pw1.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexion {

    private static EntityManagerFactory emf = null;
    private static Connection con = null;

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "Kidzania.90";
    private static final String url = "jdbc:mysql://localhost:3306/blogdb?serverTimezone=UTC";


    public void conector() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                System.out.println("Conexión exitosa a la base de datos");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            throw e;
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (con == null || con.isClosed()) {
            new Conexion().conector();
        }
        return con;
    }

    public static EntityManagerFactory createEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("bdpw1");
        }
        return emf;
    }

    public static EntityManager getEntityManager() {
        return createEntityManagerFactory().createEntityManager();
    }
}
