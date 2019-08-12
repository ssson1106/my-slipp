package net.slipp.domain;

public class Result {
	private boolean valid;
	
	private String errorMessage;
	
	private Result() {
		super();
	}

	private Result(boolean valid) {
		super();
		this.valid = valid;
	}

	private Result(boolean valid, String errorMessage) {
		super();
		this.valid = valid;
		this.errorMessage = errorMessage;
	}
	
	public static Result ok() {
		return new Result(true);
	}
	
	public static Result fail(String errorMessage) {
		return new Result(false, errorMessage);
	}
	public boolean isValid() {
		return valid;
	}
}
