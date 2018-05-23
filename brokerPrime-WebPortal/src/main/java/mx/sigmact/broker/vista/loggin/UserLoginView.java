/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.sigmact.broker.vista.loggin;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import mx.sigmact.broker.base.constants.excepcion.BusinessException;
import mx.sigmact.broker.model.dto.User;
import mx.sigmact.broker.service.logging.UserLogginService;
import mx.sigmact.broker.vista.AbstractManagedBean;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author juan
 */
@Controller("userLoginView")
@Scope(value = "view")
public class UserLoginView extends AbstractManagedBean {

    private static final long serialVersionUID = 2316623966899405140L;
    @Autowired
    @Qualifier("userLogginService")
    private UserLogginService logginService;

    @PostConstruct
    protected void init() {
        setUser(new User());
    }

    public void login() {
        boolean loggedIn = false;

        if (getUser().getUsername() != null && getUser().getPassword() != null) {
            try {
                if (logginService.logginUser(getUser()) != null) {
                    loggedIn = true;
                    getSession().setAttribute("username", getUser().getUsername());
                    redirect("inicio.jsf");
                }else{
                    addErrorMessage("Usuario o Password incorrectos", "");
                }
            } catch (BusinessException ex) {
                getLogger().error(ex.getMessage(), ex);
                addErrorMessage("Usuario o Password incorrectos", "");
            }
        } else {
            loggedIn = false;
            addErrorMessage("Proporcione usuario y contraseña", "");
        }
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }

    public void logout() {
        getSession().invalidate();
        redirect("LoginForm.jsf");
    }
}
