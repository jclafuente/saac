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
@Table(name = "facultad", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"fac_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f"),
    @NamedQuery(name = "Facultad.findByFacCodigo", query = "SELECT f FROM Facultad f WHERE f.facCodigo = :facCodigo"),
    @NamedQuery(name = "Facultad.findByFacNombre", query = "SELECT f FROM Facultad f WHERE f.facNombre = :facNombre"),
    @NamedQuery(name = "Facultad.findByFacEstado", query = "SELECT f FROM Facultad f WHERE f.facEstado = :facEstado")})
public class Facultad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fac_codigo", nullable = false)
    private Integer facCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "fac_nombre", nullable = false, length = 255)
    private String facNombre;
    @Basic(optional = false)
    @Column(name = "fac_estado", nullable = false)
    private boolean facEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultad", fetch = FetchType.LAZY)
    private List<PersonaFacultad> personaFacultadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultad", fetch = FetchType.LAZY)
    private List<Carrera> carreraList;

    public Facultad() {
    }

    public Facultad(Integer facCodigo) {
        this.facCodigo = facCodigo;
    }

    public Facultad(Integer facCodigo, String facNombre, boolean facEstado) {
        this.facCodigo = facCodigo;
        this.facNombre = facNombre;
        this.facEstado = facEstado;
    }

    public Integer getFacCodigo() {
        return facCodigo;
    }

    public void setFacCodigo(Integer facCodigo) {
        this.facCodigo = facCodigo;
    }

    public String getFacNombre() {
        return facNombre;
    }

    public void setFacNombre(String facNombre) {
        this.facNombre = facNombre;
    }

    public boolean getFacEstado() {
        return facEstado;
    }

    public void setFacEstado(boolean facEstado) {
        this.facEstado = facEstado;
    }

    public List<PersonaFacultad> getPersonaFacultadList() {
        return personaFacultadList;
    }

    public void setPersonaFacultadList(List<PersonaFacultad> personaFacultadList) {
        this.personaFacultadList = personaFacultadList;
    }

    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facCodigo != null ? facCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.facCodigo == null && other.facCodigo != null) || (this.facCodigo != null && !this.facCodigo.equals(other.facCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Facultad[ facCodigo=" + facCodigo + " ]";
    }
    
}
