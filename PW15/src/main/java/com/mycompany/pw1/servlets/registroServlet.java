package com.mycompany.pw1.servlets;

import com.mycompany.pw1.classes.Conexion;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebServlet(name = "registroServlet", urlPatterns = {"/registroServlet"})
@MultipartConfig
public class registroServlet extends HttpServlet {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdpw1");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String nombreUsuario = request.getParameter("nombre_usuario");
            String pass = request.getParameter("contrasena");
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String correoElectronico = request.getParameter("correo_electronico");
            Part filePart = request.getPart("imagen_perfil");
            String fileName = getFileName(filePart);

            // Usar una ruta relativa basada en el directorio de despliegue de la aplicación
            String appPath = request.getServletContext().getRealPath("");
            String savePath = appPath + File.separator + "imagenes" + File.separator + "usuarios";
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            String filePath = savePath + File.separator + nombreUsuario + "_" + fileName;
            String relativePath = "imagenes/usuarios/" + nombreUsuario + "_" + fileName;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String textFechaNacimiento = request.getParameter("fecha_nacimiento");
            Date fechaNacimiento;
            
            try {
                fechaNacimiento = dateFormat.parse(textFechaNacimiento);
            } catch (ParseException e) {
                out.println("{\"mensaje\":\"Error al parsear la fecha de nacimiento: " + e.getMessage() + "\"}");
                return;
            }

            Date fechaCreacion = new Date();

            try {
                // Guarda la imagen en el servidor
                filePart.write(filePath);

                Connection con = Conexion.getConnection();
                String sql = "{CALL insertarUsuario(?, ?, ?, ?, ?, ?, ?)}";
                try (CallableStatement stmt = con.prepareCall(sql)) {
                    stmt.setString(1, nombre);
                    stmt.setString(2, apellidos);
                    stmt.setDate(3, new java.sql.Date(fechaNacimiento.getTime()));
                    stmt.setString(4, correoElectronico);
                    stmt.setString(5, relativePath);
                    stmt.setString(6, nombreUsuario);
                    stmt.setString(7, pass);

                    stmt.execute();
                }

                // Redirige a la página de éxito
                response.sendRedirect("index.jsp");
            } catch (ClassNotFoundException | SQLException e) {
                Logger.getLogger(registroServlet.class.getName()).log(Level.SEVERE, "Error al registrar el usuario", e);
                out.println("{\"mensaje\":\"Error al registrar el usuario: " + e.getMessage() + "\"}");
            }
        }
    }

    private String getFileName(Part filePart) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
