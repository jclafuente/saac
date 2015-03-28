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

import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.Usuario;

/**
 * 
 * @author JNK
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {
	@PersistenceContext(unitName = "saacPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PersonaFacade() {
		super(Persona.class);
	}

	public Collection<Persona> obtenerPersona(Integer carCodigo,
			Integer linInvCodigo, Integer areaInvCodigo) throws Exception {

		Collection<Persona> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Persona.class);
		criteria.createAlias("personaCarreraList", "perCarA");
		criteria.createAlias("perCarA.carrera", "carreraA");
		criteria.setFetchMode("carreraA", FetchMode.JOIN);
		criteria.add(Restrictions.eq("carreraA.carCodigo", carCodigo));

		if (linInvCodigo != null) {
			criteria.add(Restrictions.eq("lineaInvestigacion.carCodigo",
					linInvCodigo));
		}

		result = criteria.list();
		return result;
	}

	public Collection<Persona> obtenerPersonaDocente() throws Exception {

		Collection<Persona> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Persona.class);
		criteria.createAlias("usuarioList", "usuA");
		criteria.createAlias("usuA.usuarioRolList", "usrA");
		criteria.createAlias("usrA.rol", "rolA");
		criteria.setFetchMode("rolA", FetchMode.JOIN);
		criteria.add(Restrictions.eq("rolA.rolCodigo", Integer.parseInt("0000000009")));//cod docente
		result = criteria.list();
		
		return result;
	}

	
	public Collection<Persona> obtenerPersonaEstudiante() throws Exception{
		Collection<Persona> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Persona.class);
		criteria.createAlias("usuarioList", "usuA");
		criteria.createAlias("usuA.usuarioRolList", "usrA");
		criteria.setFetchMode("usrA", FetchMode.JOIN);
		criteria.add(Restrictions.eq("usrA.rol", Integer.parseInt("0000000019")));//cod estudiante
		
		
		result = criteria.list();
		return result;
	}
	
	public Collection<Persona> obtenerPersonaCarrera(Integer facCodigo,
			Integer carCodigo) throws Exception {

		Collection<Persona> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Persona.class);
		criteria.createAlias("personaCarreraList", "perCarA");
		criteria.createAlias("perCarA.carrera", "carA");
		criteria.setFetchMode("carA", FetchMode.JOIN);
		criteria.createAlias("carA.facultad", "facA");
		criteria.setFetchMode("facA", FetchMode.JOIN);
		
		criteria.createAlias("catalogoNacionalidad", "catnA");
		criteria.setFetchMode("catnA", FetchMode.JOIN);
		criteria.createAlias("catalogoEstadoCivil", "catesA");
		criteria.setFetchMode("catesA", FetchMode.JOIN);
		criteria.createAlias("catalogoGenero", "catgA");
		criteria.setFetchMode("catgA", FetchMode.JOIN);
		
		
		if (carCodigo > 0) {
			criteria.add(Restrictions.eq("carA.carCodigo", carCodigo));
		}
		
		if (facCodigo > 0) {
			criteria.add(Restrictions.eq("facA.facCodigo", facCodigo));
		}
		
		result = criteria.list();
		return result;
	}
	
	public Collection<Persona> obtenerPersonaUsuarioRol() throws Exception {
		Collection<Persona> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Persona.class);
		criteria.createAlias("usuario", "usuA");
		criteria.createAlias("usuA.usuarioRolList", "usurA");
		criteria.setFetchMode("usurA", FetchMode.JOIN);
		
		result = criteria.list();
		return result;
		
	}
	

}
