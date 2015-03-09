package ec.edu.ute.saac.entidades;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "curso_titulacion_estudiante", catalog = "saac", schema = "", 
uniqueConstraints = { @UniqueConstraint(columnNames = { "cur_tit_est_codigo" }) })
public class CursoTitulacionEstudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "cur_tit_est_codigo", nullable = false)
	private Integer curTitEstCodigo;
    @JoinColumn(name = "seleccion_tema", referencedColumnName = "sel_tem_codigo",nullable=true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SeleccionTema seleccionTema;
    @JoinColumn(name = "curso_titulacion", referencedColumnName = "cur_tit_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CursoTitulacion cursoTitulacion;
    
    public CursoTitulacionEstudiante(){
    	  	
    }
    
    public CursoTitulacionEstudiante(Integer curTitEstCodigo) {
        this.curTitEstCodigo = curTitEstCodigo;
    }
    
	public Integer getCurTitEstCodigo() {
		return curTitEstCodigo;
	}
	public void setCurTitEstCodigo(Integer curTitEstCodigo) {
		this.curTitEstCodigo = curTitEstCodigo;
	}
	public SeleccionTema getSeleccionTema() {
		return seleccionTema;
	}
	public void setSeleccionTema(SeleccionTema seleccionTema) {
		this.seleccionTema = seleccionTema;
	}
	public CursoTitulacion getCursoTitulacion() {
		return cursoTitulacion;
	}
	public void setCursoTitulacion(CursoTitulacion cursoTitulacion) {
		this.cursoTitulacion = cursoTitulacion;
	}
    

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (curTitEstCodigo != null ? curTitEstCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CursoTitulacionEstudiante)) {
            return false;
        }
        CursoTitulacionEstudiante other = (CursoTitulacionEstudiante) object;
        if ((this.curTitEstCodigo == null && other.curTitEstCodigo != null) || (this.curTitEstCodigo != null && !this.curTitEstCodigo.equals(other.curTitEstCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.CursoTitulacionEstudiante[ curTitEstCodigo=" + curTitEstCodigo + " ]";
    }
	

}
