package com.mycompany.pw1.servlets;

import com.mycompany.pw1.models.Publicaciones;
import com.mycompany.pw1.persistencia.PublicacionesJpaController;
import com.mycompany.pw1.persistencia.exceptions.NonexistentEntityException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebServlet(name = "ActualizarPublicacionServlet", urlPatterns = {"/ActualizarPublicacionServlet"})
public class ActualizarPublicacionServlet extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdpw1");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");

        PublicacionesJpaController publicacionController = new PublicacionesJpaController(emf);
        try {
            Publicaciones publicacion = publicacionController.findPublicaciones(idPublicacion);
            publicacion.setTitulo(titulo);
            publicacion.setDescripcion(descripcion);
            publicacionController.edit(publicacion);
            response.sendRedirect("home.jsp");
        } catch (NonexistentEntityException ex) {
            request.setAttribute("error", "Error al actualizar la publicación.");
            request.getRequestDispatcher("/editarPublicacion.jsp").forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
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
        return "Servlet para actualizar publicaciones";
    }
}
