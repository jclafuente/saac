/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;

import ec.edu.ute.saac.entidades.Carrera;
import ec.edu.ute.saac.entidades.Facultad;

/**
 * 
 * @author JNK
 */
@Stateless
public class CarreraFacade extends AbstractFacade<Carrera> {
	@PersistenceContext(unitName = "saacPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CarreraFacade() {
		super(Carrera.class);
	}

	public List<Carrera> obtenerCarreraPorFacultad(Facultad facultad) {
		List<Carrera> result = null;
		Query query = em.createNamedQuery("Carrera.obtenerPorFacultad");
		query.setParameter("facultad", facultad);
		result = query.getResultList();
		return result.isEmpty() || result == null ? null : result;
	}

	/**
	 * @return
	 */
	public Collection<Carrera> obtenerCarreraFacultad() {
		List<Carrera> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Carrera.class);
		criteria.createAlias("facultad", "fac");
		criteria.setFetchMode("fac", FetchMode.JOIN);
		result = criteria.list();
		return result;

	}
	
	

}
