package ec.edu.ute.saac.controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ec.edu.ute.saac.entidades.AreaInvestigacion;
import ec.edu.ute.saac.entidades.Carrera;
import ec.edu.ute.saac.entidades.CursoTitulacion;
import ec.edu.ute.saac.entidades.CursoTitulacionEstudiante;
import ec.edu.ute.saac.entidades.LineaInvestigacion;
import ec.edu.ute.saac.entidades.Periodos;
import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.Proceso;
import ec.edu.ute.saac.entidades.SeleccionTema;
import ec.edu.ute.saac.entidades.TemasTitulacion;
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;

@ManagedBean
@ViewScoped
public class AsignarEstudianteCursoPlanControlador {

	private static final Log log = LogFactory
			.getLog(SeleccionEstudianteControlador.class);
	private MessageSender sender;
	private Utilitarios utilitarios;
	private boolean panelDatos;
	private CursoTitulacion cursoTitulacion;
	private Proceso proceso;
	private Persona persona;

	private Integer cursoSelected;
	private Integer estudianteSelected;
	
	private Collection<CursoTitulacion> listadoCursoTitulacion;// lista de curso de titulacion
	private Collection<Proceso> listadoProceso; //lista de proceso pagina inicial
	private Collection<Proceso> listadoProcesoEstudiante; //lista de proceso cuando se carga la pagina
	
	private Collection<Persona> listadoPersonaEstudiante; // lista de estudiantes


	@Inject
	private IAdministracionServicio administracionServicio;
	
	public void retornarPagina() {
		setPanelDatos(Boolean.FALSE);
	}
	
	public void initProcesoEstudiante() {
		setProceso(new Proceso());

	}
	public void activarPanelDatos() {
		limpiarCombos();
		initProcesoEstudiante();
		setPanelDatos(true);
	}
	
	public void limpiarCombos(){
		cursoSelected=Integer.valueOf(0);

	}

	public void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		cursoTitulacion = new CursoTitulacion();
		proceso = new Proceso();
		persona=new Persona();
		
		listadoCursoTitulacion = new ArrayList<CursoTitulacion>();
		listadoProcesoEstudiante = new ArrayList<Proceso>();
		listadoPersonaEstudiante = new ArrayList<Persona>();
	} 

	@PostConstruct
	public void inicializacion() {
		inicializacionEntidades();
		setPanelDatos(Boolean.FALSE);
		limpiarCombos();

		try {

			if (CollectionUtils.isEmpty(listadoCursoTitulacion)) {
				setListadoCursoTitulacion(administracionServicio
						.obtenerCursoTitulacion());
			}

			if (CollectionUtils.isEmpty(listadoPersonaEstudiante)) {
				setListadoPersonaEstudiante(administracionServicio
						.obtenerPersonaUsuarioEstudiante());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cbChangeCursoTitulacion(ValueChangeEvent valueChangeEvent) {

		Integer curCodigo = NumberUtils.createInteger(String
				.valueOf(valueChangeEvent.getNewValue()));

		try {
			
			setListadoProceso(administracionServicio
					.obtenerProcesoCursoTitulacion(curCodigo));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void btnCargarListaCursoTitulacion() {

		try {
			setListadoProceso(administracionServicio
					.obtenerProcesoCursoTitulacion(cursoSelected));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void seleccionarPersonaEstudiante(Persona persona) {
	
		try { 
			
			initProcesoEstudiante();//para seleccionar estudiante
			setEstudianteSelected(persona.getPerCodigo());
			
			proceso.setPersona(new Persona());
			proceso.setCursoTitulacion(new CursoTitulacion());
			
			proceso.getCursoTitulacion().setCurTitCodigo(getCursoSelected());
			proceso.getPersona().setPerCodigo(getEstudianteSelected());
			
			proceso.getPersona().setPerDocumentoIdentidad(persona.getPerDocumentoIdentidad());
			proceso.getPersona().setPerNombre(persona.getPerNombre());
			proceso.getPersona().setPerApellido(persona.getPerApellido());
			
			administracionServicio.crearProceso(proceso);//crea un proceso por cada estudiante seleccionado
			getListadoProcesoEstudiante().add(proceso);
			initProcesoEstudiante();//para seleccionar otro estudiante
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

	public void seleccionarProcesoCursoTitulacion(Proceso proceso) {

		try {
			setCursoSelected(proceso.getCursoTitulacion().getCurTitCodigo());
			
			setListadoProcesoEstudiante(administracionServicio
					.obtenerProcesoEstudiante(cursoSelected));
			setProceso(proceso);
			setPanelDatos(Boolean.TRUE);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	
		
	}
	
	public void btnActualizarProcesoEstudiante() {
		try {
			
			proceso.setCursoTitulacion(new CursoTitulacion());
			proceso.setPersona(new Persona());
			proceso.getCursoTitulacion().setCurTitCodigo(getCursoSelected());
			proceso.getPersona().setPerCodigo(getEstudianteSelected());
			
			/*cursoTitulacion.setPersona(new Persona());
			cursoTitulacion.getPersona().setPerCodigo(
					getPersonaDocenteSelected());
			cursoTitulacion.setPeriodos(new Periodos());
			cursoTitulacion.getPeriodos().setPrdCodigo(
					getPeriodoTitulacionSelected());*/
			
			administracionServicio.actualizarProcesoEstudiante(proceso);
			inicializacion();
			sender.sendInfoPopup("Registro Actualizado");
			

		} catch (Exception e) {

		    	e.printStackTrace();
		}

	}
	
	public void eliminarProcesoEstudiante(Proceso proceso) {
		
		try {
			
			administracionServicio.eliminarProcesoEstudiante(proceso);
			getListadoProcesoEstudiante().remove(proceso);

		
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

	public CursoTitulacion getCursoTitulacion() {
		return cursoTitulacion;
	}

	public void setCursoTitulacion(CursoTitulacion cursoTitulacion) {
		this.cursoTitulacion = cursoTitulacion;
	}

	public Integer getCursoSelected() {
		return cursoSelected;
	}

	public void setCursoSelected(Integer cursoSelected) {
		this.cursoSelected = cursoSelected;
	}

	public Collection<CursoTitulacion> getListadoCursoTitulacion() {
		return listadoCursoTitulacion;
	}

	public void setListadoCursoTitulacion(
			Collection<CursoTitulacion> listadoCursoTitulacion) {
		this.listadoCursoTitulacion = listadoCursoTitulacion;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Collection<Proceso> getListadoProceso() {
		return listadoProceso;
	}

	public void setListadoProceso(Collection<Proceso> listadoProceso) {
		this.listadoProceso = listadoProceso;
	}

	public Collection<Proceso> getListadoProcesoEstudiante() {
		return listadoProcesoEstudiante;
	}

	public void setListadoProcesoEstudiante(
			Collection<Proceso> listadoProcesoEstudiante) {
		this.listadoProcesoEstudiante = listadoProcesoEstudiante;
	}

	public Collection<Persona> getListadoPersonaEstudiante() {
		return listadoPersonaEstudiante;
	}

	public void setListadoPersonaEstudiante(
			Collection<Persona> listadoPersonaEstudiante) {
		this.listadoPersonaEstudiante = listadoPersonaEstudiante;
	}

	public Integer getEstudianteSelected() {
		return estudianteSelected;
	}

	public void setEstudianteSelected(Integer estudianteSelected) {
		this.estudianteSelected = estudianteSelected;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
