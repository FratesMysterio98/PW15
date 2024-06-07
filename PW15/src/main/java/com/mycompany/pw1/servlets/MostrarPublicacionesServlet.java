package com.mycompany.pw1.servlets;

import com.mycompany.pw1.models.Publicaciones;
import com.mycompany.pw1.persistencia.PublicacionesJpaController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebServlet(name = "MostrarPublicacionesServlet", urlPatterns = {"/homeServlet"})
public class MostrarPublicacionesServlet extends HttpServlet {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdpw1");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PublicacionesJpaController publicacionesController = new PublicacionesJpaController(emf);
        List<Publicaciones> listaPublicaciones = publicacionesController.findPublicacionesEntities();
        request.setAttribute("ListaPublicaciones", listaPublicaciones);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
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
        return "Servlet para mostrar las publicaciones en home.jsp";
    }
}
