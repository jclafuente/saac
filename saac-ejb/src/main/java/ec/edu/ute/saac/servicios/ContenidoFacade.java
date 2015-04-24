package ec.edu.ute.saac.servicios;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.edu.ute.saac.entidades.Contenido;

@Stateless
public class ContenidoFacade extends AbstractFacade<Contenido> {

    @PersistenceContext(unitName = "saacPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContenidoFacade() {
        super(Contenido.class);
    }
    
    public String  obtenerProblema(Integer prcCodigo) throws Exception {
    	String result = null;;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Contenido.class);
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("cntNombre"));
		criteria.setProjection(proList);
		
		criteria.createAlias("proceso","prcA");
		criteria.setFetchMode("prcA", FetchMode.JOIN);  
		criteria.add(Restrictions.eq("prcA.prcCodigo", prcCodigo));
		
		criteria.createAlias("componente","comA");
		criteria.add(Restrictions.eq("comA.comCodigo", Integer.valueOf("0000000001")));
		criteria.setFetchMode("comA", FetchMode.JOIN);
		
		result = criteria.uniqueResult().toString();
		return result;
	}
    
    
    public String  obtenerJustificacion(Integer prcCodigo) throws Exception {
    	String result = null;;
		Criteria criteria = ((Session) getEntityManager().getDelegate())
				.createCriteria(Contenido.class);
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("cntNombre"));
		criteria.setProjection(proList);
		
		criteria.createAlias("proceso","prcA");
		criteria.setFetchMode("prcA", FetchMode.JOIN);  
		criteria.add(Restrictions.eq("prcA.prcCodigo", prcCodigo));
		
		criteria.createAlias("componente","comA");
		criteria.add(Restrictions.eq("comA.comCodigo", Integer.valueOf("0000000002")));
		criteria.setFetchMode("comA", FetchMode.JOIN);
		
		result = criteria.uniqueResult().toString();
		return result;
	}
    
   
    
}