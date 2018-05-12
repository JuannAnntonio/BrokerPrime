/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.sigmact.broker.service.logging.impl;

import mx.sigmact.broker.base.constants.excepcion.BusinessException;
import mx.sigmact.broker.base.constants.excepcion.DAOException;
import mx.sigmact.broker.dao.UserDao;
import mx.sigmact.broker.model.dto.User;
import mx.sigmact.broker.service.BaseBusinessServices;
import mx.sigmact.broker.service.logging.UserLogginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Service("userLogginService")
public class UserLogginServiceImpl extends BaseBusinessServices implements UserLogginService {

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public User logginUser(User usuario) throws BusinessException{
        try {
            User usr = userDao.passwordValido(usuario);
            if(usr==null){
//                throw new BusinessException(ERR_LOGGIN,usuario.getDisplay_name());
            }
            return usr;
        } catch (DAOException dex) {
            throw new BusinessException(ERR_LOGGIN,dex.getCause(),dex,"user.displayName");            
        }
    }

}
