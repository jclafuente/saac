/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "cargo", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"crg_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c"),
    @NamedQuery(name = "Cargo.findByCrgCodigo", query = "SELECT c FROM Cargo c WHERE c.crgCodigo = :crgCodigo"),
    @NamedQuery(name = "Cargo.findByCrgNombre", query = "SELECT c FROM Cargo c WHERE c.crgNombre = :crgNombre"),
    @NamedQuery(name = "Cargo.findByCrgDescripcion", query = "SELECT c FROM Cargo c WHERE c.crgDescripcion = :crgDescripcion"),
    @NamedQuery(name = "Cargo.findByCrgEstado", query = "SELECT c FROM Cargo c WHERE c.crgEstado = :crgEstado")})
public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "crg_codigo", nullable = false)
    private Integer crgCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "crg_nombre", nullable = false, length = 255)
    private String crgNombre;
    @Size(max = 255)
    @Column(name = "crg_descripcion", length = 255)
    private String crgDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crg_estado", nullable = false)
    private boolean crgEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargo", fetch = FetchType.LAZY)
    private List<PersonaCarrera> personaCarreraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargo", fetch = FetchType.LAZY)
    private List<PersonaFacultad> personaFacultadList;

    public Cargo() {
    }

    public Cargo(Integer crgCodigo) {
        this.crgCodigo = crgCodigo;
    }

    public Cargo(Integer crgCodigo, String crgNombre, boolean crgEstado) {
        this.crgCodigo = crgCodigo;
        this.crgNombre = crgNombre;
        this.crgEstado = crgEstado;
    }

    public Integer getCrgCodigo() {
        return crgCodigo;
    }

    public void setCrgCodigo(Integer crgCodigo) {
        this.crgCodigo = crgCodigo;
    }

    public String getCrgNombre() {
        return crgNombre;
    }

    public void setCrgNombre(String crgNombre) {
        this.crgNombre = crgNombre;
    }

    public String getCrgDescripcion() {
        return crgDescripcion;
    }

    public void setCrgDescripcion(String crgDescripcion) {
        this.crgDescripcion = crgDescripcion;
    }

    public boolean getCrgEstado() {
        return crgEstado;
    }

    public void setCrgEstado(boolean crgEstado) {
        this.crgEstado = crgEstado;
    }

    public List<PersonaCarrera> getPersonaCarreraList() {
        return personaCarreraList;
    }

    public void setPersonaCarreraList(List<PersonaCarrera> personaCarreraList) {
        this.personaCarreraList = personaCarreraList;
    }

    public List<PersonaFacultad> getPersonaFacultadList() {
        return personaFacultadList;
    }

    public void setPersonaFacultadList(List<PersonaFacultad> personaFacultadList) {
        this.personaFacultadList = personaFacultadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crgCodigo != null ? crgCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.crgCodigo == null && other.crgCodigo != null) || (this.crgCodigo != null && !this.crgCodigo.equals(other.crgCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Cargo[ crgCodigo=" + crgCodigo + " ]";
    }
    
}
