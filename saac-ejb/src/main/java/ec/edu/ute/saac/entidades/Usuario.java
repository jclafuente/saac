/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;

/**
 *
 * @author JNK
 */
@Proxy(lazy=false)
@Entity
@Table(name = "usuario", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"usu_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuCodigo", query = "SELECT u FROM Usuario u WHERE u.usuCodigo = :usuCodigo"),
    @NamedQuery(name = "Usuario.findByUsuUteLogin", query = "SELECT u FROM Usuario u WHERE u.usuUteLogin = :usuUteLogin"),
    @NamedQuery(name = "Usuario.findByUsuUserName", query = "SELECT u FROM Usuario u WHERE u.usuUserName = :usuUserName"),
    @NamedQuery(name = "Usuario.findByUsuPassword", query = "SELECT u FROM Usuario u WHERE u.usuPassword = :usuPassword"),
    @NamedQuery(name = "Usuario.findByUsuFchRegistro", query = "SELECT u FROM Usuario u WHERE u.usuFchRegistro = :usuFchRegistro"),
    @NamedQuery(name = "Usuario.findByUsuEstado", query = "SELECT u FROM Usuario u WHERE u.usuEstado = :usuEstado")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usu_codigo", nullable = false)
    private Integer usuCodigo;
    @Basic(optional = false)

    @Size(min = 1, max = 255)
    @Column(name = "usu_ute_login", nullable = false, length = 255)
    private String usuUteLogin;
    @Basic(optional = false)

    @Size(min = 1, max = 255)
    @Column(name = "usu_user_name", nullable = false, length = 255)
    private String usuUserName;
    @Basic(optional = false)

    @Size(min = 1, max = 255)
    @Column(name = "usu_password", nullable = false, length = 255)
    private String usuPassword;
    @Basic(optional = false)

    @Column(name = "usu_fch_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuFchRegistro;
    
    @Basic(optional = false)
    @Column(name = "usu_estado", nullable = false)
    private boolean usuEstado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<UsuarioRol> usuarioRolList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<PasswordHistorico> passwordHistoricoList;
    
    @JoinColumn(name = "persona", referencedColumnName = "per_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;
    
  
    public Usuario() {
    }

    public Usuario(Integer usuCodigo) {
        this.usuCodigo = usuCodigo;
    }

    public Usuario(Integer usuCodigo, String usuUteLogin, String usuUserName, String usuPassword, Date usuFchRegistro, boolean usuEstado) {
        this.usuCodigo = usuCodigo;
        this.usuUteLogin = usuUteLogin;
        this.usuUserName = usuUserName;
        this.usuPassword = usuPassword;
        this.usuFchRegistro = usuFchRegistro;
        this.usuEstado = usuEstado;
    }

    public Integer getUsuCodigo() {
        return usuCodigo;
    }

    public void setUsuCodigo(Integer usuCodigo) {
        this.usuCodigo = usuCodigo;
    }

    public String getUsuUteLogin() {
        return usuUteLogin;
    }

    public void setUsuUteLogin(String usuUteLogin) {
        this.usuUteLogin = usuUteLogin;
    }

    public String getUsuUserName() {
        return usuUserName;
    }

    public void setUsuUserName(String usuUserName) {
        this.usuUserName = usuUserName;
    }

    public String getUsuPassword() {
        return usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }

    public Date getUsuFchRegistro() {
        return usuFchRegistro;
    }

    public void setUsuFchRegistro(Date usuFchRegistro) {
        this.usuFchRegistro = usuFchRegistro;
    }

    public boolean getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(boolean usuEstado) {
        this.usuEstado = usuEstado;
    }

    public List<UsuarioRol> getUsuarioRolList() {
        return usuarioRolList;
    }

    public void setUsuarioRolList(List<UsuarioRol> usuarioRolList) {
        this.usuarioRolList = usuarioRolList;
    }

    public List<PasswordHistorico> getPasswordHistoricoList() {
        return passwordHistoricoList;
    }

    public void setPasswordHistoricoList(List<PasswordHistorico> passwordHistoricoList) {
        this.passwordHistoricoList = passwordHistoricoList;
    }
    
    public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (usuCodigo != null ? usuCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuCodigo == null && other.usuCodigo != null) || (this.usuCodigo != null && !this.usuCodigo.equals(other.usuCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Usuario[ usuCodigo=" + usuCodigo + " ]";
    }
    
}
