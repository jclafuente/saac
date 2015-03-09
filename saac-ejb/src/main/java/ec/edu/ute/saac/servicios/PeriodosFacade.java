/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.servicios;

import ec.edu.ute.saac.entidades.Periodos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JNK
 */
@Stateless
public class PeriodosFacade extends AbstractFacade<Periodos> {
    @PersistenceContext(unitName = "saacPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodosFacade() {
        super(Periodos.class);
    }
    
}
