/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "proceso", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"prc_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p"),
    @NamedQuery(name = "Proceso.findByPrcCodigo", query = "SELECT p FROM Proceso p WHERE p.prcCodigo = :prcCodigo"),
    @NamedQuery(name = "Proceso.findByPrcFechaRegistro", query = "SELECT p FROM Proceso p WHERE p.prcFechaRegistro = :prcFechaRegistro"),
    @NamedQuery(name = "Proceso.findByPrcFechaAprobacion", query = "SELECT p FROM Proceso p WHERE p.prcFechaAprobacion = :prcFechaAprobacion")})
public class Proceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prc_codigo", nullable = false)
    private Integer prcCodigo;
    @Basic(optional = false)
    @Column(name = "prc_fecha_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date prcFechaRegistro;
    @Column(name = "prc_fecha_aprobacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prcFechaAprobacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proceso", fetch = FetchType.LAZY)
    private List<AprobacionProceso> aprobacionProcesoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proceso", fetch = FetchType.LAZY)
    private List<ParticipantesProceso> participantesProcesoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proceso", fetch = FetchType.LAZY)
    private List<MovimientoProceso> movimientoProcesoList;
    @JoinColumn(name = "tipo_proceso", referencedColumnName = "tip_prc_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoProceso tipoProceso;
    @JoinColumn(name = "temas_titulacion", referencedColumnName = "tem_tit_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TemasTitulacion temasTitulacion;
    @JoinColumn(name = "persona", referencedColumnName = "per_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;
    @JoinColumn(name = "curso_titulacion", referencedColumnName = "cur_tit_codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private CursoTitulacion cursoTitulacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proceso", fetch = FetchType.LAZY)
    private List<Archivo> archivoList;
    @JoinColumn(name = "seleccion_tema", referencedColumnName = "sel_tem_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SeleccionTema seleccionTema;
    @JoinColumn(name = "curso_titulacion_estudiante", referencedColumnName = "cur_tit_est_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CursoTitulacionEstudiante cursoTitulacionEstudiante;
    
    public Proceso() {
    }

    public Proceso(Integer prcCodigo) {
        this.prcCodigo = prcCodigo;
    }

    public Proceso(Integer prcCodigo, Date prcFechaRegistro) {
        this.prcCodigo = prcCodigo;
        this.prcFechaRegistro = prcFechaRegistro;
    }

    public Integer getPrcCodigo() {
        return prcCodigo;
    }

    public void setPrcCodigo(Integer prcCodigo) {
        this.prcCodigo = prcCodigo;
    }

    public Date getPrcFechaRegistro() {
        return prcFechaRegistro;
    }

    public void setPrcFechaRegistro(Date prcFechaRegistro) {
        this.prcFechaRegistro = prcFechaRegistro;
    }

    public Date getPrcFechaAprobacion() {
        return prcFechaAprobacion;
    }

    public void setPrcFechaAprobacion(Date prcFechaAprobacion) {
        this.prcFechaAprobacion = prcFechaAprobacion;
    }

    public List<AprobacionProceso> getAprobacionProcesoList() {
        return aprobacionProcesoList;
    }

    public void setAprobacionProcesoList(List<AprobacionProceso> aprobacionProcesoList) {
        this.aprobacionProcesoList = aprobacionProcesoList;
    }

    public List<ParticipantesProceso> getParticipantesProcesoList() {
        return participantesProcesoList;
    }

    public void setParticipantesProcesoList(List<ParticipantesProceso> participantesProcesoList) {
        this.participantesProcesoList = participantesProcesoList;
    }

    public List<MovimientoProceso> getMovimientoProcesoList() {
        return movimientoProcesoList;
    }

    public void setMovimientoProcesoList(List<MovimientoProceso> movimientoProcesoList) {
        this.movimientoProcesoList = movimientoProcesoList;
    }

    public TipoProceso getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(TipoProceso tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public TemasTitulacion getTemasTitulacion() {
        return temasTitulacion;
    }

    public void setTemasTitulacion(TemasTitulacion temasTitulacion) {
        this.temasTitulacion = temasTitulacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public CursoTitulacion getCursoTitulacion() {
        return cursoTitulacion;
    }

    public void setCursoTitulacion(CursoTitulacion cursoTitulacion) {
        this.cursoTitulacion = cursoTitulacion;
    }

    public List<Archivo> getArchivoList() {
        return archivoList;
    }

    public void setArchivoList(List<Archivo> archivoList) {
        this.archivoList = archivoList;
    }
    

    public SeleccionTema getSeleccionTema() {
		return seleccionTema;
	}

	public void setSeleccionTema(SeleccionTema seleccionTema) {
		this.seleccionTema = seleccionTema;
	}

	
	public CursoTitulacionEstudiante getCursoTitulacionEstudiante() {
		return cursoTitulacionEstudiante;
	}

	public void setCursoTitulacionEstudiante(
			CursoTitulacionEstudiante cursoTitulacionEstudiante) {
		this.cursoTitulacionEstudiante = cursoTitulacionEstudiante;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (prcCodigo != null ? prcCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Proceso)) {
            return false;
        }
        Proceso other = (Proceso) object;
        if ((this.prcCodigo == null && other.prcCodigo != null) || (this.prcCodigo != null && !this.prcCodigo.equals(other.prcCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Proceso[ prcCodigo=" + prcCodigo + " ]";
    }
    
}
