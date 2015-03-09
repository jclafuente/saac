/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import java.util.Collection;

import ec.edu.ute.saac.entidades.Proceso;
import ec.edu.ute.saac.entidades.TemasTitulacion;

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
public class ProcesoFacade extends AbstractFacade<Proceso> {
    @PersistenceContext(unitName = "saacPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoFacade() {
        super(Proceso.class);
    }
    
    public Collection<Proceso> obtenerProcesoEstudiante() throws Exception {

		Collection<Proceso> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Proceso.class);
		criteria.createAlias("cursoTitulacionEstudiante", "cureA");
		criteria.createAlias("cureA.cursoTitulacion", "curtA");
		criteria.setFetchMode("curtA", FetchMode.JOIN);
		criteria.createAlias("cureA.seleccionTema", "selA");
		criteria.createAlias("selA.perCodigo", "perA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
		criteria.createAlias("selA.temInvCodigo", "temA");		
		criteria.setFetchMode("temA", FetchMode.JOIN);
		

		/*if (areaInvCodigo > 0) {
			criteria.add(Restrictions.eq("temA.areInvCodigo", areaInvCodigo));
		}

		if (linInvCodigo > 0) {
			criteria.add(Restrictions.eq("linA.linInvCodigo", linInvCodigo));
		}

		if (carCodigo > 0) {
			criteria.add(Restrictions.eq("carA.carCodigo", carCodigo));
		}*/

		result = criteria.list();
		return result;
	}
    
    
    
}
