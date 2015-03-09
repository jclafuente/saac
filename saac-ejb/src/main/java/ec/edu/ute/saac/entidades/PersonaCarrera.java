/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "persona_carrera", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"per_car_codigo"})})
@NamedQueries({
    @NamedQuery(name = "PersonaCarrera.findAll", query = "SELECT p FROM PersonaCarrera p"),
    @NamedQuery(name = "PersonaCarrera.findByPerCarCodigo", query = "SELECT p FROM PersonaCarrera p WHERE p.perCarCodigo = :perCarCodigo"),
    @NamedQuery(name = "PersonaCarrera.findByPerCarFecha", query = "SELECT p FROM PersonaCarrera p WHERE p.perCarFecha = :perCarFecha")})
public class PersonaCarrera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "per_car_codigo", nullable = false)
    private Integer perCarCodigo;
    @Basic(optional = false)
    @Column(name = "per_car_fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date perCarFecha;
    @JoinColumn(name = "persona", referencedColumnName = "per_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;
    @JoinColumn(name = "carrera", referencedColumnName = "car_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Carrera carrera;
    @JoinColumn(name = "cargo", referencedColumnName = "crg_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cargo cargo;

    public PersonaCarrera() {
    }

    public PersonaCarrera(Integer perCarCodigo) {
        this.perCarCodigo = perCarCodigo;
    }

    public PersonaCarrera(Integer perCarCodigo, Date perCarFecha) {
        this.perCarCodigo = perCarCodigo;
        this.perCarFecha = perCarFecha;
    }

    public Integer getPerCarCodigo() {
        return perCarCodigo;
    }

    public void setPerCarCodigo(Integer perCarCodigo) {
        this.perCarCodigo = perCarCodigo;
    }

    public Date getPerCarFecha() {
        return perCarFecha;
    }

    public void setPerCarFecha(Date perCarFecha) {
        this.perCarFecha = perCarFecha;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
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
        hash += (perCarCodigo != null ? perCarCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PersonaCarrera)) {
            return false;
        }
        PersonaCarrera other = (PersonaCarrera) object;
        if ((this.perCarCodigo == null && other.perCarCodigo != null) || (this.perCarCodigo != null && !this.perCarCodigo.equals(other.perCarCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.PersonaCarrera[ perCarCodigo=" + perCarCodigo + " ]";
    }
    
}
