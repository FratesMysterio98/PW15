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
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.EntityManager;

@WebServlet(name = "homeServlet", urlPatterns = {"/homeServlet"})
public class homeServlet extends HttpServlet {

    jakarta.persistence.EntityManagerFactory emf = jakarta.persistence.Persistence.createEntityManagerFactory("bdpw1");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdpw1");
        PublicacionesJpaController publicacionController = new PublicacionesJpaController((javax.persistence.EntityManagerFactory) emf);
        
        List<Publicaciones> listaPublicaciones = publicacionController.findPublicacionesEntities();
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
        return "Short description";
    }

    public void persist(Object object) {
        EntityManager em = (EntityManager) emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void persist1(Object object) {
        EntityManager em = (EntityManager) emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
