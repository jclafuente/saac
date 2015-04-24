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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import ec.edu.ute.saac.entidades.CursoTitulacion;
import ec.edu.ute.saac.entidades.Periodos;

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

	public Collection<CursoTitulacion> obtenerCursoTitulacionPeriodo(
			Integer periodoCodigo) {
		
		Collection<CursoTitulacion> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate()).createCriteria(CursoTitulacion.class);
		criteria.createAlias("periodos", "perA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
		
		//Criterion persona =Restrictions.isNull("persona");
		//Criterion persona1 =Restrictions.isNotNull("persona");
		
		//LogicalExpression orExp = Restrictions.or(persona, persona1);
		//criteria.add(Restrictions.isNull("persona"));
		
		criteria.add(Restrictions.isNotNull("persona"));
		criteria.createAlias("persona", "persA");
		criteria.setFetchMode("persA", FetchMode.JOIN);
		
		if (periodoCodigo > 0) {
			criteria.add(Restrictions.eq("perA.prdCodigo", periodoCodigo));
		}
		
		
		result = criteria.list();
		return result;
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
	
	public Collection<CursoTitulacion> obtenerCursoTitulacionCodigo(
			Integer curTitCodigo) {
		
		Collection<CursoTitulacion> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate()).createCriteria(CursoTitulacion.class);
		
		criteria.createAlias("periodos", "perA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
		
		//Criterion persona =Restrictions.isNull("persona");
		//Criterion persona1 =Restrictions.isNotNull("persona");
		//LogicalExpression orExp = Restrictions.or(persona, persona1);
		//criteria.add(Restrictions.isNull("persona"));
		
		criteria.add(Restrictions.isNotNull("persona"));
		criteria.createAlias("persona", "persA");
		criteria.setFetchMode("persA", FetchMode.JOIN);
		
		if (curTitCodigo > 0) {
			criteria.add(Restrictions.eq("perA.prdCodigo", curTitCodigo));
		}
		
		
		result = criteria.list();
		return result;
	}
	

}
