/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import java.util.Collection;
import java.util.List;

import ec.edu.ute.saac.entidades.Carrera;
import ec.edu.ute.saac.entidades.PersonaCarrera;

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
public class PersonaCarreraFacade extends AbstractFacade<PersonaCarrera> {
	@PersistenceContext(unitName = "saacPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PersonaCarreraFacade() {
		super(PersonaCarrera.class);
	}

	public Collection<PersonaCarrera> obtenerPersonaCarrera(Integer facCodigo,
			Integer carCodigo) {
		List<PersonaCarrera> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(PersonaCarrera.class);
		criteria.createAlias("carrera", "carA");
		criteria.setFetchMode("carA", FetchMode.JOIN);
		criteria.createAlias("carA.facultad", "facA");
		criteria.setFetchMode("facA", FetchMode.JOIN);
		
		if (carCodigo > 0) {
			criteria.add(Restrictions.eq("carA.carCodigo", carCodigo));
		}
		
		if (facCodigo > 0) {
			criteria.add(Restrictions.eq("facA.facCodigo", facCodigo));
		}

		criteria.createAlias("persona", "perA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
		/*criteria.createAlias("perA.usuario", "usuA");
		criteria.setFetchMode("usuA", FetchMode.JOIN);

		criteria.createAlias("perA.catalogoNacionalidad", "catnA");
		criteria.setFetchMode("catnA", FetchMode.JOIN);

		criteria.createAlias("perA.catalogoEstadoCivil", "cateA");
		criteria.setFetchMode("cateA", FetchMode.JOIN);

		criteria.createAlias("perA.catalogoGenero", "catgA");
		criteria.setFetchMode("catgA", FetchMode.JOIN);*/
		result = criteria.list();
		return result;

	}

}
