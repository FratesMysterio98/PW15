package com.mycompany.pw1.servlets;

import com.mycompany.pw1.models.Publicaciones;
import com.mycompany.pw1.models.Usuarios;
import com.mycompany.pw1.persistencia.PublicacionesJpaController;
import com.mycompany.pw1.persistencia.UsuariosJpaController;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebServlet(name = "crearPublicacionServlet", urlPatterns = {"/crearPublicacionServlet"})
public class crearPublicacionServlet extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdpw1");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            int idUsuario = (int) request.getSession().getAttribute("idUsuario");

            String titulo = request.getParameter("ftitulo");
            String desc = request.getParameter("fdesc");
            Date fechaCreacion = new Date();
            Date fechaMovimiento = new Date();

            UsuariosJpaController usuarioController = new UsuariosJpaController(emf);
            Usuarios usuario = usuarioController.findUsuarios(idUsuario);

            PublicacionesJpaController publicacionController = new PublicacionesJpaController( emf);
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
}
