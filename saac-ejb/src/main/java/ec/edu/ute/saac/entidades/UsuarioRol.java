/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Proxy;


/**
 *
 * @author JNK
 */

@Entity
@Table(name = "usuario_rol", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"usu_rol_codigo"})})
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u"),
    @NamedQuery(name = "UsuarioRol.findByUsuRolCodigo", query = "SELECT u FROM UsuarioRol u WHERE u.usuRolCodigo = :usuRolCodigo")})
public class UsuarioRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usu_rol_codigo", nullable = false)
    private Integer usuRolCodigo;
    @JoinColumn(name = "usuario", referencedColumnName = "usu_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    @JoinColumn(name = "rol", referencedColumnName = "rol_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rol;


    public UsuarioRol() {
    }

    public UsuarioRol(Integer usuRolCodigo) {
        this.usuRolCodigo = usuRolCodigo;
    }

    public Integer getUsuRolCodigo() {
        return usuRolCodigo;
    }

    public void setUsuRolCodigo(Integer usuRolCodigo) {
        this.usuRolCodigo = usuRolCodigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (usuRolCodigo != null ? usuRolCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsuarioRol)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.usuRolCodigo == null && other.usuRolCodigo != null) || (this.usuRolCodigo != null && !this.usuRolCodigo.equals(other.usuRolCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.UsuarioRol[ usuRolCodigo=" + usuRolCodigo + " ]";
    }
    
}
