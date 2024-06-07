package com.mycompany.pw1.servlets;

import com.mycompany.pw1.models.Publicaciones;
import com.mycompany.pw1.persistencia.PublicacionesJpaController;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebServlet(name = "EditarPublicacionServlet", urlPatterns = {"/EditarPublicacionServlet"})
public class EditarPublicacionServlet extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdpw1");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
        PublicacionesJpaController publicacionController = new PublicacionesJpaController(emf);
        Publicaciones publicacion = publicacionController.findPublicaciones(idPublicacion);
        request.setAttribute("publicacion", publicacion);
        request.getRequestDispatcher("/editarPublicacion.jsp").forward(request, response);
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
        return "Servlet para editar publicaciones";
    }
}
