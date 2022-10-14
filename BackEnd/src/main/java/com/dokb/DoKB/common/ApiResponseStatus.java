package com.dokb.DoKB.common;

public enum ApiResponseStatus {
	DELETE("DELETE"), SUCCESS("SUCCESS");

	private final String label;

	ApiResponseStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
