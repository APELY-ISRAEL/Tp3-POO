package discount;

public class StandardDiscount implements DiscountStrategy {
    @Override
    public double apply(double amount) { return amount * 0.95; }
}