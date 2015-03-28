/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.controladores;

import ec.edu.ute.saac.entidades.Usuario;
import ec.edu.ute.saac.servicios.RolFacade;
import ec.edu.ute.saac.servicios.UsuarioFacade;
import ec.edu.ute.saac.utils.Contexto;
import ec.edu.ute.saac.utils.Utilitarios;
import java.security.Principal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author JNK
 */
@ManagedBean
@SessionScoped
public class AccesoController extends Contexto {

    private static final Log log = LogFactory.getLog(AccesoController.class);
    private static final long serialVersionUID = 1L;
    private Utilitarios utilitarios;
    private Usuario usuarioLogeado;
    @EJB
    RolFacade rolesFacade = new RolFacade();
    @EJB
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    private String accessState;

    public AccesoController() {
    }

    @PostConstruct
    public void inicializarDatosAcceso() {
        /*utilitarios = new Utilitarios();
        Principal user = getExternalContext().getUserPrincipal();
        log.info("---------- USER JDBC: " + user.getName() + " ---------->");
        String usuario = user.getName();
        log.info("------------> " + user.toString());
        try {
            this.usuarioLogeado = usuarioFacade.obtenerUsuarioPorNombre(usuario);
            if (this.usuarioLogeado == null) {
                this.accessState = "Denied";
            } else {
                this.accessState = "Granted";
                log.info("---------- USER ID: " + usuarioLogeado.getUsuCodigo() + " ---------->");
                log.info("---------- CONECTION ID: " + getRemoteIpConection() + " ---------->");
            }
        } catch (Exception ex) {
            log.error("inicializarDatosAcceso: " + usuario, ex);
        } finally {
            log.info("<<<<<<<<<< Access " + accessState + " >>>>>>>>>>");
        }*/
    }

    public void sendPassword() {
        try {
            System.out.println("entro");

            // ENVIO DE MAIL
            StringBuilder sms = new StringBuilder();
            char c = 0x0A;
            String br = "<br>";
            String tabsms = "\u0009 ";

            sms.append(br).append("Usted ha solicitado el envio de este correo.");
            sms.append(br).append("del Sistema de Asistencia Academica de la Universidad tecnologica Equinoccial.");
            sms.append(br);
            sms.append(tabsms);

            sms.append(br).append(br);
            sms.append("Por su seguridad se recomienda cambiar su contrase&ntilde;a para uso del sistema.");

            StringBuilder footer = new StringBuilder();
            footer.append(br).append(br).append(br).append("http://www.ute.edu.ec");
            footer.append(br).append("<img src=").append("https://lh3.googleusercontent.com/-TftsJbcoCYM/T7yQrMaKQ-I/AAAAAAAAARU/9C4oo0g9Pe0/w230-h80-k/logo.png").append(" />");

            utilitarios.enviarMailClave("geca45503@ute.edu.ec", "Informacion del sistema", "" + sms, "" + footer);
//            utilitarios.enviarMailClave("vhgalvez@ute.edu.ec", "Informacion del sistema", "" + sms, "" + footer);
            // FIN DE MAIL

        } catch (Exception e) {
            utilitarios.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }
}
