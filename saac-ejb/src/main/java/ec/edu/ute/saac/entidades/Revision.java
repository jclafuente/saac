/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "revision", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"rev_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Revision.findAll", query = "SELECT r FROM Revision r"),
    @NamedQuery(name = "Revision.findByRevCodigo", query = "SELECT r FROM Revision r WHERE r.revCodigo = :revCodigo"),
    @NamedQuery(name = "Revision.findByRevFecha", query = "SELECT r FROM Revision r WHERE r.revFecha = :revFecha")})
public class Revision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rev_codigo", nullable = false)
    private Integer revCodigo;
    @Basic(optional = false)
    @Column(name = "rev_fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date revFecha;
    @JoinColumn(name = "movimiento_proceso", referencedColumnName = "mov_prc_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MovimientoProceso movimientoProceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "revision", fetch = FetchType.LAZY)
    private List<Observacion> observacionList;

    public Revision() {
    }

    public Revision(Integer revCodigo) {
        this.revCodigo = revCodigo;
    }

    public Revision(Integer revCodigo, Date revFecha) {
        this.revCodigo = revCodigo;
        this.revFecha = revFecha;
    }

    public Integer getRevCodigo() {
        return revCodigo;
    }

    public void setRevCodigo(Integer revCodigo) {
        this.revCodigo = revCodigo;
    }

    public Date getRevFecha() {
        return revFecha;
    }

    public void setRevFecha(Date revFecha) {
        this.revFecha = revFecha;
    }

    public MovimientoProceso getMovimientoProceso() {
        return movimientoProceso;
    }

    public void setMovimientoProceso(MovimientoProceso movimientoProceso) {
        this.movimientoProceso = movimientoProceso;
    }

    public List<Observacion> getObservacionList() {
        return observacionList;
    }

    public void setObservacionList(List<Observacion> observacionList) {
        this.observacionList = observacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (revCodigo != null ? revCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Revision)) {
            return false;
        }
        Revision other = (Revision) object;
        if ((this.revCodigo == null && other.revCodigo != null) || (this.revCodigo != null && !this.revCodigo.equals(other.revCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Revision[ revCodigo=" + revCodigo + " ]";
    }
    
}
