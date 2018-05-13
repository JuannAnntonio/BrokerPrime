/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.sigmact.broker.dao.impl;

import mx.sigmact.broker.dao.BaseJDBCDao;
import mx.sigmact.broker.dao.UserDao;
import mx.sigmact.broker.dao.UsuarioSQL;
import mx.sigmact.broker.dao.mapper.UsuarioMapper;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import mx.sigmact.broker.base.constants.excepcion.DAOException;
import mx.sigmact.broker.model.dto.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Repository("userDao")
public class UserDaoImpl extends BaseJDBCDao<User> implements UserDao {

    private static final long serialVersionUID = -6351443720713415227L;

    @Override
    public int insert(User usuario) throws DAOException {
        if (usuario == null) {
            return 0;
        }

        try {

            List<Object> params = new ArrayList<>();

            params.add(usuario.getEmail());
//            params.add(usuario.getDisplay_name());
            params.add(usuario.getPassword());

            return getJdbcTemplateBase().update(UsuarioSQL.SQL_INSERT_USER, params.toArray());

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public int update(User usuario) throws DAOException {
//        if (usuario == null && usuario.getUser_id() != null) {
//            return 0;
//        }

        try {

            List<Object> params = new ArrayList<>();

            params.add(usuario.getEmail());
//            params.add(usuario.getDisplay_name());
            params.add(usuario.getPassword());
//            params.add(usuario.getUser_id());

            return getJdbcTemplateBase().update(UsuarioSQL.SQL_UPDATE_USER, params.toArray());

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public int delete(User usuario) throws DAOException {
//        if (usuario == null && usuario.getUser_id() != null) {
//            return 0;
//        }

        try {

            List<Object> params = new ArrayList<>();

//            params.add(usuario.getUser_id());

            return getJdbcTemplateBase().update(UsuarioSQL.SQL_DELETE_USER, params.toArray());

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public User findName(String name) throws DAOException {
        if (name == null) {
            return null;
        }

        try {

            List<Object> params = new ArrayList<>();

            params.add(name);

            return getJdbcTemplateBase().queryForObject(UsuarioSQL.SQL_FIND_BY_NAME, params.toArray(), new UsuarioMapper());

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public List<User> findRol(BigInteger idRol) throws DAOException {
        if (idRol == null) {
            return null;
        }

        try {

            List<Object> params = new ArrayList<>();

            params.add(idRol);

            return getJdbcTemplateBase().query(UsuarioSQL.SQL_FIND_BY_ROL, params.toArray(), new UsuarioMapper());

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public User passwordValido(User usuario) throws DAOException {
        try {

            List<Object> params = new ArrayList<>();

            params.add(usuario.getUsername());
            params.add(usuario.getPassword());

            logger.info("### Params: " + params.toArray());
            logger.info("### SQL: " + UsuarioSQL.SQL_LOGGINS);
            
            return getJdbcTemplateBase().queryForObject(UsuarioSQL.SQL_LOGGINS, 
                    params.toArray(), new UsuarioMapper());

        } catch (EmptyResultDataAccessException emty) {
            return null;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

}
