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
@Table(name = "catalogo", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cat_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Catalogo.findAll", query = "SELECT c FROM Catalogo c"),
    @NamedQuery(name = "Catalogo.findByCatCodigo", query = "SELECT c FROM Catalogo c WHERE c.catCodigo = :catCodigo"),
    @NamedQuery(name = "Catalogo.findByCatOrden", query = "SELECT c FROM Catalogo c WHERE c.catOrden = :catOrden"),
    @NamedQuery(name = "Catalogo.findByCatNemonico", query = "SELECT c FROM Catalogo c WHERE c.catNemonico = :catNemonico"),
    @NamedQuery(name = "Catalogo.findByCatValor", query = "SELECT c FROM Catalogo c WHERE c.catValor = :catValor"),
    @NamedQuery(name = "Catalogo.findByCatDescripcion", query = "SELECT c FROM Catalogo c WHERE c.catDescripcion = :catDescripcion"),
    @NamedQuery(name = "Catalogo.findByCatEstado", query = "SELECT c FROM Catalogo c WHERE c.catEstado = :catEstado"),
    @NamedQuery(name = "Catalogo.recuperarEstadoCivil", query = "select c from Catalogo c where c.grupoCatalogo.grpCatNemonico='ESTADO_CIVIL' order by c.catOrden asc"),
    @NamedQuery(name = "Catalogo.recuperarGenero", query = "select c from Catalogo c where c.grupoCatalogo.grpCatNemonico='GENERO' order by c.catOrden asc"),
    @NamedQuery(name = "Catalogo.recuperarNacionalidades", query = "select c from Catalogo c where c.grupoCatalogo.grpCatNemonico='NACIONALIDADES' order by c.catOrden asc")})
public class Catalogo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cat_codigo", nullable = false)
    private Integer catCodigo;
    @Column(name = "cat_orden")
    private Integer catOrden;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "cat_nemonico", nullable = false, length = 255)
    private String catNemonico;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "cat_valor", nullable = false, length = 255)
    private String catValor;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "cat_descripcion", nullable = false, length = 255)
    private String catDescripcion;
    @Basic(optional = false)
    @Column(name = "cat_estado", nullable = false)
    private boolean catEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catalogoAreaEstudio", fetch = FetchType.LAZY)
    private List<Capacitacion> capacitacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catalogoTipoCertificado", fetch = FetchType.LAZY)
    private List<Capacitacion> capacitacionList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catalogoTipoEvento", fetch = FetchType.LAZY)
    private List<Capacitacion> capacitacionList2;
    @JoinColumn(name = "grupo_catalogo", referencedColumnName = "grp_cat_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GrupoCatalogo grupoCatalogo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catalogoAreaTrabajo", fetch = FetchType.LAZY)
    private List<Experiencia> experienciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catalogoNacionalidad", fetch = FetchType.LAZY)
    private List<Persona> personaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catalogoEstadoCivil", fetch = FetchType.LAZY)
    private List<Persona> personaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catalogoGenero", fetch = FetchType.LAZY)
    private List<Persona> personaList2;

    public Catalogo() {
    }

    public Catalogo(Integer catCodigo) {
        this.catCodigo = catCodigo;
    }

    public Catalogo(Integer catCodigo, String catNemonico, String catValor, String catDescripcion, boolean catEstado) {
        this.catCodigo = catCodigo;
        this.catNemonico = catNemonico;
        this.catValor = catValor;
        this.catDescripcion = catDescripcion;
        this.catEstado = catEstado;
    }

    public Integer getCatCodigo() {
        return catCodigo;
    }

    public void setCatCodigo(Integer catCodigo) {
        this.catCodigo = catCodigo;
    }

    public Integer getCatOrden() {
        return catOrden;
    }

    public void setCatOrden(Integer catOrden) {
        this.catOrden = catOrden;
    }

    public String getCatNemonico() {
        return catNemonico;
    }

    public void setCatNemonico(String catNemonico) {
        this.catNemonico = catNemonico;
    }

    public String getCatValor() {
        return catValor;
    }

    public void setCatValor(String catValor) {
        this.catValor = catValor;
    }

    public String getCatDescripcion() {
        return catDescripcion;
    }

    public void setCatDescripcion(String catDescripcion) {
        this.catDescripcion = catDescripcion;
    }

    public boolean getCatEstado() {
        return catEstado;
    }

    public void setCatEstado(boolean catEstado) {
        this.catEstado = catEstado;
    }

    public List<Capacitacion> getCapacitacionList() {
        return capacitacionList;
    }

    public void setCapacitacionList(List<Capacitacion> capacitacionList) {
        this.capacitacionList = capacitacionList;
    }

    public List<Capacitacion> getCapacitacionList1() {
        return capacitacionList1;
    }

    public void setCapacitacionList1(List<Capacitacion> capacitacionList1) {
        this.capacitacionList1 = capacitacionList1;
    }

    public List<Capacitacion> getCapacitacionList2() {
        return capacitacionList2;
    }

    public void setCapacitacionList2(List<Capacitacion> capacitacionList2) {
        this.capacitacionList2 = capacitacionList2;
    }

    public GrupoCatalogo getGrupoCatalogo() {
        return grupoCatalogo;
    }

    public void setGrupoCatalogo(GrupoCatalogo grupoCatalogo) {
        this.grupoCatalogo = grupoCatalogo;
    }

    public List<Experiencia> getExperienciaList() {
        return experienciaList;
    }

    public void setExperienciaList(List<Experiencia> experienciaList) {
        this.experienciaList = experienciaList;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public List<Persona> getPersonaList1() {
        return personaList1;
    }

    public void setPersonaList1(List<Persona> personaList1) {
        this.personaList1 = personaList1;
    }

    public List<Persona> getPersonaList2() {
        return personaList2;
    }

    public void setPersonaList2(List<Persona> personaList2) {
        this.personaList2 = personaList2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catCodigo != null ? catCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Catalogo)) {
            return false;
        }
        Catalogo other = (Catalogo) object;
        if ((this.catCodigo == null && other.catCodigo != null) || (this.catCodigo != null && !this.catCodigo.equals(other.catCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Catalogo[ catCodigo=" + catCodigo + " ]";
    }
}
