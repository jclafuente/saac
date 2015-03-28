/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "persona", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"per_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByPerCodigo", query = "SELECT p FROM Persona p WHERE p.perCodigo = :perCodigo"),
    @NamedQuery(name = "Persona.findByPerDocumentoIdentidad", query = "SELECT p FROM Persona p WHERE p.perDocumentoIdentidad = :perDocumentoIdentidad"),
    @NamedQuery(name = "Persona.findByPerNombre", query = "SELECT p FROM Persona p WHERE p.perNombre = :perNombre"),
    @NamedQuery(name = "Persona.findByPerApellido", query = "SELECT p FROM Persona p WHERE p.perApellido = :perApellido"),
    @NamedQuery(name = "Persona.findByPerFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.perFechaNacimiento = :perFechaNacimiento"),
    @NamedQuery(name = "Persona.findByPerDireccion", query = "SELECT p FROM Persona p WHERE p.perDireccion = :perDireccion"),
    @NamedQuery(name = "Persona.findByPerTelfonoFijo", query = "SELECT p FROM Persona p WHERE p.perTelfonoFijo = :perTelfonoFijo"),
    @NamedQuery(name = "Persona.findByPerTelefonoMovil", query = "SELECT p FROM Persona p WHERE p.perTelefonoMovil = :perTelefonoMovil"),
    @NamedQuery(name = "Persona.findByPerEmail", query = "SELECT p FROM Persona p WHERE p.perEmail = :perEmail"),
    @NamedQuery(name = "Persona.findByPerFechaRegistro", query = "SELECT p FROM Persona p WHERE p.perFechaRegistro = :perFechaRegistro"),
    @NamedQuery(name = "Persona.findByPerEstado", query = "SELECT p FROM Persona p WHERE p.perEstado = :perEstado")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "per_codigo", nullable = false)
    private Integer perCodigo;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "per_documento_identidad", nullable = false, length = 50 )
    private String perDocumentoIdentidad;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "per_nombre", nullable = false, length = 45)
    private String perNombre;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "per_apellido", nullable = false, length=45)
    private String perApellido;
    @Basic(optional = false)
    @Column(name = "per_fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date perFechaNacimiento;
    @Basic(optional = false)
    @Size(min = 1, max = 500)
    @Column(name = "per_direccion", nullable = false, length = 500)
    private String perDireccion;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "per_telfono_fijo", nullable = false, length = 50)
    private String perTelfonoFijo;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "per_telefono_movil", nullable = false, length = 50)
    private String perTelefonoMovil;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "per_email", nullable = false, length = 45)
    private String perEmail;
    @Basic(optional = false)
    @Column(name = "per_fecha_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date perFechaRegistro;
    @Basic(optional = false)
    @Column(name = "per_estado", nullable = false)
    private boolean perEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Capacitacion> capacitacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<CursoTitulacion> cursoTitulacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<PersonaCarrera> personaCarreraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<ParticipantesProceso> participantesProcesoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perCodigo", fetch = FetchType.LAZY)
    private List<SeleccionTema> seleccionTemaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<PersonaFacultad> personaFacultadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<MovimientoProceso> movimientoProcesoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<ComisionInvestigacion> comisionInvestigacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Proceso> procesoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Experiencia> experienciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perCodigo", fetch = FetchType.LAZY)
    private List<TemasTitulacion> temasTitulacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;
    @JoinColumn(name = "catalogo_nacionalidad", referencedColumnName = "cat_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Catalogo catalogoNacionalidad;
    @JoinColumn(name = "catalogo_estado_civil", referencedColumnName = "cat_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Catalogo catalogoEstadoCivil;
    @JoinColumn(name = "catalogo_genero", referencedColumnName = "cat_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Catalogo catalogoGenero;
    @JoinColumn(name = "usuario", referencedColumnName = "usu_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    
    public Persona() {
    }

    public Persona(Integer perCodigo) {
        this.perCodigo = perCodigo;
    }

    public Persona(Integer perCodigo, String perDocumentoIdentidad, String perNombre, String perApellido, Date perFechaNacimiento, String perDireccion, String perTelfonoFijo, String perTelefonoMovil, String perEmail, Date perFechaRegistro, boolean perEstado) {
        this.perCodigo = perCodigo;
        this.perDocumentoIdentidad = perDocumentoIdentidad;
        this.perNombre = perNombre;
        this.perApellido = perApellido;
        this.perFechaNacimiento = perFechaNacimiento;
        this.perDireccion = perDireccion;
        this.perTelfonoFijo = perTelfonoFijo;
        this.perTelefonoMovil = perTelefonoMovil;
        this.perEmail = perEmail;
        this.perFechaRegistro = perFechaRegistro;
        this.perEstado = perEstado;
    }

    public Integer getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Integer perCodigo) {
        this.perCodigo = perCodigo;
    }

    public String getPerDocumentoIdentidad() {
        return perDocumentoIdentidad;
    }

    public void setPerDocumentoIdentidad(String perDocumentoIdentidad) {
        this.perDocumentoIdentidad = perDocumentoIdentidad;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public String getPerApellido() {
        return perApellido;
    }

    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    public Date getPerFechaNacimiento() {
        return perFechaNacimiento;
    }

    public void setPerFechaNacimiento(Date perFechaNacimiento) {
        this.perFechaNacimiento = perFechaNacimiento;
    }

    public String getPerDireccion() {
        return perDireccion;
    }

    public void setPerDireccion(String perDireccion) {
        this.perDireccion = perDireccion;
    }

    public String getPerTelfonoFijo() {
        return perTelfonoFijo;
    }

    public void setPerTelfonoFijo(String perTelfonoFijo) {
        this.perTelfonoFijo = perTelfonoFijo;
    }

    public String getPerTelefonoMovil() {
        return perTelefonoMovil;
    }

    public void setPerTelefonoMovil(String perTelefonoMovil) {
        this.perTelefonoMovil = perTelefonoMovil;
    }

    public String getPerEmail() {
        return perEmail;
    }

    public void setPerEmail(String perEmail) {
        this.perEmail = perEmail;
    }

    public Date getPerFechaRegistro() {
        return perFechaRegistro;
    }

    public void setPerFechaRegistro(Date perFechaRegistro) {
        this.perFechaRegistro = perFechaRegistro;
    }

    public boolean getPerEstado() {
        return perEstado;
    }

    public void setPerEstado(boolean perEstado) {
        this.perEstado = perEstado;
    }

    public List<Capacitacion> getCapacitacionList() {
        return capacitacionList;
    }

    public void setCapacitacionList(List<Capacitacion> capacitacionList) {
        this.capacitacionList = capacitacionList;
    }

    public List<CursoTitulacion> getCursoTitulacionList() {
        return cursoTitulacionList;
    }

    public void setCursoTitulacionList(List<CursoTitulacion> cursoTitulacionList) {
        this.cursoTitulacionList = cursoTitulacionList;
    }

	public List<PersonaCarrera> getPersonaCarreraList() {
        return personaCarreraList;
    }

    public void setPersonaCarreraList(List<PersonaCarrera> personaCarreraList) {
        this.personaCarreraList = personaCarreraList;
    }

    public List<ParticipantesProceso> getParticipantesProcesoList() {
        return participantesProcesoList;
    }

    public void setParticipantesProcesoList(List<ParticipantesProceso> participantesProcesoList) {
        this.participantesProcesoList = participantesProcesoList;
    }

    public List<SeleccionTema> getSeleccionTemaList() {
        return seleccionTemaList;
    }

    public void setSeleccionTemaList(List<SeleccionTema> seleccionTemaList) {
        this.seleccionTemaList = seleccionTemaList;
    }

    public List<PersonaFacultad> getPersonaFacultadList() {
        return personaFacultadList;
    }

    public void setPersonaFacultadList(List<PersonaFacultad> personaFacultadList) {
        this.personaFacultadList = personaFacultadList;
    }

    public List<MovimientoProceso> getMovimientoProcesoList() {
        return movimientoProcesoList;
    }

    public void setMovimientoProcesoList(List<MovimientoProceso> movimientoProcesoList) {
        this.movimientoProcesoList = movimientoProcesoList;
    }

    public List<ComisionInvestigacion> getComisionInvestigacionList() {
        return comisionInvestigacionList;
    }

    public void setComisionInvestigacionList(List<ComisionInvestigacion> comisionInvestigacionList) {
        this.comisionInvestigacionList = comisionInvestigacionList;
    }

    public List<Proceso> getProcesoList() {
        return procesoList;
    }

    public void setProcesoList(List<Proceso> procesoList) {
        this.procesoList = procesoList;
    }

    public List<Experiencia> getExperienciaList() {
        return experienciaList;
    }

    public void setExperienciaList(List<Experiencia> experienciaList) {
        this.experienciaList = experienciaList;
    }

    public List<TemasTitulacion> getTemasTitulacionList() {
        return temasTitulacionList;
    }

    public void setTemasTitulacionList(List<TemasTitulacion> temasTitulacionList) {
        this.temasTitulacionList = temasTitulacionList;
    }

    public Catalogo getCatalogoNacionalidad() {
        return catalogoNacionalidad;
    }

    public void setCatalogoNacionalidad(Catalogo catalogoNacionalidad) {
        this.catalogoNacionalidad = catalogoNacionalidad;
    }

    public Catalogo getCatalogoEstadoCivil() {
        return catalogoEstadoCivil;
    }

    public void setCatalogoEstadoCivil(Catalogo catalogoEstadoCivil) {
        this.catalogoEstadoCivil = catalogoEstadoCivil;
    }

    public Catalogo getCatalogoGenero() {
        return catalogoGenero;
    }

    public void setCatalogoGenero(Catalogo catalogoGenero) {
        this.catalogoGenero = catalogoGenero;
    }
	

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
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
        hash += (perCodigo != null ? perCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.perCodigo == null && other.perCodigo != null) || (this.perCodigo != null && !this.perCodigo.equals(other.perCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Persona[ perCodigo=" + perCodigo + " ]";
    }
    
}
