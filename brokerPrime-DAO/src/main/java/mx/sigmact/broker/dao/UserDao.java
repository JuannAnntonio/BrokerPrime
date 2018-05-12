/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.sigmact.broker.dao;


import java.math.BigInteger;
import java.util.List;
import mx.sigmact.broker.base.constants.excepcion.DAOException;
import mx.sigmact.broker.model.dto.User;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface UserDao {

    int insert(User usuario) throws DAOException;

    int update(User usuario) throws DAOException;

    int delete(User usuario) throws DAOException;

    User findName(String name) throws DAOException;

    List<User> findRol(BigInteger idRol) throws DAOException;

    User passwordValido(User usuario) throws DAOException;

}
