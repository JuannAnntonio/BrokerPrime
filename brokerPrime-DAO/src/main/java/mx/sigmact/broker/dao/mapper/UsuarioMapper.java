/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.sigmact.broker.dao.mapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.sigmact.broker.model.dto.User;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class UsuarioMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User usuario = new User();
        
        usuario.setEmail(rs.getString("email"));
        usuario.setEnabled(rs.getShort("enabled"));
        usuario.setFkIdInstitution(rs.getInt("fk_id_institution"));
        usuario.setIdUser(rs.getInt("id_user"));
        usuario.setPassword(rs.getString("password"));
        usuario.setPhoneNumber(rs.getString("phone_number"));
        usuario.setUsername(rs.getString("username"));
        
        return usuario;
    }

}
