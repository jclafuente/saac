package ec.edu.ute.saac.controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ec.edu.ute.saac.entidades.Carrera;
import ec.edu.ute.saac.entidades.Facultad;
import ec.edu.ute.saac.entidades.Rol;
import ec.edu.ute.saac.servicios.administracion.AdministracionServicioImpl;
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;

@ManagedBean
@ViewScoped
public class AdministracionCarreraControlador {

	private static final Log log = LogFactory
			.getLog(AdministracionCarreraControlador.class);
	private MessageSender sender;
	private Utilitarios utilitarios;
	private Carrera carrera;
	private Facultad facultad;
	private Rol rol;
	private Collection<Carrera> listadoCarreras;
	private Collection<SelectItem> listadoFacultadCarrera;
	private Collection<Facultad> listadoFacultad;
	private boolean panelDatos;
	private Integer facultadSelected;

	@Inject
	private IAdministracionServicio administracionServicio;

	public AdministracionCarreraControlador() {

	}

	@PostConstruct
	public void inicializacion() {

		try {

			inicializacionEntidades();
			setPanelDatos(false);

			if (listadoCarreras.size() <= 0) {
				listadoCarreras.addAll(administracionServicio
						.findCarreraFacultad());
			}

			if (listadoFacultad.size() <= 0) {
				listadoFacultad.addAll(administracionServicio.findAll());
			}

			if (getListadoFacultadCarrera().size() <= 0) {
				List<SelectItem> lista = new ArrayList<SelectItem>();
				for (Facultad fac : listadoFacultad) {
					lista.add(new SelectItem(fac.getFacCodigo(), fac.getFacNombre()));
				}
				getListadoFacultadCarrera().addAll(lista);
			}

		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR, error);
			utilitarios
					.error(AdministracionControlador.class.getName(),
							"Error al inicializar los datos en: AdministracionCarreraControlador",
							e);
		}
	}

	private void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		carrera = new Carrera();
		listadoCarreras = new ArrayList<Carrera>();
		listadoFacultadCarrera = new ArrayList<SelectItem>();
		listadoFacultad = new ArrayList<Facultad>();
	}

	public void seleccionarCarrera(Carrera carrera) {
		setCarrera(carrera);
		setPanelDatos(true);
		cargarCombosCarrera();
	}

	public void activarPanelDatos() {
		Facultad facultad = new Facultad();
		setFacultad(facultad);
		Carrera carrera = new Carrera();
		setCarrera(carrera);

		setPanelDatos(true);
		limpiarCombosCarrera();
	}

	public void limpiarCombosCarrera() {
		facultadSelected = Integer.valueOf(0);
	}

	public void crearCarrera() {

		try {

			administracionServicio.crearCarrera(carrera);

			MessageSender.sendInfo(Utilitarios.REGISTRO_GUARDADO, null);
			utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_GUARDADO, " ");
			// sender.sendInfoPopup("Registro Ingresado");
			inicializacion();
		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR_REGISTRO_GUARDADO,
					error);
			utilitarios.error(AdministracionControlador.class.getName(),
					"Error al procesar registro", e);
		}
	}

	public void actualizarCarrera() {

		try {

			administracionServicio.actualizarCarrera(carrera);

			MessageSender.sendInfo(Utilitarios.REGISTRO_ACTUALIZADO, null);
			utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_ACTUALIZADO, " ");
			// sender.sendInfoPopup("Registro Ingresado");
			inicializacion();
		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR_REGISTRO_GUARDADO,
					error);
			utilitarios.error(AdministracionControlador.class.getName(),
					"Error al procesar registro", e);
		}
	}

	public void cargarCombosCarrera() {
		setFacultadSelected(carrera.getFacultad().getFacCodigo());
	}

	public Collection<Carrera> getListadoCarreras() {
		return listadoCarreras;
	}

	public void setListadoCarreras(Collection<Carrera> listadoCarreras) {
		this.listadoCarreras = listadoCarreras;
	}

	public boolean isPanelDatos() {
		return panelDatos;
	}

	public void setPanelDatos(boolean panelDatos) {
		this.panelDatos = panelDatos;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Integer getFacultadSelected() {
		return facultadSelected;
	}

	public void setFacultadSelected(Integer facultadSelected) {
		this.facultadSelected = facultadSelected;
	}

	public Collection<SelectItem> getListadoFacultadCarrera() {
		return listadoFacultadCarrera;
	}

	public void setListadoFacultadCarrera(
			Collection<SelectItem> listadoFacultadCarrera) {
		this.listadoFacultadCarrera = listadoFacultadCarrera;
	}

	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Collection<Facultad> getListadoFacultad() {
		return listadoFacultad;
	}

	public void setListadoFacultad(Collection<Facultad> listadoFacultad) {
		this.listadoFacultad = listadoFacultad;
	}

	
}
