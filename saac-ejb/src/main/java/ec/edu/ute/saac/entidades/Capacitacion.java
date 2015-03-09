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
@Table(name = "capacitacion", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cur_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Capacitacion.findAll", query = "SELECT c FROM Capacitacion c"),
    @NamedQuery(name = "Capacitacion.findByCurCodigo", query = "SELECT c FROM Capacitacion c WHERE c.curCodigo = :curCodigo"),
    @NamedQuery(name = "Capacitacion.findByCapInstitucion", query = "SELECT c FROM Capacitacion c WHERE c.capInstitucion = :capInstitucion"),
    @NamedQuery(name = "Capacitacion.findByCapNombre", query = "SELECT c FROM Capacitacion c WHERE c.capNombre = :capNombre"),
    @NamedQuery(name = "Capacitacion.findByCapDiasHoras", query = "SELECT c FROM Capacitacion c WHERE c.capDiasHoras = :capDiasHoras"),
    @NamedQuery(name = "Capacitacion.findByCapFechaInicio", query = "SELECT c FROM Capacitacion c WHERE c.capFechaInicio = :capFechaInicio"),
    @NamedQuery(name = "Capacitacion.findByCapFechaFin", query = "SELECT c FROM Capacitacion c WHERE c.capFechaFin = :capFechaFin")})
public class Capacitacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cur_codigo", nullable = false)
    private Integer curCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 500)
    @Column(name = "cap_institucion", nullable = false, length = 500)
    private String capInstitucion;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "cap_nombre", nullable = false, length = 255)
    private String capNombre;
    @Basic(optional = false)
    @Column(name = "cap_dias_horas", nullable = false)
    private int capDiasHoras;
    @Basic(optional = false)
    @Column(name = "cap_fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date capFechaInicio;
    @Basic(optional = false)
    @Column(name = "cap_fecha_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date capFechaFin;
    @JoinColumn(name = "persona", referencedColumnName = "per_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;
    @JoinColumn(name = "catalogo_area_estudio", referencedColumnName = "cat_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Catalogo catalogoAreaEstudio;
    @JoinColumn(name = "catalogo_tipo_certificado", referencedColumnName = "cat_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Catalogo catalogoTipoCertificado;
    @JoinColumn(name = "catalogo_tipo_evento", referencedColumnName = "cat_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Catalogo catalogoTipoEvento;

    public Capacitacion() {
    }

    public Capacitacion(Integer curCodigo) {
        this.curCodigo = curCodigo;
    }

    public Capacitacion(Integer curCodigo, String capInstitucion, String capNombre, int capDiasHoras, Date capFechaInicio, Date capFechaFin) {
        this.curCodigo = curCodigo;
        this.capInstitucion = capInstitucion;
        this.capNombre = capNombre;
        this.capDiasHoras = capDiasHoras;
        this.capFechaInicio = capFechaInicio;
        this.capFechaFin = capFechaFin;
    }

    public Integer getCurCodigo() {
        return curCodigo;
    }

    public void setCurCodigo(Integer curCodigo) {
        this.curCodigo = curCodigo;
    }

    public String getCapInstitucion() {
        return capInstitucion;
    }

    public void setCapInstitucion(String capInstitucion) {
        this.capInstitucion = capInstitucion;
    }

    public String getCapNombre() {
        return capNombre;
    }

    public void setCapNombre(String capNombre) {
        this.capNombre = capNombre;
    }

    public int getCapDiasHoras() {
        return capDiasHoras;
    }

    public void setCapDiasHoras(int capDiasHoras) {
        this.capDiasHoras = capDiasHoras;
    }

    public Date getCapFechaInicio() {
        return capFechaInicio;
    }

    public void setCapFechaInicio(Date capFechaInicio) {
        this.capFechaInicio = capFechaInicio;
    }

    public Date getCapFechaFin() {
        return capFechaFin;
    }

    public void setCapFechaFin(Date capFechaFin) {
        this.capFechaFin = capFechaFin;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Catalogo getCatalogoAreaEstudio() {
        return catalogoAreaEstudio;
    }

    public void setCatalogoAreaEstudio(Catalogo catalogoAreaEstudio) {
        this.catalogoAreaEstudio = catalogoAreaEstudio;
    }

    public Catalogo getCatalogoTipoCertificado() {
        return catalogoTipoCertificado;
    }

    public void setCatalogoTipoCertificado(Catalogo catalogoTipoCertificado) {
        this.catalogoTipoCertificado = catalogoTipoCertificado;
    }

    public Catalogo getCatalogoTipoEvento() {
        return catalogoTipoEvento;
    }

    public void setCatalogoTipoEvento(Catalogo catalogoTipoEvento) {
        this.catalogoTipoEvento = catalogoTipoEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (curCodigo != null ? curCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Capacitacion)) {
            return false;
        }
        Capacitacion other = (Capacitacion) object;
        if ((this.curCodigo == null && other.curCodigo != null) || (this.curCodigo != null && !this.curCodigo.equals(other.curCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Capacitacion[ curCodigo=" + curCodigo + " ]";
    }
    
}
