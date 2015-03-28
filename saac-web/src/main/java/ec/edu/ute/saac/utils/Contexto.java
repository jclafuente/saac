package ec.edu.ute.saac.utils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Map;
import java.util.ResourceBundle;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Contexto implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(Contexto.class);
    private ResourceBundle mensajes = null;
    private ResourceBundle parametros = null;

    private ResourceBundle getResourceBundle() {
     /*   this.mensajes = (ResourceBundle) getRequest().getServletContext().getAttribute("mensajes");
        if (this.mensajes == null) {
            this.mensajes = ResourceBundle.getBundle("mensajes", this.getContext().getApplication().getDefaultLocale());
            getRequest().getServletContext().setAttribute("mensajes", this.mensajes);
        }*/

        return mensajes;

    }

    private ResourceBundle getParametrosAplicacion() {
     /*   this.parametros = (ResourceBundle) getRequest().getServletContext().getAttribute("parametros");
        if (this.parametros == null) {
            this.parametros = ResourceBundle.getBundle("parametros", this.getContext().getApplication().getDefaultLocale());
            getRequest().getServletContext().setAttribute("parametros", this.parametros);
        }*/

        return parametros;
    }

    //obtiene el tamaño de cada consulta para las consultas paginadas
    public int getPageSize() {
        return Integer.valueOf(getParametrosAplicacion().getString("consultas.tamanioPaginacion"));
    }

    public int getRestrincionParaImportarNuevoServidor() {
        return Integer.valueOf(getParametrosAplicacion().getString("restriccion.importar.nuevoServidor"));

    }

    public int getInitialPageNumber() {
        return 0;
    }

    public int getValorTiempoMedicionParcial() {
        return Integer.valueOf(getParametrosAplicacion().getString("valorTiempoMedicionParcial"));
    }

    protected void mostrarMensajeInfo(String key) {

        getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getResourceBundle().getString(key), null));

    }

    protected void mostrarMensajeError(String key) {
        getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getResourceBundle().getString(key), null));
    }

    protected void mostrarMensajeAlerta(String key, String... params) {
        String msgValue = getResourceBundle().getString(key);
        MessageFormat messageFormat = new MessageFormat(msgValue);
        Object[] args = null;
        if (params != null) {
            args = new Object[params.length];
            int i = 0;
            for (String s : params) {
                args[i++] = s;
            }
        } else {
            args = new Object[]{null};
        }


        String mensajeFormateado = messageFormat.format(args);
        getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeFormateado, null));
    }

    protected void mostrarMensajeAlerta(String key) {
        getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, getResourceBundle().getString(key), null));
    }

    protected void mostrarMensajePorComponente(FacesMessage.Severity severity, String summary, String detail, String componente) {
        String msgValue = getResourceBundle().getString(detail);
        FacesMessage message = new FacesMessage();
        message.setSeverity(severity);
        message.setSummary(msgValue);
        message.setDetail(msgValue);
        getContext().addMessage(componente, message);
    }

    public static void render() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }

    /**
     * Get managed bean based on the bean name.
     *
     * @param beanName the bean name
     * @return the managed bean associated with the bean name
     */
    protected Object getManagedBean(String beanName) {
        ExpressionFactory ef = getApplication().getExpressionFactory();
        ValueExpression ve = ef.createValueExpression(getElContext(), getJsfEl(beanName), Object.class);
        return ve.getValue(getElContext());
    }

    /**
     * Remove the managed bean based on the bean name.
     *
     * @param beanName the bean name of the managed bean to be removed
     */
    protected static void resetManagedBean(String beanName) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ELContext elc = fc.getELContext();
        ExpressionFactory ef = fc.getApplication().getExpressionFactory();
        ef.createValueExpression(elc, getJsfEl(beanName),
                Object.class).setValue(elc, null);
    }

    private static String getJsfEl(String value) {
        return "#{" + value + "}";
    }

    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    protected ExternalContext getExternalContext() {
        return getContext().getExternalContext();
    }

    protected ELContext getElContext() {
        return getContext().getELContext();
    }

    protected Application getApplication() {
        return getContext().getApplication();
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    protected String getRemoteIpConection() {
        return getRequest().getRemoteAddr();
    }
    
    protected Map<String, String> getRequestParameterMap() {
        return getExternalContext().getRequestParameterMap();
    }

    protected Map<String, String> obtenerParametrosFaces() {
        return getExternalContext().getRequestParameterMap();
    }

    protected String getPathReportes(String pathReporte) {
        return getRequest().getSession().getServletContext().getRealPath(pathReporte);
    }

    protected MethodExpression crearActionFormularios(String action) {
        return getApplication().getExpressionFactory().createMethodExpression(getElContext(), action, null, new Class<?>[0]);
    }

    public String cerrarSessionUsuario() {
        HttpSession session = null;
        try {
            log.info("cerrar sesion usuario: " + session);
            session = getSession();
            //session.setAttribute("controlAcceso",null);

        } catch (Exception e) {
            log.error("error al invalidar la sessión: ", e);
        }
        return "/logout2.jsf";
    }

    protected static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    protected void closeSession() {
        try {
            getExternalContext().redirect(getRequest().getContextPath() + "/login.jsf");
            HttpSession session = getSession();
            session.invalidate();
        } catch (Exception e) {
            log.error("no se puede cerrar la sesión", e);
        }

    }
}
