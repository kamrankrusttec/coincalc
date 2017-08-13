package com.principle.exceptions;

/**
 * @author Kamran
 *
 * Exception class covering the not enough coin supply exception
 */
public class NotEnoughCoinSupplyException extends RuntimeException {

	private static final long serialVersionUID = 5790159643152619043L;

	/**
	 * @param message
	 */
	public NotEnoughCoinSupplyException(String message) {
		super(message);
	}
}
