/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.Usuario;
import ec.edu.ute.saac.entidades.UsuarioRol;

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
public class UsuarioFacade extends AbstractFacade<Usuario> {

	@PersistenceContext(unitName = "saacPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UsuarioFacade() {
		super(Usuario.class);
	}

	public Usuario obtenerUsuarioPorNombre(String usuUserName) {
		List<Usuario> result = null;
		Query query = em.createNamedQuery("Usuario.findByUsuUserName");
		query.setParameter("usuUserName", usuUserName);
		result = query.getResultList();
		return result.isEmpty() || result == null ? null : result.get(0);
	}

	public Collection<Usuario> obtenerUsuarioDocente() throws Exception {

		Collection<Usuario> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Usuario.class);
		criteria.createAlias("usuarioRolList", "urolA");
		criteria.createAlias("urolA.rol", "usA");
		criteria.setFetchMode("usA", FetchMode.JOIN);
		criteria.add(Restrictions.eq("usA.rolCodigo", Integer.parseInt("0000000009")));
		
		result = criteria.list();
		return result;
	}

	public Collection<Usuario> obtenerUsuarioEstudiante() throws Exception {

		Collection<Usuario> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Usuario.class);
		criteria.createAlias("usuarioRolList", "urolA");
		criteria.createAlias("urolA.rol", "usA");
		criteria.setFetchMode("usA", FetchMode.JOIN);
		criteria.add(Restrictions.eq("usA.rolCodigo", Integer.parseInt("0000000019")));
		
		result = criteria.list();
		return result;
	}
	
	public Object obtenerPassUsuario(Integer usuCodigo) throws Exception {

		Object result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Usuario.class);
		criteria.createAlias("usuPassword", "usuA");
		criteria.setFetchMode("usuA", FetchMode.JOIN);
		criteria.add(Restrictions.eq("usuCodigo", usuCodigo));
		
		result = criteria.uniqueResult() ; 
		return result;
	}
	
	
}
