package ec.edu.ute.saac.controladores;

import ec.edu.ute.saac.utils.Utilitarios;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author jnk
 */
@ManagedBean
@SessionScoped
public class NavegacionControlador implements Serializable {

    Utilitarios utilitarios;
    private String paginaUbicacion;

    /**
     * Creates a new instance of NavegacionControlador
     */
    public NavegacionControlador() {
        utilitarios = new Utilitarios();
        paginaUbicacion = "";
    }

    public String getDevuelveFechaSistema() {
        return utilitarios.devuelveFechaEnLetras();
    }

    public Date getDevuelveFechaSistemaNOW() {
        return ((new Date()));
    }

    public void paginaPrincipal() {
        try {
            utilitarios.navigation("/index.jsf");
        } catch (Exception e) {
            utilitarios.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public void navegacionString(String url, String descripcion) {
        try {
            setPaginaUbicacion(descripcion);
            utilitarios.navigation(url);
        } catch (Exception e) {
            utilitarios.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public void navegacion(ActionEvent evt) {
        try {
            String item = (String) evt.getComponent().getAttributes().get("item");
            utilitarios.navigation(item);

        } catch (Exception e) {
            utilitarios.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public String getPaginaUbicacion() {
        return paginaUbicacion;
    }

    public void setPaginaUbicacion(String paginaUbicacion) {
        this.paginaUbicacion = paginaUbicacion;
    }
}
