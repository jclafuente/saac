package ec.edu.ute.saac.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name = "componente", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"com_codigo"})})
public class Componente implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "com_codigo", nullable = false)
    private Integer comCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 1000)
    @Column(name = "com_nombre", nullable = false, length = 1000)
    private String comNombre;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "com_descripcion", nullable = false, length = 255)
    private String comDescripcion;
    @Basic(optional = false)
    @Column(name = "com_estado", nullable = false)
    private boolean comEstado;
    
    public Componente(){
    	
    }

	public Integer getComCodigo() {
		return comCodigo;
	}

	public void setComCodigo(Integer comCodigo) {
		this.comCodigo = comCodigo;
	}

	public String getComNombre() {
		return comNombre;
	}

	public void setComNombre(String comNombre) {
		this.comNombre = comNombre;
	}

	public String getComDescripcion() {
		return comDescripcion;
	}

	public void setComDescripcion(String comDescripcion) {
		this.comDescripcion = comDescripcion;
	}

	public boolean isComEstado() {
		return comEstado;
	}

	public void setComEstado(boolean comEstado) {
		this.comEstado = comEstado;
	}
    
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (comCodigo != null ? comCodigo.hashCode() : 0);
        return hash;
    }

	
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Componente)) {
            return false;
        }
        Componente other = (Componente) object;
        if ((this.comCodigo == null && other.comCodigo != null) || (this.comCodigo != null && !this.comCodigo.equals(other.comCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Componente[ comCodigo=" + comCodigo + " ]";
    }
	
	

}
