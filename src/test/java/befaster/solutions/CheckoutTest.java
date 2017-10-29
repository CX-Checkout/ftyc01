package befaster.solutions;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutTest {

    @Test
    public void compute_sum() {
        assertThat(Sum.sum(1, 1), equalTo(2));
    }
    
    @Test
    public void compute_checkout_promoA() {
        assertThat(Checkout.checkout("AAA"), equalTo(130));
    }
    
    @Test
    public void compute_checkout_nonPromoA() {
        assertThat(Checkout.checkout("A"), equalTo(50));
    }
    
    @Test
    public void compute_checkout_mixA() {
        assertThat(Checkout.checkout("AAAA"), equalTo(180));
    }
    
    
    @Test
    public void compute_checkout_promoB() {
        assertThat(Checkout.checkout("BB"), equalTo(45));
    }
    
    @Test
    public void compute_checkout_nonPromoB() {
        assertThat(Checkout.checkout("B"), equalTo(30));
    }
    
    @Test
    public void compute_checkout_mixB() {
        assertThat(Checkout.checkout("BBBBB"), equalTo(120));
    }
    
    @Test
    public void compute_checkout_C() {
        assertThat(Checkout.checkout("C"), equalTo(20));
    }
    
    @Test
    public void compute_checkout_Cs() {
        assertThat(Checkout.checkout("CC"), equalTo(40));
    }
    
    @Test
    public void compute_checkout_D() {
        assertThat(Checkout.checkout("D"), equalTo(15));
    }
    
    @Test
    public void compute_checkout_Ds() {
        assertThat(Checkout.checkout("DD"), equalTo(30));
    }
    
    // SHOULD I ...
    @Test
    public void compute_checkout_mix() {
        assertThat(Checkout.checkout("CAABABABD"), equalTo(35+130+50+45+30));
    }
    
    @Test
    public void compute_checkout_invalid() {
        assertThat(Checkout.checkout("E"), equalTo(-1));
    }
    
    @Test
    public void compute_checkout_empty() {
        assertThat(Checkout.checkout(""), equalTo(0));
    }
    
    @Test
    public void compute_checkout_null() {
        assertThat(Checkout.checkout(null), equalTo(-1));
    }
    
    @Test
    public void compute_checkout_AxA() {
        assertThat(Checkout.checkout("AxA"), equalTo(-1));
    }
    
    @Test
    public void compute_checkout_ABCa() {
        assertThat(Checkout.checkout("ABCa"), equalTo(-1));
    }
}