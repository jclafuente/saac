/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import java.util.Collection;

import ec.edu.ute.saac.entidades.Contenido;
import ec.edu.ute.saac.entidades.Proceso;
import ec.edu.ute.saac.entidades.TemasTitulacion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author JNK
 */
@Stateless
public class ProcesoFacade extends AbstractFacade<Proceso> {
    @PersistenceContext(unitName = "saacPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoFacade() {
        super(Proceso.class);
    }
    
    /**
     * @param curCodigo
     * @retun obtengo los estudiantes del curso de titulacion
     * @throws Exception
     */
    public Collection<Proceso> obtenerProcesoEstudiante(Integer curCodigo) throws Exception {

		Collection<Proceso> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Proceso.class);
	/*	criteria.add(Restrictions.isNull("tipoProceso"));
		criteria.add(Restrictions.isNull("temasTitulacion"));
		criteria.add(Restrictions.isNull("seleccionTema"));
		criteria.add(Restrictions.isNull("cursoTitulacionEstudiante"));*/
		
		criteria.createAlias("cursoTitulacion", "curA");
		criteria.setFetchMode("curA", FetchMode.JOIN);
		criteria.createAlias("persona", "perA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
		
		
		if(curCodigo>0){
			criteria.add(Restrictions.eq("curA.curTitCodigo", curCodigo));			
		}
		
		result = criteria.list();
		return result;
	}
    
   
    /**
     * @param curCodigo
     * @retun obtengo el docente del curso de titulacion
     * @throws Exception
     */
    public Collection<Proceso> obtenerProcesoCursoTitulacion(Integer curCodigo) throws Exception {

		Collection<Proceso> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Proceso.class);
		criteria.createAlias("cursoTitulacion", "curA");
		criteria.createAlias("curA.persona", "perA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
		
		if(curCodigo>0){
			criteria.add(Restrictions.eq("curA.curTitCodigo", curCodigo));			
		}	
		
		result = criteria.list();
		return result;
	}
    
    public Collection<Proceso> obtenerProcesoPeriodo() throws Exception {
		Collection<Proceso> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Proceso.class);
		criteria.createAlias("periodo", "perA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
				
		result = criteria.list();
		return result;
	}
    
    public Collection<Proceso> obtenerProcesoPeriodoEstudiante(Integer prdCodigo) throws Exception {
		Collection<Proceso> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Proceso.class);
		criteria.createAlias("cursoTitulacion", "curA");
		criteria.setFetchMode("curA", FetchMode.JOIN);
		criteria.createAlias("periodo", "prdA");
		criteria.add(Restrictions.eq("prdA.prdCodigo", prdCodigo));
		criteria.setFetchMode("prdA", FetchMode.JOIN);
		criteria.createAlias("persona", "perA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
				
		result = criteria.list();
		return result;
	}
    

    /**
     * @param prdCodigo
     * @retun metodo prueba para obtener los estudiantes de la tabla proceso
     * @throws Exception
     */
    public Collection<Proceso> obtenerProcesoEstudianteLoggin() throws Exception {
		Collection<Proceso> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Proceso.class);	
		criteria.createAlias("persona", "perA");
		criteria.setFetchMode("perA", FetchMode.JOIN);
	
		result = criteria.list();
		return result;
	}
    
    /**
     * @param perCodigo
     * @retun metodo prueba que obtiene el tema de cada estudiante
     * @throws Exception
     */
    public Collection<Proceso> obtenerProcesoTemaTitulacionEstudiante(Integer perCodigo) throws Exception {
		Collection<Proceso> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Proceso.class);
		criteria.createAlias("temasTitulacion", "temA");
		criteria.createAlias("temA.perCodigo", "perA");
		
		criteria.createAlias("temA.areaInvestigacion", "areaA");
		criteria.createAlias("areaA.lineaInvestigacion", "linA");
		criteria.createAlias("linA.carrera", "carA");
		
		criteria.setFetchMode("perA", FetchMode.JOIN);
		criteria.setFetchMode("carA", FetchMode.JOIN);
		
		criteria.createAlias("persona", "persA");
		criteria.add(Restrictions.eq("persA.perCodigo", perCodigo));
		criteria.setFetchMode("persA", FetchMode.JOIN);
		
		criteria.createAlias("contenido_problema", "conpA");
		criteria.setFetchMode("conpA", FetchMode.JOIN);
			
		result = criteria.list();
		return result;
	}
    
    public Collection<Proceso>  obtenerProblema(Integer prcCodigo) throws Exception {
    	Collection<Proceso> result = null;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Proceso.class);
		criteria.createAlias("contenido_problema","contA");
		criteria.createAlias("contA.componente","compA");
		criteria.add(Restrictions.eq("compA.comCodigo", Integer.valueOf("0000000001")));
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("contA.cntNombre"));
		criteria.setProjection(proList);
		criteria.setFetchMode("compA", FetchMode.JOIN);  
		
		
		result = criteria.list();
		return result;
	}
}
