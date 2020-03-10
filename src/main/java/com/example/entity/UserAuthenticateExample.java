package com.example.entity;

import java.util.ArrayList;
import java.util.List;

public class UserAuthenticateExample {
    /**
     * user_authenticate
     */
    protected String orderByClause;

    /**
     * user_authenticate
     */
    protected boolean distinct;

    /**
     * user_authenticate
     */
    protected List<Criteria> oredCriteria;

    /**
     *
     *  2020-01-10
     */
    public UserAuthenticateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *
     *  2020-01-10
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     *  2020-01-10
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     *  2020-01-10
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     *  2020-01-10
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     *  2020-01-10
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     *  2020-01-10
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     *  2020-01-10
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     *  2020-01-10
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *
     *  2020-01-10
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *
     *  2020-01-10
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * user_authenticate 2020-01-10
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserAuthenticatePhoneIsNull() {
            addCriterion("user_authenticate_phone is null");
            return (Criteria) this;
        }

        public Criteria andUserAuthenticatePhoneIsNotNull() {
            addCriterion("user_authenticate_phone is not null");
            return (Criteria) this;
        }

        public Criteria andUserAuthenticatePhoneEqualTo(Integer value) {
            addCriterion("user_authenticate_phone =", value, "userAuthenticatePhone");
            return (Criteria) this;
        }

        public Criteria andUserAuthenticatePhoneNotEqualTo(Integer value) {
            addCriterion("user_authenticate_phone <>", value, "userAuthenticatePhone");
            return (Criteria) this;
        }

        public Criteria andUserAuthenticatePhoneGreaterThan(Integer value) {
            addCriterion("user_authenticate_phone >", value, "userAuthenticatePhone");
            return (Criteria) this;
        }

        public Criteria andUserAuthenticatePhoneGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_authenticate_phone >=", value, "userAuthenticatePhone");
            return (Criteria) this;
        }

        public Criteria andUserAuthenticatePhoneLessThan(Integer value) {
            addCriterion("user_authenticate_phone <", value, "userAuthenticatePhone");
            return (Criteria) this;
        }

        public Criteria andUserAuthenticatePhoneLessThanOrEqualTo(Integer value) {
            addCriterion("user_authenticate_phone <=", value, "userAuthenticatePhone");
            return (Criteria) this;
        }

        public Criteria andUserAuthenticatePhoneIn(List<Integer> values) {
            addCriterion("user_authenticate_phone in", values, "userAuthenticatePhone");
            return (Criteria) this;
        }

        public Criteria andUserAuthenticatePhoneNotIn(List<Integer> values) {
            addCriterion("user_authenticate_phone not in", values, "userAuthenticatePhone");
            return (Criteria) this;
        }

        public Criteria andUserAuthenticatePhoneBetween(Integer value1, Integer value2) {
            addCriterion("user_authenticate_phone between", value1, value2, "userAuthenticatePhone");
            return (Criteria) this;
        }

        public Criteria andUserAuthenticatePhoneNotBetween(Integer value1, Integer value2) {
            addCriterion("user_authenticate_phone not between", value1, value2, "userAuthenticatePhone");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNull() {
            addCriterion("phone_num is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNotNull() {
            addCriterion("phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumEqualTo(String value) {
            addCriterion("phone_num =", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotEqualTo(String value) {
            addCriterion("phone_num <>", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThan(String value) {
            addCriterion("phone_num >", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("phone_num >=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThan(String value) {
            addCriterion("phone_num <", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThanOrEqualTo(String value) {
            addCriterion("phone_num <=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLike(String value) {
            addCriterion("phone_num like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotLike(String value) {
            addCriterion("phone_num not like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIn(List<String> values) {
            addCriterion("phone_num in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotIn(List<String> values) {
            addCriterion("phone_num not in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumBetween(String value1, String value2) {
            addCriterion("phone_num between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotBetween(String value1, String value2) {
            addCriterion("phone_num not between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andAuthenticateDateIsNull() {
            addCriterion("authenticate_date is null");
            return (Criteria) this;
        }

        public Criteria andAuthenticateDateIsNotNull() {
            addCriterion("authenticate_date is not null");
            return (Criteria) this;
        }

        public Criteria andAuthenticateDateEqualTo(Long value) {
            addCriterion("authenticate_date =", value, "authenticateDate");
            return (Criteria) this;
        }

        public Criteria andAuthenticateDateNotEqualTo(Long value) {
            addCriterion("authenticate_date <>", value, "authenticateDate");
            return (Criteria) this;
        }

        public Criteria andAuthenticateDateGreaterThan(Long value) {
            addCriterion("authenticate_date >", value, "authenticateDate");
            return (Criteria) this;
        }

        public Criteria andAuthenticateDateGreaterThanOrEqualTo(Long value) {
            addCriterion("authenticate_date >=", value, "authenticateDate");
            return (Criteria) this;
        }

        public Criteria andAuthenticateDateLessThan(Long value) {
            addCriterion("authenticate_date <", value, "authenticateDate");
            return (Criteria) this;
        }

        public Criteria andAuthenticateDateLessThanOrEqualTo(Long value) {
            addCriterion("authenticate_date <=", value, "authenticateDate");
            return (Criteria) this;
        }

        public Criteria andAuthenticateDateIn(List<Long> values) {
            addCriterion("authenticate_date in", values, "authenticateDate");
            return (Criteria) this;
        }

        public Criteria andAuthenticateDateNotIn(List<Long> values) {
            addCriterion("authenticate_date not in", values, "authenticateDate");
            return (Criteria) this;
        }

        public Criteria andAuthenticateDateBetween(Long value1, Long value2) {
            addCriterion("authenticate_date between", value1, value2, "authenticateDate");
            return (Criteria) this;
        }

        public Criteria andAuthenticateDateNotBetween(Long value1, Long value2) {
            addCriterion("authenticate_date not between", value1, value2, "authenticateDate");
            return (Criteria) this;
        }
    }

    /**
     * user_authenticate
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * user_authenticate 2020-01-10
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}