package com.mycompany.pw1.servlets;

import com.mycompany.pw1.models.Usuarios;
import com.mycompany.pw1.persistencia.UsuariosJpaController;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "ActualizarPerfilServlet", urlPatterns = {"/ActualizarPerfilServlet"})
@MultipartConfig
public class ActualizarPerfilServlet extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdpw1");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");
            if (usuario == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String correoElectronico = request.getParameter("correo_electronico");
            String nombreUsuario = request.getParameter("nombre_usuario");
            String contrasena = request.getParameter("contrasena");
            String fechaNacimientoStr = request.getParameter("fecha_nacimiento");
            Part filePart = request.getPart("imagen_perfil");
            String fileName = getFileName(filePart);

            // Usar una ruta relativa basada en el directorio de despliegue de la aplicación
            String appPath = request.getServletContext().getRealPath("");
            String savePath = appPath + File.separator + "imagenes" + File.separator + "usuarios";
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            String filePath = savePath + File.separator + usuario.getNombreUsuario() + "_" + fileName;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = null;
            try {
                fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
            } catch (ParseException e) {
                Logger.getLogger(ActualizarPerfilServlet.class.getName()).log(Level.SEVERE, "Error al parsear la fecha de nacimiento", e);
            }

            UsuariosJpaController usuarioController = new UsuariosJpaController(emf);

            usuario.setNombre(nombre);
            usuario.setApellidos(apellidos);
            usuario.setCorreoElectronico(correoElectronico);
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasena(contrasena);
            usuario.setFechaNacimiento(fechaNacimiento);

            if (filePart.getSize() > 0) {
                filePart.write(filePath);
                usuario.setImagenPerfil("imagenes/usuarios/" + usuario.getNombreUsuario() + "_" + fileName);
            }

            usuarioController.edit(usuario);

            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect("PerfilUsuarioServlet");
        } catch (Exception ex) {
            Logger.getLogger(ActualizarPerfilServlet.class.getName()).log(Level.SEVERE, "Error al actualizar el perfil del usuario", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el perfil del usuario");
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
        return "Servlet para actualizar el perfil del usuario";
    }
}
