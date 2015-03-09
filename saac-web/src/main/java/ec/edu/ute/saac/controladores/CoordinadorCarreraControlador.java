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

import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.pojo.ItemRevision;
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;


@ManagedBean
@ViewScoped
public class CoordinadorCarreraControlador {
	
	private static final Log log = LogFactory.getLog(CoordinadorCarreraControlador.class);
	private MessageSender sender;
	private Utilitarios utilitarios;
	private boolean panelDatos;
	private Collection<Persona> listadoPersonas;
	private Persona persona;
	private Integer estadoCivilSelected;
	private Integer nacionalidadSelected;
	private Integer generoSelected;
	private Collection<SelectItem> listadoEstadoCivil;
	private Collection<SelectItem> listadoGenero;
	private Collection<SelectItem> listadoNacionalidad;
	private boolean panelObservacion2;
	private boolean panelObservacion;
	private Collection<ItemRevision> listadoItemRevisiones;
	private boolean bandFacultadCarrera;
	
	@Inject
	private IAdministracionServicio administracionServicio;
	
	public void activarPanelDatos() {
		/*System.out.println("hola pepe");
		Persona persona = new Persona();
		//setPersona(persona);
		persona = persona;
		limpiarCombos();*/
		setPanelDatos(true);
	}
	
	@PostConstruct
	public void inicializacion() {
		try {
			
			sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
			utilitarios = new Utilitarios();
			listadoEstadoCivil = new ArrayList<SelectItem>();
			listadoGenero = new ArrayList<SelectItem>();
			listadoNacionalidad = new ArrayList<SelectItem>();
			//reporteControlador = (ReporteControlador) Utilitarios.getManagedBean("reporteControlador");
			/*persona = new Persona();
			listadoProvincias = new ArrayList<SelectItem>();
			listadoCiudades = new ArrayList<SelectItem>();*/
			setListadoPersonas(new ArrayList<Persona>());
			limpiarCombos();
			setPanelDatos(false);
			setPanelObservacion(false);
			setPanelObservacion2(false);
			listadoItemRevisiones = new ArrayList<ItemRevision>();
			bandFacultadCarrera = false;
			/*limpiarCombosCarrera();
			setCiudadSelected("");
			*/

			/*listadoFacultad = new ArrayList<SelectItem>();
			listadoCarreraPorFacultad = new ArrayList<SelectItem>();
			listadoRoles = new ArrayList<SelectItem>();
			listadoObservaciones = new ArrayList<Persona>();
			listadoItemCalificacion = new ArrayList<ItemRevision>();*/
			
			ItemRevision item1 = new ItemRevision(
					"1",
					"Título propuesto",
					"El Título es coherente con el Objetivo general y demuestra ser una síntesis de la investigación. El tema es parte de las líneas de investigación de la Universidad.",
					"si", "observacion 123");
			ItemRevision item2 = new ItemRevision(
					"2",
					"Justificación",
					"Expone el problema de forma clara y concreta. Sustenta el propósito de la investigación sobre la base del problema a resolver, el estado del arte y fundamento teórico del tema. Demuestra la viabilidad técnica y económica para la ejecución del proyecto.",
					"si", "observacion 123");
			ItemRevision item3 = new ItemRevision(
					"3",
					"Hipótesis",
					"Formula la hipótesis correspondiente, como la posible solución al problema, y está vinculada al objetivo general",
					"si", "observacion 123");
			ItemRevision item4 = new ItemRevision(
					"4",
					"Objetivos propuestos: \n General \n Específicos",
					"Los objetivos propuestos permitirán obtener resultados cuantificables. El general debe establecer el resultado final de la investigación y está vinculado con el título propuesto. Los específicos ayudarán a conseguir el general.",
					"si", "observacion 123");
			ItemRevision item5 = new ItemRevision(
					"5",
					"Alcance",
					"Delimita el trabajo de investigación de tal manera que permita demostrar la posible solución al problema.",
					"no", "observacion 123");
			ItemRevision item6 = new ItemRevision(
					"6",
					"Trabajos afines realizados",
					"Lista los trabajos afines al tema y que ya se han realizado en la Universidad.",
					"no", "observacion 123");
			ItemRevision item7 = new ItemRevision(
					"7",
					"Metodología propuesta",
					"Indica de forma clara y concreta el cómo se hará la investigación: materiales, métodos, diseño experimental y otras herramientas de recopilación de datos, así como, los métodos necesarios para el análisis de los resultados. Demuestra un rigor científico y/o tecnológico adecuado a la carrera y al título a obtener.",
					"si", "observacion 123");
			ItemRevision item8 = new ItemRevision(
					"8",
					"Cronograma",
					"Presenta en una tabla el detalle en el tiempo en que se ejecutarán las principales actividades para conseguir los objetivos específicos y el general",
					"no", "observacion 123");
			ItemRevision item9 = new ItemRevision(
					"9",
					"Presupuesto",
					"Presenta, de forma lógica y real, los detalle de costos administrativos, de ejecución del trabajo y la respectiva fuente de financiamiento.",
					"no", "observacion 123");
			ItemRevision item10 = new ItemRevision(
					"10",
					"Bibliografía",
					"La Bibliografía (Libros, Revistas científicas, etc.) es la requerida por el tema de trabajo. Son ediciones actualizadas, por ej. del año 2000 en adelante, preferentemente.\nLa bibliografía corresponde con las citas expuestas en el texto.",
					"no", "observacion 123");
			ItemRevision item11 = new ItemRevision(
					"11",
					"Temario",
					"La estructura final del Trabajo de Titulación es coherente con lo propuesto en la justificación, alcance y metodología. Presenta los subtítulos más relevantes de la parte teórica y metodológica.",
					"no", "observacion 123");
			ItemRevision item12 = new ItemRevision("12",
					"Redacción y ortografía",
					"Cumple con las normas de ortografía y redacción", "no",
					"observacion 123");
			
			listadoItemRevisiones.add(item1);
			listadoItemRevisiones.add(item2);
			listadoItemRevisiones.add(item3);
			listadoItemRevisiones.add(item4);
			listadoItemRevisiones.add(item5);
			listadoItemRevisiones.add(item6);
			listadoItemRevisiones.add(item7);
			listadoItemRevisiones.add(item8);
			listadoItemRevisiones.add(item9);
			listadoItemRevisiones.add(item10);
			listadoItemRevisiones.add(item11);
			listadoItemRevisiones.add(item12);

		
			//listadoDinamico = new ArrayList<ItemRevision>();

			/**
			 * CARGA DE LISTAS
			 */
			/*if (listadoNacionalidad.size() <= 0) {
				Collection<SelectItem> lista = new ArrayList<SelectItem>();
				for (Catalogo cat : administracionServicio.obtenerCatalogoNacionalidades() ) {
					lista.add(new SelectItem(cat.getCatCodigo(), cat
							.getCatValor()));
				}
				listadoNacionalidad.addAll(lista);
			}

			if (listadoEstadoCivil.size() <= 0) {
				Collection<SelectItem> lista = new ArrayList<SelectItem>();
				for (Catalogo cat : administracionServicio.obtenerCatalogoEstadoCivil() ) {
					lista.add(new SelectItem(cat.getCatCodigo(), cat
							.getCatValor()));
				}
				listadoEstadoCivil.addAll(lista);
			}

			if (listadoGenero.size() <= 0) {
				Collection<SelectItem> lista = new ArrayList<SelectItem>();
				for (Catalogo cat : administracionServicio.obtenerCatalogoGenero()) {
					lista.add(new SelectItem(cat.getCatCodigo(), cat
							.getCatValor()));
				}
				listadoGenero.addAll(lista);
			}*/
			
			/*if (listadoNacionalidad.size() <= 0) {
				Collection<SelectItem> lista = new ArrayList<SelectItem>();
				for (Catalogo cat : administracionServicio.obtenerCatalogoNacionalidades() ) {
					lista.add(new SelectItem(cat.getCatCodigo(), cat
							.getCatValor()));
				}
				listadoNacionalidad.addAll(lista);
			}

			if (listadoEstadoCivil.size() <= 0) {
				Collection<SelectItem> lista = new ArrayList<SelectItem>();
				for (Catalogo cat : administracionServicio.obtenerCatalogoEstadoCivil() ) {
					lista.add(new SelectItem(cat.getCatCodigo(), cat
							.getCatValor()));
				}
				listadoEstadoCivil.addAll(lista);
			}

			if (listadoGenero.size() <= 0) {
				Collection<SelectItem> lista = new ArrayList<SelectItem>();
				for (Catalogo cat : administracionServicio.obtenerCatalogoGenero()) {
					lista.add(new SelectItem(cat.getCatCodigo(), cat
							.getCatValor()));
				}
				listadoGenero.addAll(lista);
			}*/

			if (listadoPersonas.size() <= 0) {
				listadoPersonas.addAll(administracionServicio.obtenerPersona());
			}

			/*if (listadoRoles.size() <= 0) {
				Collection<SelectItem> lista = new ArrayList<SelectItem>();
				for (Rol rol : administracionServicio.findAllRol()) {
					lista.add(new SelectItem(rol.getRolCodigo(), rol
							.getRolNombre()));
				}
				listadoRoles.addAll(lista);
			}*/

		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public void seleccionar(Persona persona) {
		
		 //evt.getComponent().getAttributes().get("select"));
		if (persona != null) {
			setPersona(persona);
		}
		// log.info(this.persona.getPerCodigo());
		setPanelDatos(true);
		bandFacultadCarrera = true;
		cargaCombos();
	}
	
	public void cargaCombos() {
		
		nacionalidadSelected = persona.getCatalogoNacionalidad().getCatCodigo();
		estadoCivilSelected = persona.getCatalogoEstadoCivil().getCatCodigo();
		generoSelected = persona.getCatalogoGenero().getCatCodigo();

		bandFacultadCarrera = true;

		/*if (listadoFacultad.size() <= 0) {
			Collection<SelectItem> lista = new ArrayList<SelectItem>();
			for (Facultad fac : administracionServicio.findAll()) {
				lista.add(new SelectItem(fac.getFacCodigo(), fac.getFacNombre()));
			}
			listadoFacultad.addAll(lista);
		}

		if (persona.getPersonaCarreraList().size() > 0) {
			carreraSelected = persona.getPersonaCarreraList().get(0).getCarrera().getCarCodigo();
			Facultad fac = entityManager.find(Carrera.class, carreraSelected)
					.getFacultad();
			facultadSelected = fac.getFacCodigo();

			if (listadoCarreraPorFacultad.size() <= 0) {
				Collection<SelectItem> lista = new ArrayList<SelectItem>();
				for (Carrera car : administracionServicio.findCarreraFacultad()) {
					lista.add(new SelectItem(car.getCarCodigo(), car
							.getCarNombre()));
				}
				listadoCarreraPorFacultad.addAll(lista);
			}

			tipoAsignacionCarrera = true;
			// FIXME arreglar por los cambios de ORM
			// rolSelected =
			// persona.getPersonaCarreraList().get(0).get().getRolCodigo();
		} else {
			facultadSelected = persona.getPersonaFacultadList().get(0)
					.getFacultad().getFacCodigo();
			tipoAsignacionFacultad = true;
			// rolSelected =
			// persona.getPersonaFacultadList().get(0).getRolCodigo().getRolCodigo();
		}*/
	}
	
	public void abrirObservaciones() {
		setPanelObservacion(true);
	}
	
	public void abrirObservaciones2() {
		setPanelObservacion2(true);
	}
	
	public void cerrarObservaciones() {
		setPanelObservacion(false);
	}
	
	public void cerrarObservaciones2() {
		setPanelObservacion2(false);
	}
	
	public void limpiarCombos() {
		nacionalidadSelected = Integer.valueOf(0);
		generoSelected = Integer.valueOf(0);
		estadoCivilSelected = Integer.valueOf(0);
		//rolSelected = Integer.valueOf(0);
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public boolean isPanelDatos() {
		return panelDatos;
	}

	public void setPanelDatos(boolean panelDatos) {
		this.panelDatos = panelDatos;
	}

	public Collection<Persona> getListadoPersonas() {
		return listadoPersonas;
	}

	public void setListadoPersonas(Collection<Persona> listadoPersonas) {
		this.listadoPersonas = listadoPersonas;
	}

	public Integer getEstadoCivilSelected() {
		return estadoCivilSelected;
	}

	public void setEstadoCivilSelected(Integer estadoCivilSelected) {
		this.estadoCivilSelected = estadoCivilSelected;
	}

	public Integer getNacionalidadSelected() {
		return nacionalidadSelected;
	}

	public void setNacionalidadSelected(Integer nacionalidadSelected) {
		this.nacionalidadSelected = nacionalidadSelected;
	}

	public Integer getGeneroSelected() {
		return generoSelected;
	}

	public void setGeneroSelected(Integer generoSelected) {
		this.generoSelected = generoSelected;
	}

	public Collection<SelectItem> getListadoEstadoCivil() {
		return listadoEstadoCivil;
	}

	public void setListadoEstadoCivil(Collection<SelectItem> listadoEstadoCivil) {
		this.listadoEstadoCivil = listadoEstadoCivil;
	}

	public Collection<SelectItem> getListadoGenero() {
		return listadoGenero;
	}

	public void setListadoGenero(Collection<SelectItem> listadoGenero) {
		this.listadoGenero = listadoGenero;
	}

	public Collection<SelectItem> getListadoNacionalidad() {
		return listadoNacionalidad;
	}

	public void setListadoNacionalidad(Collection<SelectItem> listadoNacionalidad) {
		this.listadoNacionalidad = listadoNacionalidad;
	}

	public boolean isPanelObservacion2() {
		return panelObservacion2;
	}

	public void setPanelObservacion2(boolean panelObservacion2) {
		this.panelObservacion2 = panelObservacion2;
	}

	public boolean isPanelObservacion() {
		return panelObservacion;
	}

	public void setPanelObservacion(boolean panelObservacion) {
		this.panelObservacion = panelObservacion;
	}

	public Collection<ItemRevision> getListadoItemRevisiones() {
		return listadoItemRevisiones;
	}

	public void setListadoItemRevisiones(
			Collection<ItemRevision> listadoItemRevisiones) {
		this.listadoItemRevisiones = listadoItemRevisiones;
	}
	

	
	
}
