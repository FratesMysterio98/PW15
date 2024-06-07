package DAO;

import com.mycompany.pw1.models.Publicaciones;
import com.mycompany.pw1.persistencia.PublicacionesJpaController;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import jakarta.persistence.Persistence;

public class PublicacionesDAO {
    private final PublicacionesJpaController publicacionesController;

    public PublicacionesDAO() {
        this.publicacionesController = new PublicacionesJpaController((javax.persistence.EntityManagerFactory) (EntityManagerFactory) Persistence.createEntityManagerFactory("bdpw1"));
    }

    public void crearPublicacion(Publicaciones publicacion) {
        publicacionesController.create(publicacion);
    }

    public List<Publicaciones> obtenerTodasLasPublicaciones() {
        return publicacionesController.findPublicacionesEntities();
    }

    // Agrega otros métodos según tus necesidades, como actualizar y borrar publicaciones.
}


