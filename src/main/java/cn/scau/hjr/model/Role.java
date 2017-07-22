package cn.scau.hjr.model;

import java.util.Set;

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
    private Set<String> hasPermission;
    private Set<String> lacksPermission;

    public Set<String> getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(Set<String> hasPermission) {
        this.hasPermission = hasPermission;
    }

    public Set<String> getLacksPermission() {
        return lacksPermission;
    }

    public void setLacksPermission(Set<String> lacksPermission) {
        this.lacksPermission = lacksPermission;
    }

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