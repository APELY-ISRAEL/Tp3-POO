package discount;

public class VipDiscount implements DiscountStrategy {
    @Override
    public double apply(double amount) { return amount * 0.80; }
}