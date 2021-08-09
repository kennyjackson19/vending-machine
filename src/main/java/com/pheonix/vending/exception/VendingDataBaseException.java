package com.pheonix.vending.exception;

public class VendingDataBaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String errorMessage;
	private String statusMessage;

	public VendingDataBaseException() {
		super();
	}

	public VendingDataBaseException(String message) {
		super(message);
	}

	public VendingDataBaseException(int errorCode, String errorMessage, String statusMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.statusMessage = statusMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getStatusMessage() {
		return statusMessage;
	}
}
