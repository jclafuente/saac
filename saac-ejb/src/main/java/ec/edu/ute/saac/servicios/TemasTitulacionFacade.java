/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import java.util.Collection;
import java.util.List;

import ec.edu.ute.saac.entidades.Carrera;
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
public class TemasTitulacionFacade extends AbstractFacade<TemasTitulacion> {
	@PersistenceContext(unitName = "saacPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TemasTitulacionFacade() {
		super(TemasTitulacion.class);
	}

	public Collection<TemasTitulacion> obtenerTemaTitulacion(Integer carCodigo,
			Integer linInvCodigo, Integer areaInvCodigo) throws Exception {

		Collection<TemasTitulacion> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(TemasTitulacion.class);
		criteria.createAlias("areaInvestigacion", "temA");
		criteria.setFetchMode("temA", FetchMode.JOIN);
		criteria.createAlias("temA.lineaInvestigacion", "linA");
		criteria.setFetchMode("linA", FetchMode.JOIN);
		criteria.createAlias("linA.carrera", "carA");

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

	public Collection<TemasTitulacion> obtenerTemaTitulacionAprobado(
			Integer carCodigo, Integer linInvCodigo, Integer areaInvCodigo)
			throws Exception {
		Collection<TemasTitulacion> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(TemasTitulacion.class);
		criteria.add(Restrictions.eq("temTitEstado", "APROBADO"));
		criteria.createAlias("areaInvestigacion", "temA");
		criteria.setFetchMode("temA", FetchMode.JOIN);
		criteria.createAlias("temA.lineaInvestigacion", "linA");
		criteria.setFetchMode("linA", FetchMode.JOIN);
		criteria.createAlias("linA.carrera", "carA");

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

	public void actualizarEstadoTemaTitulacion(TemasTitulacion temaTitulacion,
			String estado) {

		if (temaTitulacion.getTemTitEstado().toString() != "APROBADO") {
			temaTitulacion.setTemTitEstado(estado);
			edit(temaTitulacion);
		}

	}

	public void asignarEstadoTemaTitulacion(TemasTitulacion temaTitulacion,
			String estado) {

		temaTitulacion.setTemTitEstado(estado);
		edit(temaTitulacion);

	}
	
	 public Collection<TemasTitulacion> obtenerSeleccionTemaAreaInvestigacion(Integer areInvCodigo){
	    	
	    	Collection<TemasTitulacion> result = null;
			Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(TemasTitulacion.class);
			criteria.createAlias("areaInvestigacion", "areA");
			criteria.add(Restrictions.eq("areA.areInvCodigo", areInvCodigo));
			criteria.setFetchMode("areA", FetchMode.JOIN);
			result = criteria.list();
			return result;
	    }

}
