/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pw1.persistencia;

import com.mycompany.pw1.models.Ejemplo;
import com.mycompany.pw1.persistencia.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author sadam
 */
public class EjemploJpaController implements Serializable {

    public EjemploJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejemplo ejemplo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ejemplo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ejemplo ejemplo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ejemplo = em.merge(ejemplo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = ejemplo.getIdEjemplo();
                if (findEjemplo(id) == null) {
                    throw new NonexistentEntityException("The ejemplo with id " + id + " no longer exists.");
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
            Ejemplo ejemplo;
            try {
                ejemplo = em.getReference(Ejemplo.class, id);
                ejemplo.getIdEjemplo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ejemplo with id " + id + " no longer exists.", enfe);
            }
            em.remove(ejemplo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ejemplo> findEjemploEntities() {
        return findEjemploEntities(true, -1, -1);
    }

    public List<Ejemplo> findEjemploEntities(int maxResults, int firstResult) {
        return findEjemploEntities(false, maxResults, firstResult);
    }

    private List<Ejemplo> findEjemploEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ejemplo.class));
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

    public Ejemplo findEjemplo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ejemplo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEjemploCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ejemplo> rt = cq.from(Ejemplo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
