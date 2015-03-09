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
import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.Proceso;
import ec.edu.ute.saac.entidades.SeleccionTema;
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
	private Persona persona;
	private Proceso proceso;
	private CursoTitulacionEstudiante cursoTitulacionEstudiante;
	private Integer estudianteSelected;
	
	private Integer cursoTitulacionSelected;
	private Integer temaTitulacionSelected;

	private Collection<Proceso> listadoProcesoCursoTitulacion;
	private Collection<CursoTitulacionEstudiante> listadoCursoTitulacionEstudiante;
	private Collection<CursoTitulacionEstudiante> listadoCursoTitulacionEstudianteTema;
	private Collection<CursoTitulacionEstudiante> listadoCursoTitulacionEstudianteEstudiante;

	@Inject
	private IAdministracionServicio administracionServicio;

	public void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		persona = new Persona();
		proceso=new Proceso();
		cursoTitulacionEstudiante = new CursoTitulacionEstudiante();

		listadoCursoTitulacionEstudiante = new ArrayList<CursoTitulacionEstudiante>();
		listadoCursoTitulacionEstudianteTema = new ArrayList<CursoTitulacionEstudiante>();
		listadoProcesoCursoTitulacion=new ArrayList<Proceso>();

	}

	@PostConstruct
	public void inicializacion() {
		inicializacionEntidades();
		setPanelDatos(Boolean.FALSE);

		try {

			if (CollectionUtils.isEmpty(listadoCursoTitulacionEstudiante)) {
				setListadoCursoTitulacionEstudiante(administracionServicio
						.obtenerCursoTitulacionEstudiante());
			}
			

			if (CollectionUtils.isEmpty(listadoProcesoCursoTitulacion)) {
				setListadoProcesoCursoTitulacion(administracionServicio
						.obtenerProcesoEstudiante());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cbChangeCursoTitulacion(ValueChangeEvent valueChangeEvent) {

		Integer temCodigo = NumberUtils.createInteger(String
				.valueOf(valueChangeEvent.getNewValue()));

		try {

			setListadoCursoTitulacionEstudianteTema(administracionServicio
					.obtenerCursoTitulacionEstudianteTema(temCodigo));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void btnCargarListaCursoTitulacionEstudiante() {
		try {

			setListadoCursoTitulacionEstudianteEstudiante(administracionServicio
					.obtenerCursoTitulacionEstudianteEstudiante(
							cursoTitulacionSelected, temaTitulacionSelected));

			// seleccionTema.setPerCodigo(new Persona());
			// seleccionTema.getPerCodigo().setPerCodigo(persona.getPerCodigo());
			// setListadoSeleccionTema(administracionServicio.obtenerSeleccionTemaAprobado(persona));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void seleccionarSeleccionTema(SeleccionTema seleccionTema) {
		try {

			/*
			 * setPersonaEstudianteSelected(seleccionTema.getPerCodigo().
			 * getPerCodigo()); setSeleccionTema(seleccionTema);
			 */

			/*
			 * if (CollectionUtils.isEmpty(listadoEstudianteSeleccionTema))
			 * setListadoEstudianteSeleccionTema(administracionServicio
			 * .obtenerEstudianteSeleccionTema(personaEstudianteSelected));
			 */

			setPanelDatos(Boolean.TRUE);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void seleccionarPersonaEstudiante(Persona persona) {

		try {

			setPanelDatos(Boolean.FALSE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void seleccionarCursoTitulacionEstudiante(
			CursoTitulacionEstudiante cursoTitulacionEstudiante) {

		
		// setTemaTitulacion(temaTitulacion);
		try {
			
			//setCursoTitulacionEstudiante(cursoTitulacionEstudiante);
			//proceso.setCursoTitulacionEstudiante(new CursoTitulacionEstudiante());
			//proceso.getCursoTitulacionEstudiante().setCurTitEstCodigo(Integer.parseInt("01"));
			/*proceso.setCursoTitulacion(new CursoTitulacion());
			proceso.getCursoTitulacion().setCurTitCodigo(Integer.parseInt("01"));*/
			setEstudianteSelected(cursoTitulacionEstudiante.getCurTitEstCodigo());
			
			//cursoTitulacionEstudiante.setSeleccionTema(new SeleccionTema());
			//cursoTitulacionEstudiante.getSeleccionTema().setSelTemCodigo();
			proceso.setCursoTitulacionEstudiante(new CursoTitulacionEstudiante());
			proceso.getCursoTitulacionEstudiante().setCurTitEstCodigo(estudianteSelected);
			
			//proceso.setSeleccionTema(new SeleccionTema());
			//proceso.getSeleccionTema().setSelTemCodigo(Integer.parseInt("0000000003"));
			
			administracionServicio.crearProceso(proceso);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * setListadoCursoTitulacionEstudiante(administracionServicio
		 * .obtenerCursoTitulacionEstudiante());
		 */

		// setPanelDatos(true);
	}

	public boolean isPanelDatos() {
		return panelDatos;
	}

	public void setPanelDatos(boolean panelDatos) {
		this.panelDatos = panelDatos;
	}

	public CursoTitulacionEstudiante getCursoTitulacionEstudiante() {
		return cursoTitulacionEstudiante;
	}

	public void setCursoTitulacionEstudiante(
			CursoTitulacionEstudiante cursoTitulacionEstudiante) {
		this.cursoTitulacionEstudiante = cursoTitulacionEstudiante;
	}

	public Collection<CursoTitulacionEstudiante> getListadoCursoTitulacionEstudiante() {
		return listadoCursoTitulacionEstudiante;
	}

	public void setListadoCursoTitulacionEstudiante(
			Collection<CursoTitulacionEstudiante> listadoCursoTitulacionEstudiante) {
		this.listadoCursoTitulacionEstudiante = listadoCursoTitulacionEstudiante;
	}

	public Integer getCursoTitulacionSelected() {
		return cursoTitulacionSelected;
	}

	public void setCursoTitulacionSelected(Integer cursoTitulacionSelected) {
		this.cursoTitulacionSelected = cursoTitulacionSelected;
	}

	public Collection<CursoTitulacionEstudiante> getListadoCursoTitulacionEstudianteTema() {
		return listadoCursoTitulacionEstudianteTema;
	}

	public void setListadoCursoTitulacionEstudianteTema(
			Collection<CursoTitulacionEstudiante> listadoCursoTitulacionEstudianteTema) {
		this.listadoCursoTitulacionEstudianteTema = listadoCursoTitulacionEstudianteTema;
	}

	public Integer getTemaTitulacionSelected() {
		return temaTitulacionSelected;
	}

	public void setTemaTitulacionSelected(Integer temaTitulacionSelected) {
		this.temaTitulacionSelected = temaTitulacionSelected;
	}

	public Collection<CursoTitulacionEstudiante> getListadoCursoTitulacionEstudianteEstudiante() {
		return listadoCursoTitulacionEstudianteEstudiante;
	}

	public void setListadoCursoTitulacionEstudianteEstudiante(
			Collection<CursoTitulacionEstudiante> listadoCursoTitulacionEstudianteEstudiante) {
		this.listadoCursoTitulacionEstudianteEstudiante = listadoCursoTitulacionEstudianteEstudiante;
	}

	public Collection<Proceso> getListadoProcesoCursoTitulacion() {
		return listadoProcesoCursoTitulacion;
	}

	public void setListadoProcesoCursoTitulacion(
			Collection<Proceso> listadoProcesoCursoTitulacion) {
		this.listadoProcesoCursoTitulacion = listadoProcesoCursoTitulacion;
	}

	public Integer getEstudianteSelected() {
		return estudianteSelected;
	}

	public void setEstudianteSelected(Integer estudianteSelected) {
		this.estudianteSelected = estudianteSelected;
	}

}
