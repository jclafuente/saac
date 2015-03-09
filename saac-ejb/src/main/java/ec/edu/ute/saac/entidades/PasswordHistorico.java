/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "password_historico", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"pas_his_codigo"})})
@NamedQueries({
    @NamedQuery(name = "PasswordHistorico.findAll", query = "SELECT p FROM PasswordHistorico p"),
    @NamedQuery(name = "PasswordHistorico.findByPasHisCodigo", query = "SELECT p FROM PasswordHistorico p WHERE p.pasHisCodigo = :pasHisCodigo"),
    @NamedQuery(name = "PasswordHistorico.findByPasAnterior", query = "SELECT p FROM PasswordHistorico p WHERE p.pasAnterior = :pasAnterior"),
    @NamedQuery(name = "PasswordHistorico.findByPasFechaCambio", query = "SELECT p FROM PasswordHistorico p WHERE p.pasFechaCambio = :pasFechaCambio")})
public class PasswordHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pas_his_codigo", nullable = false)
    private Integer pasHisCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "pas_anterior", nullable = false, length = 255)
    private String pasAnterior;
    @Basic(optional = false)
    @Column(name = "pas_fecha_cambio", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date pasFechaCambio;
    @JoinColumn(name = "usuario", referencedColumnName = "usu_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public PasswordHistorico() {
    }

    public PasswordHistorico(Integer pasHisCodigo) {
        this.pasHisCodigo = pasHisCodigo;
    }

    public PasswordHistorico(Integer pasHisCodigo, String pasAnterior, Date pasFechaCambio) {
        this.pasHisCodigo = pasHisCodigo;
        this.pasAnterior = pasAnterior;
        this.pasFechaCambio = pasFechaCambio;
    }

    public Integer getPasHisCodigo() {
        return pasHisCodigo;
    }

    public void setPasHisCodigo(Integer pasHisCodigo) {
        this.pasHisCodigo = pasHisCodigo;
    }

    public String getPasAnterior() {
        return pasAnterior;
    }

    public void setPasAnterior(String pasAnterior) {
        this.pasAnterior = pasAnterior;
    }

    public Date getPasFechaCambio() {
        return pasFechaCambio;
    }

    public void setPasFechaCambio(Date pasFechaCambio) {
        this.pasFechaCambio = pasFechaCambio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pasHisCodigo != null ? pasHisCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PasswordHistorico)) {
            return false;
        }
        PasswordHistorico other = (PasswordHistorico) object;
        if ((this.pasHisCodigo == null && other.pasHisCodigo != null) || (this.pasHisCodigo != null && !this.pasHisCodigo.equals(other.pasHisCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.PasswordHistorico[ pasHisCodigo=" + pasHisCodigo + " ]";
    }
    
}
