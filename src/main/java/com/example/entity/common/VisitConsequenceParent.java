package com.example.entity.common;

/**
 * 封装返回持久层的父类
 * @author moerka-1
 *
 */



public abstract class VisitConsequenceParent {
	protected String message="请求成功";//返回的信息
	protected int state=0;//返回的状态 3为登录信息异常,0正常，1错误
	protected Object object;//返回的前端数据
	protected int dialogue=0;//对话状态 0正常登陆 1未登录 2登陆超时 3重复登陆
	
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

	public int getDialogue() {
		return dialogue;
	}

	public void setDialogue(int dialogue) {
		this.dialogue = dialogue;
	}
}
