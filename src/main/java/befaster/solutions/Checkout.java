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
	
	static char validProductsArr[] = {'A', 'B', 'C', 'D'};
	static String validProducts = "ABCD";
	
	public static int checkout(String sku)
	{
		if(sku == null)
			return -1;
		
		if(!sku.isEmpty() && !sku.matches("[ABCD]+"))
			return -1;
		
		int sum = 0;
		
		int productCounts[] = new int[4];
		
		for(char product : sku.toCharArray())
		{
			productCounts[validProducts.indexOf(product)] = productCounts[validProducts	.indexOf(product)]+1; 
		}
		
		int promotionalCountForA = productCounts[0]/3;
		int nonPromotionalCountForA = productCounts[0]%3;
		
		int promotionalCountForB = productCounts[1]/2;
		int nonPromotionalCountForB = productCounts[1]%2;
		
		sum = (productCounts[2]*20)+(productCounts[3]*15)+(promotionalCountForA*130)
				+(nonPromotionalCountForA*50)+(promotionalCountForB*45)+(nonPromotionalCountForB*30);
		
		return sum;
	}


	
}