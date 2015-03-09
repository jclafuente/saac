package ec.edu.ute.saac.controladores;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.TemasTitulacion;
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;

@ManagedBean
@ViewScoped
public class SeleccionTemaPlanControlador {
	
	private static final Log log = LogFactory
			.getLog(SeleccionTemaPlanControlador.class);
	private MessageSender sender;
	private Utilitarios utilitarios;
	private boolean panelDatos;
	private TemasTitulacion temaTitulacion;

	
	@Inject
	private IAdministracionServicio administracionServicio;
	
	public void inicializacionEntidades(){
		sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
		utilitarios = new Utilitarios();
		
		
	}
	
	@PostConstruct
	public void inicializacion(){
		try{
			
			inicializacionEntidades();
			setPanelDatos(Boolean.FALSE);
			
			
			
		}catch(Exception e){
			
			
		}
		
	}
	
	public void activarPanelDatos() {
		setPanelDatos(true);
		limpiarCombos();
	}
	
	public void limpiarCombos() {
		/*carreraSelected = Integer.valueOf(0);
		lineaInvestigacionSelected = Integer.valueOf(0);
		areaInvestigacionSelected = Integer.valueOf(0);*/
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
	
	public void seleccionarTemaTitulacion(TemasTitulacion temaTitulacion) {
		setTemaTitulacion(temaTitulacion);
		setPanelDatos(true);
	}
	
	public TemasTitulacion getTemaTitulacion() {
		return temaTitulacion;
	}

	public void setTemaTitulacion(TemasTitulacion temaTitulacion) {
		this.temaTitulacion = temaTitulacion;
	}

	public boolean isPanelDatos() {
		return panelDatos;
	}

	public void setPanelDatos(boolean panelDatos) {
		this.panelDatos = panelDatos;
	}
	
	

}
