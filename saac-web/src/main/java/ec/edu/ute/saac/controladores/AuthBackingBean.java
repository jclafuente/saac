package ec.edu.ute.saac.controladores;

import java.util.Enumeration;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author jnk
 */
@ManagedBean
@RequestScoped
public class AuthBackingBean {

    private static final Log log = LogFactory.getLog(AccesoController.class);

    public AuthBackingBean() {
    }

    public String logout() {
        String result = "/index?faces-redirect=true";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            //request.logout();
            if (context != null) {
                Map m = context.getExternalContext().getSessionMap();
                for (Object o : m.keySet()) {
                    System.out.println(""+o.toString());
                    m.put(o, null);
                }
//                HttpSession session = (HttpSession) context.getCurrentInstance().getExternalContext().getSession(true);
//                removeAttributes(session);
            }
        /*} catch (ServletException e) {
            log.error("Failed to logout user!", e);
            result = "/loginError?faces-redirect=true";*/
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            log.info("<<<<<<<<<< cerrando kiosko!!! >>>>>>>>>>");
        }
        return result;
//        return "/logout2.jsf";
    }
    
    private static void removeAttributes(HttpSession session) {
        Enumeration atrs = session.getAttributeNames();
        while (atrs.hasMoreElements()) {  ///esto elimina atributos guardados
            String name = (String) atrs.nextElement();
            session.removeAttribute(name);
        }
    }
}
