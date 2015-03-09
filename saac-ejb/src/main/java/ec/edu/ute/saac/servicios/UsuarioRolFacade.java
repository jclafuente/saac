/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import java.util.Collection;

import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.Usuario;
import ec.edu.ute.saac.entidades.UsuarioRol;

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
public class UsuarioRolFacade extends AbstractFacade<UsuarioRol> {
	@PersistenceContext(unitName = "saacPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {

		return em;
	}

	public Collection<UsuarioRol> obtenerUsuarioDocente() throws Exception {

		Collection<UsuarioRol> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(UsuarioRol.class);
		criteria.createAlias("rol", "rolA");
		criteria.add(Restrictions.eq("rolA.rolCodigo",
				Integer.parseInt("0000000009")));
		criteria.createAlias("usuario", "usuA");
		criteria.setFetchMode("usuA", FetchMode.JOIN);
		result = criteria.list();
		return result;
	}

	public Collection<UsuarioRol> obtenerUsuarioEstudiante() throws Exception {

		Collection<UsuarioRol> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(UsuarioRol.class);
		criteria.createAlias("rol", "rolA");
		criteria.createAlias("usuario", "usuA");
		criteria.add(Restrictions.eq("rolA.rolCodigo",Integer.parseInt("0000000019")));
		criteria.createAlias("usuA.persona", "perA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
		
		result = criteria.list();
		return result;
	}

	public UsuarioRolFacade() {
		super(UsuarioRol.class);
	}

}
