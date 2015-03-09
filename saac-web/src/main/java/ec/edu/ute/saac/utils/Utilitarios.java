package ec.edu.ute.saac.utils;

import ec.edu.ute.saac.controladores.MessageSender;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author christian
 */
public class Utilitarios {

    private ResourceBundle resource = ResourceBundle.getBundle("ec.edu.ute.saac.mensajes.messages");
    public static final String ERROR = "message.error";
    public static final String INFORMACION = "message.informacion";
    public static final String REGISTRO_GUARDADO = "message.guardar";
    public static final String REGISTRO_ACTUALIZADO = "message.actualizar";
    public static final String REGISTRO_ELIMINADO = "message.eliminar";
    public static final String ERROR_REGISTRO_GUARDADO = "message.errorGuardar";
    public static final String ERROR_REGISTRO_ACTUALIZADO = "message.errorActualizar";
    public static final String ERROR_REGISTRO_ELIMINADO = "message.errorEliminar";
    public static final String REGISTRO_EXISTENTE = "message.registroExistente";
    public static final String ERROR_SERVER_MAIL = "message.errorServerMail";
    
    protected static final int MAX_ROWS = 10;
    protected static final String PATRON_FECHA = "dd-MM--yyyy";
    private static Random random = new Random();

    /**
     * Creates a new instance of Utilitarios
     */
    public Utilitarios() {
    }

    public synchronized String generatePassword() {
        String passwd = "";
        for (char c : complete("" + (int) (random.nextDouble() * 99999999), 8, '0', true).toCharArray()) {
            int value = (int) (Integer.parseInt("" + c) + Math.round(Math.random() * 120));
            char cc = (char) value;
            //System.out.print(" | " + c + ":" + value);
            if (Character.isLetter(cc) & Character.isDefined(cc) & !Character.isWhitespace(cc)) {
                passwd += cc;
            } else {
                passwd += c;
            }
        }
        return passwd;
    }

    /**
     * Permite complementar una determinada cadena de texto con un caracter especificado
     *
     * @param data Cadena de texto original
     * @param length longitud deseada
     * @param complete caracter con el cual se completara la cadena
     * @param reverse indica si la cadena se complementara al fina(false) o al inicio(true)
     * @return cadena complementada, si la longitid es menor a la cadena original se retornara la original sin ccambios
     */
    public static synchronized String complete(String data, final int length, final char complete, final boolean reverse) {
        final int size = data.length();
        StringBuilder build = new StringBuilder();
        if (reverse) {
            for (int i = size; i < length; i++) {
                build.append(complete);
            }
            build.append(data);
        } else {
            build.append(data);
            for (int i = size; i < length; i++) {
                build.append(complete);
            }
        }
        return build.toString();
    }

    /// DATOS DE FECHA ////
    public String devuelveFechaEnLetras() {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(new Date());
        String armaFecha = devuelveDiaSemana(fecha.get(Calendar.DAY_OF_WEEK)) + fecha.get(Calendar.DAY_OF_MONTH) + " de " + devuelveMes(fecha.get(Calendar.MONTH)) + " " + fecha.get(Calendar.YEAR) + " " + fecha.get(Calendar.HOUR_OF_DAY) + ":" + devuelveMinuto(fecha.get(Calendar.MINUTE));
        return armaFecha;
    }

    private String devuelveMinuto(int minuto) {
        if (minuto < 10) {
            return String.valueOf("0" + minuto);
        } else {
            return String.valueOf(minuto);
        }
    }

    private String devuelveDiaSemana(int dia) {
        switch (dia) {
            case 1:
                return "Domingo ";
            case 2:
                return "Lunes ";
            case 3:
                return "Martes ";
            case 4:
                return "Miércoles ";
            case 5:
                return "Jueves ";
            case 6:
                return "Viernes ";
            case 7:
                return "Sabado ";
            default:
                return "";

        }
    }

    private static String devuelveMes(int mes) {
        switch (mes) {
            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";
            default:
                return "";
        }
    }

    /// MENSAJES DE LOG ///
    public void info(String clase, String mensaje) {
        Logger.getLogger(clase).log(Level.INFO, mensaje.toUpperCase());
    }

    public void error(String clase, String mensaje, Exception e) {
        Logger.getLogger(clase).log(Level.SEVERE, mensaje.toUpperCase(), e);
    }

    /*
     * ******************************
     * #### MENSAJES DE PANTALLA ####
     */
    public void ponerMensajeInfo(String key, String mensajeAdicional) {
        getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, resource.getString(key).concat(" ").concat(mensajeAdicional != null ? mensajeAdicional : ""), resource.getString(key).concat(" ").concat(
                (mensajeAdicional == null) ? "" : (mensajeAdicional == null) ? "" : mensajeAdicional)));//new FacesMessage(FacesMessage.SEVERITY_INFO,resource.getString(key).concat(" ").concat(mensajeAdicional)  ));
    }

    public void ponerMensajeError(String key, String mensajeAdicional) {
        getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(key).concat(" ").concat(mensajeAdicional != null ? mensajeAdicional : ""), resource.getString(key).concat(" ").concat(
                mensajeAdicional == null ? "" : mensajeAdicional)));//new FacesMessage(FacesMessage.SEVERITY_INFO,resource.getString(key).concat(" ").concat(mensajeAdicional)  ));
    }

    public void ponerMensajeWarn(String key, String mensajeAdicional) {
        getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, resource.getString(key).concat(" ").concat(mensajeAdicional != null ? mensajeAdicional : ""), resource.getString(key).concat(" ").concat(
                mensajeAdicional == null ? "" : mensajeAdicional)));//new FacesMessage(FacesMessage.SEVERITY_INFO,resource.getString(key).concat(" ").concat(mensajeAdicional)  ));
    }

    /// VARIABLES DE CONTEXTO  ///
    public static FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext getExternalContext() {
        return getContext().getExternalContext();
    }

    public ELContext getElContext() {
        return getContext().getELContext();
    }

    public Application getApplication() {
        return getContext().getApplication();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public Map<String, String> getRequestParameterMap() {
        return getExternalContext().getRequestParameterMap();
    }

    public Map<String, String> obtenerParametrosFaces() {
        return getExternalContext().getRequestParameterMap();
    }

    public String mayusculas(String mayusculas) {
        return mayusculas.toUpperCase().trim();
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public static String getContextPath() {
        return getRequest().getSession().getServletContext().getRealPath("/");
    }

    public static String getServletPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
    }

    public static void removeAttributes() {
        HttpSession session = getSession();
        removeAttributes(session);
    }

    /*
     * protected MethodBinding crearActionFormularios(String action) { return getApplication().createMethodBinding(action, null); }
     */
    public MethodExpression crearActionFormularios(String action) {
        return getApplication().getExpressionFactory().createMethodExpression(getElContext(), action, null, new Class<?>[0]);
    }

    private static void removeAttributes(HttpSession session) {
        Enumeration atrs = session.getAttributeNames();
        while (atrs.hasMoreElements()) {  ///esto elimina atributos guardados
            String name = (String) atrs.nextElement();
            session.removeAttribute(name);
        }
    }

    public void closeSession() {
        try {
            HttpSession session = getSession();
            removeAttributes(session);
//            session.invalidate();
            navigation("/i.jsf");
        } catch (Exception e) {
            error(getClass().getName(), "no se puede cerrar la sesión", e);
        }

    }

    public void cerrarSession() {
        try {
            if (getContext() != null) {
                Map m = getExternalContext().getSessionMap();
                for (Object o : m.keySet()) {
                    m.put(o, null);
                }
            }
            navigation("/index.jsf");
        } catch (Exception ex) {
            error(getClass().getName(), "no se puede cerrar la sesión", ex);
        }
    }

    public void navigation(String page) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String dir = returnUrl(request.getRequestURL().toString(), page);
        context.getExternalContext().redirect(dir);
    }

    private static String returnUrl(String urlReal, String page) {
        String cadena = "";
        int cont = 0;
        for (int i = 0; i < urlReal.length(); i++) {
            if (urlReal.charAt(i) == '/') {
                cont++;
            }
            if (cont < 4) {
                cadena = cadena + urlReal.charAt(i);
            }
        }
        return cadena + page;
    }

    public static Object getBean(String name) {
        return FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get(name);
    }

    /**
     * Get managed bean based on the bean name.
     *
     * @param beanName the bean name
     * @return the managed bean associated with the bean name
     */
    public static Object getManagedBean(String beanName) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ELContext elc = fc.getELContext();
        ExpressionFactory ef = fc.getApplication().getExpressionFactory();
        ValueExpression ve = ef.createValueExpression(elc, getJsfEl(beanName), Object.class);
        return ve.getValue(elc);
    }

    /**
     * Remove the managed bean based on the bean name.
     *
     * @param beanName the bean name of the managed bean to be removed
     */
    public static void resetManagedBean(String beanName) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ELContext elc = fc.getELContext();
        ExpressionFactory ef = fc.getApplication().getExpressionFactory();
        ef.createValueExpression(elc, getJsfEl(beanName),
                Object.class).setValue(elc, null);
    }

    private static String getJsfEl(String value) {
        return "#{" + value + "}";
    }

    /// METODOS DE FECHAS ///
    /**
     * Calcula la edad dada una fecha de nacimiento.
     *
     * @param fechaNac
     * @return edad
     */
    public static int calcularEdad(Date fechaNac) {
        Calendar hoy = Calendar.getInstance();
        Calendar nacimiento = Calendar.getInstance();
        hoy.setTime(new Date());
        nacimiento.setTime(fechaNac);
        int diffAnio = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
        int diffMes = hoy.get(Calendar.MONTH) - nacimiento.get(Calendar.MONTH);
        int diffDia = hoy.get(Calendar.DAY_OF_MONTH) - nacimiento.get(Calendar.DAY_OF_MONTH);
        // Si está en ese año pero todavía no los ha cumplido
        if ((diffMes < 0) || ((diffMes == 0) && (diffDia < 0))) {
            diffAnio = diffAnio - 1;
        }
        return diffAnio;
    }

    public static <T> T eliminarObjetoDeLista(List<T> lista, T objeto) {
        int index = 0;
        for (T t : lista) {
            if (t.equals(objeto)) {
                break;
            }
            index++;
        }
        return lista.remove(index);
    }

    /**
     * *
     * Permite renderizar la vista, este metodo se usara cuando se desee repintar o actualizar posterior a una tarea no controlada o fuera de aviso de ajax push
     */
    public static void render() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }

    /**
     * hace minuscula la primera letra de una determinada palabra o caena de texto
     *
     * @param value palabra o cadena a descapitalizar
     * @return palabra o cadena con la primera le tra descapitalizada
     */
    public static String lowerCapital(String value) {
        value = value.substring(0, 1).toLowerCase() + value.substring(1);
        return value;
    }

    /**
     * metodo que devuelve el tiempo transcurrido entre 2 fechas
     *
     * @param one
     * @param second
     * @return
     */
    public static Calendar substractDate(Date one, Date second) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(one);
        c2.setTime(second);
        c1.setTimeInMillis(c2.getTimeInMillis() - c1.getTimeInMillis());
        return c1;
    }

    /// ENVIO DE MAIL ///
    private ModeloSmtp obtenerParametrosServidorMail() throws Exception {
        LeerXml obtenerValoresXml = new LeerXml();
        ModeloSmtp cargaParametros = obtenerValoresXml.obtenerParametrosServidorSmtp();
        return cargaParametros;
    }

    public void enviarMailClave(String mail, String asunto, String contenido, String password) throws Exception {
        try {
            System.out.println("entro al metodo pa enviar");
            HtmlEmail email = new HtmlEmail();
            ModeloSmtp parametrosServidorSmtp = obtenerParametrosServidorMail();
            email = new HtmlEmail();
            email.setAuthentication(parametrosServidorSmtp.getUser(), parametrosServidorSmtp.getPassword());
            email.setSSL(true);
            email.setTLS(true);
            email.setSmtpPort(new Integer(parametrosServidorSmtp.getPort()));
            email.setHostName(parametrosServidorSmtp.getHostName());
            email.addTo(mail);
            email.setFrom(parametrosServidorSmtp.getFrom(), parametrosServidorSmtp.getName());
            email.setSubject(asunto);
            email.setHtmlMsg("<img src=http://www.teradeportes.com/images/futbolnacional/2010/UTE301010_152739.jpg /> <br /> <br />"
                    + "Estimad@:\n\n" + contenido + "\n\n" + password);
            email.send();
            System.out.println("envio el correo");
        } catch (Exception e) {
            e.printStackTrace();
            MessageSender.sendError(ERROR_SERVER_MAIL, " - No se envio el correo.");
        }
    }
}
