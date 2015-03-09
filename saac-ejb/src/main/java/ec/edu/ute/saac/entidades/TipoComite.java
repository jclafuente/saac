/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "tipo_comite", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tip_com_codigo"})})
@NamedQueries({
    @NamedQuery(name = "TipoComite.findAll", query = "SELECT t FROM TipoComite t"),
    @NamedQuery(name = "TipoComite.findByTipComCodigo", query = "SELECT t FROM TipoComite t WHERE t.tipComCodigo = :tipComCodigo")})
public class TipoComite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tip_com_codigo", nullable = false)
    private Integer tipComCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoComite", fetch = FetchType.LAZY)
    private List<Comite> comiteList;

    public TipoComite() {
    }

    public TipoComite(Integer tipComCodigo) {
        this.tipComCodigo = tipComCodigo;
    }

    public Integer getTipComCodigo() {
        return tipComCodigo;
    }

    public void setTipComCodigo(Integer tipComCodigo) {
        this.tipComCodigo = tipComCodigo;
    }

    public List<Comite> getComiteList() {
        return comiteList;
    }

    public void setComiteList(List<Comite> comiteList) {
        this.comiteList = comiteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipComCodigo != null ? tipComCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoComite)) {
            return false;
        }
        TipoComite other = (TipoComite) object;
        if ((this.tipComCodigo == null && other.tipComCodigo != null) || (this.tipComCodigo != null && !this.tipComCodigo.equals(other.tipComCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.TipoComite[ tipComCodigo=" + tipComCodigo + " ]";
    }
    
}
