/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import ec.edu.ute.saac.entidades.Catalogo;
import ec.edu.ute.saac.entidades.Persona;

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
public class CatalogoFacade extends AbstractFacade<Catalogo> {

    @PersistenceContext(unitName = "saacPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatalogoFacade() {
        super(Catalogo.class);
    }
    
    public Collection<Catalogo> obtenerCatalogoNac() throws Exception {

		Collection<Catalogo> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Catalogo.class);
		criteria.createAlias("grupoCatalogo", "catA");
		criteria.setFetchMode("catA", FetchMode.JOIN);
		criteria.add(Restrictions.eq("catA.grpCatCodigo", Integer.parseInt("0000000001")));//nacionalidad 1

		result = criteria.list();
		return result;
	}
    
    public Collection<Catalogo> obtenerCatalogoEstCiv() throws Exception {

		Collection<Catalogo> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Catalogo.class);
		criteria.createAlias("grupoCatalogo", "catA");
		criteria.setFetchMode("catA", FetchMode.JOIN);
		criteria.add(Restrictions.eq("catA.grpCatCodigo", Integer.parseInt("0000000002")));//estado civil 2 

		result = criteria.list();
		return result;
	}
    
    public Collection<Catalogo> obtenerCatalogoGenero() throws Exception {

		Collection<Catalogo> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Catalogo.class);
		criteria.createAlias("grupoCatalogo", "catA");
		criteria.setFetchMode("catA", FetchMode.JOIN);
		criteria.add(Restrictions.eq("catA.grpCatCodigo", Integer.parseInt("0000000004")));//genero 4

		result = criteria.list();
		return result;
	}
    
}
