/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.controladores;

import java.util.ArrayList;
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
import ec.edu.ute.saac.servicios.administracion.IAdministracionServicio;
import ec.edu.ute.saac.utils.Utilitarios;

/**
 *
 * @author JNK
 */
@ManagedBean
@ViewScoped
public class AdministracionControlador {
    
    private static final Log log = LogFactory.getLog(AdministracionControlador.class);
//    @PersistenceContext(unitName = "saacPU")
//    private EntityManager entityManager;
    private MessageSender sender;
    private Utilitarios utilitarios;
    private boolean panelDatos;
    /**
     * ***************************************
     */
    private Facultad facultad;
    private List<Facultad> listadoFacultad;
    /**
     * ***************************************
     */
    private Rol rol;
    private List<Rol> listadoRoles;
    /**
     * ***************************************
     */
    private Carrera carrera;
    private Integer facultadSelected;
    private List<Carrera> listadoCarreras;
    private List<SelectItem> listadoFacultadCarrera;
    /**
     * ***************************************
     */
    /*
    @EJB
    private FacultadFacade facultadFacade;
    
    @EJB
    private CarreraFacade carreraFacade;
    @EJB
    RolFacade rolFacade;
    @Resource
    UserTransaction u;*/
    
    
    @Inject
    private IAdministracionServicio administracionServicio;
    
    
    public AdministracionControlador() {
    }
    
    private void inicializacionEntidades()
    {
    	sender = (MessageSender) Utilitarios.getManagedBean("messageSender");
        utilitarios = new Utilitarios();
        
        facultad = new Facultad();
        carrera = new Carrera();
        rol = new Rol();
        
        listadoFacultad = new ArrayList<Facultad>();
        listadoCarreras = new ArrayList<Carrera>();
        listadoRoles = new ArrayList<Rol>();
        listadoFacultadCarrera = new ArrayList<SelectItem>();
    }
    
    
    @PostConstruct
    public void inicializacion() {
        try {
            
        	inicializacionEntidades();
        	limpiarCombosCarrera();
            
            
            setPanelDatos(false);
            
            if (listadoFacultad.size() <= 0) {
            	
            	
            	listadoFacultad.addAll(administracionServicio.findAll());
            	
                //listadoFacultad.addAll(facultadFacade.findAll());
            }
            
            if (listadoCarreras.size() <= 0) {
                listadoCarreras.addAll(administracionServicio.findCarreraFacultad());
            }
            
            if (listadoRoles.size() <= 0) {
                listadoRoles.addAll(administracionServicio.findAllRol());
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
            utilitarios.error(AdministracionControlador.class.getName(), "Error al inicializar los datosen: AdministracionControlador", e);
        }
    }
    
    /**
     * 
     */
    public void activarPanelDatos() {
        Facultad facultad = new Facultad();
        setFacultad(facultad);
        Carrera carrera = new Carrera();
        setCarrera(carrera);
        Rol r = new Rol();
        setRol(r);
        /**
         * **************************
         */
        setPanelDatos(true);
        limpiarCombosCarrera();
        /**
         * **************************
         */
    }
    
    /**
     * @param facultad
     */
    public void seleccionarFacultad(Facultad facultad) {
        log.info("facultad seleccionada -->> " + facultad.getFacNombre());
        setFacultad(facultad);
        setPanelDatos(true);
    }
    
    public void seleccionarRol(Rol rol) {
        setRol(rol);
        setPanelDatos(true);
    }
    
    public void seleccionarCarrera(Carrera carrera) {
    	setCarrera(carrera);
        setPanelDatos(true);
        cargarCombosCarrera();
    }
    
    public void limpiarCombosCarrera() {
        facultadSelected = Integer.valueOf(0);
    }
    
    public void cargarCombosCarrera() {
        setFacultadSelected(carrera.getFacultad().getFacCodigo());
    }

    /**
     * Metodos CRUD para la administricacion
     */
    public void guardarFacultad() {
        try {
        	
            facultad.setFacEstado(Boolean.TRUE);
            /*u.begin();
            entityManager.persist(facultad);
            u.commit();
            
           */ 
            
            administracionServicio.crearFacultad(facultad);
            
            
            
            MessageSender.sendInfo(Utilitarios.REGISTRO_GUARDADO, null);
            utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_GUARDADO, " ");
//            sender.sendInfoPopup("Registro Ingresado");
            inicializacion();
        } catch (Exception e) {
            String error = (String) e.getMessage();
            utilitarios.ponerMensajeError(Utilitarios.ERROR_REGISTRO_GUARDADO, error);
            utilitarios.error(AdministracionControlador.class.getName(), "Error al procesar registro", e);
        }
    }
    
    public void actualizarFacultad() {
        try {
        	/*
            u.begin();
            entityManager.merge(facultad);
            u.commit();
            */
            
            administracionServicio.actualizarFacultad(facultad);
            
            
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
    
    public void guardarCarrera() {
        try {
            
        	
        	
        	//TODO crear servicio poara la carrera
            /*Facultad fac;
            fac = entityManager.find(Facultad.class, getFacultadSelected());
            carrera.setFacultad(fac);
            
            u.begin();
            entityManager.persist(getCarrera());
            u.commit();
            */
            
            
            
            MessageSender.sendInfo(Utilitarios.REGISTRO_GUARDADO, null);
            utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_GUARDADO, " ");
//            sender.sendInfoPopup("Registro Ingresado");
            inicializacion();
        } catch (Exception e) {
            String error = (String) e.getMessage();
            utilitarios.ponerMensajeError(Utilitarios.ERROR_REGISTRO_GUARDADO, error);
            utilitarios.error(AdministracionControlador.class.getName(), "Error al procesar registro", e);
        }
    }
    
    public void actualizarCarrera() {
        try {
            
        	
        	/*
        	u.begin();
            entityManager.merge(getCarrera());
            u.commit();
            */
            
            
            
            
            
            
            
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
    
    public void guardarRol() {
        try {
            rol.setRolEstado(true);
            
            
            administracionServicio.crearRol(rol);
            
            
            /*
            u.begin();
            entityManager.persist(rol);
            u.commit();
            */    
            
            MessageSender.sendInfo(Utilitarios.REGISTRO_GUARDADO, null);
            utilitarios.ponerMensajeInfo(Utilitarios.REGISTRO_GUARDADO, " ");
//            sender.sendInfoPopup("Registro Ingresado");
            inicializacion();
        } catch (Exception e) {
            String error = (String) e.getMessage();
            utilitarios.ponerMensajeError(Utilitarios.ERROR_REGISTRO_GUARDADO, error);
            utilitarios.error(AdministracionControlador.class.getName(), "Error al procesar registro", e);
        }
    }
    
    public void actualizarRol() {
        try {
        	
        	
        	
        	
        	/*
            u.begin();
            entityManager.merge(rol);
            u.commit();
            */
            
            
            
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

    /**
     * Metodos de Getters y Setters
     */
    /**
     * @return the facultad
     */
    public Facultad getFacultad() {
        return facultad;
    }

    /**
     * @param facultad the facultad to set
     */
    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    /**
     * @return the listadoFacultad
     */
    public List<Facultad> getListadoFacultad() {
        return listadoFacultad;
    }

    /**
     * @param listadoFacultad the listadoFacultad to set
     */
    public void setListadoFacultad(List<Facultad> listadoFacultad) {
        this.listadoFacultad = listadoFacultad;
    }

    /**
     * @return the panelDatos
     */
    public boolean isPanelDatos() {
        return panelDatos;
    }

    /**
     * @param panelDatos the panelDatos to set
     */
    public void setPanelDatos(boolean panelDatos) {
        this.panelDatos = panelDatos;
    }

    /**
     * @return the carrera
     */
    public Carrera getCarrera() {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    /**
     * @return the listadoCarreras
     */
    public List<Carrera> getListadoCarreras() {
        return listadoCarreras;
    }

    /**
     * @param listadoCarreras the listadoCarreras to set
     */
    public void setListadoCarreras(List<Carrera> listadoCarreras) {
        this.listadoCarreras = listadoCarreras;
    }

    /**
     * @return the listadoFacultadCarrera
     */
    public List<SelectItem> getListadoFacultadCarrera() {
        return listadoFacultadCarrera;
    }

    /**
     * @param listadoFacultadCarrera the listadoFacultadCarrera to set
     */
    public void setListadoFacultadCarrera(List<SelectItem> listadoFacultadCarrera) {
        this.listadoFacultadCarrera = listadoFacultadCarrera;
    }

    /**
     * @return the facultadSelected
     */
    public Integer getFacultadSelected() {
        return facultadSelected;
    }

    /**
     * @param facultadSelected the facultadSelected to set
     */
    public void setFacultadSelected(Integer facultadSelected) {
        this.facultadSelected = facultadSelected;
    }

    /**
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * @return the listadoRoles
     */
    public List<Rol> getListadoRoles() {
        return listadoRoles;
    }

    /**
     * @param listadoRoles the listadoRoles to set
     */
    public void setListadoRoles(List<Rol> listadoRoles) {
        this.listadoRoles = listadoRoles;
    }
}
