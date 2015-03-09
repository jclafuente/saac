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
@Table(name = "requerimiento", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"req_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Requerimiento.findAll", query = "SELECT r FROM Requerimiento r"),
    @NamedQuery(name = "Requerimiento.findByReqCodigo", query = "SELECT r FROM Requerimiento r WHERE r.reqCodigo = :reqCodigo"),
    @NamedQuery(name = "Requerimiento.findByReqNombre", query = "SELECT r FROM Requerimiento r WHERE r.reqNombre = :reqNombre"),
    @NamedQuery(name = "Requerimiento.findByReqDescripcion", query = "SELECT r FROM Requerimiento r WHERE r.reqDescripcion = :reqDescripcion"),
    @NamedQuery(name = "Requerimiento.findByReqEstado", query = "SELECT r FROM Requerimiento r WHERE r.reqEstado = :reqEstado")})
public class Requerimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "req_codigo", nullable = false)
    private Integer reqCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 1000)
    @Column(name = "req_nombre", nullable = false, length = 1000)
    private String reqNombre;
    @Basic(optional = false)
    @Size(min = 1, max = 1000)
    @Column(name = "req_descripcion", nullable = false, length = 1000)
    private String reqDescripcion;
    @Column(name = "req_estado")
    private Boolean reqEstado;
    @JoinColumn(name = "tipo_proceso", referencedColumnName = "tip_prc_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoProceso tipoProceso;

    public Requerimiento() {
    }

    public Requerimiento(Integer reqCodigo) {
        this.reqCodigo = reqCodigo;
    }

    public Requerimiento(Integer reqCodigo, String reqNombre, String reqDescripcion) {
        this.reqCodigo = reqCodigo;
        this.reqNombre = reqNombre;
        this.reqDescripcion = reqDescripcion;
    }

    public Integer getReqCodigo() {
        return reqCodigo;
    }

    public void setReqCodigo(Integer reqCodigo) {
        this.reqCodigo = reqCodigo;
    }

    public String getReqNombre() {
        return reqNombre;
    }

    public void setReqNombre(String reqNombre) {
        this.reqNombre = reqNombre;
    }

    public String getReqDescripcion() {
        return reqDescripcion;
    }

    public void setReqDescripcion(String reqDescripcion) {
        this.reqDescripcion = reqDescripcion;
    }

    public Boolean getReqEstado() {
        return reqEstado;
    }

    public void setReqEstado(Boolean reqEstado) {
        this.reqEstado = reqEstado;
    }

    public TipoProceso getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(TipoProceso tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reqCodigo != null ? reqCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Requerimiento)) {
            return false;
        }
        Requerimiento other = (Requerimiento) object;
        if ((this.reqCodigo == null && other.reqCodigo != null) || (this.reqCodigo != null && !this.reqCodigo.equals(other.reqCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Requerimiento[ reqCodigo=" + reqCodigo + " ]";
    }
    
}
