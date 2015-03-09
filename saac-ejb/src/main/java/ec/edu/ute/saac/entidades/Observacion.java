/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "observacion", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"obs_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Observacion.findAll", query = "SELECT o FROM Observacion o"),
    @NamedQuery(name = "Observacion.findByObsCodigo", query = "SELECT o FROM Observacion o WHERE o.obsCodigo = :obsCodigo"),
    @NamedQuery(name = "Observacion.findByObsDescripcion", query = "SELECT o FROM Observacion o WHERE o.obsDescripcion = :obsDescripcion"),
    @NamedQuery(name = "Observacion.findByObsEstado", query = "SELECT o FROM Observacion o WHERE o.obsEstado = :obsEstado")})
public class Observacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "obs_codigo", nullable = false)
    private Integer obsCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 1000)
    @Column(name = "obs_descripcion", nullable = false, length = 1000)
    private String obsDescripcion;
    @Basic(optional = false)
    @Column(name = "obs_estado", nullable = false)
    private boolean obsEstado;
    @JoinColumn(name = "revision", referencedColumnName = "rev_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Revision revision;

    public Observacion() {
    }

    public Observacion(Integer obsCodigo) {
        this.obsCodigo = obsCodigo;
    }

    public Observacion(Integer obsCodigo, String obsDescripcion, boolean obsEstado) {
        this.obsCodigo = obsCodigo;
        this.obsDescripcion = obsDescripcion;
        this.obsEstado = obsEstado;
    }

    public Integer getObsCodigo() {
        return obsCodigo;
    }

    public void setObsCodigo(Integer obsCodigo) {
        this.obsCodigo = obsCodigo;
    }

    public String getObsDescripcion() {
        return obsDescripcion;
    }

    public void setObsDescripcion(String obsDescripcion) {
        this.obsDescripcion = obsDescripcion;
    }

    public boolean getObsEstado() {
        return obsEstado;
    }

    public void setObsEstado(boolean obsEstado) {
        this.obsEstado = obsEstado;
    }

    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (obsCodigo != null ? obsCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Observacion)) {
            return false;
        }
        Observacion other = (Observacion) object;
        if ((this.obsCodigo == null && other.obsCodigo != null) || (this.obsCodigo != null && !this.obsCodigo.equals(other.obsCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Observacion[ obsCodigo=" + obsCodigo + " ]";
    }
    
}
