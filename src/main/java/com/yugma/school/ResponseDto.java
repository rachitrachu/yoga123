package com.yugma.school;

import java.util.UUID;

public class ResponseDto {

	private long timestamp = UUID.randomUUID().getMostSignificantBits();
	private String error;
	private Object message;
	private Object developerMessage;
	private String status;
	
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public Object getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(Object developerMessage) {
		this.developerMessage = developerMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
