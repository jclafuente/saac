package ec.edu.ute.saac.controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
public class AdministracionRolControlador {

	private static final Log log = LogFactory
			.getLog(AdministracionRolControlador.class);
	private MessageSender sender;
	private Utilitarios utilitarios;
	private Rol rol;
	private boolean panelDatos;
	private Collection<Rol> listadoRoles;

	@Inject
	private IAdministracionServicio administracionServicio;

	@PostConstruct
	public void inicializacion() {

		try {

			inicializacionEntidades();
			setPanelDatos(false);

			if (listadoRoles.size() <= 0) {
				listadoRoles.addAll(administracionServicio.findAllRol());
			}

		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR, error);
			utilitarios
					.error(AdministracionControlador.class.getName(),
							"Error al inicializar los datos en: AdministracionRolControlador",
							e);
		}
	}

	private void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		rol = new Rol();
		listadoRoles = new ArrayList<Rol>();
	}

	public void activarPanelDatos() {

		initRol();
		setPanelDatos(true);
	}
	
	private void initRol(){
		setRol(new Rol()); 
	}

	public void crearRol() {
		try {

			rol.setRolEstado(true);
			administracionServicio.crearRol(rol);
			
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
	
	public void actualizarRol() {
        try {
        	
        	administracionServicio.actualizarRol(rol);
        
            MessageSender.sendInfo(Utilitarios.REGISTRO_ACTUALIZADO, null);
            utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_ACTUALIZADO, " ");
//            sender.sendInfoPopup("Registro Ingresado");
            inicializacion();
        } catch (Exception e) {
            String error = (String) e.getMessage();
            utilitarios.ponerMensajeError(Utilitarios.ERROR_REGISTRO_GUARDADO, error);
            utilitarios.error(AdministracionControlador.class.getName(), "Error al procesar registro", e);
        }
    }

	public void seleccionarRol(Rol rol) {
		setRol(rol);
		setPanelDatos(true);
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

	public Collection<Rol> getListadoRoles() {
		return listadoRoles;
	}

	public void setListadoRoles(Collection<Rol> listadoRoles) {
		this.listadoRoles = listadoRoles;
	}

}
