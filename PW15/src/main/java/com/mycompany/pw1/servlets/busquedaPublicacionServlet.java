package com.mycompany.pw1.servlets;

import com.mycompany.pw1.models.Publicaciones;
import com.mycompany.pw1.persistencia.PublicacionesJpaController;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebServlet(name = "busquedaPublicacionServlet", urlPatterns = {"/busquedaPublicacionServlet"})
public class busquedaPublicacionServlet extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdpw1");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String texto = request.getParameter("txtBusqueda");

        PublicacionesJpaController publicacionController = new PublicacionesJpaController((javax.persistence.EntityManagerFactory) emf);
        List<Publicaciones> listaPublicaciones = publicacionController.findPublicaciones(texto);
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
        return "Servlet para buscar publicaciones";
    }
}
