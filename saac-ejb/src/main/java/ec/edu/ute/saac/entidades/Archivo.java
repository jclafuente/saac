/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "archivo", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"arc_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Archivo.findAll", query = "SELECT a FROM Archivo a"),
    @NamedQuery(name = "Archivo.findByArcCodigo", query = "SELECT a FROM Archivo a WHERE a.arcCodigo = :arcCodigo"),
    @NamedQuery(name = "Archivo.findByArcUrl", query = "SELECT a FROM Archivo a WHERE a.arcUrl = :arcUrl"),
    @NamedQuery(name = "Archivo.findByArcDescripcion", query = "SELECT a FROM Archivo a WHERE a.arcDescripcion = :arcDescripcion"),
    @NamedQuery(name = "Archivo.findByArcEstado", query = "SELECT a FROM Archivo a WHERE a.arcEstado = :arcEstado")})
public class Archivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "arc_codigo", nullable = false)
    private Integer arcCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 500)
    @Column(name = "arc_url", nullable = false, length = 500)
    private String arcUrl;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "arc_descripcion", nullable = false, length = 255)
    private String arcDescripcion;
    @Basic(optional = false)
    @Column(name = "arc_estado", nullable = false)
    private boolean arcEstado;
    @JoinColumn(name = "proceso", referencedColumnName = "prc_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proceso proceso;

    public Archivo() {
    }

    public Archivo(Integer arcCodigo) {
        this.arcCodigo = arcCodigo;
    }

    public Archivo(Integer arcCodigo, String arcUrl, String arcDescripcion, boolean arcEstado) {
        this.arcCodigo = arcCodigo;
        this.arcUrl = arcUrl;
        this.arcDescripcion = arcDescripcion;
        this.arcEstado = arcEstado;
    }

    public Integer getArcCodigo() {
        return arcCodigo;
    }

    public void setArcCodigo(Integer arcCodigo) {
        this.arcCodigo = arcCodigo;
    }

    public String getArcUrl() {
        return arcUrl;
    }

    public void setArcUrl(String arcUrl) {
        this.arcUrl = arcUrl;
    }

    public String getArcDescripcion() {
        return arcDescripcion;
    }

    public void setArcDescripcion(String arcDescripcion) {
        this.arcDescripcion = arcDescripcion;
    }

    public boolean getArcEstado() {
        return arcEstado;
    }

    public void setArcEstado(boolean arcEstado) {
        this.arcEstado = arcEstado;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arcCodigo != null ? arcCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Archivo)) {
            return false;
        }
        Archivo other = (Archivo) object;
        if ((this.arcCodigo == null && other.arcCodigo != null) || (this.arcCodigo != null && !this.arcCodigo.equals(other.arcCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Archivo[ arcCodigo=" + arcCodigo + " ]";
    }
    
}
