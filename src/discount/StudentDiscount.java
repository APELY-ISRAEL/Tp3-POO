package discount;

public class StudentDiscount implements DiscountStrategy {
    @Override
    public double apply(double amount) { return amount * 0.90; }
}