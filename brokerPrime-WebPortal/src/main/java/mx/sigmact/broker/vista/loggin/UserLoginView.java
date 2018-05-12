/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.sigmact.broker.vista.loggin;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import mx.sigmact.broker.service.logging.UserLogginService;
import mx.sigmact.broker.vista.AbstractManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author xtati
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
        
    }

    public void login(ActionEvent event) {
        
    }

}
