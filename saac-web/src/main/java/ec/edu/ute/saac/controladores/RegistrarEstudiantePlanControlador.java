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

import com.mysql.fabric.xmlrpc.base.Array;

import ec.edu.ute.saac.entidades.Carrera;
import ec.edu.ute.saac.entidades.Catalogo;
import ec.edu.ute.saac.entidades.CursoTitulacion;
import ec.edu.ute.saac.entidades.Facultad;
import ec.edu.ute.saac.entidades.GrupoCatalogo;
import ec.edu.ute.saac.entidades.LineaInvestigacion;
import ec.edu.ute.saac.entidades.Periodos;
import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.PersonaCarrera;
import ec.edu.ute.saac.entidades.Rol;
import ec.edu.ute.saac.entidades.TemasTitulacion;
import ec.edu.ute.saac.entidades.Usuario;
import ec.edu.ute.saac.entidades.UsuarioRol;
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;

@ManagedBean
@ViewScoped
public class RegistrarEstudiantePlanControlador {

	private MessageSender sender;
	private Utilitarios utilitarios;
	private boolean panelDatos;
	private PersonaCarrera personaCarrera;
	private Persona persona;
	private Rol rol;

	private Integer facultadSelected;
	private Integer carreraSelected;
	private Integer nacionalidadSelected;
	private Integer estadoCivilSelected;
	private Integer generoSelected;
	private Integer usuarioSelected;
	private Object usuarioPassword;

	private Collection<Facultad> listadoFacultad;
	private Collection<Carrera> listadoCarrera;
	private Collection<PersonaCarrera> listadoPersonaCarrera;
	private Collection<Persona> listadoPersonaEstudiante;
	private Collection<Catalogo>listadoCatalogoNacionalidad;
	private Collection<Catalogo>listadoCatalogoEstadoCivil;
	private Collection<Catalogo>listadoCatalogoGenero;
	private Collection<Usuario>listadoUsuario;
	
	@Inject
	private IAdministracionServicio administracionServicio;

	public void activarPanelDatos() {
		setPanelDatos(Boolean.TRUE);
	}

	private void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		personaCarrera = new PersonaCarrera();
		persona = new Persona();
		rol=new Rol();

		listadoFacultad = new ArrayList<Facultad>();
		listadoCarrera = new ArrayList<Carrera>();
		listadoPersonaCarrera = new ArrayList<PersonaCarrera>();
		listadoPersonaEstudiante = new ArrayList<Persona>();
		listadoCatalogoNacionalidad=new ArrayList<Catalogo>();
		listadoCatalogoEstadoCivil=new ArrayList<Catalogo>();
		listadoCatalogoGenero=new ArrayList<Catalogo>();
		listadoUsuario=new ArrayList<Usuario>();
		
	}

	@PostConstruct
	public void inicializacion() {

		try {

			inicializacionEntidades();
			setPanelDatos(Boolean.FALSE);

			if (CollectionUtils.isEmpty(listadoFacultad)) {
				setListadoFacultad(administracionServicio.obtenerFacultad());
			}
			
			if (CollectionUtils.isEmpty(listadoCatalogoNacionalidad)) {
				setListadoCatalogoNacionalidad(administracionServicio.obtenerCatalogoNac() );
			}
			
			if (CollectionUtils.isEmpty(listadoCatalogoEstadoCivil)) {
				setListadoCatalogoEstadoCivil(administracionServicio.obtenerCatalogoEstadoCiv());
			}
			
			if (CollectionUtils.isEmpty(listadoCatalogoGenero)) {
				setListadoCatalogoGenero(administracionServicio.obtenerCatalogoGenero());
			}
			
			if (CollectionUtils.isEmpty(listadoUsuario)) {
				setListadoUsuario(administracionServicio.obtenerUsuarios());
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
	

	public void cbChangeNombreUsuario(ValueChangeEvent valueChangeEvent) {

		Integer usuCodigo = NumberUtils.createInteger(String
				.valueOf(valueChangeEvent.getNewValue()));
		try {
			persona.getUsuario().setUsuPassword(administracionServicio.obtenerPassUsuario(usuCodigo).toString() ) ;
			/*setListadoCarrera(administracionServicio
					.obtenerCarreraFacultad(facCodigo));*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void cbChangeFacultad(ValueChangeEvent valueChangeEvent) {

		Integer facCodigo = NumberUtils.createInteger(String
				.valueOf(valueChangeEvent.getNewValue()));

		try {
			setListadoCarrera(administracionServicio
					.obtenerCarreraFacultad(facCodigo));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void btnCargarListaPersonaCarrera() {
		try {

			/*
			 * setListadoPersonaCarrera(administracionServicio
			 * .obtenerPersonaCarrera(facultadSelected, carreraSelected));
			 */

			setListadoPersonaEstudiante(administracionServicio
					.obtenerPersonaCarreraFacultad(facultadSelected,
							carreraSelected));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizarEstudiante() {
		try {
			
			
			administracionServicio.actualizarPersona(persona);
			sender.sendInfoPopup("Registro Actualizado");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void seleccionarEstudiante(Persona persona) {
		setNacionalidadSelected(persona.getCatalogoNacionalidad().getCatCodigo());
		setEstadoCivilSelected(persona.getCatalogoEstadoCivil().getCatCodigo());
		setGeneroSelected(persona.getCatalogoGenero().getCatCodigo());
		setUsuarioSelected(persona.getUsuario().getUsuCodigo()); 
		setPersona(persona);
		// setPersonaCarrera(personaCarrera);
		setPanelDatos(Boolean.TRUE);
	}

	public boolean isPanelDatos() {
		return panelDatos;
	}

	public void setPanelDatos(boolean panelDatos) {
		this.panelDatos = panelDatos;
	}

	public PersonaCarrera getPersonaCarrera() {
		return personaCarrera;
	}

	public void setPersonaCarrera(PersonaCarrera personaCarrera) {
		this.personaCarrera = personaCarrera;
	}

	public Integer getFacultadSelected() {
		return facultadSelected;
	}

	public void setFacultadSelected(Integer facultadSelected) {
		this.facultadSelected = facultadSelected;
	}

	public Integer getCarreraSelected() {
		return carreraSelected;
	}

	public void setCarreraSelected(Integer carreraSelected) {
		this.carreraSelected = carreraSelected;
	}

	public Collection<Facultad> getListadoFacultad() {
		return listadoFacultad;
	}

	public void setListadoFacultad(Collection<Facultad> listadoFacultad) {
		this.listadoFacultad = listadoFacultad;
	}

	public Collection<Carrera> getListadoCarrera() {
		return listadoCarrera;
	}

	public void setListadoCarrera(Collection<Carrera> listadoCarrera) {
		this.listadoCarrera = listadoCarrera;
	}

	public Collection<PersonaCarrera> getListadoPersonaCarrera() {
		return listadoPersonaCarrera;
	}

	public void setListadoPersonaCarrera(
			Collection<PersonaCarrera> listadoPersonaCarrera) {
		this.listadoPersonaCarrera = listadoPersonaCarrera;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Collection<Persona> getListadoPersonaEstudiante() {
		return listadoPersonaEstudiante;
	}

	public void setListadoPersonaEstudiante(
			Collection<Persona> listadoPersonaEstudiante) {
		this.listadoPersonaEstudiante = listadoPersonaEstudiante;
	}

	public Integer getNacionalidadSelected() {
		return nacionalidadSelected;
	}

	public void setNacionalidadSelected(Integer nacionalidadSelected) {
		this.nacionalidadSelected = nacionalidadSelected;
	}

	public Collection<Catalogo> getListadoCatalogoNacionalidad() {
		return listadoCatalogoNacionalidad;
	}

	public void setListadoCatalogoNacionalidad(
			Collection<Catalogo> listadoCatalogoNacionalidad) {
		this.listadoCatalogoNacionalidad = listadoCatalogoNacionalidad;
	}

	public Integer getEstadoCivilSelected() {
		return estadoCivilSelected;
	}

	public void setEstadoCivilSelected(Integer estadoCivilSelected) {
		this.estadoCivilSelected = estadoCivilSelected;
	}

	public Integer getGeneroSelected() {
		return generoSelected;
	}

	public void setGeneroSelected(Integer generoSelected) {
		this.generoSelected = generoSelected;
	}

	public Collection<Catalogo> getListadoCatalogoEstadoCivil() {
		return listadoCatalogoEstadoCivil;
	}

	public void setListadoCatalogoEstadoCivil(
			Collection<Catalogo> listadoCatalogoEstadoCivil) {
		this.listadoCatalogoEstadoCivil = listadoCatalogoEstadoCivil;
	}

	public Collection<Catalogo> getListadoCatalogoGenero() {
		return listadoCatalogoGenero;
	}

	public void setListadoCatalogoGenero(Collection<Catalogo> listadoCatalogoGenero) {
		this.listadoCatalogoGenero = listadoCatalogoGenero;
	}

	public Collection<Usuario> getListadoUsuario() {
		return listadoUsuario;
	}

	public void setListadoUsuario(Collection<Usuario> listadoUsuario) {
		this.listadoUsuario = listadoUsuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Integer getUsuarioSelected() {
		return usuarioSelected;
	}

	public void setUsuarioSelected(Integer usuarioSelected) {
		this.usuarioSelected = usuarioSelected;
	}

	public Object getUsuarioPassword() {
		return usuarioPassword;
	}

	public void setUsuarioPassword(Object usuarioPassword) {
		this.usuarioPassword = usuarioPassword;
	}

	



}
