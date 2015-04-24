/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "curso_titulacion", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cur_tit_codigo"})})
@NamedQueries({
    @NamedQuery(name = "CursoTitulacion.findAll", query = "SELECT c FROM CursoTitulacion c"),
    @NamedQuery(name = "CursoTitulacion.findByCurTitCodigo", query = "SELECT c FROM CursoTitulacion c WHERE c.curTitCodigo = :curTitCodigo")})
public class CursoTitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cur_tit_codigo", nullable = false)
    private Integer curTitCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "cur_tit_nombre", nullable = false, length = 255)
    private String curTitNombre;
    @JoinColumn(name = "persona", referencedColumnName = "per_codigo",nullable=true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY )
    private Persona persona;
    @JoinColumn(name = "periodos", referencedColumnName = "prd_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Periodos periodos;
    @OneToMany(mappedBy = "cursoTitulacion", fetch = FetchType.LAZY)
    private List<Proceso> procesoList;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "cur_tit_estado", nullable = false, length = 255)
    private String curTitEstado;

    public CursoTitulacion() {
    }

    public CursoTitulacion(Integer curTitCodigo) {
        this.curTitCodigo = curTitCodigo;
    }

    public Integer getCurTitCodigo() {
        return curTitCodigo;
    }

    public void setCurTitCodigo(Integer curTitCodigo) {
        this.curTitCodigo = curTitCodigo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Periodos getPeriodos() {
        return periodos;
    }

    public void setPeriodos(Periodos periodos) {
        this.periodos = periodos;
    }

    public List<Proceso> getProcesoList() {
        return procesoList;
    }

    public void setProcesoList(List<Proceso> procesoList) {
        this.procesoList = procesoList;
    }
    
    public String getCurTitNombre() {
		return curTitNombre;
	}

	public void setCurTitNombre(String curTitNombre) {
		this.curTitNombre = curTitNombre;
	}


	public String getCurTitEstado() {
		return curTitEstado;
	}

	public void setCurTitEstado(String curTitEstado) {
		this.curTitEstado = curTitEstado;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (curTitCodigo != null ? curTitCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CursoTitulacion)) {
            return false;
        }
        CursoTitulacion other = (CursoTitulacion) object;
        if ((this.curTitCodigo == null && other.curTitCodigo != null) || (this.curTitCodigo != null && !this.curTitCodigo.equals(other.curTitCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.CursoTitulacion[ curTitEstCodigo=" + curTitCodigo + " ]";
    }
    
}
