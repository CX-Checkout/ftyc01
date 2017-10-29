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
	
	static String validProducts = "ABCDE";
	
	public static int checkout(String sku)
	{
		if(sku == null)
			return -1;
		
		if(!sku.isEmpty() && !sku.matches("["+validProducts+"]+"))
			return -1;
		
		int sum = 0;
		
		int productCounts[] = new int[validProducts.length()];
		
		for(char product : sku.toCharArray())
		{
			productCounts[validProducts.indexOf(product)] = productCounts[validProducts	.indexOf(product)]+1; 
		}
		
		int promotional5CountForA = productCounts[0] / 5;
		int nonPromotional5CountForA = productCounts[0] % 5;
		
		int promotional3CountForA = nonPromotional5CountForA / 3;
		int nonPromotional3CountForA = nonPromotional5CountForA % 3;
		
		int noOfFreeBs = productCounts[4]/2;
		
		// give free Bs from E promotion
		productCounts[1] = productCounts[1] - noOfFreeBs < 0 ? 0 : productCounts[1] - noOfFreeBs;
		
		int promotionalCountForB = productCounts[1] / 2;
		int nonPromotionalCountForB = productCounts[1] % 2;
		
		sum = (productCounts[2] * 20)+ // C
			  (productCounts[3] * 15)+ // D 
			  (productCounts[4] * 40)+ // E
			  (promotional5CountForA * 200)+ //
			  (promotional3CountForA * 130)+ //
			  (nonPromotional3CountForA * 50)+ //
			  (promotionalCountForB * 45)+ //
			  (nonPromotionalCountForB * 30);
		
		return sum;
	}


	
}
