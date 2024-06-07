package com.mycompany.pw1.servlets;

import com.mycompany.pw1.models.Publicaciones;
import com.mycompany.pw1.models.Usuarios;
import com.mycompany.pw1.persistencia.PublicacionesJpaController;
import com.mycompany.pw1.persistencia.UsuariosJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "crearPublicacionServlet", urlPatterns = {"/crearPublicacionServlet"})
public class crearPublicacionServlet extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdpw1");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Verificar si el usuario está en sesión
            Integer idUsuario = (Integer) request.getSession().getAttribute("idUsuario");
            if (idUsuario == null) {
                response.sendRedirect("index.jsp"); // Redirigir al login si el usuario no está en sesión
                return;
            }

            String titulo = request.getParameter("ftitulo");
            String desc = request.getParameter("fdesc");
            Date fechaCreacion = new Date();
            Date fechaMovimiento = new Date();

            UsuariosJpaController usuarioController = new UsuariosJpaController(emf);
            Usuarios usuario = usuarioController.findUsuarios(idUsuario);

            if (usuario == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Usuario no encontrado.");
                return;
            }

            PublicacionesJpaController publicacionController = new PublicacionesJpaController(emf);
            Publicaciones publicacion = new Publicaciones();

            publicacion.setTitulo(titulo);
            publicacion.setDescripcion(desc);
            publicacion.setFechaCreacion(fechaCreacion);
            publicacion.setFechaMovimiento(fechaMovimiento);
            publicacion.setEstatus(true);
            publicacion.setUsuario(usuario);
            publicacionController.create(publicacion);

            List<Publicaciones> listaPublicaciones = publicacionController.findPublicacionesEntities();
            request.setAttribute("ListaPublicaciones", listaPublicaciones);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(crearPublicacionServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear la publicación");
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
        return "Servlet que crea una publicación";
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
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
