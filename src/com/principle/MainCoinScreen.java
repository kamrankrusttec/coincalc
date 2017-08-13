package com.principle;

import com.principle.constants.PrincipleConstants;
import com.principle.utils.CoinEngine;

/**
 * @author Kamran
 *
 * Main class to start the application
 */
public class MainCoinScreen {
	
	/**
	 * @param args
	 */
	public static void main(String args[]){
		int totalCents = PrincipleConstants.TOTAL_CENTS;
		String propertiesFile = PrincipleConstants.COIN_INVENTORY_FILENAME;
		int optimalChangeFlag = 0;
		if (args.length > 0){
			totalCents = Integer.valueOf(args[0]);
			optimalChangeFlag = Integer.valueOf(args[1]);
			propertiesFile = args[2];
		}else{
			System.err.println("Usage: java -jar [totalCents(integer)] [optimalChangeNeeded 0/1(0 for NO and 1 for YES)] [propertiesFilePath]");
			System.exit(1);
		}
		// Calling CoinEngine for conversion
		CoinEngine coinEngine = new CoinEngine();
		coinEngine.convertAndReturnCoins(totalCents, optimalChangeFlag, propertiesFile);
	}
}