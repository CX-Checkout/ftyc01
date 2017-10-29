package befaster.solutions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Checkout {
	
	/*static Object validProductsPriceMatric[][] = {
			{'A', 50}
	}*/
	
	static String validProducts = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/*static int productPrices[] = {
									50,30,20,15,40,10,20,10,35,60,80,90,15,40,10,50,30,50,30,20,40,50,20,90,10,50
								};*/
	
	// ROUND 5 PRICES
	static int productPrices[] = {
			50,30,20,15,40,10,20,10,35,60,70,90,15,40,10,50,30,50,20,20,40,50,20,17,20,21
		};
	
	public static int checkout(String sku)
	{
		if(sku == null)
			return -1;
		
		if(!sku.isEmpty() && !sku.matches("[" + validProducts + "]+"))
			return -1;
		
		int totalPrice = 0;
		
		int productCounts[] = new int[validProducts.length()];
		
		for(char product : sku.toCharArray())
		{
			productCounts[validProducts.indexOf(product)] = productCounts[validProducts	.indexOf(product)] + 1; 
		}	
		
		// give free Fs from F promotion
		//int noOfFreeFs = productCounts[5] / 3;	
		//productCounts[5] = productCounts[5] - noOfFreeFs < 0 ? 0 : productCounts[5] - noOfFreeFs;
	
		
		
		markProductFreePromotion(productCounts, 'E', 'B', 2);
		markProductFreePromotion(productCounts, 'F', 'F', 2+1);
		markProductFreePromotion(productCounts, 'N', 'M', 3);
		markProductFreePromotion(productCounts, 'R', 'Q', 3);
		markProductFreePromotion(productCounts, 'U', 'U', 3+1);
		
		totalPrice += calcPriceForBuyAny3Promotion(sku, productCounts, "STXYZ", 3, 45);
		
		totalPrice += calcPriceForPromotion(productCounts, 'A', 5, 200);
		totalPrice += calcPriceForPromotion(productCounts, 'A', 3, 130);
		
		totalPrice += calcPriceForPromotion(productCounts, 'B', 2, 45);
		totalPrice += calcPriceForPromotion(productCounts, 'P', 5, 200);
		totalPrice += calcPriceForPromotion(productCounts, 'H', 10, 80);
		totalPrice += calcPriceForPromotion(productCounts, 'H', 5, 45);
		totalPrice += calcPriceForPromotion(productCounts, 'K', 2, 120);
		totalPrice += calcPriceForPromotion(productCounts, 'Q', 3, 80);
		totalPrice += calcPriceForPromotion(productCounts, 'V', 3, 130);
		totalPrice += calcPriceForPromotion(productCounts, 'V', 2, 90);
		
		totalPrice += sumNonPromotionalProducts(productCounts);

		
		return totalPrice;
	}
	
	static String productsInOfferCheapestToExpensive = "ZSTYX"; 

	private static int calcPriceForBuyAny3Promotion(String sku, int[] productCounts, String productsInOffer, int pCount, int amount) {
		String sortedPromotionProductDesc = "";
		
		for(char product : productsInOfferCheapestToExpensive.toCharArray())
		{
			int count = sku.length() - sku.replace(product+"", "").length();
			char[] chars = new char[count];
			Arrays.fill(chars, product);
			String s = new String(chars);
			sortedPromotionProductDesc += s;
		}
		
		int sum = 0;
		
		if(sortedPromotionProductDesc.isEmpty())
			return sum;
		
		for(int round = 1; round <= (sortedPromotionProductDesc.length() / pCount); round++)
		{
			sum += amount;
			char product1 = sortedPromotionProductDesc.charAt((round-1) * pCount);
			char product2 = sortedPromotionProductDesc.charAt((round-1) * pCount+1);
			char product3 = sortedPromotionProductDesc.charAt((round-1) * pCount+2);
			int indexOfProduct1 = validProducts.indexOf(product1);
			int indexOfProduct2 = validProducts.indexOf(product2);
			int indexOfProduct3 = validProducts.indexOf(product3);
			productCounts[indexOfProduct1] = productCounts[indexOfProduct1] - 1;
			productCounts[indexOfProduct2] = productCounts[indexOfProduct2] - 1; 
			productCounts[indexOfProduct3] = productCounts[indexOfProduct3] - 1; 
		}
		
		return sum;
	}

	private static void markProductFreePromotion(int productCounts[], char srcP, char destP, int srcPCount) {
		int indexOfSrc = validProducts.indexOf(srcP);
		int indexOfDest = validProducts.indexOf(destP);
		int noOfFreeDestProducts = productCounts[indexOfSrc] / srcPCount;	
		productCounts[indexOfDest] = productCounts[indexOfDest] - noOfFreeDestProducts < 0 ? 0 : productCounts[indexOfDest] - noOfFreeDestProducts;
	}

	private static int calcPriceForPromotion(int[] productCounts, char product, int promotionalCount, int promotionalPrice) {
		int indexOfProduct = validProducts.indexOf(product);
		int nbOfPromotions = productCounts[indexOfProduct] / promotionalCount;
		productCounts[indexOfProduct] = productCounts[indexOfProduct] % promotionalCount;
		return nbOfPromotions * promotionalPrice;
	}

	private static int sumNonPromotionalProducts(int[] productCounts) {
		int amountSum = 0;
		int index = 0;
		for(int productCount : productCounts)
		{
			amountSum += productCount * productPrices[index];
			index++;
		}
		return amountSum;
	}


	
}
