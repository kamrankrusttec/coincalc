package com.principle.exceptions;

/**
 * @author Kamran
 * 
 * Exception class covering the denomination not found exception
 */
public class DenominationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6071478064400548805L;

	/**
	 * @param message
	 */
	public DenominationNotFoundException(String message) {
		super(message);
	}
}
