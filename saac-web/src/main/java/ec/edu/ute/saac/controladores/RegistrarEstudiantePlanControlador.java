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

import ec.edu.ute.saac.entidades.Catalogo;
import ec.edu.ute.saac.entidades.GrupoCatalogo;
import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.Rol;
import ec.edu.ute.saac.entidades.Usuario;
import ec.edu.ute.saac.entidades.UsuarioRol;
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;

@ManagedBean
@ViewScoped
public class RegistrarEstudiantePlanControlador {

	private static final Log log = LogFactory.getLog(RegistrarEstudiantePlanControlador.class);
	private MessageSender sender;
	private Utilitarios utilitarios;
	private boolean PanelDatos;
	private Persona persona;
	private Usuario usuario;
	private Catalogo catalogo;
	private UsuarioRol usuarioRol;

	public Collection<UsuarioRol> listadoPersonaEstudiante;
	public Collection<Catalogo> listadoCatalogoNac;
	public Collection<Catalogo> listadoCatalogoEstCiv;
	public Collection<Catalogo> listadoCatalogoGenero;

	public Integer nacionalidadSelected;
	public Integer estadoCivSelected;
	public Integer generoSelected;

	@Inject
	private IAdministracionServicio administracionServicio;

	public void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		persona = new Persona();
		catalogo = new Catalogo();
		usuario=new Usuario();
		usuarioRol=new UsuarioRol();

		listadoPersonaEstudiante = new ArrayList<UsuarioRol>();
		listadoCatalogoNac = new ArrayList<Catalogo>();
		listadoCatalogoEstCiv = new ArrayList<Catalogo>();
		listadoCatalogoGenero = new ArrayList<Catalogo>();
	}

	@PostConstruct
	public void inicializacion() {

		try {

			inicializacionEntidades();
			setPanelDatos(Boolean.FALSE);
			//usuario.setPersona(new Persona());
			if (CollectionUtils.isEmpty(listadoPersonaEstudiante)) {
				setListadoPersonaEstudiante(administracionServicio
						.obtenerPersonaEstudiante());
			}

			if (CollectionUtils.isEmpty(listadoCatalogoNac)) {
				setListadoCatalogoNac(administracionServicio
						.obtenerCatalogoNac());
			}

			if (CollectionUtils.isEmpty(listadoCatalogoEstCiv)) {
				setListadoCatalogoEstCiv(administracionServicio
						.obtenerCatalogoEstadoCiv());
			}

			if (CollectionUtils.isEmpty(listadoCatalogoGenero)) {
				setListadoCatalogoGenero(administracionServicio
						.obtenerCatalogoGenero());
			}

		} catch (Exception e) {

		}
	}

	public void crearEstudiante() {
		try {

			//usuario.setPerEstado(true);
			/*persona.setUsuario(new Usuario());
			persona.getUsuario().setUsuCodigo(usuario.getUsuCodigo());
			persona.getUsuario().setUsuUserName(usuario.getUsuUserName());
			persona.getUsuario().setUsuPassword(usuario.getUsuPassword());
			persona.getUsuario().setUsuFchRegistro(usuario.getUsuFchRegistro());
			persona.getUsuario().setUsuEstado(usuario.getUsuEstado());*/
		//	usuario.setPersona(new Persona());
			
		//	usuario.getPersona().getCatalogoNacionalidad().setCatCodigo(nacionalidadSelected);
		//	usuario.getPersona().getCatalogoEstadoCivil().setCatCodigo(estadoCivSelected);
	//		usuario.getPersona().getCatalogoGenero().setCatCodigo(generoSelected);
			/*persona.setCatalogoNacionalidad(new Catalogo());
			persona.getCatalogoNacionalidad().setCatCodigo(nacionalidadSelected);
			persona.setCatalogoEstadoCivil(new Catalogo());
			persona.getCatalogoEstadoCivil().setCatCodigo(estadoCivSelected);
			persona.setCatalogoGenero(new Catalogo());
			persona.getCatalogoGenero().setCatCodigo(generoSelected);*/
			
			administracionServicio.crearUsuarioRol(usuarioRol);
			
			//administracionServicio.crearPersona(persona);

			MessageSender.sendInfo(Utilitarios.REGISTRO_GUARDADO, null);
			utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_GUARDADO, " ");
			sender.sendInfoPopup("Registro Ingresado");

			inicializacion();
		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR_REGISTRO_GUARDADO,
					error);
			utilitarios.error(AdministracionControlador.class.getName(),
					"Error al procesar registro", e);
		}
	}

	public void crearUsuarioRol(){
		
		try {
			
			administracionServicio.crearUsuarioRol(usuarioRol);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cbChangeCatalogoNac(ValueChangeEvent valueChangeEvent) {

		Integer CatNacCodigo = NumberUtils.createInteger(String
				.valueOf(valueChangeEvent.getNewValue()));

		try {
			catalogo.setCatCodigo(CatNacCodigo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initPersona() {
		// /setPersona(new Persona());
	}

	public void seleccionarEstudiante(Persona persona) {
		// setPersona(persona);
		// setPanelDatos(true);
	}

	public void activarPanelDatos() {
		initPersona();
		setPanelDatos(true);
	}

	public boolean isPanelDatos() {
		return PanelDatos;
	}

	public void setPanelDatos(boolean panelDatos) {
		PanelDatos = panelDatos;
	}

	public Collection<Catalogo> getListadoCatalogoNac() {
		return listadoCatalogoNac;
	}

	public void setListadoCatalogoNac(Collection<Catalogo> listadoCatalogoNac) {
		this.listadoCatalogoNac = listadoCatalogoNac;
	}

	public Integer getNacionalidadSelected() {
		return nacionalidadSelected;
	}

	public void setNacionalidadSelected(Integer nacionalidadSelected) {
		this.nacionalidadSelected = nacionalidadSelected;
	}

	public Integer getEstadoCivSelected() {
		return estadoCivSelected;
	}

	public void setEstadoCivSelected(Integer estadoCivSelected) {
		this.estadoCivSelected = estadoCivSelected;
	}

	public Collection<Catalogo> getListadoCatalogoEstCiv() {
		return listadoCatalogoEstCiv;
	}

	public void setListadoCatalogoEstCiv(
			Collection<Catalogo> listadoCatalogoEstCiv) {
		this.listadoCatalogoEstCiv = listadoCatalogoEstCiv;
	}

	public Integer getGeneroSelected() {
		return generoSelected;
	}

	public void setGeneroSelected(Integer generoSelected) {
		this.generoSelected = generoSelected;
	}

	public Collection<Catalogo> getListadoCatalogoGenero() {
		return listadoCatalogoGenero;
	}

	public void setListadoCatalogoGenero(
			Collection<Catalogo> listadoCatalogoGenero) {
		this.listadoCatalogoGenero = listadoCatalogoGenero;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Collection<UsuarioRol> getListadoPersonaEstudiante() {
		return listadoPersonaEstudiante;
	}

	public void setListadoPersonaEstudiante(
			Collection<UsuarioRol> listadoPersonaEstudiante) {
		this.listadoPersonaEstudiante = listadoPersonaEstudiante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioRol getUsuarioRol() {
		return usuarioRol;
	}

	public void setUsuarioRol(UsuarioRol usuarioRol) {
		this.usuarioRol = usuarioRol;
	}

}
