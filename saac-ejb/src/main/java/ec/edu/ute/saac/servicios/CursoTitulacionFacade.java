/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ec.edu.ute.saac.entidades.CursoTitulacion;

/**
 * 
 * @author JNK
 */
@Stateless
public class CursoTitulacionFacade extends AbstractFacade<CursoTitulacion> {
	@PersistenceContext(unitName = "saacPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CursoTitulacionFacade() {
		super(CursoTitulacion.class);
	}

	public Collection<CursoTitulacion> obtenerCursoTitulacion() {

		Collection<CursoTitulacion> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(CursoTitulacion.class);
		criteria.createAlias("periodos", "perA");
		criteria.createAlias("persona", "persA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
		criteria.setFetchMode("persA", FetchMode.JOIN);
		
		

		result = criteria.list();
		return result;

	}

}
