package befaster.solutions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class CheckoutTest {
	
	private String sku;
	private int expectedAmount;
	
	public CheckoutTest(String sku, int expectedAmount) {
		super();
		this.sku = sku;
		this.expectedAmount = expectedAmount;
	}
	
	@Parameters(name="{index}: checkout{0}={1}")
	public static Collection<Object[]> data()
	{
		return Arrays.asList(new Object[][] {
			
		// ROUND 1 TESTS 
			{"AAA", 130},
			{"A", 50},
			{"AAAA", 180},
			{"BB", 45},
			{"B", 30},
			{"BBBBB", 120},
			{"C", 20},
			{"CC", 40},
			{"D", 15},
			{"DD", 30},
			{"CAABABABD", 35+130+50+45+30},
			{"Z", -1},
			{"", 0},
			{"AxA", -1},
			{"ABCa", -1},
			
		// ROUND 2 TESTS
			{"E", 40},
			{"e", -1}, 
			{"EE", 80},
			{"EBE", 80},
			{"EBEB", 110},
			{"AAAAA", 200},
			{"AAAAAAAA", 200+130},
			{"AAAAAAAAA", 200+130+50},
			
		// ROUND 3 TESTS
			{"F", 10},
			{"f", -1}, 
			{"FF", 20},
			{"FFF", 20},
			{"FFFF", 30},
			{"AAAFF", 130+20},
			{"AAFAFFAA", 200+20},
			
			{null, -1}
		});
	}
	


    @Test
    public void compute_checkout() {
        assertThat(Checkout.checkout(sku), equalTo(expectedAmount));
    }
  
}
