package dao;

import com.mycompany.pw1.classes.Conexion;
import com.mycompany.pw1.models.Usuarios;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class usuariodaO {

    public void registrarUsuario(Usuarios usuario) {
        EntityManager em = (EntityManager) Conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            em.persist(usuario);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Usuarios obtenerUsuarioPorNombre(String nombreUsuario) {
        EntityManager em = (EntityManager) Conexion.getEntityManager();
        try {
            TypedQuery<Usuarios> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario", Usuarios.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}

