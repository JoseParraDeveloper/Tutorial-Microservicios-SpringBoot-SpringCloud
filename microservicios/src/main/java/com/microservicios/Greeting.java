package com.microservicios;

public class Greeting {

	private Long id;
	private String message;

	public Greeting() {
		super();
	}

	public Greeting(Long id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
