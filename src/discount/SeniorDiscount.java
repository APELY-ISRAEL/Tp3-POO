package discount;


public class SeniorDiscount implements DiscountStrategy {
    @Override
    public double apply(double amount) { return amount * 0.85; }
}