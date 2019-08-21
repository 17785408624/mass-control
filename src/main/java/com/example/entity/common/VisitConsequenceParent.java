package com.example.entity.common;

/**
 * 封装返回持久层的父类
 * @author moerka-1
 *
 */



public abstract class VisitConsequenceParent {
	protected String message="请求成功";//返回的信息
	protected int state=0;//返回的状态 3为登录信息异常,0正常，1返回数据错误
	protected Object object;//返回的前端数据
	protected Integer amount;//返回对象的记录总数
	
	protected abstract void checkData();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}


	
	
    
}
