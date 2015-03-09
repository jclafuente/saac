/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import java.util.Collection;

import ec.edu.ute.saac.entidades.LineaInvestigacion;
import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.SeleccionTema;
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
public class SeleccionTemaFacade extends AbstractFacade<SeleccionTema> {
	@PersistenceContext(unitName = "saacPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public SeleccionTemaFacade() {
		super(SeleccionTema.class);
	}

	public Collection<SeleccionTema> obtenerSeleccionTemaAprobado(
			Integer carCodigo, Integer linInvCodigo, Integer areaInvCodigo,Integer temaCodigo)
			throws Exception {

		Collection<SeleccionTema> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(SeleccionTema.class);
		criteria.createAlias("perCodigo", "perA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
		criteria.createAlias("temInvCodigo", "selA");
		criteria.setFetchMode("selA", FetchMode.JOIN);
		criteria.createAlias("selA.areaInvestigacion", "temA");
		criteria.setFetchMode("temA", FetchMode.JOIN);
		criteria.createAlias("temA.lineaInvestigacion", "linA");
		criteria.setFetchMode("linA", FetchMode.JOIN);
		criteria.createAlias("linA.carrera", "carA");

		if (temaCodigo > 0) {
			criteria.add(Restrictions.eq("selA.temTitCodigo", temaCodigo));
		}
		
		if (areaInvCodigo > 0) {
			criteria.add(Restrictions.eq("temA.areInvCodigo", areaInvCodigo));
		}

		if (linInvCodigo > 0) {
			criteria.add(Restrictions.eq("linA.linInvCodigo", linInvCodigo));
		}

		if (carCodigo > 0) {
			criteria.add(Restrictions.eq("carA.carCodigo", carCodigo));
		}

		
		
		result = criteria.list();
		return result;
	}

	public Collection<SeleccionTema> obtenerEstudianteSeleccionTema(Integer perCodigo)
			throws Exception {
		Collection<SeleccionTema> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(SeleccionTema.class);
		criteria.createAlias("perCodigo", "perA");
		criteria.add(Restrictions.eq("perA.perCodigo", perCodigo));
		result = criteria.list();
		return result;
	}
	
	public Collection<SeleccionTema> obtenerEstudiantesSeleccionTema()
			throws Exception {
		Collection<SeleccionTema> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(SeleccionTema.class);
		criteria.createAlias("perCodigo", "perA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
		
		result = criteria.list();
		return result;
	}
	
   

}
