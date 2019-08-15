package com.example.entity.user;

/**
 * 用户信息审核申请表
 */
public class UserInfoAuditEntity {
    private Integer user_info_audit_id;//主键id
    private Integer user_info_audit_state;//审核状态 1未审核 2拒绝 3 通过
    private String user_info_audit_describe;//审核描述
    private Long user_info_audit_addtime;//审核添加时间 申请时间
    private Long user_info_audit_updatetime;//审核修改时间 、审核时间
    private Integer user_info_audit_type;//审核类型 1初审 2变更审核
    private Integer user_info_audit_content;//审核类容 1第三方机构信息 2专家信息
    private Integer info_id;//审核的信息id
    private Integer user_id_add;//添加入id，申请人
    private Integer user_id_edit;//修改人id，审核人

    public Integer getUser_info_audit_id() {
        return user_info_audit_id;
    }

    public void setUser_info_audit_id(Integer user_info_audit_id) {
        this.user_info_audit_id = user_info_audit_id;
    }

    public Integer getUser_info_audit_state() {
        return user_info_audit_state;
    }

    public void setUser_info_audit_state(Integer user_info_audit_state) {
        this.user_info_audit_state = user_info_audit_state;
    }

    public String getUser_info_audit_describe() {
        return user_info_audit_describe;
    }

    public void setUser_info_audit_describe(String user_info_audit_describe) {
        this.user_info_audit_describe = user_info_audit_describe;
    }

    public Long getUser_info_audit_addtime() {
        return user_info_audit_addtime;
    }

    public void setUser_info_audit_addtime(Long user_info_audit_addtime) {
        this.user_info_audit_addtime = user_info_audit_addtime;
    }

    public Long getUser_info_audit_updatetime() {
        return user_info_audit_updatetime;
    }

    public void setUser_info_audit_updatetime(Long user_info_audit_updatetime) {
        this.user_info_audit_updatetime = user_info_audit_updatetime;
    }

    public Integer getUser_info_audit_type() {
        return user_info_audit_type;
    }

    public void setUser_info_audit_type(Integer user_info_audit_type) {
        this.user_info_audit_type = user_info_audit_type;
    }

    public Integer getUser_info_audit_content() {
        return user_info_audit_content;
    }

    public void setUser_info_audit_content(Integer user_info_audit_content) {
        this.user_info_audit_content = user_info_audit_content;
    }

    public Integer getInfo_id() {
        return info_id;
    }

    public void setInfo_id(Integer info_id) {
        this.info_id = info_id;
    }

    public Integer getUser_id_add() {
        return user_id_add;
    }

    public void setUser_id_add(Integer user_id_add) {
        this.user_id_add = user_id_add;
    }

    public Integer getUser_id_edit() {
        return user_id_edit;
    }

    public void setUser_id_edit(Integer user_id_edit) {
        this.user_id_edit = user_id_edit;
    }
}
