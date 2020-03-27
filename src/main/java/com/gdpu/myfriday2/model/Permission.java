package com.gdpu.myfriday2.model;

public class Permission {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.permission_id
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    private Long permissionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.parent_id
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.permission_name
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    private String permissionName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.css
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    private String css;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.href
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    private String href;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.type
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.permission
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    private String permission;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.sort
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    private Integer sort;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.permission_id
     *
     * @return the value of permission.permission_id
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.permission_id
     *
     * @param permissionId the value for permission.permission_id
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.parent_id
     *
     * @return the value of permission.parent_id
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.parent_id
     *
     * @param parentId the value for permission.parent_id
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.permission_name
     *
     * @return the value of permission.permission_name
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.permission_name
     *
     * @param permissionName the value for permission.permission_name
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.css
     *
     * @return the value of permission.css
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public String getCss() {
        return css;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.css
     *
     * @param css the value for permission.css
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public void setCss(String css) {
        this.css = css == null ? null : css.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.href
     *
     * @return the value of permission.href
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public String getHref() {
        return href;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.href
     *
     * @param href the value for permission.href
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.type
     *
     * @return the value of permission.type
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.type
     *
     * @param type the value for permission.type
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.permission
     *
     * @return the value of permission.permission
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public String getPermission() {
        return permission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.permission
     *
     * @param permission the value for permission.permission
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.sort
     *
     * @return the value of permission.sort
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.sort
     *
     * @param sort the value for permission.sort
     *
     * @mbg.generated Fri Mar 27 16:45:25 CST 2020
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}