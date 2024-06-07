package com.mycompany.pw1.servlets;

import com.mycompany.pw1.models.Usuarios;
import com.mycompany.pw1.persistencia.UsuariosJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdpw1");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String nombreUsuario = request.getParameter("fusuario");
        String pass = request.getParameter("fcontra");

        if (nombreUsuario != null && !nombreUsuario.isEmpty() && pass != null && !pass.isEmpty()) {
            UsuariosJpaController usuarioController = new UsuariosJpaController(emf);
            Usuarios usuario = usuarioController.findUsuario(nombreUsuario, pass);

            if (usuario != null) {
                request.getSession().setAttribute("idUsuario", usuario.getId());
                request.getSession().setAttribute("usuario", usuario); // Almacena el objeto usuario en la sesión
                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Por favor, ingresa tanto usuario como contraseña.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
