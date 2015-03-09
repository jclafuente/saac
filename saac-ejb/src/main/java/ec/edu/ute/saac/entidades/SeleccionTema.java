/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "seleccion_tema", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"sel_tem_codigo"})})
@NamedQueries({
    @NamedQuery(name = "SeleccionTema.findAll", query = "SELECT s FROM SeleccionTema s"),
    @NamedQuery(name = "SeleccionTema.findBySelTemCodigo", query = "SELECT s FROM SeleccionTema s WHERE s.selTemCodigo = :selTemCodigo"),
    @NamedQuery(name = "SeleccionTema.findBySelTemFecha", query = "SELECT s FROM SeleccionTema s WHERE s.selTemFecha = :selTemFecha"),
    @NamedQuery(name = "SeleccionTema.findBySelTemAprobacion", query = "SELECT s FROM SeleccionTema s WHERE s.selTemAprobacion = :selTemAprobacion")})
public class SeleccionTema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sel_tem_codigo", nullable = false)
    private Integer selTemCodigo;
    @Basic(optional = false)
    @Column(name = "sel_tem_fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date selTemFecha;
    @Basic(optional = false)
    @Column(name = "sel_tem_aprobacion", nullable = false)
    private boolean selTemAprobacion;
    @JoinColumn(name = "tem_inv_codigo", referencedColumnName = "tem_tit_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TemasTitulacion temInvCodigo;
    @JoinColumn(name = "per_codigo", referencedColumnName = "per_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona perCodigo;

    public SeleccionTema() {
    }

    public SeleccionTema(Integer selTemCodigo) {
        this.selTemCodigo = selTemCodigo;
    }

    public SeleccionTema(Integer selTemCodigo, Date selTemFecha, boolean selTemAprobacion) {
        this.selTemCodigo = selTemCodigo;
        this.selTemFecha = selTemFecha;
        this.selTemAprobacion = selTemAprobacion;
    }

    public Integer getSelTemCodigo() {
        return selTemCodigo;
    }

    public void setSelTemCodigo(Integer selTemCodigo) {
        this.selTemCodigo = selTemCodigo;
    }

    public Date getSelTemFecha() {
        return selTemFecha;
    }

    public void setSelTemFecha(Date selTemFecha) {
        this.selTemFecha = selTemFecha;
    }

    public boolean getSelTemAprobacion() {
        return selTemAprobacion;
    }

    public void setSelTemAprobacion(boolean selTemAprobacion) {
        this.selTemAprobacion = selTemAprobacion;
    }

    public TemasTitulacion getTemInvCodigo() {
        return temInvCodigo;
    }

    public void setTemInvCodigo(TemasTitulacion temInvCodigo) {
        this.temInvCodigo = temInvCodigo;
    }

    public Persona getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Persona perCodigo) {
        this.perCodigo = perCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (selTemCodigo != null ? selTemCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SeleccionTema)) {
            return false;
        }
        SeleccionTema other = (SeleccionTema) object;
        if ((this.selTemCodigo == null && other.selTemCodigo != null) || (this.selTemCodigo != null && !this.selTemCodigo.equals(other.selTemCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.SeleccionTema[ selTemCodigo=" + selTemCodigo + " ]";
    }
    
}
