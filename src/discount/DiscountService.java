package discount;

public class DiscountService {
    private final DiscountStrategy strategy;

    public DiscountService(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double applyDiscount(double amount) {
        return strategy.apply(amount);
    }
}