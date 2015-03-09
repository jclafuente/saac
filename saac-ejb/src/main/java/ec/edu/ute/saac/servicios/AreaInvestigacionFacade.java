/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import java.util.Collection;
import java.util.List;

import ec.edu.ute.saac.entidades.AreaInvestigacion;
import ec.edu.ute.saac.entidades.LineaInvestigacion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author JNK
 */
@Stateless
public class AreaInvestigacionFacade extends AbstractFacade<AreaInvestigacion> {
    @PersistenceContext(unitName = "saacPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreaInvestigacionFacade() {
        super(AreaInvestigacion.class);
    }
    
    public Collection<AreaInvestigacion> obtenerAreaInvestigacionLineaInvestigacion(Integer linInvCodigo ){
		List<AreaInvestigacion> result = null;
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(AreaInvestigacion.class);
		criteria.createAlias("lineaInvestigacion", "lin");
		criteria.add(Restrictions.eq("lin.linInvCodigo", linInvCodigo));
		criteria.setFetchMode("lin", FetchMode.JOIN);
		result = criteria.list();
		return result;
		
	}
    
 
    
 
    
}
