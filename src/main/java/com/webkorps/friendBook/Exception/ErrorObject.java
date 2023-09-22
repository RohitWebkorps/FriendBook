package com.webkorps.friendBook.Exception;

public class ErrorObject {
	
	private int status;
	
	private String message;
	
	private long timestamp;

	public int getStatusCode() {
		return status;
	}

	public void setStatusCode(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public ErrorObject() {
	}

	public ErrorObject(int status, String message, long timestamp) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	
}