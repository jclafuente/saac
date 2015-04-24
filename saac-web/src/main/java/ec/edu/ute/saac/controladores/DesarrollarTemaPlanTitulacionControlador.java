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

import ec.edu.ute.saac.entidades.Componente;
import ec.edu.ute.saac.entidades.Contenido;
import ec.edu.ute.saac.entidades.Periodos;
import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.Proceso;
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;

@ManagedBean
@ViewScoped
public class DesarrollarTemaPlanTitulacionControlador {
	private static final Log log = LogFactory
			.getLog(DesarrollarTemaPlanTitulacionControlador.class);
	private MessageSender sender;
	private Utilitarios utilitarios;
	private boolean panelDatos;
	private Proceso proceso;
	private Contenido contenido;
	private Componente componente;

	private Integer estudianteSelected;
	private Integer procesoSelected;
	private String problema;
	private String justificacion;

	private Collection<Proceso> listadoProcesoEstudiante; // lista de
															// estudiantes de
															// proceso
	private Collection<Proceso> listadoProcesoTemaTitulacionEstudiante; // lista
																		// de
																		// temas
																		// de
																		// titulacion
																		// por
																		// estudiante
	private Collection<Contenido> listadoContenido; // lista de temas de
													// titulacion por estudiante

	private Collection<Proceso> prueba;
	@Inject
	private IAdministracionServicio administracionServicio;

	public void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		proceso = new Proceso();
		/*contenido = new Contenido();
		componente = new Componente();*/

		listadoProcesoEstudiante = new ArrayList<Proceso>();
		listadoProcesoTemaTitulacionEstudiante = new ArrayList<Proceso>();
		listadoContenido = new ArrayList<Contenido>();
		prueba = new ArrayList<Proceso>();
	}

	@PostConstruct
	public void inicializacion() {

		try {
			inicializacionEntidades();
			setPanelDatos(Boolean.FALSE);

			if (CollectionUtils.isEmpty(listadoProcesoEstudiante)) {
				setListadoProcesoEstudiante(administracionServicio
						.obtenerProcesoEstudianteLoggin());
			}

		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR, error);
			utilitarios.error(AdministracionControlador.class.getName(),
					"Error al inicializar los datos en: DocenteControlador", e);
		}

	}

	public void cbChangeEstudiante(ValueChangeEvent valueChangeEvent) {

		Integer curCodigo = NumberUtils.createInteger(String
				.valueOf(valueChangeEvent.getNewValue()));

		try {

			/*
			 * setListadoProceso(administracionServicio
			 * .obtenerProcesoCursoTitulacion(curCodigo));
			 */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void btnCargarTemaPlanTitulacion() {

		try {
			setListadoProcesoTemaTitulacionEstudiante(administracionServicio
					.obtenerProcesoTemaTitulacionEstudiante(getEstudianteSelected()));

			/*
			 * setListadoProceso(administracionServicio
			 * .obtenerProcesoCursoTitulacion(cursoSelected));
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void seleccionarProcesoCursoTitulacion(Proceso proceso) {

		try {
			/*
			 * setCursoSelected(proceso.getCursoTitulacion().getCurTitCodigo());
			 * 
			 * setListadoProcesoEstudiante(administracionServicio
			 * .obtenerProcesoEstudiante(cursoSelected));
			 */
			//proceso.setContenido_problema(new Contenido());  
		//	setProblema(administracionServicio.obtenerProblema(proceso.getPrcCodigo()));
			setPrueba(administracionServicio.obtenerProblema(proceso.getPrcCodigo()));
			setProceso(proceso);   
			/*contenido.setProceso(new Proceso());
			contenido.getProceso().setPrcCodigo(proceso.getPrcCodigo());
			// contenido.setCntNombre(getProblema());
			// 
			// setListadoContenido(administracionServicio.obtenerProblema(proceso.getPrcCodigo()));

			setProblema(administracionServicio.obtenerProblema(proceso
					.getPrcCodigo()));
					
			setJustificacion(administracionServicio
					.obtenerJustificacion(proceso.getPrcCodigo()));

			setContenido(contenido);
*/			// contenido.setCntNombre(getProblema());

			/*
			 * setListadoContenido(administracionServicio
			 * .obtenerContenidoProceso(proceso.getPrcCodigo()));
			 */

			setPanelDatos(Boolean.TRUE);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void btnCrearPlanTitulacion() {
		try {

			/*
			 * cursoTitulacion.setPersona(new Persona());
			 * cursoTitulacion.getPersona
			 * ().setPerCodigo(getPersonaDocenteSelected());
			 * cursoTitulacion.setPeriodos(new Periodos());
			 * cursoTitulacion.getPeriodos
			 * ().setPrdCodigo(getPeriodoTitulacionSelected());
			 * cursoTitulacion.setCurTitEstado("REGISTRADO");
			 * cursoTitulacion.setCurTitNombre("borrar");
			 */

			/*setProblema(administracionServicio.obtenerProblema(proceso
					.getPrcCodigo()));
			
			setJustificacion(administracionServicio
					.obtenerJustificacion(proceso.getPrcCodigo()));

			setContenido(contenido);*/

			// administracionServicio.crearCursoTitulacion(cursoTitulacion);

			/*
			 * getListadoCursoTitulacion().add(cursoTitulacion);
			 * inicializacion(); limpiarCombos(); activarPanelDatos();
			 */
			setPanelDatos(Boolean.TRUE);

		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR_REGISTRO_GUARDADO,
					error);
			utilitarios.error(RegistrarCursoPlanControlador.class.getName(),
					"Error al procesar registro", e);
		}
	}

	public boolean isPanelDatos() {
		return panelDatos;
	}

	public void setPanelDatos(boolean panelDatos) {
		this.panelDatos = panelDatos;
	}

	public Integer getEstudianteSelected() {
		return estudianteSelected;
	}

	public void setEstudianteSelected(Integer estudianteSelected) {
		this.estudianteSelected = estudianteSelected;
	}

	public Collection<Proceso> getListadoProcesoEstudiante() {
		return listadoProcesoEstudiante;
	}

	public void setListadoProcesoEstudiante(
			Collection<Proceso> listadoProcesoEstudiante) {
		this.listadoProcesoEstudiante = listadoProcesoEstudiante;
	}

	public Collection<Proceso> getListadoProcesoTemaTitulacionEstudiante() {
		return listadoProcesoTemaTitulacionEstudiante;
	}

	public void setListadoProcesoTemaTitulacionEstudiante(
			Collection<Proceso> listadoProcesoTemaTitulacionEstudiante) {
		this.listadoProcesoTemaTitulacionEstudiante = listadoProcesoTemaTitulacionEstudiante;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Contenido getContenido() {
		return contenido;
	}

	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public Integer getProcesoSelected() {
		return procesoSelected;
	}

	public void setProcesoSelected(Integer procesoSelected) {
		this.procesoSelected = procesoSelected;
	}

	public Collection<Contenido> getListadoContenido() {
		return listadoContenido;
	}

	public void setListadoContenido(Collection<Contenido> listadoContenido) {
		this.listadoContenido = listadoContenido;
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	
	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public Collection<Proceso> getPrueba() {
		return prueba;
	}

	public void setPrueba(Collection<Proceso> prueba) {
		this.prueba = prueba;
	}

	
}
