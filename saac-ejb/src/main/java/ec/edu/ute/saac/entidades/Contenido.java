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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contenido", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cnt_codigo"})})
public class Contenido implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cnt_codigo", nullable = false)
    private Integer cntCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "cnt_nombre", nullable = false, length = 255)
    private String cntNombre;
    @Basic(optional = false)
    @Column(name = "cnt_estado", nullable = false)
    private boolean cntEstado;
    @JoinColumn(name = "proceso", referencedColumnName = "prc_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proceso proceso;
    @JoinColumn(name = "componente", referencedColumnName = "com_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Componente componente;
    
    public Contenido(){
    	
    }

	public Integer getCntCodigo() {
		return cntCodigo;
	}

	public void setCntCodigo(Integer cntCodigo) {
		this.cntCodigo = cntCodigo;
	}

	public String getCntNombre() {
		return cntNombre;
	}

	public void setCntNombre(String cntNombre) {
		this.cntNombre = cntNombre;
	}

	public boolean isCntEstado() {
		return cntEstado;
	}

	public void setCntEstado(boolean cntEstado) {
		this.cntEstado = cntEstado;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}
    
	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (cntCodigo != null ? cntCodigo.hashCode() : 0);
        return hash;
    }

	
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Contenido)) {
            return false;
        }
        Contenido other = (Contenido) object;
        if ((this.cntCodigo == null && other.cntCodigo != null) || (this.cntCodigo != null && !this.cntCodigo.equals(other.cntCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Contenido[ cntCodigo=" + cntCodigo + " ]";
    }

}
