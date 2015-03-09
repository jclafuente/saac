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
@Table(name = "linea_investigacion", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"lin_inv_codigo"})})
@NamedQueries({
    @NamedQuery(name = "LineaInvestigacion.findAll", query = "SELECT l FROM LineaInvestigacion l"),
    @NamedQuery(name = "LineaInvestigacion.findByLinInvCodigo", query = "SELECT l FROM LineaInvestigacion l WHERE l.linInvCodigo = :linInvCodigo"),
    @NamedQuery(name = "LineaInvestigacion.findByLinInvNombre", query = "SELECT l FROM LineaInvestigacion l WHERE l.linInvNombre = :linInvNombre"),
    @NamedQuery(name = "LineaInvestigacion.findByLinInvDescripcion", query = "SELECT l FROM LineaInvestigacion l WHERE l.linInvDescripcion = :linInvDescripcion"),
    @NamedQuery(name = "LineaInvestigacion.findByLinInvEstado", query = "SELECT l FROM LineaInvestigacion l WHERE l.linInvEstado = :linInvEstado"),
    @NamedQuery(name = "LineaInvestigacion.buscarPorCarrera", query = "SELECT l FROM LineaInvestigacion l WHERE l.carrera = :carrera")})
public class LineaInvestigacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lin_inv_codigo", nullable = false)
    private Integer linInvCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "lin_inv_nombre", nullable = false, length = 255)
    private String linInvNombre;
    @Size(max = 255)
    @Column(name = "lin_inv_descripcion", length = 255)
    private String linInvDescripcion;
    @Basic(optional = false)
    @Column(name = "lin_inv_estado", nullable = false)
    private boolean linInvEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lineaInvestigacion", fetch = FetchType.LAZY)
    private List<AreaInvestigacion> areaInvestigacionList;
    @JoinColumn(name = "carrera", referencedColumnName = "car_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Carrera carrera;

    public LineaInvestigacion() {
    }

    public LineaInvestigacion(Integer linInvCodigo) {
        this.linInvCodigo = linInvCodigo;
    }

    public LineaInvestigacion(Integer linInvCodigo, String linInvNombre, boolean linInvEstado) {
        this.linInvCodigo = linInvCodigo;
        this.linInvNombre = linInvNombre;
        this.linInvEstado = linInvEstado;
    }

    public Integer getLinInvCodigo() {
        return linInvCodigo;
    }

    public void setLinInvCodigo(Integer linInvCodigo) {
        this.linInvCodigo = linInvCodigo;
    }

    public String getLinInvNombre() {
        return linInvNombre;
    }

    public void setLinInvNombre(String linInvNombre) {
        this.linInvNombre = linInvNombre;
    }

    public String getLinInvDescripcion() {
        return linInvDescripcion;
    }

    public void setLinInvDescripcion(String linInvDescripcion) {
        this.linInvDescripcion = linInvDescripcion;
    }

    public boolean getLinInvEstado() {
        return linInvEstado;
    }

    public void setLinInvEstado(boolean linInvEstado) {
        this.linInvEstado = linInvEstado;
    }

    public List<AreaInvestigacion> getAreaInvestigacionList() {
        return areaInvestigacionList;
    }

    public void setAreaInvestigacionList(List<AreaInvestigacion> areaInvestigacionList) {
        this.areaInvestigacionList = areaInvestigacionList;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (linInvCodigo != null ? linInvCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LineaInvestigacion)) {
            return false;
        }
        LineaInvestigacion other = (LineaInvestigacion) object;
        if ((this.linInvCodigo == null && other.linInvCodigo != null) || (this.linInvCodigo != null && !this.linInvCodigo.equals(other.linInvCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.LineaInvestigacion[ linInvCodigo=" + linInvCodigo + " ]";
    }
}
