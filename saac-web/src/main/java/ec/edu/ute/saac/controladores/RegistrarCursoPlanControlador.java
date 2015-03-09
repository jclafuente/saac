package ec.edu.ute.saac.controladores;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

import ec.edu.ute.saac.entidades.CursoTitulacion;
import ec.edu.ute.saac.entidades.Periodos;
import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.TemasTitulacion;
import ec.edu.ute.saac.entidades.Usuario;
import ec.edu.ute.saac.entidades.UsuarioRol;
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;

@ManagedBean
@ViewScoped
public class RegistrarCursoPlanControlador {

	private MessageSender sender;
	private Utilitarios utilitarios;
	private CursoTitulacion cursoTitulacion;

	private boolean panelDatos;
	private Collection<CursoTitulacion> listadoCursoTitulacion;
	private Collection<Persona> listadoPersonaDocente;
	private Collection<Periodos> listadoPeriodo;
	private Collection<Usuario> listadoUsuario;

	private Integer periodoTitulacionSelected;
	private Integer cursoTitulacionSelected;
	private Integer personaDocenteSelected;

	@Inject
	private IAdministracionServicio administracionServicio;

	public RegistrarCursoPlanControlador() {

	}

	private void inicializacionEntidades() {

		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		cursoTitulacion = new CursoTitulacion();

		listadoPersonaDocente = new ArrayList<Persona>();
		listadoCursoTitulacion = new ArrayList<CursoTitulacion>();
		listadoPeriodo = new ArrayList<Periodos>();
		listadoUsuario = new ArrayList<Usuario>();
	}

	@PostConstruct
	public void inicializacion() {

		try {

			inicializacionEntidades();
			setPanelDatos(Boolean.FALSE);
			// cargarCombos();

			if (CollectionUtils.isEmpty(listadoCursoTitulacion)) {
				setListadoCursoTitulacion(administracionServicio
						.obtenerCursoTitulacion());
			}

			if (CollectionUtils.isEmpty(listadoPeriodo)) {
				setListadoPeriodo(administracionServicio.obtenerPeriodo());
			}

			/*
			 * if (CollectionUtils.isEmpty(listadoUsuario)) {
			 * setListadoUsuario(administracionServicio.obtenerUsuario()); }
			 */

			if (CollectionUtils.isEmpty(listadoPersonaDocente)) {
				setListadoPersonaDocente(administracionServicio
						.obtenerPersonaUsuario());
			}

		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR, error);
			utilitarios
					.error(RegistrarCursoPlanControlador.class.getName(),
							"Error al inicializar los datos en: RegistrarCursoPlanControlador",
							e);
		}

	}

	public void retornarPagina() {
		setPanelDatos(Boolean.FALSE);
		periodoTitulacionSelected = Integer.valueOf(0);
		personaDocenteSelected = Integer.valueOf(0);
	}

	public void btnCrearCursoTitulacion() {
		try {

			cursoTitulacion.setPersona(new Persona());
			cursoTitulacion.getPersona().setPerCodigo(
					getPersonaDocenteSelected());
			cursoTitulacion.setPeriodos(new Periodos());
			cursoTitulacion.getPeriodos().setPrdCodigo(
					getPeriodoTitulacionSelected());
			cursoTitulacion.setCurTitEstado("REGISTRADO");
			// cursoTitulacion.setCurTitNombre("borrar");
			administracionServicio.crearCursoTitulacion(cursoTitulacion);

			MessageSender.sendInfo(Utilitarios.REGISTRO_GUARDADO, null);
			utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_GUARDADO, " ");
			sender.sendInfoPopup("Registro Ingresado");

			getListadoCursoTitulacion().add(cursoTitulacion);
			inicializacion();
			limpiarCombos();
			activarPanelDatos();
			setPanelDatos(Boolean.TRUE);

		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR_REGISTRO_GUARDADO,
					error);
			utilitarios.error(RegistrarCursoPlanControlador.class.getName(),
					"Error al procesar registro", e);
		}
	}

	public void btnGuardarCursoTitulacion() {
		try {

			cursoTitulacion.setPersona(new Persona());
			cursoTitulacion.getPersona().setPerCodigo(
					getPersonaDocenteSelected());
			cursoTitulacion.setPeriodos(new Periodos());
			cursoTitulacion.getPeriodos().setPrdCodigo(
					getCursoTitulacionSelected());
			cursoTitulacion.setCurTitEstado("REGISTRADO");
			// cursoTitulacion.setCurTitNombre("borrar");
			administracionServicio.crearCursoTitulacion(cursoTitulacion);

			MessageSender.sendInfo(Utilitarios.REGISTRO_GUARDADO, null);
			utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_GUARDADO, " ");
			sender.sendInfoPopup("Registro Ingresado");

			getListadoCursoTitulacion().add(cursoTitulacion);
			inicializacion();
			// setPanelDatos(Boolean.TRUE);

		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR_REGISTRO_GUARDADO,
					error);
			utilitarios.error(RegistrarCursoPlanControlador.class.getName(),
					"Error al procesar registro", e);
		}
	}

	public void btnEliminarCursoTitulacion(CursoTitulacion cursoTitulacion) {

		try {

			administracionServicio.eliminarCursoTitulacion(cursoTitulacion);
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

	public void btnActualizarCursoTitulacion() {
		try {

			administracionServicio.actualizarCursoTitulacion(cursoTitulacion);
			MessageSender.sendInfo(Utilitarios.REGISTRO_ACTUALIZADO, null);
			utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_ACTUALIZADO, " ");
			sender.sendInfoPopup("Registro Actualizado");
			// setPanelDatos(Boolean.FALSE);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void seleccionarCursoTitulacion(CursoTitulacion cursoTitulacion) {
		setPeriodoTitulacionSelected(cursoTitulacion.getPeriodos()
				.getPrdCodigo());
		setPersonaDocenteSelected(cursoTitulacion.getPersona().getPerCodigo());
		setCursoTitulacion(cursoTitulacion);
		setPanelDatos(Boolean.TRUE);
	}

	public void activarPanelDatos() {
		setPanelDatos(true);
		limpiarCombos();
	}

	public void limpiarCombos() {
		setCursoTitulacion(new CursoTitulacion());
		periodoTitulacionSelected = Integer.valueOf(0);
		personaDocenteSelected = Integer.valueOf(0);
	}

	public boolean isPanelDatos() {
		return panelDatos;
	}

	public void setPanelDatos(boolean panelDatos) {
		this.panelDatos = panelDatos;
	}

	public Collection<CursoTitulacion> getListadoCursoTitulacion() {
		return listadoCursoTitulacion;
	}

	public Collection<Periodos> getListadoPeriodo() {
		return listadoPeriodo;
	}

	public void setListadoPeriodo(Collection<Periodos> listadoPeriodo) {
		this.listadoPeriodo = listadoPeriodo;
	}

	public void setListadoCursoTitulacion(
			Collection<CursoTitulacion> listadoCursoTitulacion) {
		this.listadoCursoTitulacion = listadoCursoTitulacion;
	}

	public Integer getCursoTitulacionSelected() {
		return cursoTitulacionSelected;
	}

	public void setCursoTitulacionSelected(Integer cursoTitulacionSelected) {
		this.cursoTitulacionSelected = cursoTitulacionSelected;
	}

	public Collection<Persona> getListadoPersonaDocente() {
		return listadoPersonaDocente;
	}

	public void setListadoPersonaDocente(
			Collection<Persona> listadoPersonaDocente) {
		this.listadoPersonaDocente = listadoPersonaDocente;
	}

	public Integer getPersonaDocenteSelected() {
		return personaDocenteSelected;
	}

	public void setPersonaDocenteSelected(Integer personaDocenteSelected) {
		this.personaDocenteSelected = personaDocenteSelected;
	}

	public CursoTitulacion getCursoTitulacion() {
		return cursoTitulacion;
	}

	public void setCursoTitulacion(CursoTitulacion cursoTitulacion) {
		this.cursoTitulacion = cursoTitulacion;
	}

	public Collection<Usuario> getListadoUsuario() {
		return listadoUsuario;
	}

	public void setListadoUsuario(Collection<Usuario> listadoUsuario) {
		this.listadoUsuario = listadoUsuario;
	}

	public Integer getPeriodoTitulacionSelected() {
		return periodoTitulacionSelected;
	}

	public void setPeriodoTitulacionSelected(Integer periodoTitulacionSelected) {
		this.periodoTitulacionSelected = periodoTitulacionSelected;
	}

}
