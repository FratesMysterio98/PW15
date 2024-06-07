package com.mycompany.pw1.servlets;

import com.mycompany.pw1.models.Usuarios;
import com.mycompany.pw1.persistencia.UsuariosJpaController;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PerfilUsuarioServlet", urlPatterns = {"/PerfilUsuarioServlet"})
public class PerfilUsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Obtener la sesión del usuario
        Integer idUsuario = (Integer) request.getSession().getAttribute("idUsuario");

        if (idUsuario != null) {
            // Cargar los detalles del usuario desde la base de datos
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdpw1");
            UsuariosJpaController usuarioController = new UsuariosJpaController(emf);
            Usuarios usuario = usuarioController.findUsuarios(idUsuario);

            // Establecer el atributo del usuario en la solicitud
            request.setAttribute("usuario", usuario);
        } else {
            // Redirigir a la página de inicio de sesión si no hay un usuario en sesión
            response.sendRedirect("index.jsp");
            return;
        }

        // Redirigir a la página del perfil
        request.getRequestDispatcher("/Perfil.jsp").forward(request, response);
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
        return "Servlet para mostrar el perfil del usuario";
    }
}
