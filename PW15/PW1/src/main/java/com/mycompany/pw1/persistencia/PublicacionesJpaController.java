package com.mycompany.pw1.persistencia;

import com.mycompany.pw1.models.Publicaciones;
import com.mycompany.pw1.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PublicacionesJpaController implements Serializable {

    public PublicacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Publicaciones publicaciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(publicaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Publicaciones publicaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            publicaciones = em.merge(publicaciones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = publicaciones.getIdPublicacion();
                if (findPublicaciones(id) == null) {
                    throw new NonexistentEntityException("The publicaciones with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Publicaciones publicaciones;
            try {
                publicaciones = em.getReference(Publicaciones.class, id);
                publicaciones.getIdPublicacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The publicaciones with id " + id + " no longer exists.", enfe);
            }
            em.remove(publicaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Publicaciones> findPublicacionesEntities() {
        return findPublicacionesEntities(true, -1, -1);
    }

    public List<Publicaciones> findPublicacionesEntities(int maxResults, int firstResult) {
        return findPublicacionesEntities(false, maxResults, firstResult);
    }

    private List<Publicaciones> findPublicacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Publicaciones.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Publicaciones findPublicaciones(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Publicaciones.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Publicaciones> findPublicaciones(String texto) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            
            CriteriaQuery cq = builder.createQuery();
            
            Root <Publicaciones> root = cq.from(Publicaciones.class); 
            
            cq.select(root);
            
            Predicate predicateLikeTitulo = builder.like(root.get("titulo"),"%"+texto+"%");
            Predicate predicateLikeDescripcion = builder.like(root.get("descripcion"),"%"+texto+"%");
            Predicate predicateOr = builder.or(predicateLikeTitulo, predicateLikeDescripcion);
            Predicate predicateEstatus = builder.equal(root.get("estatus"), true);
            Predicate predicateFinal = builder.and(predicateOr, predicateEstatus);
            
            cq.where(predicateFinal);
            Query q = em.createQuery(cq);
            
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getPublicacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Publicaciones> rt = cq.from(Publicaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
