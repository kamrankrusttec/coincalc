package com.principle.services;

import java.util.Collection;

import junit.framework.TestCase;

import org.junit.Test;

import com.principle.Coin;
import com.principle.exceptions.DenominationNotFoundException;
import com.principle.utils.CoinEngine;

public class CoinServiceImplementationTest extends TestCase {

	int totalCents = -1;
	String propertiesFile = null;
	int optimalChangeFlag = -1;
	private CoinEngine coinEngine;
	private CoinServiceImplementation coinServiceImplementation;

	@Override
	protected void setUp() throws Exception {
		totalCents = 10;
		propertiesFile = "F:\\TEST_FOLDER\\coin-inventory.properties";
		coinServiceImplementation = CoinServiceImplementation.getInstance();
		coinEngine = new CoinEngine();
	}

	@Test
	public void testGetInstance() {
		assertEquals(CoinServiceImplementation.class,coinServiceImplementation.getClass());
	}

	@Test
	public void testGetOptimalChangeFor() {
		optimalChangeFlag = 1;
		Collection<Coin> coins = coinEngine.convertAndReturnCoins(totalCents, optimalChangeFlag, propertiesFile);
		// Must return one coin - expected to pass
		assertEquals(coins.size(), 1);
		// Must have denomination 10 - expected to pass
		assertEquals(coins.iterator().next().getCoinDenomination(), 10);
	}

	@Test
	public void testGetChangeWithLimitedCoinSupply() {
		optimalChangeFlag = 0;
		try {
	        coinEngine.convertAndReturnCoins(totalCents, optimalChangeFlag, propertiesFile);
	        fail("DenominationNotFoundException was not thrown");
	    } catch(DenominationNotFoundException e) {
	        // keep blank, means test passed
	    	// you can change exception type to fail this test
	    }
	}

	@Test
	public void testGetNumberOfCoinsAvailableForDenomination() {
		int expected = 1;
		int actual = coinServiceImplementation.getNumberOfCoinsAvailableForDenomination("100", propertiesFile);
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdateNumberOfCoinsAvailableForDenomination() {
		optimalChangeFlag = 0;
	    String denomination = "10"; 
	    int expected = 1;
	    int coinsAvailable = 
	    		coinServiceImplementation.getNumberOfCoinsAvailableForDenomination(denomination, propertiesFile); 
	    Collection<Coin> coins = coinEngine.convertAndReturnCoins(totalCents, optimalChangeFlag, propertiesFile);
	    Coin coin = coins.iterator().next();
	    int coinsUsed = coin.getNumberOfCoins();
	    int actual = coinsAvailable - coinsUsed;
	    assertEquals(expected, actual);
	}

}
