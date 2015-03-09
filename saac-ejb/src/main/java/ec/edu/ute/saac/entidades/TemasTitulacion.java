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
@Table(name = "temas_titulacion", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tem_tit_codigo"})})
@NamedQueries({
    @NamedQuery(name = "TemasTitulacion.findAll", query = "SELECT t FROM TemasTitulacion t"),
    @NamedQuery(name = "TemasTitulacion.findByTemTitCodigo", query = "SELECT t FROM TemasTitulacion t WHERE t.temTitCodigo = :temTitCodigo"),
    @NamedQuery(name = "TemasTitulacion.findByTemTitNombre", query = "SELECT t FROM TemasTitulacion t WHERE t.temTitNombre = :temTitNombre"),
    @NamedQuery(name = "TemasTitulacion.findByTemTitDescripcion", query = "SELECT t FROM TemasTitulacion t WHERE t.temTitDescripcion = :temTitDescripcion"),
    @NamedQuery(name = "TemasTitulacion.findByTemTitEstado", query = "SELECT t FROM TemasTitulacion t WHERE t.temTitEstado = :temTitEstado")})
public class TemasTitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tem_tit_codigo", nullable = false)
    private Integer temTitCodigo;
    @Basic(optional = false)
    @Column(name = "tem_tit_nombre", nullable = false)
    private String temTitNombre;
    @Size(max = 255)
    @Column(name = "tem_tit_descripcion", length = 255)
    private String temTitDescripcion;
    @Basic(optional = false)
    @Column(name = "tem_tit_estado", nullable = false)
    private String temTitEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "temInvCodigo", fetch = FetchType.LAZY)
    private List<SeleccionTema> seleccionTemaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "temasTitulacion", fetch = FetchType.LAZY)
    private List<Proceso> procesoList;
    @JoinColumn(name = "per_codigo", referencedColumnName = "per_codigo", nullable = true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona perCodigo;
    @JoinColumn(name = "area_investigacion", referencedColumnName = "are_inv_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AreaInvestigacion areaInvestigacion;

    public TemasTitulacion() {
    }

    public TemasTitulacion(Integer temTitCodigo) {
        this.temTitCodigo = temTitCodigo;
    }

    public TemasTitulacion(Integer temTitCodigo, String temTitNombre, String temTitEstado) {
        this.temTitCodigo = temTitCodigo;
        this.temTitNombre = temTitNombre;
        this.temTitEstado = temTitEstado;
    }

    public Integer getTemTitCodigo() {
        return temTitCodigo;
    }

    public void setTemTitCodigo(Integer temTitCodigo) {
        this.temTitCodigo = temTitCodigo;
    }

    public String getTemTitNombre() {
        return temTitNombre;
    }

    public void setTemTitNombre(String temTitNombre) {
        this.temTitNombre = temTitNombre;
    }

    public String getTemTitDescripcion() {
        return temTitDescripcion;
    }

    public void setTemTitDescripcion(String temTitDescripcion) {
        this.temTitDescripcion = temTitDescripcion;
    }

    public String getTemTitEstado() {
        return temTitEstado;
    }

    public void setTemTitEstado(String temTitEstado) {
        this.temTitEstado = temTitEstado;
    }

    public List<SeleccionTema> getSeleccionTemaList() {
        return seleccionTemaList;
    }

    public void setSeleccionTemaList(List<SeleccionTema> seleccionTemaList) {
        this.seleccionTemaList = seleccionTemaList;
    }

    public List<Proceso> getProcesoList() {
        return procesoList;
    }

    public void setProcesoList(List<Proceso> procesoList) {
        this.procesoList = procesoList;
    }

	public Persona getPerCodigo() {
		return perCodigo;
	}

	public void setPerCodigo(Persona perCodigo) {
		this.perCodigo = perCodigo;
	}

	public AreaInvestigacion getAreaInvestigacion() {
        return areaInvestigacion;
    }

    public void setAreaInvestigacion(AreaInvestigacion areaInvestigacion) {
        this.areaInvestigacion = areaInvestigacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (temTitCodigo != null ? temTitCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TemasTitulacion)) {
            return false;
        }
        TemasTitulacion other = (TemasTitulacion) object;
        if ((this.temTitCodigo == null && other.temTitCodigo != null) || (this.temTitCodigo != null && !this.temTitCodigo.equals(other.temTitCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.TemasTitulacion[ temTitCodigo=" + temTitCodigo + " ]";
    }
    
}
