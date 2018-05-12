/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.sigmact.broker.service.logging;


import mx.sigmact.broker.base.constants.excepcion.BusinessException;
import mx.sigmact.broker.model.dto.User;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface UserLogginService {
    String ERR_LOGGIN = "usr.invalido";

    User logginUser(User usuario) throws BusinessException;
}
