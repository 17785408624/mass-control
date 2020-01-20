package com.example.entity;

public class UserAuthenticate {
    /**
     * 主键
     */
    private Integer userAuthenticatePhone;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 手机号码
     */
    private String phoneNum;

    /**
     * 验证时间
     */
    private Long authenticateDate;

    /**
     * 主键
     * @return user_authenticate_phone 主键
     */
    public Integer getUserAuthenticatePhone() {
        return userAuthenticatePhone;
    }

    /**
     * 主键
     * @param userAuthenticatePhone 主键
     */
    public void setUserAuthenticatePhone(Integer userAuthenticatePhone) {
        this.userAuthenticatePhone = userAuthenticatePhone;
    }

    /**
     * 用户id
     * @return user_id 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户id
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 手机号码
     * @return phone_num 手机号码
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 手机号码
     * @param phoneNum 手机号码
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    /**
     * 验证时间
     * @return authenticate_date 验证时间
     */
    public Long getAuthenticateDate() {
        return authenticateDate;
    }

    /**
     * 验证时间
     * @param authenticateDate 验证时间
     */
    public void setAuthenticateDate(Long authenticateDate) {
        this.authenticateDate = authenticateDate;
    }
}