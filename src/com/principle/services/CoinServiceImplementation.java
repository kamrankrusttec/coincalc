package com.principle.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.principle.Coin;
import com.principle.exceptions.DenominationNotFoundException;
import com.principle.exceptions.NotEnoughCoinSupplyException;
import com.principle.utils.PrincipleUtil;

/*
 * coin service interface implementation class
 * singleton class to allow only one object across 
 * the life-cycle of the application
 */

public class CoinServiceImplementation implements CoinService {
	
	private int indexForMaxDenomination = 0;
	private List<Coin> list = new ArrayList<Coin>();
	private static CoinServiceImplementation coinServiceImplementation;
	PrincipleUtil principleUtil = new PrincipleUtil();

	private CoinServiceImplementation() {
	}

	public static CoinServiceImplementation getInstance() {
		if (coinServiceImplementation == null) {
			coinServiceImplementation = new CoinServiceImplementation();
		}
		return coinServiceImplementation;
	}

	/* (non-Javadoc)
	 * @see com.principle.services.CoinService#getOptimalChangeFor(int)
	 * 
	 * This method returns optimal change assuming unlimited supply
	 * of coins.Works on the maximum denomination coin used principle where 
	 * the algorithm uses coins in descending order of their value and 
	 * uses them to achieve optimal change, i.e. to use the least number of coins 
	 * to achieve total value required.
	 */
	@Override
	public Collection<Coin> getOptimalChangeFor(int cents) {
		int maxDenomination = Coin.denominations[indexForMaxDenomination];
		if (cents >= maxDenomination) {
			int quotientWhichIsNumberOfCoins = cents / maxDenomination;
			int remainder = cents % maxDenomination;
			if (remainder == 0) {
				addCoinToList(quotientWhichIsNumberOfCoins, maxDenomination);
			} else {
				addCoinToList(quotientWhichIsNumberOfCoins, maxDenomination);
				int remainingAmount = cents - (quotientWhichIsNumberOfCoins * maxDenomination);
				maxDenomination = Coin.denominations[indexForMaxDenomination + 1];
				int nextQuotientWhichIsNumberOfCoins = remainingAmount	/ maxDenomination;
				if (nextQuotientWhichIsNumberOfCoins == 0) {
					updateMaxDenomAndRepeatForOptimalChange(remainingAmount);
				} else {
					addCoinToList(nextQuotientWhichIsNumberOfCoins,	maxDenomination);
					remainingAmount = remainingAmount - (nextQuotientWhichIsNumberOfCoins * maxDenomination);
					if (remainingAmount > 0) {
						updateMaxDenomAndRepeatForOptimalChange(remainingAmount);
					}
				}
			}
		} else {
			++indexForMaxDenomination;
			getOptimalChangeFor(cents);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.principle.services.CoinService#getChangeWithLimitedCoinSupply(int, java.lang.String)
	 * 
	 * This method returns normal change assuming limited supply
	 * of coins. Works on the maximum denomination coin used principle where 
	 * the algorithm uses coins in descending order of their value and 
	 * uses them to achieve optimal change, i.e. to use the least number of coins 
	 * to achieve total value required. However the difference from optimal change strategy is 
	 * to check if the required number of coins for the intended denomination is available.
	 */
	@Override
	public Collection<Coin> getChangeWithLimitedCoinSupply(int cents, String propertiesFile) {
		if (indexForMaxDenomination == Coin.denominations.length){
			throw new NotEnoughCoinSupplyException("Not enough coins available for change");
		}
		int currentDenomination = Coin.denominations[indexForMaxDenomination];
		int coinsNeeded = cents / currentDenomination;
		int availableCoinsForThisDenom = getNumberOfCoinsAvailableForDenomination(String.valueOf(currentDenomination), propertiesFile);
		if (cents >= currentDenomination) {
				if (coinsNeeded <= availableCoinsForThisDenom){
					addCoinToList(coinsNeeded, currentDenomination);
					int remainingAmount = cents	- (coinsNeeded * currentDenomination);
					if (remainingAmount > 0){
						updateMaxDenomAndRepeatForChange(remainingAmount, propertiesFile);
					}
				}else{
					int deficit = coinsNeeded - availableCoinsForThisDenom;
					coinsNeeded = coinsNeeded - deficit;
					addCoinToList(coinsNeeded, currentDenomination);
					int remainingAmount = cents	- (coinsNeeded * currentDenomination);
					coinsNeeded = remainingAmount / currentDenomination;
					updateMaxDenomAndRepeatForChange(remainingAmount, propertiesFile);
				}
		} else {
			++indexForMaxDenomination;
			getChangeWithLimitedCoinSupply(cents, propertiesFile);
		}
		return list;
	}

	/**
	 * @param cents
	 * @param remainingAmount
	 */
	private void updateMaxDenomAndRepeatForOptimalChange(int remainingAmount) {
		++indexForMaxDenomination;
		getOptimalChangeFor(remainingAmount);
	}

	/**
	 * @param remainingAmount
	 * @param propertiesFile
	 */
	private void updateMaxDenomAndRepeatForChange(int remainingAmount, String propertiesFile) {
		++indexForMaxDenomination;
		getChangeWithLimitedCoinSupply(remainingAmount, propertiesFile);
	}

	/**
	 * @param coinsToUse
	 * @param denomination
	 */
	private void addCoinToList(int coinsToUse, int denomination) {
			Coin coin = new Coin(coinsToUse, denomination);
			if (coin.getNumberOfCoins() != 0){
				list.add(coin);
			}
	}

	/* (non-Javadoc)
	 * @see com.principle.services.CoinService#getNumberOfCoinsAvailableForDenomination(java.lang.String, java.lang.String)
	 */
	@Override
	public int getNumberOfCoinsAvailableForDenomination(String denomination, String propertiesFile){
		principleUtil.loadPropertiesFile(propertiesFile);
		int coinsAvailableForThisDenomination = -1;
		if (principleUtil.getAvailableCoins(denomination) != null){
			coinsAvailableForThisDenomination = Integer.valueOf(principleUtil.getAvailableCoins(denomination));
		}else{
			throw new DenominationNotFoundException("Denomination "+denomination+ " not found in configuration file");
		}
		return coinsAvailableForThisDenomination;
	}

	/* (non-Javadoc)
	 * @see com.principle.services.CoinService#updateNumberOfCoinsAvailableForDenomination(int, int)
	 */
	@Override
	public void updateNumberOfCoinsAvailableForDenomination(int denomination, int coinsLeft, String propertiesFile) {
		principleUtil.loadPropertiesFile(propertiesFile);
		principleUtil.setAvailableCoins(denomination, coinsLeft);
	}
}
