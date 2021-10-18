package com.trial.base.db.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * <pre>
 *     @author : Trial
 *     @time   : 7/7/21
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */

public class RoleBean extends LitePalSupport {

    @Column(unique = true)
    private int id;
    private String roleName;
    private String roleCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
