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
@Table(name = "grupo_catalogo", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"grp_cat_codigo"})})
@NamedQueries({
    @NamedQuery(name = "GrupoCatalogo.findAll", query = "SELECT g FROM GrupoCatalogo g"),
    @NamedQuery(name = "GrupoCatalogo.findByGrpCatCodigo", query = "SELECT g FROM GrupoCatalogo g WHERE g.grpCatCodigo = :grpCatCodigo"),
    @NamedQuery(name = "GrupoCatalogo.findByGrpCatNemonico", query = "SELECT g FROM GrupoCatalogo g WHERE g.grpCatNemonico = :grpCatNemonico"),
    @NamedQuery(name = "GrupoCatalogo.findByGrpCatValor", query = "SELECT g FROM GrupoCatalogo g WHERE g.grpCatValor = :grpCatValor"),
    @NamedQuery(name = "GrupoCatalogo.findByGrpCatEstado", query = "SELECT g FROM GrupoCatalogo g WHERE g.grpCatEstado = :grpCatEstado")})
public class GrupoCatalogo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "grp_cat_codigo", nullable = false)
    private Integer grpCatCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "grp_cat_nemonico", nullable = false, length = 255)
    private String grpCatNemonico;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "grp_cat_valor", nullable = false, length = 255)
    private String grpCatValor;
    @Basic(optional = false)
    @Column(name = "grp_cat_estado", nullable = false)
    private boolean grpCatEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoCatalogo", fetch = FetchType.LAZY)
    private List<Catalogo> catalogoList;

    public GrupoCatalogo() {
    }

    public GrupoCatalogo(Integer grpCatCodigo) {
        this.grpCatCodigo = grpCatCodigo;
    }

    public GrupoCatalogo(Integer grpCatCodigo, String grpCatNemonico, String grpCatValor, boolean grpCatEstado) {
        this.grpCatCodigo = grpCatCodigo;
        this.grpCatNemonico = grpCatNemonico;
        this.grpCatValor = grpCatValor;
        this.grpCatEstado = grpCatEstado;
    }

    public Integer getGrpCatCodigo() {
        return grpCatCodigo;
    }

    public void setGrpCatCodigo(Integer grpCatCodigo) {
        this.grpCatCodigo = grpCatCodigo;
    }

    public String getGrpCatNemonico() {
        return grpCatNemonico;
    }

    public void setGrpCatNemonico(String grpCatNemonico) {
        this.grpCatNemonico = grpCatNemonico;
    }

    public String getGrpCatValor() {
        return grpCatValor;
    }

    public void setGrpCatValor(String grpCatValor) {
        this.grpCatValor = grpCatValor;
    }

    public boolean getGrpCatEstado() {
        return grpCatEstado;
    }

    public void setGrpCatEstado(boolean grpCatEstado) {
        this.grpCatEstado = grpCatEstado;
    }

    public List<Catalogo> getCatalogoList() {
        return catalogoList;
    }

    public void setCatalogoList(List<Catalogo> catalogoList) {
        this.catalogoList = catalogoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grpCatCodigo != null ? grpCatCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GrupoCatalogo)) {
            return false;
        }
        GrupoCatalogo other = (GrupoCatalogo) object;
        if ((this.grpCatCodigo == null && other.grpCatCodigo != null) || (this.grpCatCodigo != null && !this.grpCatCodigo.equals(other.grpCatCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.GrupoCatalogo[ grpCatCodigo=" + grpCatCodigo + " ]";
    }
    
}
