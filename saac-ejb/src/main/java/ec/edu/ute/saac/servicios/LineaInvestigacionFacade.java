/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import ec.edu.ute.saac.entidades.Carrera;
import ec.edu.ute.saac.entidades.LineaInvestigacion;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author JNK
 */
@Stateless
public class LineaInvestigacionFacade extends AbstractFacade<LineaInvestigacion> {

    @PersistenceContext(unitName = "saacPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaInvestigacionFacade() {
        super(LineaInvestigacion.class);
    }

    public List<LineaInvestigacion> obtenerUsuarioPorNombre(Carrera carrera) {
        List<LineaInvestigacion> result = null;
        Query query = em.createNamedQuery("LineaInvestigacion.buscarPorCarrera");
        query.setParameter("carrera", carrera);
        result = query.getResultList();
        return result.isEmpty() || result == null ? null : result;
    }
    
    
    public Collection<LineaInvestigacion> obtenerLineaInvestigacionCarrera(Integer carCodigo){
    	
    	Collection<LineaInvestigacion> result = null;
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(LineaInvestigacion.class);
		criteria.createAlias("carrera", "car");
		criteria.add(Restrictions.eq("car.carCodigo", carCodigo));
		criteria.setFetchMode("car", FetchMode.JOIN);
		result = criteria.list();
		
		return result;
    }
}
