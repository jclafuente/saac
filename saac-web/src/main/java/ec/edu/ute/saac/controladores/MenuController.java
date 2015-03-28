/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.controladores;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author jnk
 */
@ManagedBean
@SessionScoped
public class MenuController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(AccesoController.class);
    /**
     * ****************************************************************** *
     */
    private boolean renderSubMenu = false;
    private boolean SubMenu1 = false;
    private boolean SubMenu2 = false;
    private boolean SubMenu3 = false;
    private boolean SubMenu4 = false;
    private boolean SubMenu5 = false;
    private boolean SubMenu6 = false;
    private boolean SubMenu7 = false;
    private boolean SubMenu8 = false;
    private boolean SubMenu9 = false;
    private boolean SubMenu10 = false;
    private boolean SubMenu11 = false;
    private boolean SubMenu12 = false;
    private boolean SubMenu13 = false;
    private boolean SubMenu14 = false;

    /**
     * Creates a new instance of MenuController
     */
    public MenuController() {
        resetMenu();
    }

    @PostConstruct
    public void inicializacion() {
        resetMenu();
    }

    public void activarSubMenu(int numMenu) {
        resetMenu();
        switch (numMenu) {
            case 1:
                setSubMenu1(true);
                break;
            case 2:
                setSubMenu2(true);
                break;
            case 3:
                setSubMenu3(true);
                break;
            case 4:
                setSubMenu4(true);
                break;
            case 5:
                setSubMenu5(true);
                break;
            case 6:
                setSubMenu6(true);
                break;
            case 7:
                setSubMenu7(true);
                break;
            case 8:
                setSubMenu8(true);
                break;
            case 9:
                setSubMenu9(true);
                break;
            case 10:
                setSubMenu10(true);
                break;
            case 11:
                setSubMenu11(true);
                break;
            case 12:
                setSubMenu12(true);
                break;
            case 13:
                setSubMenu13(true);
                break;
            case 14:
                setSubMenu14(true);
                break;

        }
        setRenderSubMenu(true);
    }

    private void resetMenu() {
        setRenderSubMenu(false);
        setSubMenu1(false);
        setSubMenu2(false);
        setSubMenu3(false);
        setSubMenu4(false);
        setSubMenu5(false);
        setSubMenu6(false);
        setSubMenu7(false);
        setSubMenu8(false);
        setSubMenu9(false);
        setSubMenu10(false);
        setSubMenu11(false);
        setSubMenu12(false);
        setSubMenu13(false);
        setSubMenu14(false);

    }

    /**
     * @return the renderSubMenu
     */
    public boolean isRenderSubMenu() {
        return renderSubMenu;
    }

    /**
     * @param renderSubMenu the renderSubMenu to set
     */
    public void setRenderSubMenu(boolean renderSubMenu) {
        this.renderSubMenu = renderSubMenu;
    }

    public boolean isSubMenu1() {
        return SubMenu1;
    }

    public void setSubMenu1(boolean SubMenu1) {
        this.SubMenu1 = SubMenu1;
    }

    public boolean isSubMenu2() {
        return SubMenu2;
    }

    public void setSubMenu2(boolean SubMenu2) {
        this.SubMenu2 = SubMenu2;
    }

    public boolean isSubMenu3() {
        return SubMenu3;
    }

    public void setSubMenu3(boolean SubMenu3) {
        this.SubMenu3 = SubMenu3;
    }

    public boolean isSubMenu4() {
        return SubMenu4;
    }

    public void setSubMenu4(boolean SubMenu4) {
        this.SubMenu4 = SubMenu4;
    }

    public boolean isSubMenu5() {
        return SubMenu5;
    }

    public void setSubMenu5(boolean SubMenu5) {
        this.SubMenu5 = SubMenu5;
    }

    public boolean isSubMenu6() {
        return SubMenu6;
    }

    public void setSubMenu6(boolean SubMenu6) {
        this.SubMenu6 = SubMenu6;
    }

    public boolean isSubMenu7() {
        return SubMenu7;
    }

    public void setSubMenu7(boolean SubMenu7) {
        this.SubMenu7 = SubMenu7;
    }

    public boolean isSubMenu8() {
        return SubMenu8;
    }

    public void setSubMenu8(boolean SubMenu8) {
        this.SubMenu8 = SubMenu8;
    }

    public boolean isSubMenu9() {
        return SubMenu9;
    }

    public void setSubMenu9(boolean SubMenu9) {
        this.SubMenu9 = SubMenu9;
    }

    public boolean isSubMenu10() {
        return SubMenu10;
    }

    public void setSubMenu10(boolean SubMenu10) {
        this.SubMenu10 = SubMenu10;
    }

    public boolean isSubMenu11() {
        return SubMenu11;
    }

    public void setSubMenu11(boolean SubMenu11) {
        this.SubMenu11 = SubMenu11;
    }

    public boolean isSubMenu12() {
        return SubMenu12;
    }

    public void setSubMenu12(boolean SubMenu12) {
        this.SubMenu12 = SubMenu12;
    }

    public boolean isSubMenu13() {
        return SubMenu13;
    }

    public void setSubMenu13(boolean SubMenu13) {
        this.SubMenu13 = SubMenu13;
    }

    public boolean isSubMenu14() {
        return SubMenu14;
    }

    public void setSubMenu14(boolean SubMenu14) {
        this.SubMenu14 = SubMenu14;
    }

}
