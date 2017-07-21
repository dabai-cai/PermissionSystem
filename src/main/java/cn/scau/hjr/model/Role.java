package cn.scau.hjr.model;

public class Role {
    private Integer roleId;

    public String getAboutRole() {
        return aboutRole;
    }

    public void setAboutRole(String aboutRole) {
        this.aboutRole = aboutRole;
    }

    private String aboutRole;

    private String rolename;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }


}