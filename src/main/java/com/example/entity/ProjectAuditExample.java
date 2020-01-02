package com.example.entity;

import java.util.ArrayList;
import java.util.List;

public class ProjectAuditExample {
    /**
     * project_audit
     */
    protected String orderByClause;

    /**
     * project_audit
     */
    protected boolean distinct;

    /**
     * project_audit
     */
    protected List<Criteria> oredCriteria;

    /**
     *
     *  2019-09-15
     */
    public ProjectAuditExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *
     *  2019-09-15
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     *  2019-09-15
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     *  2019-09-15
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     *  2019-09-15
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     *  2019-09-15
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     *  2019-09-15
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     *  2019-09-15
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     *  2019-09-15
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
     *  2019-09-15
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *
     *  2019-09-15
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * project_audit 2019-09-15
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

        public Criteria andProjectAuditIdIsNull() {
            addCriterion("project_audit_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectAuditIdIsNotNull() {
            addCriterion("project_audit_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectAuditIdEqualTo(Object value) {
            addCriterion("project_audit_id =", value, "projectAuditId");
            return (Criteria) this;
        }

        public Criteria andProjectAuditIdNotEqualTo(Integer value) {
            addCriterion("project_audit_id <>", value, "projectAuditId");
            return (Criteria) this;
        }

        public Criteria andProjectAuditIdGreaterThan(Integer value) {
            addCriterion("project_audit_id >", value, "projectAuditId");
            return (Criteria) this;
        }

        public Criteria andProjectAuditIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_audit_id >=", value, "projectAuditId");
            return (Criteria) this;
        }

        public Criteria andProjectAuditIdLessThan(Integer value) {
            addCriterion("project_audit_id <", value, "projectAuditId");
            return (Criteria) this;
        }

        public Criteria andProjectAuditIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_audit_id <=", value, "projectAuditId");
            return (Criteria) this;
        }

        public Criteria andProjectAuditIdIn(List<Integer> values) {
            addCriterion("project_audit_id in", values, "projectAuditId");
            return (Criteria) this;
        }

        public Criteria andProjectAuditIdNotIn(List<Integer> values) {
            addCriterion("project_audit_id not in", values, "projectAuditId");
            return (Criteria) this;
        }

        public Criteria andProjectAuditIdBetween(Integer value1, Integer value2) {
            addCriterion("project_audit_id between", value1, value2, "projectAuditId");
            return (Criteria) this;
        }

        public Criteria andProjectAuditIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_audit_id not between", value1, value2, "projectAuditId");
            return (Criteria) this;
        }

        public Criteria andProjectInfoIdIsNull() {
            addCriterion("project_info_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectInfoIdIsNotNull() {
            addCriterion("project_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectInfoIdEqualTo(Integer value) {
            addCriterion("project_info_id =", value, "projectInfoId");
            return (Criteria) this;
        }

        public Criteria andProjectInfoIdNotEqualTo(Integer value) {
            addCriterion("project_info_id <>", value, "projectInfoId");
            return (Criteria) this;
        }

        public Criteria andProjectInfoIdGreaterThan(Integer value) {
            addCriterion("project_info_id >", value, "projectInfoId");
            return (Criteria) this;
        }

        public Criteria andProjectInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_info_id >=", value, "projectInfoId");
            return (Criteria) this;
        }

        public Criteria andProjectInfoIdLessThan(Integer value) {
            addCriterion("project_info_id <", value, "projectInfoId");
            return (Criteria) this;
        }

        public Criteria andProjectInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_info_id <=", value, "projectInfoId");
            return (Criteria) this;
        }

        public Criteria andProjectInfoIdIn(List<Integer> values) {
            addCriterion("project_info_id in", values, "projectInfoId");
            return (Criteria) this;
        }

        public Criteria andProjectInfoIdNotIn(List<Integer> values) {
            addCriterion("project_info_id not in", values, "projectInfoId");
            return (Criteria) this;
        }

        public Criteria andProjectInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("project_info_id between", value1, value2, "projectInfoId");
            return (Criteria) this;
        }

        public Criteria andProjectInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_info_id not between", value1, value2, "projectInfoId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIsNull() {
            addCriterion("audit_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIsNotNull() {
            addCriterion("audit_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdEqualTo(Integer value) {
            addCriterion("audit_user_id =", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotEqualTo(Integer value) {
            addCriterion("audit_user_id <>", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdGreaterThan(Integer value) {
            addCriterion("audit_user_id >", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("audit_user_id >=", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLessThan(Integer value) {
            addCriterion("audit_user_id <", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("audit_user_id <=", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIn(List<Integer> values) {
            addCriterion("audit_user_id in", values, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotIn(List<Integer> values) {
            addCriterion("audit_user_id not in", values, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdBetween(Integer value1, Integer value2) {
            addCriterion("audit_user_id between", value1, value2, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("audit_user_id not between", value1, value2, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdIsNull() {
            addCriterion("add_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAddUserIdIsNotNull() {
            addCriterion("add_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddUserIdEqualTo(Integer value) {
            addCriterion("add_user_id =", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdNotEqualTo(Integer value) {
            addCriterion("add_user_id <>", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdGreaterThan(Integer value) {
            addCriterion("add_user_id >", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("add_user_id >=", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdLessThan(Integer value) {
            addCriterion("add_user_id <", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("add_user_id <=", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdIn(List<Integer> values) {
            addCriterion("add_user_id in", values, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdNotIn(List<Integer> values) {
            addCriterion("add_user_id not in", values, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdBetween(Integer value1, Integer value2) {
            addCriterion("add_user_id between", value1, value2, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("add_user_id not between", value1, value2, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddDatetimeIsNull() {
            addCriterion("add_datetime is null");
            return (Criteria) this;
        }

        public Criteria andAddDatetimeIsNotNull() {
            addCriterion("add_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andAddDatetimeEqualTo(Long value) {
            addCriterion("add_datetime =", value, "addDatetime");
            return (Criteria) this;
        }

        public Criteria andAddDatetimeNotEqualTo(Long value) {
            addCriterion("add_datetime <>", value, "addDatetime");
            return (Criteria) this;
        }

        public Criteria andAddDatetimeGreaterThan(Long value) {
            addCriterion("add_datetime >", value, "addDatetime");
            return (Criteria) this;
        }

        public Criteria andAddDatetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("add_datetime >=", value, "addDatetime");
            return (Criteria) this;
        }

        public Criteria andAddDatetimeLessThan(Long value) {
            addCriterion("add_datetime <", value, "addDatetime");
            return (Criteria) this;
        }

        public Criteria andAddDatetimeLessThanOrEqualTo(Long value) {
            addCriterion("add_datetime <=", value, "addDatetime");
            return (Criteria) this;
        }

        public Criteria andAddDatetimeIn(List<Long> values) {
            addCriterion("add_datetime in", values, "addDatetime");
            return (Criteria) this;
        }

        public Criteria andAddDatetimeNotIn(List<Long> values) {
            addCriterion("add_datetime not in", values, "addDatetime");
            return (Criteria) this;
        }

        public Criteria andAddDatetimeBetween(Long value1, Long value2) {
            addCriterion("add_datetime between", value1, value2, "addDatetime");
            return (Criteria) this;
        }

        public Criteria andAddDatetimeNotBetween(Long value1, Long value2) {
            addCriterion("add_datetime not between", value1, value2, "addDatetime");
            return (Criteria) this;
        }

        public Criteria andAuditUserRoleIsNull() {
            addCriterion("audit_user_role is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserRoleIsNotNull() {
            addCriterion("audit_user_role is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserRoleEqualTo(Integer value) {
            addCriterion("audit_user_role =", value, "auditUserRole");
            return (Criteria) this;
        }

        public Criteria andAuditUserRoleNotEqualTo(Integer value) {
            addCriterion("audit_user_role <>", value, "auditUserRole");
            return (Criteria) this;
        }

        public Criteria andAuditUserRoleGreaterThan(Integer value) {
            addCriterion("audit_user_role >", value, "auditUserRole");
            return (Criteria) this;
        }

        public Criteria andAuditUserRoleGreaterThanOrEqualTo(Integer value) {
            addCriterion("audit_user_role >=", value, "auditUserRole");
            return (Criteria) this;
        }

        public Criteria andAuditUserRoleLessThan(Integer value) {
            addCriterion("audit_user_role <", value, "auditUserRole");
            return (Criteria) this;
        }

        public Criteria andAuditUserRoleLessThanOrEqualTo(Integer value) {
            addCriterion("audit_user_role <=", value, "auditUserRole");
            return (Criteria) this;
        }

        public Criteria andAuditUserRoleIn(List<Integer> values) {
            addCriterion("audit_user_role in", values, "auditUserRole");
            return (Criteria) this;
        }

        public Criteria andAuditUserRoleNotIn(List<Integer> values) {
            addCriterion("audit_user_role not in", values, "auditUserRole");
            return (Criteria) this;
        }

        public Criteria andAuditUserRoleBetween(Integer value1, Integer value2) {
            addCriterion("audit_user_role between", value1, value2, "auditUserRole");
            return (Criteria) this;
        }

        public Criteria andAuditUserRoleNotBetween(Integer value1, Integer value2) {
            addCriterion("audit_user_role not between", value1, value2, "auditUserRole");
            return (Criteria) this;
        }

        public Criteria andAuditContentIsNull() {
            addCriterion("audit_content is null");
            return (Criteria) this;
        }

        public Criteria andAuditContentIsNotNull() {
            addCriterion("audit_content is not null");
            return (Criteria) this;
        }

        public Criteria andAuditContentEqualTo(String value) {
            addCriterion("audit_content =", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentNotEqualTo(String value) {
            addCriterion("audit_content <>", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentGreaterThan(String value) {
            addCriterion("audit_content >", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentGreaterThanOrEqualTo(String value) {
            addCriterion("audit_content >=", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentLessThan(String value) {
            addCriterion("audit_content <", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentLessThanOrEqualTo(String value) {
            addCriterion("audit_content <=", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentLike(String value) {
            addCriterion("audit_content like", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentNotLike(String value) {
            addCriterion("audit_content not like", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentIn(List<String> values) {
            addCriterion("audit_content in", values, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentNotIn(List<String> values) {
            addCriterion("audit_content not in", values, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentBetween(String value1, String value2) {
            addCriterion("audit_content between", value1, value2, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentNotBetween(String value1, String value2) {
            addCriterion("audit_content not between", value1, value2, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditDatetimeIsNull() {
            addCriterion("audit_datetime is null");
            return (Criteria) this;
        }

        public Criteria andAuditDatetimeIsNotNull() {
            addCriterion("audit_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andAuditDatetimeEqualTo(Long value) {
            addCriterion("audit_datetime =", value, "auditDatetime");
            return (Criteria) this;
        }

        public Criteria andAuditDatetimeNotEqualTo(Long value) {
            addCriterion("audit_datetime <>", value, "auditDatetime");
            return (Criteria) this;
        }

        public Criteria andAuditDatetimeGreaterThan(Long value) {
            addCriterion("audit_datetime >", value, "auditDatetime");
            return (Criteria) this;
        }

        public Criteria andAuditDatetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("audit_datetime >=", value, "auditDatetime");
            return (Criteria) this;
        }

        public Criteria andAuditDatetimeLessThan(Long value) {
            addCriterion("audit_datetime <", value, "auditDatetime");
            return (Criteria) this;
        }

        public Criteria andAuditDatetimeLessThanOrEqualTo(Long value) {
            addCriterion("audit_datetime <=", value, "auditDatetime");
            return (Criteria) this;
        }

        public Criteria andAuditDatetimeIn(List<Long> values) {
            addCriterion("audit_datetime in", values, "auditDatetime");
            return (Criteria) this;
        }

        public Criteria andAuditDatetimeNotIn(List<Long> values) {
            addCriterion("audit_datetime not in", values, "auditDatetime");
            return (Criteria) this;
        }

        public Criteria andAuditDatetimeBetween(Long value1, Long value2) {
            addCriterion("audit_datetime between", value1, value2, "auditDatetime");
            return (Criteria) this;
        }

        public Criteria andAuditDatetimeNotBetween(Long value1, Long value2) {
            addCriterion("audit_datetime not between", value1, value2, "auditDatetime");
            return (Criteria) this;
        }

        public Criteria andAuditRevampIsNull() {
            addCriterion("audit_revamp is null");
            return (Criteria) this;
        }

        public Criteria andAuditRevampIsNotNull() {
            addCriterion("audit_revamp is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRevampEqualTo(Long value) {
            addCriterion("audit_revamp =", value, "auditRevamp");
            return (Criteria) this;
        }

        public Criteria andAuditRevampNotEqualTo(Long value) {
            addCriterion("audit_revamp <>", value, "auditRevamp");
            return (Criteria) this;
        }

        public Criteria andAuditRevampGreaterThan(Long value) {
            addCriterion("audit_revamp >", value, "auditRevamp");
            return (Criteria) this;
        }

        public Criteria andAuditRevampGreaterThanOrEqualTo(Long value) {
            addCriterion("audit_revamp >=", value, "auditRevamp");
            return (Criteria) this;
        }

        public Criteria andAuditRevampLessThan(Long value) {
            addCriterion("audit_revamp <", value, "auditRevamp");
            return (Criteria) this;
        }

        public Criteria andAuditRevampLessThanOrEqualTo(Long value) {
            addCriterion("audit_revamp <=", value, "auditRevamp");
            return (Criteria) this;
        }

        public Criteria andAuditRevampIn(List<Long> values) {
            addCriterion("audit_revamp in", values, "auditRevamp");
            return (Criteria) this;
        }

        public Criteria andAuditRevampNotIn(List<Long> values) {
            addCriterion("audit_revamp not in", values, "auditRevamp");
            return (Criteria) this;
        }

        public Criteria andAuditRevampBetween(Long value1, Long value2) {
            addCriterion("audit_revamp between", value1, value2, "auditRevamp");
            return (Criteria) this;
        }

        public Criteria andAuditRevampNotBetween(Long value1, Long value2) {
            addCriterion("audit_revamp not between", value1, value2, "auditRevamp");
            return (Criteria) this;
        }

        public Criteria andAuditStateIsNull() {
            addCriterion("audit_state is null");
            return (Criteria) this;
        }

        public Criteria andAuditStateIsNotNull() {
            addCriterion("audit_state is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStateEqualTo(Integer value) {
            addCriterion("audit_state =", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateNotEqualTo(Integer value) {
            addCriterion("audit_state <>", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateGreaterThan(Integer value) {
            addCriterion("audit_state >", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("audit_state >=", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateLessThan(Integer value) {
            addCriterion("audit_state <", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateLessThanOrEqualTo(Integer value) {
            addCriterion("audit_state <=", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateIn(List<Integer> values) {
            addCriterion("audit_state in", values, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateNotIn(List<Integer> values) {
            addCriterion("audit_state not in", values, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateBetween(Integer value1, Integer value2) {
            addCriterion("audit_state between", value1, value2, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateNotBetween(Integer value1, Integer value2) {
            addCriterion("audit_state not between", value1, value2, "auditState");
            return (Criteria) this;
        }
    }

    /**
     * project_audit
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * project_audit 2019-09-15
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