package com.principle;

/**
 * @author Kamran
 *
 * Coin entity to hold properties, getters and setters
 */

public class Coin {
	
	public static Integer[] denominations = new Integer[]{1,2,5,10,20,50,100};
	private int numberOfCoins;
	private int valueOfCoin;
	
	/**
	 * @param numberOfCoins
	 * @param valueOfCoin
	 */
	public Coin(int numberOfCoins, int valueOfCoin){
		this.numberOfCoins = numberOfCoins;
		this.valueOfCoin = valueOfCoin;
	}
	
	/**
	 * @return
	 */
	public int getNumberOfCoins() {
		return numberOfCoins;
	}
	
	/**
	 * @param numberOfCoins
	 */
	public void setNumberOfCoins(int numberOfCoins) {
		this.numberOfCoins = numberOfCoins;
	}
	
	/**
	 * @return
	 */
	public int getCoinDenomination() {
		return valueOfCoin;
	}
	
	/**
	 * @param valueOfCoin
	 */
	public void setValueOfCoin(int valueOfCoin) {
		this.valueOfCoin = valueOfCoin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOfCoins;
		result = prime * result + valueOfCoin;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coin other = (Coin) obj;
		if (numberOfCoins != other.numberOfCoins)
			return false;
		if (valueOfCoin != other.valueOfCoin)
			return false;
		return true;
	}
}
