package ec.edu.ute.saac.controladores;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ec.edu.ute.saac.entidades.AreaInvestigacion;
import ec.edu.ute.saac.entidades.Carrera;
import ec.edu.ute.saac.entidades.LineaInvestigacion;
import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.TemasTitulacion;
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;

@ManagedBean
@ViewScoped
public class DocenteControlador {

	private static final Log log = LogFactory
			.getLog(DocenteControlador.class);
	private MessageSender sender;
	private Utilitarios utilitarios;
	private Carrera carrera;
	private LineaInvestigacion lineaInvestigacion;
	private AreaInvestigacion areaInvestigacion;
	private TemasTitulacion temaTitulacion;

	// private Collection<SelectItem> listadoCarrera;
	private Collection<Carrera> listadoCarreras;
	private Collection<TemasTitulacion> listadoTemaTitulacion;

	private Collection<SelectItem> listadolineaInvestigacionCarrera;
	private Collection<LineaInvestigacion> listadolineaInvestigacion;

	private Collection<SelectItem> listadoAreaInvestigacionLineaInvestigacion;
	private Collection<AreaInvestigacion> listadoAreaInvestigacion;

	private boolean panelDatos;
	private Integer carreraSelected;
	private Collection<Persona> listadoPersonas;
	private Integer lineaInvestigacionSelected;
	private Integer areaInvestigacionSelected;
	private boolean estadoTemaTitulacion;

	
	@Inject
	private IAdministracionServicio administracionServicio;

	public DocenteControlador() {
		System.out.println("constructor");
		
	}

	private void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		carrera = new Carrera();
		lineaInvestigacion = new LineaInvestigacion();
		areaInvestigacion = new AreaInvestigacion();
		temaTitulacion = new TemasTitulacion();

		listadolineaInvestigacionCarrera = new ArrayList<SelectItem>();
		listadolineaInvestigacion = new ArrayList<LineaInvestigacion>();

		listadoAreaInvestigacionLineaInvestigacion = new ArrayList<SelectItem>();
		listadoAreaInvestigacion = new ArrayList<AreaInvestigacion>();

		listadoCarreras = new ArrayList<Carrera>();
		// listadoCarrera = new ArrayList<SelectItem>();

		listadoPersonas = new ArrayList<Persona>();
		listadoTemaTitulacion = new ArrayList<TemasTitulacion>();

	}

	public void retornarPagina(){
		setPanelDatos(Boolean.FALSE);
	}
	@PostConstruct
	public void inicializacion() {

		try {
			inicializacionEntidades();
			setPanelDatos(Boolean.FALSE);
			cargarCombos();
			if (CollectionUtils.isEmpty(listadoCarreras)) {
				setListadoCarreras(administracionServicio.obtenerCarrera());
			}

			if (CollectionUtils.isEmpty(listadoPersonas)) {
				setListadoPersonas(administracionServicio.obtenerPersona());
			}

			/*
			 * if (CollectionUtils.isEmpty(listadoTemaTitulacion)){
			 * setListadoTemaTitulacion
			 * (administracionServicio.obtenerTemaTitulacion(null, null, null));
			 * }
			 */

			/*
			 * if (getListadolineaInvestigacionCarrera().size() <= 0) {
			 * Collection<SelectItem> lista = new ArrayList<SelectItem>(); for
			 * (LineaInvestigacion linea : listadolineaInvestigacion) {
			 * lista.add(new SelectItem(linea.getLinInvCodigo(), linea
			 * .getLinInvNombre())); }
			 * getListadolineaInvestigacionCarrera().addAll(lista); }
			 */

			// if (getListadoCarrera().size() <= 0) {
			// Collection<SelectItem> lista = new ArrayList<SelectItem>();
			// for (Carrera carrera : listadoCarreras) {
			// lista.add(new SelectItem(carrera.getCarCodigo(), carrera
			// .getCarNombre()));
			// }
			// getListadoCarrera().addAll(lista);
			// }

			/*
			 * if (getListadoAreaInvestigacionLineaInvestigacion().size() <= 0)
			 * { Collection<SelectItem> lista = new ArrayList<SelectItem>(); for
			 * (AreaInvestigacion area : listadoAreaInvestigacion) {
			 * lista.add(new SelectItem(area.getAreInvCodigo(), area
			 * .getAreInvNombre())); }
			 * getListadoAreaInvestigacionLineaInvestigacion().addAll(lista); }
			 */

		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR, error);
			utilitarios.error(AdministracionControlador.class.getName(),
					"Error al inicializar los datos en: DocenteControlador", e);
		}

	}
	
	public void seleccionar(Persona persona) {
//      setPersona((Persona) evt.getComponent().getAttributes().get("select"));
      if (persona != null) {
         // setPersona(persona);
      }
//      log.info(this.persona.getPerCodigo());
      setPanelDatos(true);
//      bandFacultafCarrera = true;
//      cargaCombos();
  }

	public void btnCargarListaPersona() {
		try {
			setListadoPersonas(administracionServicio.obtenerPersona(
					carreraSelected, null, null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void btnCargarListaTemasTitulacion() {
		try {
			setListadoTemaTitulacion(administracionServicio
					.obtenerTemaTitulacion(carreraSelected,
							lineaInvestigacionSelected,
							areaInvestigacionSelected));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cbChangeCarrera(ValueChangeEvent valueChangeEvent) {

		Integer carCodigo = NumberUtils.createInteger(String
				.valueOf(valueChangeEvent.getNewValue()));

		try {
			setListadolineaInvestigacion(administracionServicio
					.obtenerLineaInvestigacionCarrera(carCodigo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cbChangeLineaInvestigacion(ValueChangeEvent valueChangeEvent) {

		Integer linCodigo = NumberUtils.createInteger(String
				.valueOf(valueChangeEvent.getNewValue()));

		try {
			setListadoAreaInvestigacion(administracionServicio
					.obtenerAreaInvestigacionLineaInvestigacion(linCodigo));
			/*
			 * setListadolineaInvestigacion(administracionServicio
			 * .obtenerLineaInvestigacionCarrera(linCodigo));
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void seleccionarTemaTitulacion(TemasTitulacion temaTitulacion) {
		setTemaTitulacion(temaTitulacion);
		setPanelDatos(true);
	}

	public void activarPanelDatos() {
		setPanelDatos(true);
		limpiarCombos();
	}

	public void limpiarCombos() {
		carreraSelected = Integer.valueOf(0);
		lineaInvestigacionSelected = Integer.valueOf(0);
		areaInvestigacionSelected = Integer.valueOf(0);
	}

	public void cargarCombos() {
		setCarreraSelected(carrera.getCarCodigo());
		setLineaInvestigacionSelected(lineaInvestigacion.getLinInvCodigo());
		setAreaInvestigacionSelected(areaInvestigacion.getAreInvCodigo());
	}

	// public Collection<SelectItem> getListadoCarrera() {
	// return listadoCarrera;
	// }
	//
	// public void setListadoCarrera(Collection<SelectItem> listadoCarrera) {
	// this.listadoCarrera = listadoCarrera;
	// }

	
	public void btnAprobarTemaTitulacion(String estado) {
		try {
			administracionServicio.actualizarTemaTitulacion(temaTitulacion, estado);
			
			//administracionServicio.actualizarTemaTitulacion(temaTitulacion);
			
		} catch (Exception e) {
			MessageSender.sendInfo(Utilitarios.REGISTRO_ACTUALIZADO, null);
			utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_ACTUALIZADO, " ");
			// sender.sendInfoPopup("Registro Ingresado");
			inicializacion();

			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR_REGISTRO_GUARDADO,
					error);
			utilitarios.error(AdministracionControlador.class.getName(),
					"Error al procesar registro", e);
		}

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

	public Integer getCarreraSelected() {
		return carreraSelected;
	}

	public void setCarreraSelected(Integer carreraSelected) {
		this.carreraSelected = carreraSelected;
	}

	public Collection<Persona> getListadoPersonas() {
		return listadoPersonas;
	}

	public void setListadoPersonas(Collection<Persona> listadoPersonas) {
		this.listadoPersonas = listadoPersonas;
	}

	public LineaInvestigacion getLineaInvestigacion() {
		return lineaInvestigacion;
	}

	public void setLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
		this.lineaInvestigacion = lineaInvestigacion;
	}

	public Collection<SelectItem> getListadolineaInvestigacionCarrera() {
		return listadolineaInvestigacionCarrera;
	}

	public void setListadolineaInvestigacionCarrera(
			Collection<SelectItem> listadolineaInvestigacionCarrera) {
		this.listadolineaInvestigacionCarrera = listadolineaInvestigacionCarrera;
	}

	public Collection<LineaInvestigacion> getListadolineaInvestigacion() {
		return listadolineaInvestigacion;
	}

	public void setListadolineaInvestigacion(
			Collection<LineaInvestigacion> listadolineaInvestigacion) {
		this.listadolineaInvestigacion = listadolineaInvestigacion;
	}

	public Integer getLineaInvestigacionSelected() {
		return lineaInvestigacionSelected;
	}

	public void setLineaInvestigacionSelected(Integer lineaInvestigacionSelected) {
		this.lineaInvestigacionSelected = lineaInvestigacionSelected;
	}

	public Collection<SelectItem> getListadoAreaInvestigacionLineaInvestigacion() {
		return listadoAreaInvestigacionLineaInvestigacion;
	}

	public void setListadoAreaInvestigacionLineaInvestigacion(
			Collection<SelectItem> listadoAreaInvestigacionLineaInvestigacion) {
		this.listadoAreaInvestigacionLineaInvestigacion = listadoAreaInvestigacionLineaInvestigacion;
	}

	public Collection<AreaInvestigacion> getListadoAreaInvestigacion() {
		return listadoAreaInvestigacion;
	}

	public void setListadoAreaInvestigacion(
			Collection<AreaInvestigacion> listadoAreaInvestigacion) {
		this.listadoAreaInvestigacion = listadoAreaInvestigacion;
	}

	public Integer getAreaInvestigacionSelected() {
		return areaInvestigacionSelected;
	}

	public void setAreaInvestigacionSelected(Integer areaInvestigacionSelected) {
		this.areaInvestigacionSelected = areaInvestigacionSelected;
	}

	public Collection<TemasTitulacion> getListadoTemaTitulacion() {
		return listadoTemaTitulacion;
	}

	public void setListadoTemaTitulacion(
			Collection<TemasTitulacion> listadoTemaTitulacion) {
		this.listadoTemaTitulacion = listadoTemaTitulacion;
	}

	public TemasTitulacion getTemaTitulacion() {
		return temaTitulacion;
	}

	public void setTemaTitulacion(TemasTitulacion temaTitulacion) {
		this.temaTitulacion = temaTitulacion;
	}

	public boolean isEstadoTemaTitulacion() {
		return estadoTemaTitulacion;
	}

	public void setEstadoTemaTitulacion(boolean estadoTemaTitulacion) {
		this.estadoTemaTitulacion = estadoTemaTitulacion;
	}
	
}
