package com.principle.utils;

import java.util.Collection;

import com.principle.Coin;
import com.principle.services.CoinServiceImplementation;

/**
 * @author Kamran
 * 
 * This class starts change conversion process by sending input
 * to the coin service
 */
public class CoinEngine {

	PrincipleUtil principleUtil;
	CoinServiceImplementation coinServiceImplementation = null;

	public CoinEngine() {
		principleUtil = new PrincipleUtil();
		principleUtil.sortDemoniationsArrayDesc();
		coinServiceImplementation = CoinServiceImplementation.getInstance();
	}

	public Collection<Coin> convertAndReturnCoins(int totalCents, int optimalChangeFlag,
			String propertiesFile) {
		principleUtil.loadPropertiesFile(propertiesFile);
		Collection<Coin> coins = null;
		System.out
				.println("###### THANK YOU FOR USING PRINCIPLE VENDING MACHINE ######");
		if (optimalChangeFlag == 0) {
			System.out.println("SERVING UP CHANGE WITH LIMITED COIN SUPPLY");
			coins = coinServiceImplementation.getChangeWithLimitedCoinSupply(
					totalCents, propertiesFile);
		} else if (optimalChangeFlag == 1) {
			System.out
					.println("SERVING UP OPTIMAL CHANGE WITH UNLIMITED COIN SUPPLY");
			coins = coinServiceImplementation.getOptimalChangeFor(totalCents);
		} else {
			System.out.println("NO OPTION INPUT FOR CHANGE");
		}
		System.out.println("TOTAL CENTS FOR CHANGE: " + totalCents
				+ "c\nHERE IS YOUR CHANGE: \n");
		for (Coin coin : coins) {
			System.out.println(coin.getNumberOfCoins()
					+ " coin(s) of denomination " + coin.getCoinDenomination()
					+ "c each.");
			/*
			 * updating number of coins left in properties file ONLY if the
			 * conversion went through successfully and in case of normal change
			 * with limited coin supply
			 */
			if (optimalChangeFlag == 0) {
				int denomination = coin.getCoinDenomination();
				int coinsLeft = coinServiceImplementation.getNumberOfCoinsAvailableForDenomination(String.valueOf(denomination), propertiesFile)
						- coin.getNumberOfCoins();
				coinServiceImplementation.updateNumberOfCoinsAvailableForDenomination(denomination, coinsLeft, propertiesFile);
			}
		}
		System.out
				.println("\n##########################################################");
//		coins.clear();
		return coins;
	}
}
