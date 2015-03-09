/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "persona_facultad", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"per_fac_codigo"})})
@NamedQueries({
    @NamedQuery(name = "PersonaFacultad.findAll", query = "SELECT p FROM PersonaFacultad p"),
    @NamedQuery(name = "PersonaFacultad.findByPerFacCodigo", query = "SELECT p FROM PersonaFacultad p WHERE p.perFacCodigo = :perFacCodigo")})
public class PersonaFacultad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "per_fac_codigo", nullable = false)
    private Integer perFacCodigo;
    @JoinColumn(name = "persona", referencedColumnName = "per_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;
    @JoinColumn(name = "facultad", referencedColumnName = "fac_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Facultad facultad;
    @JoinColumn(name = "cargo", referencedColumnName = "crg_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cargo cargo;

    public PersonaFacultad() {
    }

    public PersonaFacultad(Integer perFacCodigo) {
        this.perFacCodigo = perFacCodigo;
    }

    public Integer getPerFacCodigo() {
        return perFacCodigo;
    }

    public void setPerFacCodigo(Integer perFacCodigo) {
        this.perFacCodigo = perFacCodigo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perFacCodigo != null ? perFacCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PersonaFacultad)) {
            return false;
        }
        PersonaFacultad other = (PersonaFacultad) object;
        if ((this.perFacCodigo == null && other.perFacCodigo != null) || (this.perFacCodigo != null && !this.perFacCodigo.equals(other.perFacCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.PersonaFacultad[ perFacCodigo=" + perFacCodigo + " ]";
    }
    
}
