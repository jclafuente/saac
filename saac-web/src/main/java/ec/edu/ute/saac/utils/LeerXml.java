/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author christian
 */
public class LeerXml {

    private Document dom;

    public LeerXml() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // File archivoJasper = new File(request.getSession().getServletContext().getRealPath("/reportes/ReportePrueba.jrxml"));
            //dom = db.parse("configuracion.xml");
            dom = db.parse(request.getSession().getServletContext().getRealPath("/smtp/configuracion.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }
        return textVal;
    }

    private ModeloSmtp seteoParametrosSmtp(Element elemento) {
        ModeloSmtp modeloSmtp = new ModeloSmtp();
        modeloSmtp.setHostName(getTextValue(elemento, "hostName"));
        modeloSmtp.setPort(getTextValue(elemento, "port"));
        modeloSmtp.setUser(getTextValue(elemento, "user"));
        modeloSmtp.setPassword(getTextValue(elemento, "password"));
        modeloSmtp.setFrom(getTextValue(elemento, "from"));
        modeloSmtp.setName(getTextValue(elemento, "name"));
        return modeloSmtp;
    }

    public ModeloSmtp obtenerParametrosServidorSmtp() throws Exception {
        ModeloSmtp objModeloSmtp = null;
        Element elemento = dom.getDocumentElement();
        NodeList nl = elemento.getElementsByTagName("serverMail");
        if (nl != null && nl.getLength() > 0) {
            Element tagResultado = (Element) nl.item(0);
            objModeloSmtp = seteoParametrosSmtp(tagResultado);
        }
        return objModeloSmtp;
    }
}
