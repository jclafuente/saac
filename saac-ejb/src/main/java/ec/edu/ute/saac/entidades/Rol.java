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
@Table(name = "rol", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"rol_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByRolCodigo", query = "SELECT r FROM Rol r WHERE r.rolCodigo = :rolCodigo"),
    @NamedQuery(name = "Rol.findByRolNombre", query = "SELECT r FROM Rol r WHERE r.rolNombre = :rolNombre"),
    @NamedQuery(name = "Rol.findByRolDescripcion", query = "SELECT r FROM Rol r WHERE r.rolDescripcion = :rolDescripcion"),
    @NamedQuery(name = "Rol.findByRolEstado", query = "SELECT r FROM Rol r WHERE r.rolEstado = :rolEstado")})
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rol_codigo", nullable = false)
    private Integer rolCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "rol_nombre", nullable = false, length = 255)
    private String rolNombre;
    @Size(max = 255)
    @Column(name = "rol_descripcion", length = 255)
    private String rolDescripcion;
    @Basic(optional = false)
    @Column(name = "rol_estado", nullable = false)
    private boolean rolEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol", fetch = FetchType.LAZY)
    private List<UsuarioRol> usuarioRolList;

    public Rol() {
    }

    public Rol(Integer rolCodigo) {
        this.rolCodigo = rolCodigo;
    }

    public Rol(Integer rolCodigo, String rolNombre, boolean rolEstado) {
        this.rolCodigo = rolCodigo;
        this.rolNombre = rolNombre;
        this.rolEstado = rolEstado;
    }

    public Integer getRolCodigo() {
        return rolCodigo;
    }

    public void setRolCodigo(Integer rolCodigo) {
        this.rolCodigo = rolCodigo;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getRolDescripcion() {
        return rolDescripcion;
    }

    public void setRolDescripcion(String rolDescripcion) {
        this.rolDescripcion = rolDescripcion;
    }

    public boolean getRolEstado() {
        return rolEstado;
    }

    public void setRolEstado(boolean rolEstado) {
        this.rolEstado = rolEstado;
    }

    public List<UsuarioRol> getUsuarioRolList() {
        return usuarioRolList;
    }

    public void setUsuarioRolList(List<UsuarioRol> usuarioRolList) {
        this.usuarioRolList = usuarioRolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolCodigo != null ? rolCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rolCodigo == null && other.rolCodigo != null) || (this.rolCodigo != null && !this.rolCodigo.equals(other.rolCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Rol[ rolCodigo=" + rolCodigo + " ]";
    }
    
}
