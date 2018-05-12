package mx.sigmact.broker.model.dto;

import mx.sigmact.broker.base.BaseModel;

/**
 * A user in the system, mostly used for traders. The user can be any of trader,
 * back office, admin, institutional admin. The type of user will be the ones
 * the ROLES table gives to each user. Created on 15/10/16.
 *
 * @author Juan
 */
public class User extends BaseModel{

    private int idUser;
    private String username;
    private String password;
    private String email;
    private int fkIdInstitution;
    private short enabled;
    private String phoneNumber;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFkIdInstitution() {
        return fkIdInstitution;
    }

    public void setFkIdInstitution(int fkIdInstitution) {
        this.fkIdInstitution = fkIdInstitution;
    }

    public short getEnabled() {
        return enabled;
    }

    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User that = (User) o;

        if (idUser != that.idUser) {
            return false;
        }
        if (fkIdInstitution != that.fkIdInstitution) {
            return false;
        }
        if (enabled != that.enabled) {
            return false;
        }
        if (username != null ? !username.equals(that.username) : that.username != null) {
            return false;
        }
        if (password != null ? !password.equals(that.password) : that.password != null) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + fkIdInstitution;
        result = 31 * result + (int) enabled;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}
