/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import ec.edu.ute.saac.entidades.Carrera;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.edu.ute.saac.entidades.Facultad;
import ec.edu.ute.saac.entidades.PersonaCarrera;
import java.util.ArrayList;
import java.util.List;
import ec.edu.ute.saac.entidades.LineaInvestigacion;
import ec.edu.ute.saac.entidades.ComisionInvestigacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author roberto
 */
public class Controlador implements Serializable {

    public Controlador (EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carrera carrera) {
        if (carrera.getPersonaCarreraList() == null) {
            carrera.setPersonaCarreraList(new ArrayList<PersonaCarrera>());
        }
        if (carrera.getLineaInvestigacionList() == null) {
            carrera.setLineaInvestigacionList(new ArrayList<LineaInvestigacion>());
        }
        if (carrera.getComisionInvestigacionList() == null) {
            carrera.setComisionInvestigacionList(new ArrayList<ComisionInvestigacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facultad facultad = carrera.getFacultad();
            if (facultad != null) {
                facultad = em.getReference(facultad.getClass(), facultad.getFacCodigo());
                carrera.setFacultad(facultad);
            }
            List<PersonaCarrera> attachedPersonaCarreraList = new ArrayList<PersonaCarrera>();
            for (PersonaCarrera personaCarreraListPersonaCarreraToAttach : carrera.getPersonaCarreraList()) {
                personaCarreraListPersonaCarreraToAttach = em.getReference(personaCarreraListPersonaCarreraToAttach.getClass(), personaCarreraListPersonaCarreraToAttach.getPerCarCodigo());
                attachedPersonaCarreraList.add(personaCarreraListPersonaCarreraToAttach);
            }
            carrera.setPersonaCarreraList(attachedPersonaCarreraList);
            List<LineaInvestigacion> attachedLineaInvestigacionList = new ArrayList<LineaInvestigacion>();
            for (LineaInvestigacion lineaInvestigacionListLineaInvestigacionToAttach : carrera.getLineaInvestigacionList()) {
                lineaInvestigacionListLineaInvestigacionToAttach = em.getReference(lineaInvestigacionListLineaInvestigacionToAttach.getClass(), lineaInvestigacionListLineaInvestigacionToAttach.getLinInvCodigo());
                attachedLineaInvestigacionList.add(lineaInvestigacionListLineaInvestigacionToAttach);
            }
            carrera.setLineaInvestigacionList(attachedLineaInvestigacionList);
            List<ComisionInvestigacion> attachedComisionInvestigacionList = new ArrayList<ComisionInvestigacion>();
            for (ComisionInvestigacion comisionInvestigacionListComisionInvestigacionToAttach : carrera.getComisionInvestigacionList()) {
                comisionInvestigacionListComisionInvestigacionToAttach = em.getReference(comisionInvestigacionListComisionInvestigacionToAttach.getClass(), comisionInvestigacionListComisionInvestigacionToAttach.getComInvCodigo());
                attachedComisionInvestigacionList.add(comisionInvestigacionListComisionInvestigacionToAttach);
            }
            carrera.setComisionInvestigacionList(attachedComisionInvestigacionList);
            em.persist(carrera);
            if (facultad != null) {
                facultad.getCarreraList().add(carrera);
                facultad = em.merge(facultad);
            }
            for (PersonaCarrera personaCarreraListPersonaCarrera : carrera.getPersonaCarreraList()) {
                Carrera oldCarreraOfPersonaCarreraListPersonaCarrera = personaCarreraListPersonaCarrera.getCarrera();
                personaCarreraListPersonaCarrera.setCarrera(carrera);
                personaCarreraListPersonaCarrera = em.merge(personaCarreraListPersonaCarrera);
                if (oldCarreraOfPersonaCarreraListPersonaCarrera != null) {
                    oldCarreraOfPersonaCarreraListPersonaCarrera.getPersonaCarreraList().remove(personaCarreraListPersonaCarrera);
                    oldCarreraOfPersonaCarreraListPersonaCarrera = em.merge(oldCarreraOfPersonaCarreraListPersonaCarrera);
                }
            }
            for (LineaInvestigacion lineaInvestigacionListLineaInvestigacion : carrera.getLineaInvestigacionList()) {
                Carrera oldCarreraOfLineaInvestigacionListLineaInvestigacion = lineaInvestigacionListLineaInvestigacion.getCarrera();
                lineaInvestigacionListLineaInvestigacion.setCarrera(carrera);
                lineaInvestigacionListLineaInvestigacion = em.merge(lineaInvestigacionListLineaInvestigacion);
                if (oldCarreraOfLineaInvestigacionListLineaInvestigacion != null) {
                    oldCarreraOfLineaInvestigacionListLineaInvestigacion.getLineaInvestigacionList().remove(lineaInvestigacionListLineaInvestigacion);
                    oldCarreraOfLineaInvestigacionListLineaInvestigacion = em.merge(oldCarreraOfLineaInvestigacionListLineaInvestigacion);
                }
            }
            for (ComisionInvestigacion comisionInvestigacionListComisionInvestigacion : carrera.getComisionInvestigacionList()) {
                Carrera oldCarreraOfComisionInvestigacionListComisionInvestigacion = comisionInvestigacionListComisionInvestigacion.getCarrera();
                comisionInvestigacionListComisionInvestigacion.setCarrera(carrera);
                comisionInvestigacionListComisionInvestigacion = em.merge(comisionInvestigacionListComisionInvestigacion);
                if (oldCarreraOfComisionInvestigacionListComisionInvestigacion != null) {
                    oldCarreraOfComisionInvestigacionListComisionInvestigacion.getComisionInvestigacionList().remove(comisionInvestigacionListComisionInvestigacion);
                    oldCarreraOfComisionInvestigacionListComisionInvestigacion = em.merge(oldCarreraOfComisionInvestigacionListComisionInvestigacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<Carrera> findCarreraEntities() {
        return findCarreraEntities(true, -1, -1);
    }

    public List<Carrera> findCarreraEntities(int maxResults, int firstResult) {
        return findCarreraEntities(false, maxResults, firstResult);
    }

    private List<Carrera> findCarreraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carrera.class));
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

    public Carrera findCarrera(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carrera.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarreraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carrera> rt = cq.from(Carrera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
