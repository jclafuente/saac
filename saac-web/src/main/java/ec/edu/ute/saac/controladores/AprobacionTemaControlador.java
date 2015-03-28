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

import ec.edu.ute.saac.entidades.AreaInvestigacion;
import ec.edu.ute.saac.entidades.Carrera;
import ec.edu.ute.saac.entidades.LineaInvestigacion;
import ec.edu.ute.saac.entidades.TemasTitulacion;
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;

@ManagedBean
@ViewScoped
public class AprobacionTemaControlador {

	private MessageSender sender;
	private Utilitarios utilitarios;
	private TemasTitulacion temaTitulacion;
	private Carrera carrera;
	private LineaInvestigacion lineaInvestigacion;
	private AreaInvestigacion areaInvestigacion;

	private Collection<Carrera> listadoCarrera;
	private Collection<TemasTitulacion> listadoTemaTitulacion;
	private Collection<LineaInvestigacion> listadolineaInvestigacion;
	private Collection<AreaInvestigacion> listadoAreaInvestigacion;

	private Integer carreraSelected;
	private Integer lineaInvestigacionSelected;
	private Integer areaInvestigacionSelected;

	private boolean panelDatos;

	@Inject
	private IAdministracionServicio administracionServicio;

	public void inicializacionEntidades() {
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		carrera = new Carrera();
		lineaInvestigacion = new LineaInvestigacion();
		areaInvestigacion = new AreaInvestigacion();
		temaTitulacion = new TemasTitulacion();

		listadoCarrera = new ArrayList<Carrera>();
		listadoAreaInvestigacion = new ArrayList<AreaInvestigacion>();
		listadolineaInvestigacion = new ArrayList<LineaInvestigacion>();
		listadoTemaTitulacion = new ArrayList<TemasTitulacion>();
	}

	@PostConstruct
	public void inicializacion() {

		try {

			inicializacionEntidades();
			setPanelDatos(Boolean.FALSE);

			if (CollectionUtils.isEmpty(listadoCarrera)) {
				setListadoCarrera(administracionServicio.obtenerCarrera());
			}

		} catch (Exception e) {
			String error = (String) e.getMessage();
			utilitarios.ponerMensajeError(Utilitarios.ERROR, error);
			utilitarios
					.error(AdministracionControlador.class.getName(),
							"Error al inicializar los datos en: AprobacionTemaControlador",
							e);
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

	public void btnAprobarTemaTitulacion(String estado) {
		try {
			
			administracionServicio.actualizarTemaTitulacion(temaTitulacion,estado);
			
			setListadoTemaTitulacion(getListadoTemaTitulacion());
			setPanelDatos(Boolean.FALSE);
			
			sender.sendInfoPopup("Proceso finalizado");
	/*		MessageSender.sendInfo(Utilitarios.REGISTRO_GUARDADO, null);
			utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_GUARDADO, " ");*/

		} catch (Exception e) {


		}

	}

	public void retornarPagina() {
		setPanelDatos(Boolean.FALSE);
	}

	public void seleccionarTemaTitulacion(TemasTitulacion temaTitulacion) {
		setTemaTitulacion(temaTitulacion);
		setPanelDatos(true);
	}

	public void activarPanelDatos() {
		setPanelDatos(true);

	}

	public Collection<Carrera> getListadoCarreras() {
		return listadoCarrera;
	}

	public void setListadoCarrera(Collection<Carrera> listadoCarrera) {
		this.listadoCarrera = listadoCarrera;
	}

	public Collection<TemasTitulacion> getListadoTemaTitulacion() {
		return listadoTemaTitulacion;
	}

	public void setListadoTemaTitulacion(
			Collection<TemasTitulacion> listadoTemaTitulacion) {
		this.listadoTemaTitulacion = listadoTemaTitulacion;
	}

	public Collection<LineaInvestigacion> getListadolineaInvestigacion() {
		return listadolineaInvestigacion;
	}

	public void setListadolineaInvestigacion(
			Collection<LineaInvestigacion> listadolineaInvestigacion) {
		this.listadolineaInvestigacion = listadolineaInvestigacion;
	}

	public Collection<AreaInvestigacion> getListadoAreaInvestigacion() {
		return listadoAreaInvestigacion;
	}

	public void setListadoAreaInvestigacion(
			Collection<AreaInvestigacion> listadoAreaInvestigacion) {
		this.listadoAreaInvestigacion = listadoAreaInvestigacion;
	}

	public Integer getCarreraSelected() {
		return carreraSelected;
	}

	public void setCarreraSelected(Integer carreraSelected) {
		this.carreraSelected = carreraSelected;
	}

	public Integer getAreaInvestigacionSelected() {
		return areaInvestigacionSelected;
	}

	public void setAreaInvestigacionSelected(Integer areaInvestigacionSelected) {
		this.areaInvestigacionSelected = areaInvestigacionSelected;
	}

	public Integer getLineaInvestigacionSelected() {
		return lineaInvestigacionSelected;
	}

	public void setLineaInvestigacionSelected(Integer lineaInvestigacionSelected) {
		this.lineaInvestigacionSelected = lineaInvestigacionSelected;
	}

	public boolean isPanelDatos() {
		return panelDatos;
	}

	public void setPanelDatos(boolean panelDatos) {
		this.panelDatos = panelDatos;
	}

	public Collection<Carrera> getListadoCarrera() {
		return listadoCarrera;
	}

	public TemasTitulacion getTemaTitulacion() {
		return temaTitulacion;
	}

	public void setTemaTitulacion(TemasTitulacion temaTitulacion) {
		this.temaTitulacion = temaTitulacion;
	}

}
