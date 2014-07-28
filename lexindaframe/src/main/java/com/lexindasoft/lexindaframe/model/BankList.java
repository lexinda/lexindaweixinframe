package com.lexindasoft.lexindaframe.model;

public class BankList {
	private int bankId;
	private String bankDesc;
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public String getBankDesc() {
		return bankDesc;
	}
	public void setBankDesc(String bankDesc) {
		this.bankDesc = bankDesc;
	}
	@Override
	public String toString() {
		return "BankList [bankId=" + bankId + ", bankDesc=" + bankDesc + "]";
	}
	
}
