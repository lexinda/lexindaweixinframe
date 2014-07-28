package com.lexindasoft.lexindaframe.model;

public class Access_UserToken extends WeixinResponse {
private String access_token;
	
	private Integer expires_in;

    //记录token生成的时间，用来确定有没有过期，非微信api反序列化结果
    private Long createTime;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public boolean expired(){
        return ((System.currentTimeMillis() - this.getCreateTime()) / 1000 >= this.getExpires_in());
    }
}
