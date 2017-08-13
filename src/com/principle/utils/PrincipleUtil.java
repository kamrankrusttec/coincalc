package com.principle.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import com.principle.Coin;

/**
 * @author Kamran
 *
 * Utility class containing all utility code 
 * to be used across various layers of application
 */
public class PrincipleUtil {
	Properties properties = new Properties();
	File propertiesFile;
	
	/**
	 * @return
	 */
	public File getPropertiesFile() {
		return propertiesFile;
	}

	/**
	 * @param propertiesFile
	 */
	public void setPropertiesFile(File propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	/**
	 * @param fileName
	 * 
	 * This class loads coin denomination configuration file
	 */
	public void loadPropertiesFile(String fileName){
		propertiesFile = new File(fileName);
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fileName);
			properties.load(inputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		setPropertiesFile(propertiesFile);
	}

	/**
	 * @param property
	 * @return
	 */
	public String getAvailableCoins(String property) {
		return properties.getProperty(property);
	}
	
	/**
	 * @param denomination
	 * @param coinsLeft
	 */
	public void setAvailableCoins(int denomination, int coinsLeft) {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(propertiesFile);
			properties.setProperty(String.valueOf(denomination), String.valueOf(coinsLeft));
			properties.store(outputStream, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * This method sorts the denominations array in descending order
	 */
	public void sortDemoniationsArrayDesc(){
		Arrays.sort(Coin.denominations, Collections.reverseOrder());
	}
}
