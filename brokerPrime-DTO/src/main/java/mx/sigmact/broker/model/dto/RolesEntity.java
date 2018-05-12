package mx.sigmact.broker.model.dto;

import mx.sigmact.broker.base.BaseModel;

/**
 *
 * RolesEntity used by the security.
 * Created on 15/10/16.
 * @author Juan
 */
public class RolesEntity extends BaseModel{
    private int fkIdUser;
    private String role;

    public int getFkIdUser() {
        return fkIdUser;
    }

    public void setFkIdUser(int fkIdUser) {
        this.fkIdUser = fkIdUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesEntity that = (RolesEntity) o;

        if (fkIdUser != that.fkIdUser) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fkIdUser;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
