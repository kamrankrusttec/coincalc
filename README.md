This project resolves the coin-changing problem using the "highest value coin used first" principle. 

It covers both approaches,

- UNLIMITED SUPPLY OF COINS

- LIMITED SUPPLY OF COINS

The logic is quite performant and better than the orthodox algorithms where we get sum of all values and then keep 
subtracting to get the lowest so as to use the least number of coins.

EXAMPLE USAGE AND OUTPUT (ON WINDOWS MACHINE): (jar file added to target folder) 
(properties file included named coin-inventory.properties)

#########################################################################

Usage: java -jar [jarFile] [totalCents(integer)] [optimalChangeNeeded 0/1(0 for NO and 1 for YES)] [propertiesFilePath]


java -jar coincalc.jar 78 1 F:\\TEST_FOLDER\\coin-inventory.properties

###### THANK YOU FOR USING PRINCIPLE VENDING MACHINE ######

SERVING UP OPTIMAL CHANGE WITH UNLIMITED COIN SUPPLY

TOTAL CENTS FOR CHANGE: 78c

HERE IS YOUR CHANGE:


1 coin(s) of denomination 50c each.

1 coin(s) of denomination 20c each.

1 coin(s) of denomination 5c each.

1 coin(s) of denomination 2c each.

1 coin(s) of denomination 1c each.

#########################################################################


All use cases have been covered and minimal unit test cases have been added as well.


Please try and feel free to get in touch at,


kamran@krusttechnologies.com


Best regards,

Mohammad Kamran.


P.S. It is an Eclipse-based project so you can import it directly into Eclipse should you wish to do so.

