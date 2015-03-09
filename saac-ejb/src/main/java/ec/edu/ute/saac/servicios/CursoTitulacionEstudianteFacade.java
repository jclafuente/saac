package ec.edu.ute.saac.servicios;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ec.edu.ute.saac.entidades.CursoTitulacionEstudiante;

@Stateless
public class CursoTitulacionEstudianteFacade extends AbstractFacade<CursoTitulacionEstudiante>{
	
	@PersistenceContext(unitName = "saacPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CursoTitulacionEstudianteFacade() {
		super(CursoTitulacionEstudiante.class);
	}

	public Collection<CursoTitulacionEstudiante> obtenerCursoTitulacionEstudiantes()
			throws Exception {
		Collection<CursoTitulacionEstudiante> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(CursoTitulacionEstudiante.class);
		criteria.createAlias("seleccionTema", "selA");
		criteria.createAlias("selA.temInvCodigo", "temA");
		criteria.setFetchMode("temA.temTitCodigo", FetchMode.JOIN);
		
		criteria.createAlias("selA.perCodigo", "perA");
		criteria.setFetchMode("perA.perCodigo", FetchMode.JOIN);
		
		criteria.createAlias("cursoTitulacion", "curA");
		criteria.setFetchMode("curA.curTitCodigo", FetchMode.JOIN);
		criteria.createAlias("curA.persona", "persA");
		criteria.setFetchMode("persA.perCodigo", FetchMode.JOIN);

		result = criteria.list();
		return result;
	}
	
	public Collection<CursoTitulacionEstudiante> obtenerCursoTitulacionEstudianteTema(Integer curCodigo)
			throws Exception {
		Collection<CursoTitulacionEstudiante> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(CursoTitulacionEstudiante.class);
		criteria.createAlias("cursoTitulacion", "curA");
		criteria.setFetchMode("curA", FetchMode.JOIN);
		criteria.add(Restrictions.eq("curA.curTitCodigo", curCodigo));
		criteria.createAlias("seleccionTema", "selA");
		criteria.createAlias("selA.temInvCodigo", "temA");
		criteria.setFetchMode("temA", FetchMode.JOIN);
		
		result = criteria.list();
		return result;
	}
	
	public Collection<CursoTitulacionEstudiante> obtenerCursoTitulacionEstudianteEstudiante(Integer curCodigo, Integer temCodigo)
			throws Exception {
		Collection<CursoTitulacionEstudiante> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(CursoTitulacionEstudiante.class);
		criteria.createAlias("seleccionTema", "selA");
		criteria.createAlias("selA.perCodigo", "persA");
		criteria.setFetchMode("persA", FetchMode.JOIN);
		criteria.createAlias("selA.temInvCodigo", "temA");
		criteria.add(Restrictions.eq("temA.temTitCodigo", temCodigo));
		criteria.setFetchMode("temA", FetchMode.JOIN);
	
		criteria.createAlias("cursoTitulacion", "curA");
		criteria.setFetchMode("curA.curTitCodigo", FetchMode.JOIN);
		criteria.add(Restrictions.eq("curA.curTitCodigo", curCodigo));
		
		result = criteria.list();
		return result;
	}
	
	
}
