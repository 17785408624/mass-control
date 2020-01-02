package com.example.entity;

import java.util.ArrayList;
import java.util.List;

public class ProjectReplyExample {
    /**
     * project_reply
     */
    protected String orderByClause;

    /**
     * project_reply
     */
    protected boolean distinct;

    /**
     * project_reply
     */
    protected List<Criteria> oredCriteria;

    /**
     *
     *  2019-09-26
     */
    public ProjectReplyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *
     *  2019-09-26
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     *  2019-09-26
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     *  2019-09-26
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     *  2019-09-26
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     *  2019-09-26
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     *  2019-09-26
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     *  2019-09-26
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     *  2019-09-26
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
     *  2019-09-26
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *
     *  2019-09-26
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * project_reply 2019-09-26
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

        public Criteria andProjectReplyIdIsNull() {
            addCriterion("project_reply_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectReplyIdIsNotNull() {
            addCriterion("project_reply_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectReplyIdEqualTo(Integer value) {
            addCriterion("project_reply_id =", value, "projectReplyId");
            return (Criteria) this;
        }

        public Criteria andProjectReplyIdNotEqualTo(Integer value) {
            addCriterion("project_reply_id <>", value, "projectReplyId");
            return (Criteria) this;
        }

        public Criteria andProjectReplyIdGreaterThan(Integer value) {
            addCriterion("project_reply_id >", value, "projectReplyId");
            return (Criteria) this;
        }

        public Criteria andProjectReplyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_reply_id >=", value, "projectReplyId");
            return (Criteria) this;
        }

        public Criteria andProjectReplyIdLessThan(Integer value) {
            addCriterion("project_reply_id <", value, "projectReplyId");
            return (Criteria) this;
        }

        public Criteria andProjectReplyIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_reply_id <=", value, "projectReplyId");
            return (Criteria) this;
        }

        public Criteria andProjectReplyIdIn(List<Integer> values) {
            addCriterion("project_reply_id in", values, "projectReplyId");
            return (Criteria) this;
        }

        public Criteria andProjectReplyIdNotIn(List<Integer> values) {
            addCriterion("project_reply_id not in", values, "projectReplyId");
            return (Criteria) this;
        }

        public Criteria andProjectReplyIdBetween(Integer value1, Integer value2) {
            addCriterion("project_reply_id between", value1, value2, "projectReplyId");
            return (Criteria) this;
        }

        public Criteria andProjectReplyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_reply_id not between", value1, value2, "projectReplyId");
            return (Criteria) this;
        }

        public Criteria andReplyAddtimeIsNull() {
            addCriterion("reply_addtime is null");
            return (Criteria) this;
        }

        public Criteria andReplyAddtimeIsNotNull() {
            addCriterion("reply_addtime is not null");
            return (Criteria) this;
        }

        public Criteria andReplyAddtimeEqualTo(Long value) {
            addCriterion("reply_addtime =", value, "replyAddtime");
            return (Criteria) this;
        }

        public Criteria andReplyAddtimeNotEqualTo(Long value) {
            addCriterion("reply_addtime <>", value, "replyAddtime");
            return (Criteria) this;
        }

        public Criteria andReplyAddtimeGreaterThan(Long value) {
            addCriterion("reply_addtime >", value, "replyAddtime");
            return (Criteria) this;
        }

        public Criteria andReplyAddtimeGreaterThanOrEqualTo(Long value) {
            addCriterion("reply_addtime >=", value, "replyAddtime");
            return (Criteria) this;
        }

        public Criteria andReplyAddtimeLessThan(Long value) {
            addCriterion("reply_addtime <", value, "replyAddtime");
            return (Criteria) this;
        }

        public Criteria andReplyAddtimeLessThanOrEqualTo(Long value) {
            addCriterion("reply_addtime <=", value, "replyAddtime");
            return (Criteria) this;
        }

        public Criteria andReplyAddtimeIn(List<Long> values) {
            addCriterion("reply_addtime in", values, "replyAddtime");
            return (Criteria) this;
        }

        public Criteria andReplyAddtimeNotIn(List<Long> values) {
            addCriterion("reply_addtime not in", values, "replyAddtime");
            return (Criteria) this;
        }

        public Criteria andReplyAddtimeBetween(Long value1, Long value2) {
            addCriterion("reply_addtime between", value1, value2, "replyAddtime");
            return (Criteria) this;
        }

        public Criteria andReplyAddtimeNotBetween(Long value1, Long value2) {
            addCriterion("reply_addtime not between", value1, value2, "replyAddtime");
            return (Criteria) this;
        }

        public Criteria andReplyEdittimeIsNull() {
            addCriterion("reply_edittime is null");
            return (Criteria) this;
        }

        public Criteria andReplyEdittimeIsNotNull() {
            addCriterion("reply_edittime is not null");
            return (Criteria) this;
        }

        public Criteria andReplyEdittimeEqualTo(Long value) {
            addCriterion("reply_edittime =", value, "replyEdittime");
            return (Criteria) this;
        }

        public Criteria andReplyEdittimeNotEqualTo(Long value) {
            addCriterion("reply_edittime <>", value, "replyEdittime");
            return (Criteria) this;
        }

        public Criteria andReplyEdittimeGreaterThan(Long value) {
            addCriterion("reply_edittime >", value, "replyEdittime");
            return (Criteria) this;
        }

        public Criteria andReplyEdittimeGreaterThanOrEqualTo(Long value) {
            addCriterion("reply_edittime >=", value, "replyEdittime");
            return (Criteria) this;
        }

        public Criteria andReplyEdittimeLessThan(Long value) {
            addCriterion("reply_edittime <", value, "replyEdittime");
            return (Criteria) this;
        }

        public Criteria andReplyEdittimeLessThanOrEqualTo(Long value) {
            addCriterion("reply_edittime <=", value, "replyEdittime");
            return (Criteria) this;
        }

        public Criteria andReplyEdittimeIn(List<Long> values) {
            addCriterion("reply_edittime in", values, "replyEdittime");
            return (Criteria) this;
        }

        public Criteria andReplyEdittimeNotIn(List<Long> values) {
            addCriterion("reply_edittime not in", values, "replyEdittime");
            return (Criteria) this;
        }

        public Criteria andReplyEdittimeBetween(Long value1, Long value2) {
            addCriterion("reply_edittime between", value1, value2, "replyEdittime");
            return (Criteria) this;
        }

        public Criteria andReplyEdittimeNotBetween(Long value1, Long value2) {
            addCriterion("reply_edittime not between", value1, value2, "replyEdittime");
            return (Criteria) this;
        }

        public Criteria andReplyAdduserIdIsNull() {
            addCriterion("reply_adduser_id is null");
            return (Criteria) this;
        }

        public Criteria andReplyAdduserIdIsNotNull() {
            addCriterion("reply_adduser_id is not null");
            return (Criteria) this;
        }

        public Criteria andReplyAdduserIdEqualTo(Integer value) {
            addCriterion("reply_adduser_id =", value, "replyAdduserId");
            return (Criteria) this;
        }

        public Criteria andReplyAdduserIdNotEqualTo(Integer value) {
            addCriterion("reply_adduser_id <>", value, "replyAdduserId");
            return (Criteria) this;
        }

        public Criteria andReplyAdduserIdGreaterThan(Integer value) {
            addCriterion("reply_adduser_id >", value, "replyAdduserId");
            return (Criteria) this;
        }

        public Criteria andReplyAdduserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_adduser_id >=", value, "replyAdduserId");
            return (Criteria) this;
        }

        public Criteria andReplyAdduserIdLessThan(Integer value) {
            addCriterion("reply_adduser_id <", value, "replyAdduserId");
            return (Criteria) this;
        }

        public Criteria andReplyAdduserIdLessThanOrEqualTo(Integer value) {
            addCriterion("reply_adduser_id <=", value, "replyAdduserId");
            return (Criteria) this;
        }

        public Criteria andReplyAdduserIdIn(List<Integer> values) {
            addCriterion("reply_adduser_id in", values, "replyAdduserId");
            return (Criteria) this;
        }

        public Criteria andReplyAdduserIdNotIn(List<Integer> values) {
            addCriterion("reply_adduser_id not in", values, "replyAdduserId");
            return (Criteria) this;
        }

        public Criteria andReplyAdduserIdBetween(Integer value1, Integer value2) {
            addCriterion("reply_adduser_id between", value1, value2, "replyAdduserId");
            return (Criteria) this;
        }

        public Criteria andReplyAdduserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_adduser_id not between", value1, value2, "replyAdduserId");
            return (Criteria) this;
        }

        public Criteria andReplyEdituserIdIsNull() {
            addCriterion("reply_edituser_id is null");
            return (Criteria) this;
        }

        public Criteria andReplyEdituserIdIsNotNull() {
            addCriterion("reply_edituser_id is not null");
            return (Criteria) this;
        }

        public Criteria andReplyEdituserIdEqualTo(Integer value) {
            addCriterion("reply_edituser_id =", value, "replyEdituserId");
            return (Criteria) this;
        }

        public Criteria andReplyEdituserIdNotEqualTo(Integer value) {
            addCriterion("reply_edituser_id <>", value, "replyEdituserId");
            return (Criteria) this;
        }

        public Criteria andReplyEdituserIdGreaterThan(Integer value) {
            addCriterion("reply_edituser_id >", value, "replyEdituserId");
            return (Criteria) this;
        }

        public Criteria andReplyEdituserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_edituser_id >=", value, "replyEdituserId");
            return (Criteria) this;
        }

        public Criteria andReplyEdituserIdLessThan(Integer value) {
            addCriterion("reply_edituser_id <", value, "replyEdituserId");
            return (Criteria) this;
        }

        public Criteria andReplyEdituserIdLessThanOrEqualTo(Integer value) {
            addCriterion("reply_edituser_id <=", value, "replyEdituserId");
            return (Criteria) this;
        }

        public Criteria andReplyEdituserIdIn(List<Integer> values) {
            addCriterion("reply_edituser_id in", values, "replyEdituserId");
            return (Criteria) this;
        }

        public Criteria andReplyEdituserIdNotIn(List<Integer> values) {
            addCriterion("reply_edituser_id not in", values, "replyEdituserId");
            return (Criteria) this;
        }

        public Criteria andReplyEdituserIdBetween(Integer value1, Integer value2) {
            addCriterion("reply_edituser_id between", value1, value2, "replyEdituserId");
            return (Criteria) this;
        }

        public Criteria andReplyEdituserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_edituser_id not between", value1, value2, "replyEdituserId");
            return (Criteria) this;
        }

        public Criteria andReplyContentIsNull() {
            addCriterion("reply_content is null");
            return (Criteria) this;
        }

        public Criteria andReplyContentIsNotNull() {
            addCriterion("reply_content is not null");
            return (Criteria) this;
        }

        public Criteria andReplyContentEqualTo(String value) {
            addCriterion("reply_content =", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotEqualTo(String value) {
            addCriterion("reply_content <>", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentGreaterThan(String value) {
            addCriterion("reply_content >", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentGreaterThanOrEqualTo(String value) {
            addCriterion("reply_content >=", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLessThan(String value) {
            addCriterion("reply_content <", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLessThanOrEqualTo(String value) {
            addCriterion("reply_content <=", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLike(String value) {
            addCriterion("reply_content like", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotLike(String value) {
            addCriterion("reply_content not like", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentIn(List<String> values) {
            addCriterion("reply_content in", values, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotIn(List<String> values) {
            addCriterion("reply_content not in", values, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentBetween(String value1, String value2) {
            addCriterion("reply_content between", value1, value2, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotBetween(String value1, String value2) {
            addCriterion("reply_content not between", value1, value2, "replyContent");
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

        public Criteria andReplyTimeIsNull() {
            addCriterion("reply_time is null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNotNull() {
            addCriterion("reply_time is not null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeEqualTo(Long value) {
            addCriterion("reply_time =", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotEqualTo(Long value) {
            addCriterion("reply_time <>", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThan(Long value) {
            addCriterion("reply_time >", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("reply_time >=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThan(Long value) {
            addCriterion("reply_time <", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThanOrEqualTo(Long value) {
            addCriterion("reply_time <=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIn(List<Long> values) {
            addCriterion("reply_time in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotIn(List<Long> values) {
            addCriterion("reply_time not in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeBetween(Long value1, Long value2) {
            addCriterion("reply_time between", value1, value2, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotBetween(Long value1, Long value2) {
            addCriterion("reply_time not between", value1, value2, "replyTime");
            return (Criteria) this;
        }
    }

    /**
     * project_reply
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * project_reply 2019-09-26
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