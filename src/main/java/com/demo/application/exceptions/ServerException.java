package com.demo.application.exceptions;

public class ServerException extends Exception {

	private static final long serialVersionUID = -9066243716288036945L;
	public ServerException() {
		super();
	}
	public ServerException(String message){
		super(message);
	}
	public static ServerException with(String string) {
		return new ServerException(string);
	}
}
