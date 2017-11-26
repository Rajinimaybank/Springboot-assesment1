package com.intellect.user.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.intellect.common.CommonConstants;
import com.intellect.common.ValidationError;

public class UserResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resMsg;
	private String userId;
	private List<ValidationError> valErrors;
	
	public UserResponse(String resMsg, String userId, List<ValidationError> valErrors) {
		super();
		this.resMsg = resMsg;
		this.userId = userId;
		this.valErrors = valErrors;
	}
	public String getResMsg() {
		if(null==resMsg){
			resMsg=CommonConstants.EMPTY;
		}
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getUserId() {
		if(null==userId){
			userId=CommonConstants.EMPTY;
		}
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<ValidationError> getValErrors() {
		if(null==valErrors){
			valErrors= new ArrayList<>();
		}
		return valErrors;
	}
	public void setValErrors(List<ValidationError> valErrors) {
		this.valErrors = valErrors;
	}
	@Override
	public String toString() {
		return "UserResponse [resMsg=" + resMsg + ", userId=" + userId + ", valErrors=" + valErrors + "]";
	}

}
