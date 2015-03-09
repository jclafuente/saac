/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "experiencia", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"exp_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Experiencia.findAll", query = "SELECT e FROM Experiencia e"),
    @NamedQuery(name = "Experiencia.findByExpCodigo", query = "SELECT e FROM Experiencia e WHERE e.expCodigo = :expCodigo"),
    @NamedQuery(name = "Experiencia.findByExpInstitucion", query = "SELECT e FROM Experiencia e WHERE e.expInstitucion = :expInstitucion"),
    @NamedQuery(name = "Experiencia.findByExpPuesto", query = "SELECT e FROM Experiencia e WHERE e.expPuesto = :expPuesto"),
    @NamedQuery(name = "Experiencia.findByExpFechaDesde", query = "SELECT e FROM Experiencia e WHERE e.expFechaDesde = :expFechaDesde"),
    @NamedQuery(name = "Experiencia.findByExpFechaHasta", query = "SELECT e FROM Experiencia e WHERE e.expFechaHasta = :expFechaHasta"),
    @NamedQuery(name = "Experiencia.findByExpActividades", query = "SELECT e FROM Experiencia e WHERE e.expActividades = :expActividades")})
public class Experiencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "exp_codigo", nullable = false)
    private Integer expCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "exp_institucion", nullable = false, length = 255)
    private String expInstitucion;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "exp_puesto", nullable = false, length = 255)
    private String expPuesto;
    @Basic(optional = false)
    @Column(name = "exp_fecha_desde", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expFechaDesde;
    @Basic(optional = false)
    @Column(name = "exp_fecha_hasta", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expFechaHasta;
    @Size(max = 500)
    @Column(name = "exp_actividades", length = 500)
    private String expActividades;
    @JoinColumn(name = "persona", referencedColumnName = "per_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;
    @JoinColumn(name = "catalogo_area_trabajo", referencedColumnName = "cat_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Catalogo catalogoAreaTrabajo;

    public Experiencia() {
    }

    public Experiencia(Integer expCodigo) {
        this.expCodigo = expCodigo;
    }

    public Experiencia(Integer expCodigo, String expInstitucion, String expPuesto, Date expFechaDesde, Date expFechaHasta) {
        this.expCodigo = expCodigo;
        this.expInstitucion = expInstitucion;
        this.expPuesto = expPuesto;
        this.expFechaDesde = expFechaDesde;
        this.expFechaHasta = expFechaHasta;
    }

    public Integer getExpCodigo() {
        return expCodigo;
    }

    public void setExpCodigo(Integer expCodigo) {
        this.expCodigo = expCodigo;
    }

    public String getExpInstitucion() {
        return expInstitucion;
    }

    public void setExpInstitucion(String expInstitucion) {
        this.expInstitucion = expInstitucion;
    }

    public String getExpPuesto() {
        return expPuesto;
    }

    public void setExpPuesto(String expPuesto) {
        this.expPuesto = expPuesto;
    }

    public Date getExpFechaDesde() {
        return expFechaDesde;
    }

    public void setExpFechaDesde(Date expFechaDesde) {
        this.expFechaDesde = expFechaDesde;
    }

    public Date getExpFechaHasta() {
        return expFechaHasta;
    }

    public void setExpFechaHasta(Date expFechaHasta) {
        this.expFechaHasta = expFechaHasta;
    }

    public String getExpActividades() {
        return expActividades;
    }

    public void setExpActividades(String expActividades) {
        this.expActividades = expActividades;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Catalogo getCatalogoAreaTrabajo() {
        return catalogoAreaTrabajo;
    }

    public void setCatalogoAreaTrabajo(Catalogo catalogoAreaTrabajo) {
        this.catalogoAreaTrabajo = catalogoAreaTrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (expCodigo != null ? expCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Experiencia)) {
            return false;
        }
        Experiencia other = (Experiencia) object;
        if ((this.expCodigo == null && other.expCodigo != null) || (this.expCodigo != null && !this.expCodigo.equals(other.expCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Experiencia[ expCodigo=" + expCodigo + " ]";
    }
    
}
