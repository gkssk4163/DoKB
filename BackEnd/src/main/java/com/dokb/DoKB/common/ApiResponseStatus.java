package com.dokb.DoKB.common;

public enum ApiResponseStatus {
	DELETE("DELETE");

	private final String label;

	ApiResponseStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
