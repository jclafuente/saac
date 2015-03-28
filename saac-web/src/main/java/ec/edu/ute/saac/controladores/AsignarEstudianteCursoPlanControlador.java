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

import ec.edu.ute.saac.entidades.AreaInvestigacion;
import ec.edu.ute.saac.entidades.Carrera;
import ec.edu.ute.saac.entidades.CursoTitulacion;
import ec.edu.ute.saac.entidades.CursoTitulacionEstudiante;
import ec.edu.ute.saac.entidades.LineaInvestigacion;
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
	private Integer cursoSelected;
	private Integer estudianteSelected;

	private CursoTitulacionEstudiante cursoTitulacionEstudiante;

	private Collection<CursoTitulacion> listadoCursoTitulacion;
	private Collection<SeleccionTema> listadoEstudianteSeleccionTema;
	private Collection<CursoTitulacionEstudiante> listadoCursoTitulacionEstudiante;

	@Inject
	private IAdministracionServicio administracionServicio;

	public void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		cursoTitulacionEstudiante = new CursoTitulacionEstudiante();

		listadoCursoTitulacion = new ArrayList<CursoTitulacion>();
		listadoEstudianteSeleccionTema = new ArrayList<SeleccionTema>();
		listadoCursoTitulacionEstudiante = new ArrayList<CursoTitulacionEstudiante>();

	}

	@PostConstruct
	public void inicializacion() {
		inicializacionEntidades();
		setPanelDatos(Boolean.FALSE);
		try {

			/*if (CollectionUtils.isEmpty(listadoCursoTitulacion)) {
				setListadoCursoTitulacion(administracionServicio
						.obtenerCursoTitulacion());
			}*/

			if (CollectionUtils.isEmpty(listadoEstudianteSeleccionTema)) {
				setListadoEstudianteSeleccionTema(administracionServicio
						.obtenerEstudiantesSeleccionTema());
			}

			if (CollectionUtils.isEmpty(listadoCursoTitulacionEstudiante)) {
				setListadoCursoTitulacionEstudiante(administracionServicio
						.obtenerCursoTitulacionEstudiante());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void btnAsignarEstudianteCursoTitulacion() {
		try {

			cursoTitulacionEstudiante.setSeleccionTema(new SeleccionTema());
			cursoTitulacionEstudiante.getSeleccionTema().setSelTemCodigo(
					getEstudianteSelected());
			cursoTitulacionEstudiante.setCursoTitulacion(new CursoTitulacion());
			cursoTitulacionEstudiante.getCursoTitulacion().setCurTitCodigo(
					getCursoSelected());

			administracionServicio
					.crearCursoTitulacionEstudiante(cursoTitulacionEstudiante);

			MessageSender.sendInfo(Utilitarios.REGISTRO_GUARDADO, null);
			utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_GUARDADO, " ");
			sender.sendInfoPopup("Registro Ingresado");

			getListadoCursoTitulacionEstudiante()
					.add(cursoTitulacionEstudiante);
			inicializacion();
			limpiarCombos();
			activarPanelDatos();
			setPanelDatos(Boolean.TRUE);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void seleccionarCursoTitulacionEstudiante(
			CursoTitulacionEstudiante cursoTitulacionEstudiante) {
		
		setCursoSelected(cursoTitulacionEstudiante.getCursoTitulacion()
				.getCurTitCodigo());
		setEstudianteSelected(cursoTitulacionEstudiante.getSeleccionTema()
				.getPerCodigo().getPerCodigo());

		setCursoTitulacionEstudiante(cursoTitulacionEstudiante);
		setPanelDatos(Boolean.TRUE);
	}

	public void retornarPagina() {
		setPanelDatos(Boolean.FALSE);
		/*
		 * periodoTitulacionSelected=Integer.valueOf(0);
		 * personaDocenteSelected=Integer.valueOf(0);
		 */
	}

	public void limpiarCombos() {
		// setCursoTitulacionEstudiante(new CursoTitulacionEstudiante());
		cursoSelected = Integer.valueOf(0);
		estudianteSelected = Integer.valueOf(0);
	}

	public void btnEliminarCursoTitulacionEstudiante(
			CursoTitulacionEstudiante cursoTitulacionEstudiante) {

		try {

			administracionServicio
					.eliminarCursoTitulacionEstudiante(cursoTitulacionEstudiante);
			inicializacion();
			activarPanelDatos();
			setPanelDatos(Boolean.TRUE);

			MessageSender.sendInfo(Utilitarios.REGISTRO_ELIMINADO, null);
			utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_ELIMINADO, " ");
			sender.sendInfoPopup("Registro Eliminado");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void btnActualizarCursoTitulacionEstudiante() {

		try {

			cursoTitulacionEstudiante.setCursoTitulacion(new CursoTitulacion());
			cursoTitulacionEstudiante.getCursoTitulacion().setCurTitCodigo(
					cursoSelected);
			cursoTitulacionEstudiante.setSeleccionTema(new SeleccionTema());
			cursoTitulacionEstudiante.getSeleccionTema().setSelTemCodigo(
					estudianteSelected);
			administracionServicio
					.actualizarCursoTitulacionEstudiante(cursoTitulacionEstudiante);

			MessageSender.sendInfo(Utilitarios.REGISTRO_ACTUALIZADO, null);
			utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_ACTUALIZADO, " ");
			sender.sendInfoPopup("Registro Actualizado");
			inicializacion();
			limpiarCombos();
			activarPanelDatos();
			setPanelDatos(Boolean.TRUE);
			//getListadoCursoTitulacionEstudiante()	.add(cursoTitulacionEstudiante);
			// setPanelDatos(Boolean.FALSE);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void activarPanelDatos() {
		limpiarCombos();
		setCursoTitulacionEstudiante(new CursoTitulacionEstudiante());
		setPanelDatos(true);
	}

	public boolean isPanelDatos() {
		return panelDatos;
	}

	public void setPanelDatos(boolean panelDatos) {
		this.panelDatos = panelDatos;
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

	public Integer getEstudianteSelected() {
		return estudianteSelected;
	}

	public void setEstudianteSelected(Integer estudianteSelected) {
		this.estudianteSelected = estudianteSelected;
	}

	public Collection<SeleccionTema> getListadoEstudianteSeleccionTema() {
		return listadoEstudianteSeleccionTema;
	}

	public void setListadoEstudianteSeleccionTema(
			Collection<SeleccionTema> listadoEstudianteSeleccionTema) {
		this.listadoEstudianteSeleccionTema = listadoEstudianteSeleccionTema;
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

}
