package com.example.demo.utils;

public class EnrollmentCreationResponse {

	private boolean success;
    private String errorMessage;

    // Constructors

    public EnrollmentCreationResponse() {
    }

    public EnrollmentCreationResponse(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    // Getters and Setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
	
}
