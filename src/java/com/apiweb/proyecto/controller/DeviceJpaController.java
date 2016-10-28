/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiweb.proyecto.controller;

import com.apiweb.proyecto.controller.exceptions.NonexistentEntityException;
import com.apiweb.proyecto.controller.exceptions.RollbackFailureException;
import com.apiweb.proyecto.entities.Device;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


/**
 *
 * @author kronos16
 */
public class DeviceJpaController implements Serializable {

    public DeviceJpaController(EntityManagerFactory emf) {
        
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Device device) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(device);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Device device) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            device = em.merge(device);
            em.getTransaction().commit();
        } catch (Exception ex) {
           
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = device.getIdDevice();
                if (findDevice(id) == null) {
                    throw new NonexistentEntityException("The device with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Device device;
            try {
                device = em.getReference(Device.class, id);
                device.getIdDevice();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The device with id " + id + " no longer exists.", enfe);
            }
            em.remove(device);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Device> findDeviceEntities() {
        return findDeviceEntities(true, -1, -1);
    }

    public List<Device> findDeviceEntities(int maxResults, int firstResult) {
        return findDeviceEntities(false, maxResults, firstResult);
    }

    private List<Device> findDeviceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Device.class));
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

    public Device findDevice(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Device.class, id);
        } finally {
            em.close();
        }
    }

    public int getDeviceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Device> rt = cq.from(Device.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
