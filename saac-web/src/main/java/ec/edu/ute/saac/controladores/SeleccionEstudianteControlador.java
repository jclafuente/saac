package ec.edu.ute.saac.controladores;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ec.edu.ute.saac.entidades.CursoTitulacion;
import ec.edu.ute.saac.entidades.CursoTitulacionEstudiante;
import ec.edu.ute.saac.entidades.Periodos;
import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.Proceso;
import ec.edu.ute.saac.entidades.SeleccionTema;
import ec.edu.ute.saac.entidades.TemasTitulacion;
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;

@ManagedBean
@ViewScoped
public class SeleccionEstudianteControlador {

	private static final Log log = LogFactory
			.getLog(SeleccionEstudianteControlador.class);
	private MessageSender sender;
	private Utilitarios utilitarios;
	private boolean panelDatos;
	private TemasTitulacion temaTitulacion;
	private Periodos periodo;

	private Integer docenteSelected;
	private Integer periodoSelected;
	private Integer temaTitulacionSelected;

	private Collection<Persona> listadoPersonaDocente;// lista de docente
	private Collection<TemasTitulacion> listadoTemaTitulacionDocente;// lista de temas titulaciion
	private Collection<Proceso> listadoProcesoPeriodo;// lista de docente
	private Collection<Proceso> listadoProcesoEstudiante;// lista de estudiantes de proceso

	@Inject
	private IAdministracionServicio administracionServicio;
	
	public void retornarPagina() {
		setPanelDatos(Boolean.FALSE);
	}
	
	public void limpiarCombos(){
		
		docenteSelected=Integer.valueOf(0);
	}

	public void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		temaTitulacion = new TemasTitulacion();
		periodo = new Periodos();

		listadoPersonaDocente = new ArrayList<Persona>();// lista del combo solo docentes
		listadoTemaTitulacionDocente = new ArrayList<TemasTitulacion>(); // lista de temas propuestos por docente
		listadoProcesoPeriodo = new ArrayList<Proceso>();// lista de periodos de proceso
		listadoProcesoEstudiante = new ArrayList<Proceso>();
	}

	@PostConstruct
	public void inicializacion() {
		inicializacionEntidades();
		setPanelDatos(Boolean.FALSE);
		limpiarCombos();
		try {

			if (CollectionUtils.isEmpty(listadoPersonaDocente)) {
				setListadoPersonaDocente(administracionServicio
						.obtenerPersonaUsuario());
			}

			if (CollectionUtils.isEmpty(listadoProcesoPeriodo)) {
				setListadoProcesoPeriodo(administracionServicio
						.obtenerProcesoPeriodo());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void btnCargarListaTemaTitulacionDocente() {

		try {
			setListadoTemaTitulacionDocente(administracionServicio
					.obtenerTemaTitulacionDocente(docenteSelected));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void btnCargarListaProcesoEstudiante() {

		try {

			setListadoProcesoEstudiante(administracionServicio
					.obtenerProcesoPeriodoEstudiante(periodoSelected));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void seleccionarTemaTitulacion(TemasTitulacion temasTitulacion) {

		
		if(temasTitulacion.getTemTitEstado().equals("APROBADO"))
		{
			try 
			{
				setTemaTitulacionSelected(temasTitulacion.getTemTitCodigo());
				setPanelDatos(Boolean.TRUE);	

			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}else
			
		{
			sender.sendInfoPopup("SU TEMA DE TITULACION NO HA SIDO APROBADO");	
		}

	}
	
	public void seleccionarProcesoEstudiante(Proceso proceso) {

		try {
		
			proceso.setTemasTitulacion(new TemasTitulacion());
			proceso.getTemasTitulacion().setTemTitCodigo(getTemaTitulacionSelected());
			administracionServicio.actualizarProcesoEstudiante(proceso);
			inicializacion();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isPanelDatos() {
		return panelDatos;
	}

	public void setPanelDatos(boolean panelDatos) {
		this.panelDatos = panelDatos;
	}

	public Collection<Persona> getListadoPersonaDocente() {
		return listadoPersonaDocente;
	}

	public void setListadoPersonaDocente(
			Collection<Persona> listadoPersonaDocente) {
		this.listadoPersonaDocente = listadoPersonaDocente;
	}

	public TemasTitulacion getTemaTitulacion() {
		return temaTitulacion;
	}

	public void setTemaTitulacion(TemasTitulacion temaTitulacion) {
		this.temaTitulacion = temaTitulacion;
	}

	public Collection<TemasTitulacion> getListadoTemaTitulacionDocente() {
		return listadoTemaTitulacionDocente;
	}

	public void setListadoTemaTitulacionDocente(
			Collection<TemasTitulacion> listadoTemaTitulacionDocente) {
		this.listadoTemaTitulacionDocente = listadoTemaTitulacionDocente;
	}

	public Integer getDocenteSelected() {
		return docenteSelected;
	}

	public void setDocenteSelected(Integer docenteSelected) {
		this.docenteSelected = docenteSelected;
	}

	public Integer getPeriodoSelected() {
		return periodoSelected;
	}

	public void setPeriodoSelected(Integer periodoSelected) {
		this.periodoSelected = periodoSelected;
	}

	public Periodos getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodos periodo) {
		this.periodo = periodo;
	}

	public Collection<Proceso> getListadoProcesoPeriodo() {
		return listadoProcesoPeriodo;
	}

	public void setListadoProcesoPeriodo(
			Collection<Proceso> listadoProcesoPeriodo) {
		this.listadoProcesoPeriodo = listadoProcesoPeriodo;
	}

	public Collection<Proceso> getListadoProcesoEstudiante() {
		return listadoProcesoEstudiante;
	}

	public void setListadoProcesoEstudiante(
			Collection<Proceso> listadoProcesoEstudiante) {
		this.listadoProcesoEstudiante = listadoProcesoEstudiante;
	}

	public Integer getTemaTitulacionSelected() {
		return temaTitulacionSelected;
	}

	public void setTemaTitulacionSelected(Integer temaTitulacionSelected) {
		this.temaTitulacionSelected = temaTitulacionSelected;
	}

	
}
