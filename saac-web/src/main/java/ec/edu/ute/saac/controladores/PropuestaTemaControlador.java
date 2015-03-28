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
public class PropuestaTemaControlador {

	private static final Log log = LogFactory
			.getLog(PropuestaTemaControlador.class);
	private MessageSender sender;
	private Utilitarios utilitarios;
	private boolean panelDatos;
	private Integer carreraSelected;
	private Integer lineaInvestigacionSelected;

	private Integer areaInvestigacionSelected;
	private Collection<Carrera> listadoCarreras;
	private Carrera carrera;
	private LineaInvestigacion lineaInvestigacion;
	private AreaInvestigacion areaInvestigacion;
	private TemasTitulacion temaTitulacion;
	private Collection<TemasTitulacion> listadoTemaTitulacion;
	private Collection<LineaInvestigacion> listadoLineaInvestigacion;
	private Collection<AreaInvestigacion> listadoaAreaInvestigacion;

	@Inject
	private IAdministracionServicio administracionServicio;

	public void initTemaTitulacion() {
		setTemaTitulacion(new TemasTitulacion());
	}

	public void activarPanelDatos() {
		
		limpiarCombos();
		initTemaTitulacion();
		setPanelDatos(Boolean.TRUE);

	}

	public void limpiarCombos() {
		carreraSelected = Integer.valueOf(0);
		lineaInvestigacionSelected = Integer.valueOf(0);
		areaInvestigacionSelected = Integer.valueOf(0);
	}

	public void retornarPagina() {
		setPanelDatos(Boolean.FALSE);
	}

	private void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		carrera = new Carrera();
		lineaInvestigacion = new LineaInvestigacion();
		areaInvestigacion = new AreaInvestigacion();
		temaTitulacion = new TemasTitulacion();

		listadoCarreras = new ArrayList<Carrera>();
		listadoLineaInvestigacion = new ArrayList<LineaInvestigacion>();
		listadoaAreaInvestigacion = new ArrayList<AreaInvestigacion>();
		listadoTemaTitulacion = new ArrayList<TemasTitulacion>();
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

		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR, error);
			utilitarios
					.error(AdministracionControlador.class.getName(),
							"Error al inicializar los datos en: PropuestaTemaControlador",
							e);
		}

	}

	public void btnCargarListaTema() {
		try {

			setListadoTemaTitulacion(administracionServicio
					.obtenerTemaTitulacion(carreraSelected,
							lineaInvestigacionSelected,
							areaInvestigacionSelected));
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

		Integer linInvCodigo = NumberUtils.createInteger(String
				.valueOf(valueChangeEvent.getNewValue()));

		try {
			
			setListadoaAreaInvestigacion(administracionServicio
					.obtenerAreaInvestigacionLineaInvestigacion(linInvCodigo)); 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void btnActualizarTemaTitulacion() {
		try {

			administracionServicio.actualizarTemaTitulacion(temaTitulacion);
			
			setListadoTemaTitulacion(administracionServicio
					.obtenerTemaTitulacion(carreraSelected,
							lineaInvestigacionSelected,
							areaInvestigacionSelected));
			
			setPanelDatos(Boolean.FALSE);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void btnCrearTemaTitulacion() {

		try {
			
			temaTitulacion.setAreaInvestigacion(new AreaInvestigacion());
			temaTitulacion.getAreaInvestigacion().setAreInvCodigo(
					areaInvestigacionSelected);
			temaTitulacion.getAreaInvestigacion().setLineaInvestigacion(
					new LineaInvestigacion());
			temaTitulacion.getAreaInvestigacion().getLineaInvestigacion()
					.setLinInvCodigo(lineaInvestigacionSelected);
			temaTitulacion.getAreaInvestigacion().getLineaInvestigacion()
					.setCarrera(new Carrera());
			temaTitulacion.getAreaInvestigacion().getLineaInvestigacion()
					.getCarrera().setCarCodigo(carreraSelected);
			temaTitulacion.setTemTitEstado("POR APROBAR");
			
			administracionServicio.crearTemaTitulacion(temaTitulacion);
			
			setListadoTemaTitulacion(administracionServicio
					.obtenerTemaTitulacion(carreraSelected,
							lineaInvestigacionSelected,
							areaInvestigacionSelected));
			
			sender.sendInfoPopup("Registro Ingresado");
			MessageSender.sendInfo(Utilitarios.REGISTRO_GUARDADO, null);
			utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_GUARDADO, " ");
			
			
			setPanelDatos(Boolean.FALSE);
			
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void seleccionarTemaTitulacion(TemasTitulacion temaTitulacion) {
		setTemaTitulacion(temaTitulacion);
		setPanelDatos(true);
	}

	public Collection<LineaInvestigacion> getListadoLineaInvestigacion() {
		return listadoLineaInvestigacion;
	}

	public void setListadoLineaInvestigacion(
			Collection<LineaInvestigacion> listadoLineaInvestigacion) {
		this.listadoLineaInvestigacion = listadoLineaInvestigacion;
	}

	public Collection<AreaInvestigacion> getListadoaAreaInvestigacion() {
		return listadoaAreaInvestigacion;
	}

	public void setListadoaAreaInvestigacion(
			Collection<AreaInvestigacion> listadoaAreaInvestigacion) {
		this.listadoaAreaInvestigacion = listadoaAreaInvestigacion;
	}

	public void cargarCombos() {

		setCarreraSelected(carrera.getCarCodigo());
		setLineaInvestigacionSelected(lineaInvestigacion.getLinInvCodigo());
		setAreaInvestigacionSelected(areaInvestigacion.getAreInvCodigo());

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

	public Collection<LineaInvestigacion> getListadolineaInvestigacion() {
		return listadoLineaInvestigacion;
	}

	public void setListadolineaInvestigacion(
			Collection<LineaInvestigacion> listadolineaInvestigacion) {
		this.listadoLineaInvestigacion = listadolineaInvestigacion;
	}

	public Collection<Carrera> getListadoCarreras() {
		return listadoCarreras;
	}

	public void setListadoCarreras(Collection<Carrera> listadoCarreras) {
		this.listadoCarreras = listadoCarreras;
	}

	public Integer getLineaInvestigacionSelected() {
		return lineaInvestigacionSelected;
	}

	public void setLineaInvestigacionSelected(Integer lineaInvestigacionSelected) {
		this.lineaInvestigacionSelected = lineaInvestigacionSelected;
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

	public TemasTitulacion getTemaTitulacion() {
		return temaTitulacion;
	}

	public void setTemaTitulacion(TemasTitulacion temaTitulacion) {
		this.temaTitulacion = temaTitulacion;
	}

	public void setListadoTemaTitulacion(
			Collection<TemasTitulacion> listadoTemaTitulacion) {
		this.listadoTemaTitulacion = listadoTemaTitulacion;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public LineaInvestigacion getLineaInvestigacion() {
		return lineaInvestigacion;
	}

	public void setLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
		this.lineaInvestigacion = lineaInvestigacion;
	}

	public AreaInvestigacion getAreaInvestigacion() {
		return areaInvestigacion;
	}

	public void setAreaInvestigacion(AreaInvestigacion areaInvestigacion) {
		this.areaInvestigacion = areaInvestigacion;
	}

}
