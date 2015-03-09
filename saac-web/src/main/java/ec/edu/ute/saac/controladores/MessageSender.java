package ec.edu.ute.saac.controladores;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @author JNK
 */
@ManagedBean(name = "messageSender")
@SessionScoped
public class MessageSender implements Serializable{

    private boolean visible;
    private String message;
    private String title;

    public MessageSender() {
        visible = false;
        message = "";
        title = "";
    }

    /**
     * Permite poner en falso el indicador de visibilidad del popup
     */
    public void close() {
        setVisible(false);
    }

    /**
     * Permite lanzar un mensaje de error con el mensaje y destino indicados
     * @param destiny   Indica el destido del mensaje en la aplicaion o vista (form:localizacion)
     * @param message   Mensaje a mostrar
     */
    public static void sendError(String destiny, String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message);
        context.addMessage(destiny, msg);
    }

    /**
     * Permite lanzar un mensaje de alerta con el mensaje y destino indicados
     * @param destiny   Indica el destido del mensaje en la aplicaion o vista (form:localizacion)
     * @param message   Mensaje a mostrar
     */
    public static void sendWarning(String destiny, String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", message);
        context.addMessage(destiny, msg);
    }

    /**
     * Permite lanzar un mensaje de informacion al con el mensaje y destino indicados
     * @param destiny   Indica el destido del mensaje en la aplicaion o vista (form:localizacion)
     * @param message   Mensaje a mostrar
     */
    public static void sendInfo(String destiny, String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", message);
        context.addMessage(destiny, msg);
    }

    /**
     * Permite lanzar un mensaje de error fatal  con el mensaje y destino indicados
     * @param destiny   Indica el destido del mensaje en la aplicaion o vista (form:localizacion)
     * @param message   Mensaje a mostrar
     */
    public static void sendFatal(String destiny, String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal", message);
        context.addMessage(destiny, msg);
    }

    /***************** Message PopUp's ********************/
    /**
     * permite lanzar un mensaje de error en modo popup, se indica el mensaje a enviar
     * @param message   Mensaje a enviar
     */
    public void sendErrorPopup(String message) {
        setMessage(message);
        setTitle("Error");
        setVisible(true);
    }

    /**
     * permite lanzar un mensaje de informacion en modo popup, se indica el mensaje a enviar
     * @param message   Mensaje a enviar
     */
    public void sendWarningPopup(String message) {
        this.setMessage(message);
        this.setTitle("Alerta");
        this.setVisible(true);
    }

    /**
     * permite lanzar un mensaje de informacion en modo popup, se indica el mensaje a enviar
     * @param message   Mensaje a enviar
     */
    public void sendInfoPopup(String message) {
        this.setMessage(message);
        this.setTitle("Información");
        this.setVisible(true);
    }

    /**
     * permite lanzar un mensaje de error fatal en modo popup, se indica el mensaje a enviar
     * @param message   Mensaje a enviar
     */
    public void sendFatalPopup(String message) {
        this.setMessage(message);
        this.setTitle("Error Fatal");
        this.setVisible(true);
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
