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
@Table(name = "periodos", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"prd_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Periodos.findAll", query = "SELECT p FROM Periodos p"),
    @NamedQuery(name = "Periodos.findByPrdCodigo", query = "SELECT p FROM Periodos p WHERE p.prdCodigo = :prdCodigo"),
    @NamedQuery(name = "Periodos.findByPrdFechaInicio", query = "SELECT p FROM Periodos p WHERE p.prdFechaInicio = :prdFechaInicio"),
    @NamedQuery(name = "Periodos.findByPrdFechaFinal", query = "SELECT p FROM Periodos p WHERE p.prdFechaFinal = :prdFechaFinal"),
    @NamedQuery(name = "Periodos.findByPrdEstado", query = "SELECT p FROM Periodos p WHERE p.prdEstado = :prdEstado")})
public class Periodos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prd_codigo", nullable = false)
    private Integer prdCodigo;
    @Basic(optional = false)
    @Column(name = "prd_fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date prdFechaInicio;
    @Basic(optional = false)
    @Column(name = "prd_fecha_final", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date prdFechaFinal;
    @Basic(optional = false)
    @Column(name = "prd_estado", nullable = false)
    private boolean prdEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodos", fetch = FetchType.LAZY)
    private List<CursoTitulacion> cursoTitulacionList;

    public Periodos() {
    }

    public Periodos(Integer prdCodigo) {
        this.prdCodigo = prdCodigo;
    }

    public Periodos(Integer prdCodigo, Date prdFechaInicio, Date prdFechaFinal, boolean prdEstado) {
        this.prdCodigo = prdCodigo;
        this.prdFechaInicio = prdFechaInicio;
        this.prdFechaFinal = prdFechaFinal;
        this.prdEstado = prdEstado;
    }

    public Integer getPrdCodigo() {
        return prdCodigo;
    }

    public void setPrdCodigo(Integer prdCodigo) {
        this.prdCodigo = prdCodigo;
    }

    public Date getPrdFechaInicio() {
        return prdFechaInicio;
    }

    public void setPrdFechaInicio(Date prdFechaInicio) {
        this.prdFechaInicio = prdFechaInicio;
    }

    public Date getPrdFechaFinal() {
        return prdFechaFinal;
    }

    public void setPrdFechaFinal(Date prdFechaFinal) {
        this.prdFechaFinal = prdFechaFinal;
    }

    public boolean getPrdEstado() {
        return prdEstado;
    }

    public void setPrdEstado(boolean prdEstado) {
        this.prdEstado = prdEstado;
    }

    public List<CursoTitulacion> getCursoTitulacionList() {
        return cursoTitulacionList;
    }

    public void setCursoTitulacionList(List<CursoTitulacion> cursoTitulacionList) {
        this.cursoTitulacionList = cursoTitulacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prdCodigo != null ? prdCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Periodos)) {
            return false;
        }
        Periodos other = (Periodos) object;
        if ((this.prdCodigo == null && other.prdCodigo != null) || (this.prdCodigo != null && !this.prdCodigo.equals(other.prdCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Periodos[ prdCodigo=" + prdCodigo + " ]";
    }
    
}
