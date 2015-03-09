package ec.edu.ute.saac.controladores;

import java.util.ArrayList;
import java.util.Collection;

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
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;

@ManagedBean
@ViewScoped
public class AdministracionFacultadControlador {

	private static final Log log = LogFactory
			.getLog(AdministracionFacultadControlador.class);
	private MessageSender sender;
	private Utilitarios utilitarios;
	private Facultad facultad;
	private Carrera carrera;
	private Rol rol;
	private boolean panelDatos;
	private Integer facultadSelected;
	private Collection<Facultad> listadoFacultad;

	@Inject
	private IAdministracionServicio administracionServicio;

	@PostConstruct
	public void inicializacion() {

		try {

			inicializacionEntidades();
			setPanelDatos(false);

			if (listadoFacultad.size() <= 0) {
				listadoFacultad.addAll(administracionServicio.findAll());
			}

		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR, error);
			utilitarios
					.error(AdministracionControlador.class.getName(),
							"Error al inicializar los datos en: AdministracionFacultadControlador",
							e);
		}
	}

	private void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		carrera = new Carrera();
		listadoFacultad = new ArrayList<Facultad>();
	}

	public void activarPanelDatos() {
		Facultad facultad = new Facultad();
		setFacultad(facultad);
		Carrera carrera = new Carrera();
		setCarrera(carrera);

		setPanelDatos(true);

	}

	public void seleccionarFacultad(Facultad facultad) {
		log.info("facultad seleccionada -->> " + facultad.getFacNombre());
		setFacultad(facultad);
		setPanelDatos(true);
	}

	public void crearFacultad() {
		try {

			facultad.setFacEstado(Boolean.TRUE);
			administracionServicio.crearFacultad(facultad);

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

	public void actualizarFacultad() {
		try {

			administracionServicio.actualizarFacultad(facultad);

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

	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public boolean isPanelDatos() {
		return panelDatos;
	}

	public void setPanelDatos(boolean panelDatos) {
		this.panelDatos = panelDatos;
	}

	public Collection<Facultad> getListadoFacultad() {
		return listadoFacultad;
	}

	public void setListadoFacultad(Collection<Facultad> listadoFacultad) {
		this.listadoFacultad = listadoFacultad;
	}

}
