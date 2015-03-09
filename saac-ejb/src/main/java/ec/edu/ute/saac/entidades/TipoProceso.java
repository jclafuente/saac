/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "tipo_proceso", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tip_prc_codigo"})})
@NamedQueries({
    @NamedQuery(name = "TipoProceso.findAll", query = "SELECT t FROM TipoProceso t"),
    @NamedQuery(name = "TipoProceso.findByTipPrcCodigo", query = "SELECT t FROM TipoProceso t WHERE t.tipPrcCodigo = :tipPrcCodigo"),
    @NamedQuery(name = "TipoProceso.findByTipPrcNombre", query = "SELECT t FROM TipoProceso t WHERE t.tipPrcNombre = :tipPrcNombre"),
    @NamedQuery(name = "TipoProceso.findByTipPrcEstado", query = "SELECT t FROM TipoProceso t WHERE t.tipPrcEstado = :tipPrcEstado"),
    @NamedQuery(name = "TipoProceso.findByTipPrcDescripcion", query = "SELECT t FROM TipoProceso t WHERE t.tipPrcDescripcion = :tipPrcDescripcion")})
public class TipoProceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tip_prc_codigo", nullable = false)
    private Integer tipPrcCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "tip_prc_nombre", nullable = false, length = 255)
    private String tipPrcNombre;
    @Basic(optional = false)
    @Column(name = "tip_prc_estado", nullable = false)
    private boolean tipPrcEstado;
    @Size(max = 255)
    @Column(name = "tip_prc_descripcion", length = 255)
    private String tipPrcDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoProceso", fetch = FetchType.LAZY)
    private List<Requerimiento> requerimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoProceso", fetch = FetchType.LAZY)
    private List<Proceso> procesoList;

    public TipoProceso() {
    }

    public TipoProceso(Integer tipPrcCodigo) {
        this.tipPrcCodigo = tipPrcCodigo;
    }

    public TipoProceso(Integer tipPrcCodigo, String tipPrcNombre, boolean tipPrcEstado) {
        this.tipPrcCodigo = tipPrcCodigo;
        this.tipPrcNombre = tipPrcNombre;
        this.tipPrcEstado = tipPrcEstado;
    }

    public Integer getTipPrcCodigo() {
        return tipPrcCodigo;
    }

    public void setTipPrcCodigo(Integer tipPrcCodigo) {
        this.tipPrcCodigo = tipPrcCodigo;
    }

    public String getTipPrcNombre() {
        return tipPrcNombre;
    }

    public void setTipPrcNombre(String tipPrcNombre) {
        this.tipPrcNombre = tipPrcNombre;
    }

    public boolean getTipPrcEstado() {
        return tipPrcEstado;
    }

    public void setTipPrcEstado(boolean tipPrcEstado) {
        this.tipPrcEstado = tipPrcEstado;
    }

    public String getTipPrcDescripcion() {
        return tipPrcDescripcion;
    }

    public void setTipPrcDescripcion(String tipPrcDescripcion) {
        this.tipPrcDescripcion = tipPrcDescripcion;
    }

    public List<Requerimiento> getRequerimientoList() {
        return requerimientoList;
    }

    public void setRequerimientoList(List<Requerimiento> requerimientoList) {
        this.requerimientoList = requerimientoList;
    }

    public List<Proceso> getProcesoList() {
        return procesoList;
    }

    public void setProcesoList(List<Proceso> procesoList) {
        this.procesoList = procesoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipPrcCodigo != null ? tipPrcCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoProceso)) {
            return false;
        }
        TipoProceso other = (TipoProceso) object;
        if ((this.tipPrcCodigo == null && other.tipPrcCodigo != null) || (this.tipPrcCodigo != null && !this.tipPrcCodigo.equals(other.tipPrcCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.TipoProceso[ tipPrcCodigo=" + tipPrcCodigo + " ]";
    }
    
}
