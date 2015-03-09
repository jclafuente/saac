/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import ec.edu.ute.saac.entidades.Catalogo;
import ec.edu.ute.saac.entidades.Facultad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JNK
 */
@Stateless
public class FacultadFacade extends AbstractFacade<Facultad> {

    @PersistenceContext(unitName = "saacPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacultadFacade() {
        super(Facultad.class);
    }

    public List<Facultad> obtenerFacultadPorCodigo(Facultad facultad) {
        List<Facultad> result = null;
        Query query = em.createNamedQuery("Facultad.findByFacCodigo");
        query.setParameter("facCodigo", facultad.getFacCodigo());
        result = query.getResultList();
        return result.isEmpty() || result == null ? null : result;
    }
}
