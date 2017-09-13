package com.vuclip.umobmy.bean;

public class Notification {

	private Integer error_code;
	private String transaction_id;
	private String error_desc;
	private String hash;

	public Integer getError_code() {
		return error_code;
	}

	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getError_desc() {
		return error_desc;
	}

	public void setError_desc(String error_desc) {
		this.error_desc = error_desc;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public String toString() {
		return "Notification [error_code=" + error_code + ", transaction_id=" + transaction_id + ", error_desc="
				+ error_desc + ", hash=" + hash + "]";
	}

}
