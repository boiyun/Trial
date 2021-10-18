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
public class UserInfo extends LitePalSupport {
    private int id;
    @Column(unique = true)
    private String userId;
    private String name;
    private String account;
    private String pbx;
    private String type;
    private String mobile;
    private String email;
    private String status;
    private String product;
    private RoleBean roleBean;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPbx() {
        return pbx;
    }

    public void setPbx(String pbx) {
        this.pbx = pbx;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public RoleBean getRoleBean() {
        return roleBean;
    }

    public void setRoleBean(RoleBean roleBean) {
        this.roleBean = roleBean;
    }
}
